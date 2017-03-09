package biometricsecurity.model;

import java.io.Serializable;

/**
 *
 * @author Supun
 */
public interface AuthCredintials extends Serializable{
    
    // To check the variance value with a given login info
    public boolean getAuthentication(AuthCredintials loginInfo);
    
    // If success, update the statistics to make the model more accurate
    public void updateStats(AuthCredintials loginInfo);
    
}
