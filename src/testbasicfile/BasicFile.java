package testbasicfile;

// Import Statements.
import java.io.BufferedReader;
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
import java.util.logging.Level;
import java.util.logging.Logger;
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
    
    // Default Constructor.
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
        for (int i = 0; i <= N - M; i++) 
        { 
            int j = 0; 
      
            /* For current index i, check for 
            pattern match */
            for (j = 0; j < M; j++)
            {
                if (s2.charAt(i + j) != s1.charAt(j))
                {
                    break;
                }
            }

            if (j == M)
            {
                return true;
            }
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
    
    
    public String listRecursive(File dir) 
    {        
        String counter = "";
        
        if (dir.isDirectory())
        {
            File[] f = dir.listFiles();
            
            for (File i : f)
            {
                if (i.isFile())
                {
                    counter = counter + "\tFile: " + i.getAbsoluteFile() + i.length() + " bytes\n";
                } 
                else  // (i.isDirectory())  
                {                    
                    counter = counter + "\tDirectory: " + i.getName() + "\n";
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
        // String to hold the output of the attributes.
        String resultingString = "";
                
        // The Absolute Path of the File.
        resultingString = "Absolute Path: " + fileObject.getAbsolutePath() + "\n";
        
        // Files and Directories that are in the Path of the File.
        resultingString = resultingString + listRecursive(fileObject.getParentFile());
        
        // The Size of the File in Kilobytes.
        resultingString = resultingString + "Size: " + (fileObject.length() / 1000) + " KB" +"\n"; 
        
        // The Number of Lines in the File (if text file is selected)
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
        
        // Showing the Attributes of the File in a Scroll Pane
        showScrollPane(resultingString, "Input File Attributes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Input 05 Display Content of File.
    void displayFile() 
    {
        try
        {
            String lines = "";
            BufferedReader br = new BufferedReader(new FileReader(fileObject));
            String line = null;
            while ((line = br.readLine()) != null)
            {                
                lines = lines + line + "\n";
            }
            
            // Showing the Attributes of the File in a Scroll Pane
            showScrollPane(lines, "Input File Attributes", JOptionPane.INFORMATION_MESSAGE);
        }
        catch (IOException exceptionName)
        {
            display("Approved option was not selected", exceptionName.toString(),JOptionPane.ERROR_MESSAGE);
        }

    }
    
    void searchFile()
    {
        
        
        //Construct the LineNumberReader object 
        try
        {            
            LineNumberReader lnr = new LineNumberReader(new FileReader(fileObject));
            String word = JOptionPane.showInputDialog(null, "Enter the word to search in the file: ");
            //word = " " + word + " ";
            String line = "";
            String s = "";
            
            String[] words=null;  //Intialize the word Array
            
            
            while ((line = lnr.readLine()) != null)
            {
                words=line.split(" ");  //Split the word using space
                for (String testWord : words)
                {
                    if (testWord.equalsIgnoreCase(word))   //Search for the given word
                    {
                        s =lnr.getLineNumber() + ": " + line + '\n';
                        System.out.println(s);
                    }
                }
//                // If searching for the word.
//                if (isSubstring(word, line))
//                {                  
//                      s = s + "Line : " + lnr.getLineNumber() + "  " + line + '\n';                                         
//                }                
            }
            
            //System.out.println(s);
            
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
}
