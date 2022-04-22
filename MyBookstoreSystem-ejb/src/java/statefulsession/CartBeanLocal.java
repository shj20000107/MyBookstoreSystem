/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package statefulsession;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author 沈海健
 */
@Local
public interface CartBeanLocal {

    void add(String isbn);

    void set(String isbn, Integer num);

    List<Cart> gets();

    String getString();
    
}
