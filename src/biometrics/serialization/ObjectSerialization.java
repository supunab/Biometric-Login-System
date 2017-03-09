package biometrics.serialization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Supun
 */
public class ObjectSerialization {
    
    public void saveObject(Serializable object, String name){
        // Saving the object using the given save name
        try {
            FileOutputStream fileOut = new FileOutputStream("data/"+name+".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(object);
            out.close();
            fileOut.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public Serializable loadObject(String name){
        
        FileInputStream fIn = null;
        Serializable object = null;
        
        try {
            fIn = new FileInputStream("data/"+name+".ser");
            ObjectInputStream in = new ObjectInputStream(fIn);
            object = (Serializable) in.readObject();
            in.close();
            fIn.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fIn.close();
            } catch (IOException ex) {
                Logger.getLogger(ObjectSerialization.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return object;
        
    }
    
    public boolean isFileAvailable(String fileName){
        // Checks availability of a file inside the data/directory
        File file = new File("data/"+fileName+".ser");
        
        return file.isFile() && !file.isDirectory();
        
    }
}
