package biometricsecurity.model;

/**
 *
 * @author Supun
 */
public class KeyStrokeAuthCredintials implements AuthCredintials{

    double[][] averageTime;
    int[][] count;
    
    public KeyStrokeAuthCredintials(){
        // first 26 simple letters and rest of the 26 capital letters and 53rd for space
        averageTime = new double[53][53];
        count = new int[53][53];
        
        // Initialize arrays with zeros
        for(int i=0; i<53; i++){
            for(int j=0; j<53; j++){
                count[i][j] = 0;
                averageTime[i][j] = 0;
            }
        }
    }
    
    public void updateDataSet(String sentence, double[] times){
        for(int i=1; i<sentence.length(); i++){
            int prev = (int) sentence.charAt(i-1);
            int current = (int) sentence.charAt(i);
            
            // Check capital or simple
            if (prev >= 97){
                // simple
                prev = prev - 97 + 26;
            }else if(prev==32){
                prev = 52; // Space
            }
            else{
                // capital
                prev -= 65;
            }
            
            if(current >= 97){
                current = current - 97 + 26;
            }else if(current==32){
                current = 52;
            }
            else{
                current -= 65;
            }
            
            averageTime[prev][current] = (averageTime[prev][current] * count[prev][current] + times[i-1]) / (count[prev][current] + 1);
            count[prev][current] ++;
        }
    }
    
    private double getOverallAverage(){
        
        double totalTime = 0;
        int totalCount = 0;
        
        for(int i=0; i < 53; i ++){
            for(int j=0 ; j < 53; j++){
                totalTime += averageTime[i][j];
                totalCount += count[i][j];
            }
        }
        
        return totalTime/totalCount;
    }
    
    public boolean getAuthentication(String sentence, double[] times){
        double variance = 0;
        double overallAverage = getOverallAverage();
        
        for(int i=1; i < sentence.length(); i ++){
            int prev = (int) sentence.charAt(i-1);
            int current = (int) sentence.charAt(i);
            
            // Check capital or simple
            if (prev >= 97){
                // simple
                prev = prev - 97 + 26;
            }else if(prev == 32){
                prev = 52;
            }
            else{
                // capital
                prev -= 65;
            }
            
            if(current >= 97){
                current = current - 97 + 26;
            }else if(current==32){
                current = 52;
            }
            else{
                current -= 65;
            }
            
            // Take a variability measurement
            // If dataset doesn't have a value for this state transfer take the overall average value
            if (count[prev][current] == 0){
                // Use the overall average
                variance += Math.pow(overallAverage - times[i-1], 2);
            }else{
                variance += Math.pow(averageTime[prev][current] - times[i], 2);
            }
            
        }
        
        variance = Math.sqrt(variance);
        
        System.out.println("Variance : "+variance+" max: "+(25 * sentence.length()));
        if (variance> Math.max(35 * sentence.length(), 700)){
            return false;
        }
        
        updateDataSet(sentence, times);
        return true;
        
    }
    
    @Override
    public boolean getAuthentication(AuthCredintials loginInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateStats(AuthCredintials loginInfo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
