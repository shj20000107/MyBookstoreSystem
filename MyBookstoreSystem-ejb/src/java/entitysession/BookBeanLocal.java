/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entitysession;

import entity.Bookinfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 沈海健
 */
@Local
public interface BookBeanLocal {

    void create(Bookinfo bookinfo);

    void edit(Bookinfo bookinfo);

    void remove(Bookinfo bookinfo);

    Bookinfo find(Object id);

    List<Bookinfo> findAll();

    List<Bookinfo> findRange(int[] range);

    int count();

    List<Bookinfo> find(String key);

    Bookinfo get(String isbn);
    
}
