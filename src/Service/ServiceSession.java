/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import Entity.User;

/**
 *
 * @author aymen
 */
public class ServiceSession {
    static ServiceSession instance;
    User loggedInUser;
    
    private ServiceSession()
    {
        
    }

    public static ServiceSession getInstance() {
       if(instance == null)
        {
            instance = new ServiceSession();
            return instance;
        }
        else
            return instance;
    }

   
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
