/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricsecurity.model;

import biometrics.serialization.ObjectSerialization;
import java.util.ArrayList;

/**
 *
 * @author Supun
 */
public class BiometricSecurity {
    
    private ArrayList<String> userNames;

    public BiometricSecurity() {
        loadUserNames();
    }
    
    private void loadUserNames(){
        ObjectSerialization serialization = new ObjectSerialization();
        if (serialization.isFileAvailable("usernames")){
            userNames = (ArrayList<String>) serialization.loadObject("usernames");
        }
        else{
            userNames = new ArrayList<String>();
        }
    }

    public ArrayList<String> getUserNames() {
        return userNames;
    }
    
    private void addNewUserName(String userName){
        // This method should only be called after uniqueness of the username is confirmed
        userName = userName.toLowerCase();
        userNames.add(userName);
        ObjectSerialization serialization = new ObjectSerialization();
        serialization.saveObject(userNames, "usernames");
    }
    
    public void createUserHandAuth(String userName, float[] fingerHeights, float[] fingerWidths){
        User user = new User(userName, AuthType.HAND_GEOMETRY, fingerHeights, fingerWidths);
        ObjectSerialization serialization = new ObjectSerialization();
        serialization.saveObject(user,userName);
        
        // Update User Name list
        addNewUserName(userName);
    }
    
    public AuthType getAuthType(String userName){
        // This method shoud be called after checking userName existance
        ObjectSerialization serialization = new ObjectSerialization();
        return ((User) serialization.loadObject(userName)).getAuthType();
    }
    
    public boolean validateHandAuth(String userName, float[] fingerHeights, float[] fingerWidths){
        ObjectSerialization serialization = new ObjectSerialization();
        User user = (User) serialization.loadObject(userName);
        
        return user.getCredintials().getAuthentication(new HandAuthCredintials(fingerHeights, fingerWidths));
    }
}
