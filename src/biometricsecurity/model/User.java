/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricsecurity.model;

import java.io.Serializable;

/**
 *
 * @author Supun
 */
public class User implements Serializable{
    private final String username;
    private final AuthType authType;
    private AuthCredintials credintials;

    public User(String username, AuthType authType, float[] fingerHeights, float[] fingerWidths) {
        this.username = username.toLowerCase();
        this.authType = authType;
        this.credintials = new HandAuthCredintials(fingerHeights, fingerWidths);
    }

    public AuthType getAuthType() {
        return authType;
    }

    public AuthCredintials getCredintials() {
        return credintials;
    }

    public String getUsername() {
        return username;
    }
    
}
