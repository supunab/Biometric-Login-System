/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricsecurity.controller;

import biometricsecurity.model.BiometricSecurity;
import biometricsecurity.view.SignUpFrame;
import biometricsecurity.view.StartFrame;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class MainController {
    BiometricSecurity biometricSecurity;
    
    public MainController(){
        // Show the welcome screen
        biometricSecurity = new BiometricSecurity();
        
        StartFrame frame = new StartFrame(this);
        frame.setVisible(true);
        
    }
    
    private boolean checkUserNameExists(String userName){
        ArrayList<String> userNames = biometricSecurity.getUserNames();
        
        for(String name : userNames){
            if(userName.equals(name)) return true;
        }
        
        return false;
    }
    
    public boolean createUserHandAuth(String name, float[] fingerHeights, float[] fingerWidths){
        if (checkUserNameExists(name)){
            return false;
        }
        
        biometricSecurity.createUserHandAuth(name, fingerHeights, fingerWidths);
        return true;
    }
    
    public boolean createUserKeyStroke (String name){
        if (checkUserNameExists(name)){
            return false;
        }
        
        // Implement the logic here
        return true;
    }
    
    
}
