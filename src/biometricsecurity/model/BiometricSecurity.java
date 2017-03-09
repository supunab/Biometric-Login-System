package biometricsecurity.model;

import biometrics.serialization.ObjectSerialization;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Supun
 */
public class BiometricSecurity {
    
    private ArrayList<String> userNames;
    private User currentUser;

    public BiometricSecurity() {
        loadUserNames();
    }
    
    private void loadUserNames(){
        ObjectSerialization serialization = new ObjectSerialization();
        if (serialization.isFileAvailable("usernames")){
            userNames = (ArrayList<String>) serialization.loadObject("usernames");
        }
        else{
            userNames = new ArrayList<>();
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
    
    public void createUserKeyStroke(String userName){
        User user = new User(userName, AuthType.KEY_STROKE_DYNAMICS);
        ObjectSerialization serialization = new ObjectSerialization();
        currentUser = user;
        serialization.saveObject(user, userName);
        
        // update user name list
        addNewUserName(userName);
    }
    
    public String getSenetence(int count){
        String[] sentences = new String[10];
        
        sentences[0] = "While I am typing my profile is created";
        sentences[1] = "It has been a long time";
        sentences[2] = "One day all your hardwork will pay off";
        sentences[3] = "Follow your dreams or live a simple life";
        sentences[4] = "You have got to find what you love";
        sentences[5] = "None of this had even a hope of any practical application in my life";
        sentences[6] = "My second story is about love and loss";
        sentences[7] = "If you live each day as if it was your last someday you will be right";
        sentences[8] = "Your time is limited so do not waste it";
        sentences[9] = "Finaly stay hungry and stay foolish";
        
        
        return sentences[count];
        
    }

    public void updateCurrentDataSet(String sentence, double[] times){
        ((KeyStrokeAuthCredintials)currentUser.getCredintials()).updateDataSet(sentence, times);
    }
    
    public void saveKeyStrokeUser(){
        ObjectSerialization serialization = new ObjectSerialization();
        serialization.saveObject(currentUser, currentUser.getUsername());
    }
    
    public String getAuthSentence(){
        // Use a random variable and return random sentences from an arary
        String[] sentence = new String[5];
        
        sentence[0] = "You do not have to agree with all";
        sentence[1] = "One day you will realize you are correct";
        sentence[2] = "Try harder you will never be dissapointed";
        sentence[3] = "If there is a will there is a way";
        sentence[4] = "Do whatever you feel right";
        
        return sentence[Math.abs(new Random().nextInt()) % 5];
    }
    
    public boolean validateKeyStrokeAuth(String userName, String sentence, double[] times){
        ObjectSerialization serialization = new ObjectSerialization();
        User user = (User) serialization.loadObject(userName);
        return ((KeyStrokeAuthCredintials)user.getCredintials()).getAuthentication(sentence, times);
    }
}
