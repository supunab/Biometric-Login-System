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
public class HandAuthCredintials implements AuthCredintials{
    
    private float[] fingerHeights;
    private float[] fingerWidths;
    private int loginCount;

    public HandAuthCredintials(float[] fingerHeights, float[] fingerWidths) {
        this.fingerHeights = fingerHeights.clone();
        this.fingerWidths = fingerWidths.clone();
        loginCount = 1;
    }

    @Override
    public boolean getAuthentication(AuthCredintials loginInfo) {
        float variance = 0;
        
        // Getting the variance of heights and widths
        // Difference is raised to power 2 because larger the difference, higher the probability of false login (exponential)
        for(int i=0; i < 5; i ++){
            variance += Math.pow(fingerHeights[i] - ((HandAuthCredintials) loginInfo).getFingerHeights()[i], 2);
            variance += Math.pow(fingerWidths[i] - ((HandAuthCredintials) loginInfo).getFingerWidths()[i], 2);
        }
        
        if (variance > 10){
            // false login
            return false;
        }
        else{
            // login succees
            // average out the initial values with login data
            updateStats(loginInfo);
            return true;
        }
    }

    @Override
    public void updateStats(AuthCredintials loginInfo) {
        for(int i=0; i < 5; i ++){
            fingerHeights[i] = (loginCount * fingerHeights[i] + ((HandAuthCredintials) loginInfo).getFingerHeights()[i])/(loginCount + 1);
            fingerWidths[i] = (loginCount * fingerWidths[i] + ((HandAuthCredintials)loginInfo).getFingerWidths()[i]) / (loginCount + 1);
            
            loginCount ++;
        }
    }

    public float[] getFingerHeights() {
        return fingerHeights;
    }

    public float[] getFingerWidths() {
        return fingerWidths;
    }
    
}
