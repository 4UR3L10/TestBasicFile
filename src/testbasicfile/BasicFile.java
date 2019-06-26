package testbasicfile;

// Import Statements.
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.IOException;
import java.io.LineNumberReader;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
 
//import java.io.FileInputStream; 
//import java.io.BufferedInputStream; 


public class BasicFile
{
    // Creating a file object.
    File fileObject;
    
    // Counter for the name of the file.
    int counter = 1;
    
    // Counter for the number of lines.
    int numberLines = 0;
    
    // String to get all directories.????????????????????????????????????????????
    String directories = "";
    
    // Constructor.
    public BasicFile()
    {
        
    }
    
    // Display Path of the file.
    void diplayPath(){
        JOptionPane.showMessageDialog(null, fileObject.getPath());
    }
    
    // Template Method for Messages.
    void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
    }
    
    // Method for the scroll pane.
    void showScrollPane(String resultString, String heading, int MESSAGE_TYPE)
    {
        JTextArea textAreaObject = new JTextArea(resultString, 20, 50);
        JScrollPane scrollPaneObject = new JScrollPane(textAreaObject);
        JOptionPane.showMessageDialog(null, scrollPaneObject, heading, MESSAGE_TYPE);
    }
    
    // Input 01 Select File
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
    
    // Input 02 Copy File
    void copyFile()
    {
        try
        {            
            // Creating DataInputStream Object.
            DataInputStream datInpStrObj = new DataInputStream(new FileInputStream(fileObject.getPath()));
            
            // Creating FileOutput Object.
            FileOutputStream fileOutStrObj = new FileOutputStream(fileObject.getParent() + "\\" + "copy-" + fileObject.getName(), true);             
            
            // Available Stream to be read.
            int length = datInpStrObj.available();
            
            // Create buffer. 
            byte[] buf = new byte[length];
            
            // Read the full data into the buffer. 
            datInpStrObj.readFully(buf); 
            
            // From the buffer into the location.. 
            fileOutStrObj.write(buf, 0, buf.length);
            
            // Display info.
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
    
//        TESTINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGFOR WRITE OUTPUT FILE  
    boolean isSubstring(String s1, String s2) 
    { 
        int M = s1.length(); 
        int N = s2.length(); 
      
        /* A loop to slide pat[] one by one */
        for (int i = 0; i <= N - M; i++) { 
            int j; 
      
            /* For current index i, check for 
            pattern match */
            for (j = 0; j < M; j++) 
                if (s2.charAt(i + j) != s1.charAt(j)) 
                    break; 
      
            if (j == M) 
                return true; 
        } 
      
        return false; 
    }
    
 
    String readLineByLine(File f) throws IOException
    {
        //Construct the LineNumberReader object 
        LineNumberReader lnr = new LineNumberReader(new FileReader(f));

        String line = "";
        String s = "";

        while ((line = lnr.readLine()) != null)
        {
            s = s + "Line : " + lnr.getLineNumber() + "  " + line + "\n"; 
        }

        return s;
    }

    void saveFile() throws IOException
    {
        JFileChooser choose = new JFileChooser(".");
        int status = choose.showSaveDialog(null);
        
        if (status != JFileChooser.APPROVE_OPTION)
        {
            throw new IOException();
        }
        
        File f = choose.getSelectedFile();          

        FileWriter fw = new FileWriter(f); // Append.
    

        String s = readLineByLine(fileObject);  //goood

        fw.write(s, 0, s.length());

        fw.flush();

        fw.close();
    }

    
    void writeOutputFile()
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
            
            // If user selected a txt file give feedback else throw IOException.
            if(isSubstring(".txt",  fileObject.getName()))
            {
              JOptionPane.showMessageDialog(null, "You have selected a text file");
            } else {
                throw new IOException();
            }
            
            
            // If the file was succesfully chosen.
            display(fileObject.getName(), "File has been choosen",JOptionPane.INFORMATION_MESSAGE);
            
//            TESTINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGG
            //readLineByLine(fileObject);
            saveFile();
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
//        TESTINGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGGFOR WRITE OUTPUT FILE
    
    
    public String listRecursive(File dir) // change method....
    {        
        String counter = "";
        
        if (dir.isDirectory())
        {
            File[] f = dir.listFiles();
            
            for (File i : f)
            {
                if (i.isFile())
                {
//                    System.out.println("\tFile: " + i.getAbsoluteFile() + "\t" + i.length() + " bytes");
                      //JOptionPane.showMessageDialog(null, "\tFile: " + i.getAbsoluteFile() + "\t" + i.length() + " bytes");
                    counter = counter + "\tFile: " + i.getAbsoluteFile() + "\t" + i.length() + " bytes\n";
                } 
                else  // (i.isDirectory())  
                {
                    //JOptionPane.showMessageDialog(null, "Directory: " + i.getName());
                    counter = counter + "Directory: " + i.getName();
                    listRecursive(i);  // Recursive call 
                }
            }
        }
        return counter;
    }
    
    // Read Lines Of The File.
    void readNumberOfLInes(File f) throws IOException
    {
        LineNumberReader lnr = null;
        try
        {
            //Construct the LineNumberReader object 
            lnr = new LineNumberReader(new FileReader(f));

            String line = "";

            while ((line = lnr.readLine()) != null)
            {                            
                numberLines++;
            }
        } 
        finally
        {
            //Close the LineNumberReader.
            try  
            {
                lnr.close();
            } 
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    
    // Input 04 Show Attributes.
    void showAttributes() 
    {
        String resultingString = "";
                
        resultingString = "Absolute Path: " + fileObject.getAbsolutePath() + "\n";
        
        
        
              resultingString = resultingString + listRecursive(fileObject.getParentFile());
        
              
        
        
        
        
        resultingString = resultingString + "Size: " + (fileObject.length() / 1000) + " KB" +"\n";        
        try
        {
            // If user selected a txt file show number of lines.
            if (isSubstring(".txt", fileObject.getName()))
            {
                readNumberOfLInes(fileObject);
                resultingString = resultingString + "Number of lines: " + numberLines +"\n";
            }
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        
        showScrollPane(resultingString, "Input File Attributes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    
}
