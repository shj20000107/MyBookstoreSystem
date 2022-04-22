/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import entity.Bookinfo;
import entitysession.BookBeanLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import statefulsession.Cart;
import statefulsession.CartBeanLocal;

/**
 *
 * @author 沈海健
 */
public class CtrlServlet extends HttpServlet {
    @EJB
    private BookBeanLocal bookBean;
    CartBeanLocal cartBean;
    String pageFrom;    //来自所有.jsp文件的page（是哪个页面）
    String pageAct;     //来自所有.jsp文件的submit（要转向何方）
    String errMsg;      //错误信息
    HttpSession session;//会话声明
    RequestDispatcher dispatcher;//页面跳转
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    //分别对比两个页面转移方法 和 参数传递方法
    /**
     * 一、页面转移：重定向法：response.sendRedirect(".jsp") 和 转移法：request.getRequestDispatcher(".jsp").forward(request,response)
     * 1.重定向法：可以将页面跳转到任何页面，不一定局限于本web应用中，但只能在url中带parameter或者放在session中，无法使用request.setAttribute来传递。
     * 2.转移法：只能跳转到本web应用中的页面上，跳转后浏览器地址栏不会变化，使用这种方式跳转，传值可以使用三种方法：url中带parameter，session，request.setAttribute
     * 
     * 二、参数传递：会话法：session.setAttribute(String, Object) + session.getAttribute(String)[在jsp中使用] 和 传递法request.setAttribute(String, Object) + request.getAttribute(String)[在jsp中使用]
     * 1.会话法：经测试，session里的参数能维持整个session周期——cart页面依然能用“SearchBookList” —— 通常把Bean里产生的、不能通过form生成的数据 放session里
     * 2.传递法：request里的参数只在同一个request周期中保存，而利用转移法能保持同一个request周期，重定向发则会使request周期失效——通常把form能生成的、仅在下个页面继续使用的数据 放request里
     * 
     * 三、总结：
     * 用转移法转移页面 可以与 request传递参数 或 session传递参数 结合起来使用
     * 用重定向法转移页面，只能用 session传递参数
    */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        pageFrom = request.getParameter("page");//获得request来自哪个页面
        pageAct = request.getParameter("submit");//获得某个页面的request想要干嘛
        session = request.getSession(true);//从当前request中获取session，如果获取不到session，则会自动创建一个session，默认为true,并返回新创建的session；如果获取到，则返回获取到的session;
        if(request.getSession().getAttribute(request.getLocalAddr()) == null){
            cartBean = this.lookupCartBeanLocal();
            request.getSession().setAttribute(request.getLocalAddr(), this.cartBean);
        }
        else{
            this.cartBean = (CartBeanLocal)request.getSession().getAttribute(request.getLocalAddr());
        }
        
        switch (pageFrom) {
            case "Search"://key用request传递
                //按key值模糊搜索
                String key = request.getParameter("key");
                if(key.equals("")){
                    errMsg = "NullKey";
                    session.setAttribute("errMsg", errMsg);
                    request.getRequestDispatcher("Error.jsp").forward(request,response);
                    return;
                }
                if(bookBean.find(key) == null || bookBean.find(key).isEmpty()){
                    errMsg = "EmptySearchBookList";
                    session.setAttribute("errMsg", errMsg);
                    request.getRequestDispatcher("Error.jsp").forward(request,response);
                    return;
                }
                List<Bookinfo> searchBookList = bookBean.find(key);
                session.setAttribute("SearchBookList", searchBookList);//SearchBookList放session里，如果遇到EmptyCartList，在error回来后还得用
                request.getRequestDispatcher("Directory.jsp").forward(request,response);
                break;
            case "Directory":
                //如果接收Directory页的request
                if(pageAct.equals("Search")){
                    request.getRequestDispatcher("Search.jsp").forward(request,response);
                }
                else{//pageAct.equals("Cart")
                    String[] choose;
                    if(request.getParameterValues("choose") == null){
                        choose = new String[0];
                    }
                    else{
                        choose = request.getParameterValues("choose");//choose中为选中图书的isbn号
                    }
                    for(int i = 0; i < choose.length; i++){//先把新选中的图书往购物车里添加
                        cartBean.add(choose[i]);
                    }
                    if(cartBean.gets().isEmpty()){
                        errMsg = "EmptyCartList";
                        session.setAttribute("errMsg", errMsg);
                        request.getRequestDispatcher("Error.jsp").forward(request,response);
                        return;
                    }
                    List<Cart> cartList = cartBean.gets();
                    session.setAttribute("CartList", cartList);//购物车用session传递，作为会话中恒定存在的数据，不仅Cart.jsp要用，Order.jsp也要用，而且无法通过Cart.jsp的form传递给Order.jsp，只能放session里
                    request.getRequestDispatcher("Cart.jsp").forward(request,response);
                }   
                break;
            case "Cart":
                if(pageAct.equals("Search")){
                    request.getRequestDispatcher("Search.jsp").forward(request,response);
                }
                else if(pageAct.equals("Return")){
                    request.getRequestDispatcher("Directory.jsp").forward(request,response);
                }
                else{//进入订单页面结算
                    int total = 0;//总价
                    List<Cart> cartList = (List<Cart>)session.getAttribute("CartList");//提交结算时的购物车
                    for(int i = 0; i < cartList.size(); i ++){
                        double price = cartList.get(i).getPrice();
                        Integer number = Integer.valueOf(request.getParameter(cartList.get(i).getIsbn()));
                        if(number < 0){
                            errMsg = "FaultFormat";
                            session.setAttribute("errMsg", errMsg);
                            total = 0;
                            request.getRequestDispatcher("Error.jsp").forward(request,response);
                            return;
                        }
                        total += (price * number);
                    }
                    if(total == 0){
                        errMsg = "FaultFormat";
                        session.setAttribute("errMsg", errMsg);
                        request.getRequestDispatcher("Error.jsp").forward(request,response);
                        return;
                    }
                    if(request.getParameter("address").equals("")){
                        errMsg = "EmptyAddress";
                        session.setAttribute("errMsg", errMsg);
                        request.getRequestDispatcher("Error.jsp").forward(request,response);
                        return;
                    }
                    total = 0;
                    for(int i = 0; i < cartList.size(); i ++){
                        String isbn = cartList.get(i).getIsbn();
                        double price = cartList.get(i).getPrice();
                        Integer number = Integer.valueOf(request.getParameter(cartList.get(i).getIsbn()));
                        cartBean.set(isbn, number);//如果number是0，CartBean会从购物车删去这本书
                        total += (price * number);
                    }
                    request.setAttribute("FinalList", cartBean.gets());
                    request.setAttribute("address", new String(request.getParameter("address").getBytes("ISO-8859-1"), "GB2312"));
                    request.setAttribute("total", total);
                    request.getRequestDispatcher("Order.jsp").forward(request,response);
                }
                break;
            default:
                switch(errMsg){
                    case "NullKey":
                        request.getRequestDispatcher("Search.jsp").forward(request,response);
                        break;
                    case "EmptySearchBookList":
                        request.getRequestDispatcher("Search.jsp").forward(request,response);
                        break;
                    case "EmptyCartList":
                        request.getRequestDispatcher("Directory.jsp").forward(request,response);
                        break;
                    case "FaultFormat":
                        request.getRequestDispatcher("Cart.jsp").forward(request,response);
                        break;
                    case "EmptyAddress":
                        request.getRequestDispatcher("Cart.jsp").forward(request,response);
                        break;
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private CartBeanLocal lookupCartBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CartBeanLocal) c.lookup("java:global/MyBookstoreSystem/MyBookstoreSystem-ejb/CartBean!statefulsession.CartBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
