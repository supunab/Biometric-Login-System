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
public interface AuthCredintials {
    
    // To check the variance value with a given login info
    public boolean getAuthentication(AuthCredintials loginInfo);
    
    // If success, update the statistics to make the model more accurate
    public void updateStats(AuthCredintials loginInfo);
    
}
