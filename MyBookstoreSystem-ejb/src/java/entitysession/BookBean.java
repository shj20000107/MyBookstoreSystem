/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitysession;

import entity.Bookinfo;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author 沈海健
 */
@Stateless
public class BookBean extends AbstractFacade<Bookinfo> implements BookBeanLocal {
    @PersistenceContext(unitName = "MyBookstoreSystem-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BookBean() {
        super(Bookinfo.class);
    }

    @Override
    public List<Bookinfo> find(String key) {
        List<Bookinfo> bookList = em.createNamedQuery("Bookinfo.findAll").getResultList();//先获得一个包含所有元组的List，再从List挑出与key匹配的元组，最后输出挑选后的List，从而实现模糊搜索并输出。
        List<Bookinfo> results = new ArrayList<Bookinfo>();
        Pattern pattern = Pattern.compile(key, Pattern.CASE_INSENSITIVE);//大小写不敏感型匹配
        //对title属性进行模糊搜索
        for(int i = 0; i < bookList.size(); i ++){
            Matcher matcher = pattern.matcher((bookList.get(i)).getTitle());
            if(matcher.find()){
                if(!results.contains(bookList.get(i)))//不重复添加
                    results.add(bookList.get(i));
            }
        }
        //对author属性进行模糊搜索
        for(int i = 0; i < bookList.size(); i ++){
            Matcher matcher = pattern.matcher((bookList.get(i)).getAuthor());
            if(matcher.find()){
                if(!results.contains(bookList.get(i)))//不重复添加
                    results.add(bookList.get(i));
            }
        }
        //对press属性进行模糊搜索
        for(int i = 0; i < bookList.size(); i ++){
            Matcher matcher = pattern.matcher((bookList.get(i)).getPress());
            if(matcher.find()){
                if(!results.contains(bookList.get(i)))//不重复添加
                    results.add(bookList.get(i));
            }
        }
        return results;//先不做模糊搜索，直接输出全部图书
    }

    @Override
    public Bookinfo get(String isbn) {
        return (Bookinfo)em.createNamedQuery("Bookinfo.findByIsbn").setParameter("isbn", isbn).getSingleResult();
    }
    
}
