package testbasicfile;

// Import Statements.
import java.io.File;
import java.io.FileNotFoundException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
 
//import java.io.FileInputStream; 
//import java.io.BufferedInputStream; 


public class BasicFile
{
    // Creating a file object.
    File fileObject;
    
    // Constructor.
    public BasicFile()
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
    
    // Template Method for Messages.
    void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
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
