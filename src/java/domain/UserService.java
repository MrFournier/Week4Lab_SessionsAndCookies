/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author 766375
 */
public class UserService implements Serializable {
    
    public UserService() {
        
    }
    
    public User userLogin(String userName, String password) {
        if((userName.equals("adam") && password.equals("password")) || (userName.equals("betty") && password.equals("password"))) {
            User u = new User(userName, password);
            return u;
        }
        
        return null;
    }
}
