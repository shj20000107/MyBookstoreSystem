/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statefulsession;

import entitysession.BookBeanLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author 沈海健
 */
@Stateful
public class CartBean implements CartBeanLocal {
    @EJB
    private BookBeanLocal bookBean;
    private List<Cart> cartList = new ArrayList<Cart>();

    @Override
    public void add(String isbn) {
        boolean isExist = false;
        Cart temp = new Cart();
        String title = bookBean.get(isbn).getTitle();
        double price = bookBean.get(isbn).getPrice();
        for(int i = 0; i < cartList.size(); i++){
            temp = cartList.get(i);
            if(temp.getIsbn().equals(isbn)){
                isExist = true;
                break;
            }
        }
        if(isExist){//如果cartList里已经有该图书了，不再往里加，而是把其数量加一
            temp.setNumber(temp.getNumber() + 1);
        }
        else{//如果cartList里没有该图书，那么加入到cartList里
            Cart cart = new Cart(isbn, title, price);//number默认置为1
            cartList.add(cart);
        }
    }
    
    @Override
    public void set(String isbn, Integer num) {
        for(int i = 0; i < cartList.size(); i ++){
            if(cartList.get(i).getIsbn().equals(isbn))
                if(num == 0)//如果设置的数量是0，则直接移除它
                    cartList.remove(i);
                else
                    cartList.get(i).setNumber(num);
        }
    }

    @Override
    public List<Cart> gets() {
        return cartList;
    }

    @Override
    public String getString() {
        return "This is a test case for Stateful Session Bean";
    }
}
