package testbasicfile;

// Import Statements.
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
 
//import java.io.FileInputStream; 
//import java.io.BufferedInputStream; 


public class BasicFile
{
    // Creating a file object.
    File fileObject;
    
    // Counter for the name of the file.
    int counter = 1;
    
    // Constructor.
    public BasicFile()
    {
//        // Creating FileChooser object
//        JFileChooser chooseObject = new JFileChooser(".");
        
//        // Brings up the dialog box for selecting a file or directory.
//        int status = chooseObject.showOpenDialog(null);
//        
//        // Try-Catch statement in case the was a problem with the file or the user exits. 
//        try
//        {
//            // If the option was not a valid one.
//            if (status != JFileChooser.APPROVE_OPTION) throw new IOException();
//            {              
//                // Returning a file and storing it in the file object.
//                fileObject = chooseObject.getSelectedFile();
//            }            
//            
//            // If file does not exist.
//            if (!fileObject.exists())
//            {
//                throw new FileNotFoundException();
//            }
//            
//            // If the file was succesfully chosen.
//            display(fileObject.getName(), "File has been choosen",JOptionPane.INFORMATION_MESSAGE);
//        } 
//        
//        // In case the file was not found.
//        catch (FileNotFoundException exceptionName)
//        {
//            display("File not found ....", exceptionName.toString(), JOptionPane.WARNING_MESSAGE);
//        } 
//        
//        // In case the user cancel or exits.
//        catch (IOException exceptionName)
//        {
//            display("Approved option was not selected", exceptionName.toString(),JOptionPane.ERROR_MESSAGE);
//        }
    }
    
    void diplayPath(){
        JOptionPane.showMessageDialog(null, fileObject.getPath());
    }
    // Template Method for Messages.
    void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
    }
    
    
    
    void selecFile()
    {
        // Creating FileChooser object
        JFileChooser chooseObject = new JFileChooser(".");
        
       // Brings up the dialog box for selecting a file or directory.
        int status = chooseObject.showOpenDialog(null);
        
        // Try-Catch statement in case the was a problem with the file or the user exits. 
        try
        {
            // If the option was not a valid one.
            if (status != JFileChooser.APPROVE_OPTION) throw new IOException();
            {              
                // Returning a file and storing it in the file object.
                fileObject = chooseObject.getSelectedFile();
            }            
            
            // If file does not exist.
            if (!fileObject.exists())
            {
                throw new FileNotFoundException();
            }
            
            // If the file was succesfully chosen.
            display(fileObject.getName(), "File has been choosen",JOptionPane.INFORMATION_MESSAGE);
        } 
        
        // In case the file was not found.
        catch (FileNotFoundException exceptionName)
        {
            display("File not found ....", exceptionName.toString(), JOptionPane.WARNING_MESSAGE);
        } 
        
        // In case the user cancel or exits.
        catch (IOException exceptionName)
        {
            display("Approved option was not selected", exceptionName.toString(),JOptionPane.ERROR_MESSAGE);
        } 
    }
    
    void copyFile()
    {
        try
        {
            
            // Creating DataInputStream Object.
            DataInputStream datInpStrObj = new DataInputStream(new FileInputStream(fileObject.getPath()));
            
            
            
            // SOLVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
//            String tempString = fileObject.getParent() + "\\" + fileObject.getName();
//            
//            // Creating FileOutput Object.
//            if (tempString.equalsIgnoreCase(fileObject.getPath()))
//            {
//                // Creating FileOutput Object.
//                tempString = fileObject.getParent() + "\\" + Integer.toString(counter) + fileObject.getName(); 
//                counter++;
//            }
            
               // Creating FileOutput Object.
               FileOutputStream fileOutStrObj = new FileOutputStream(fileObject.getParent() + "\\" + "copy-" + fileObject.getName(), true); 
            //SOLVEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
            
            
            
            // Available Stream to be read.
            int length = datInpStrObj.available();
            // Create buffer. 
            byte[] buf = new byte[length];
            // Read the full data into the buffer. 
            datInpStrObj.readFully(buf); 
            // From the buffer into the location.. 
            fileOutStrObj.write(buf, 0, buf.length);
            
            JOptionPane.showMessageDialog(null, "File was succesfully copied");
            JOptionPane.showMessageDialog(null, "File is in:  " + fileObject.getParent());
        }
        // In case the file was not created.
        catch (FileNotFoundException exceptionName)
        {
            display("File was not created", exceptionName.toString(),JOptionPane.ERROR_MESSAGE);
        }
        // In case the file was not copied.
        catch (IOException exceptionName)
        {
            display("Approved option was not selected", exceptionName.toString(),JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    

//IT IS IN USE?????????????????????????????????????????????????????????????????
/*    
    String getPath()
    {
        return fileObject.getAbsolutePath();
    }

    long getFileSize()
    {
        return fileObject.length();
    }

    String canRead()
    {
        return (fileObject.canRead()) ? "This file can be opened for reading" : "Cannot read this file";
    }

    String directoryOrFile()
    {
        return (fileObject.isDirectory()) ? "This is a directory and not an ordinary file" : "This is a file and not a directory";
    }

    String exists()
    {
        return (fileObject.exists()) ? "The physical file exists" : "The physical file does not exists";
    }
*/
//IT IS IN USE?????????????????????????????????????????????????????????????????
}
