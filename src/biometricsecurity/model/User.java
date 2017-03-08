/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package biometricsecurity.model;

/**
 *
 * @author Supun
 */
public class User {
    private final String username;
    private final AuthType authType;
    private AuthCredintials credintials;

    public User(String username, AuthType authType, AuthCredintials credintials) {
        this.username = username;
        this.authType = authType;
        this.credintials = credintials;
    }
}
