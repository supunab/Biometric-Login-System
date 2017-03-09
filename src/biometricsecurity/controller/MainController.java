package biometricsecurity.controller;

import biometricsecurity.model.AuthType;
import biometricsecurity.model.BiometricSecurity;
import biometricsecurity.view.KeyStrokeTrainDialog;
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
    
    public boolean checkUserNameExists(String userName){
        ArrayList<String> userNames = biometricSecurity.getUserNames();
        
        for(String name : userNames){
            // Username is case insensitive
            if(userName.equalsIgnoreCase(name)) return true;
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
    
    public AuthType getAuthType(String userName){
        return biometricSecurity.getAuthType(userName);
    }
    
    public boolean validateHandAuth(String userName, float[] fingerHeights, float[] fingerWidths){
        return biometricSecurity.validateHandAuth(userName, fingerHeights, fingerWidths);
    }
    
    public boolean createUserKeyStroke (String name){
        if (checkUserNameExists(name)){
            return false;
        }
        
        biometricSecurity.createUserKeyStroke(name);
        return true;
    }
    
    public void updateCurrentDataSet(String sentence, double[] times){
        biometricSecurity.updateCurrentDataSet(sentence, times);
    }
    
    public void saveKeyStrokeUser(){
        biometricSecurity.saveKeyStrokeUser();
    }
    
    public String getSentence(int count){
        return biometricSecurity.getSenetence(count);
    }
    
    public String getAuthSentene(){
        return biometricSecurity.getAuthSentence();
    }
    
    public boolean validateKeyStrokeAuth(String userName, String sentence, double[] times){
        return biometricSecurity.validateKeyStrokeAuth(userName, sentence, times);
    }
}
