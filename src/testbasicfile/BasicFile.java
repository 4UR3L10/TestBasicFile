/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/

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
import java.io.StreamTokenizer;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
// Importing my own Exception.
import testbasicfile.MyException;

public class BasicFile
{
    // Creating a file object.
    File fileObject;
    
    // Counter for the number of lines.
    int numberLines = 0;
        
    // Default Constructor.
    public BasicFile(){}
        
    // Input 01 Select File.
    public void selecFile()
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
    
    // Input 02 Copy File.
    public void copyFile()
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
    
    // Input 03 Save File.
    public void saveFile()
    {
        try
        {
            // If user selected a txt file.
            if (isSubstring(".txt", fileObject.getName())) 
            {
                try
                {
                    // Checking if there is file loaded.
                    fileObject.canExecute();

                    // Choosing to save or overwrite the file.
                    JFileChooser choose = new JFileChooser(".");                    
                    int status = choose.showSaveDialog(null);                    

                    // If not save the file throw exception.
                    if (status != JFileChooser.APPROVE_OPTION)
                    {
                        throw new IOException();
                    }

                    // Getting the file and passing it to File Writer.
                    File f = choose.getSelectedFile();

                    // Append.
                    FileWriter fileWriterObject = new FileWriter(f);

                    // Set all lines read by the method and storing them into the String.
                    String writerString = readLineByLine(fileObject);

                    // Writing the info to the file.
                    fileWriterObject.write(writerString, 0, writerString.length());
                    fileWriterObject.flush();

                    // Closing.
                    fileWriterObject.close();
                } // In case the user cancel or exits.
                catch (IOException exceptionName)
                {
                    display("Approved option was not selected", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
                }
            } 
            else
            {               
               throw new MyException(fileObject.getName());
            }
        }
        // In case the user select a file that is not a text file.
        catch(MyException exceptionName)
        {
            display(exceptionName.toString(),"Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Input 04 Show Attributes.
    public void showAttributes() 
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
        // In case the user cancel or exits.
        catch (IOException exceptionName)
        {
            display("Approved option was not selected", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
        }    
        
        // Showing the Attributes of the File in a Scroll Pane
        showScrollPane(resultingString, "Input File Attributes", JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Input 05 Display Content of File.
    public void displayFile()
    {
        try
        {
            // If user selected a txt file.
            if (isSubstring(".txt", fileObject.getName()))
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
                    display("Approved option was not selected", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
                }
            } 
            else
            {
                throw new MyException(fileObject.getName());
            }
        } 
        // In case the user select a file that is not a text file.
        catch (MyException exceptionName)
        {
            display(exceptionName.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Input 06 Search a given String.    
    public void searchFile()
    {
        try
        {
            // If user selected a txt file.
            if (isSubstring(".txt", fileObject.getName()))
            {
                // Getting the File to Search for the Given Word. 
                try
                {
                    LineNumberReader lnr = new LineNumberReader(new FileReader(fileObject));
                    String word = JOptionPane.showInputDialog(null, "Enter the word to search in the file: ");
                    String line = "";
                    String s = "\t\t\tFound on:\n";
                    int lineHolder = 0;
                    String[] words = null;  //Intialize the word Array.

                    while ((line = lnr.readLine()) != null)
                    {
                        // Split the word using space.
                        words = line.split(" ");

                        for (String testWord : words)
                        {
                            // Search for the given word.
                            if (testWord.equalsIgnoreCase(word))
                            {
                                // If the Word is repeated in the same line do not show it twice.
                                if (lineHolder == lnr.getLineNumber())
                                {

                                } else
                                {
                                    s = s + lnr.getLineNumber() + ": " + line + '\n';
                                    lineHolder = lnr.getLineNumber();
                                }
                            }
                        }
                    }

                    // Shows the line(s) in which the word was found.
                    showScrollPane(s, "Input File Search", JOptionPane.INFORMATION_MESSAGE);
                } // In case the file was not found.
                catch (FileNotFoundException exceptionName)
                {
                    display("File not found ....", exceptionName.toString(), JOptionPane.WARNING_MESSAGE);
                } // In case the user cancel or exits.
                catch (IOException exceptionName)
                {
                    display("Error", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
                }
            } 
            else
            {
                throw new MyException(fileObject.getName());
            }
        } 
        // In case the user select a file that is not a text file.
        catch (MyException exceptionName)
        {
            display(exceptionName.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Input 07 Search using the Tokenizer.
    public void searchFileTokenizer()
    {
        try
        {
            // If user selected a txt file.
            if (isSubstring(".txt", fileObject.getName()))
            {

                try
                {
                    // Initialization.
                    int lineNumber = 0;
                    boolean found = false;
                    String line = "";  // Hold one line.
                    String lineReader = "";  // Temp Var to hold the a line.
                    String finishLine = "\t\t\tFound on:\n";  // Line to hold the final output.
                    StreamTokenizer st = new StreamTokenizer(new FileReader(fileObject));
                    String word = JOptionPane.showInputDialog(null, "Enter the word to search in the file: ");

                    // Given.
                    st.eolIsSignificant(true);     // Recognize end of line as token 
                    st.wordChars('"', '"');         // Recognize double quote (") as token 
                    st.wordChars('@', '@');    // Recognize at (@) as token 
                    st.wordChars(',', ',');          // Recognize comma (,) as token 
                    st.wordChars('\'', '\'');      // Recognize single quote (') as token 
                    st.wordChars('!', '!');         // Recognize exclamation(!) as token 
                    st.lowerCaseMode(true); // Convert uppercase characters to lower case 

                    // While Not End of File keep looping.
                    while (st.nextToken() != StreamTokenizer.TT_EOF)
                    {
                        // Assign each reading to the following categories: word,char,number and End of Line.
                        switch (st.ttype)
                        {
                            // WORD.
                            case StreamTokenizer.TT_WORD:
                                // Build the line using the temp var.
                                lineReader = lineReader + " " + st.sval;

                                // Coverting the Given Input and Word to lowercase.
                                String newLowerWord = st.sval.toLowerCase();
                                word = word.toLowerCase();

                                // Using method to find the given input. 
                                if (isSubstring(word, newLowerWord))
                                {
                                    found = true;
                                }
                            break;

                            // NUMBER
                            case StreamTokenizer.TT_NUMBER:
                                // Build the line using the temp var.
                                lineReader = lineReader + " " + st.nval;
                            break;

                            // END OF LINE [ENTER] \n.
                            case StreamTokenizer.TT_EOL:
                                // In each End Of Line increasing the line number.
                                lineNumber++;

                                // Build the line using the temp var.
                                lineReader = lineReader + "\n";

                                // In case of found get the line in where the word was found and build output.
                                if (found == true)
                                {
                                    line = lineReader;
                                    finishLine = finishLine + lineNumber + ": " + line;
                                    found = false;  // If found reset.
                                }

                                // Reset Values.
                                line = "";
                                lineReader = "";
                            break;

                            default:
                                // System.out.println((char) st.ttype + " ++> not recognized");
                            break;
                        }
                    }

                    // Getting the END OF FILE LINE.
                    lineReader = lineReader + "\n";
                    if (found == true)
                    {
                        line = lineReader;
                        lineNumber++;
                        finishLine = finishLine + lineNumber + ": " + line;
                        found = false;
                    }

                    line = "";
                    lineReader = "";

                    // Shows the line(s) in which the word was found.
                    showScrollPane(finishLine, "Tokenized Input File Search", JOptionPane.INFORMATION_MESSAGE);
                } 
                // In case the file was not found.
                catch (FileNotFoundException exceptionName)
                {
                    display("File not found ....", exceptionName.toString(), JOptionPane.WARNING_MESSAGE);
                } 
                // In case the user cancel or exits.
                catch (IOException exceptionName)
                {
                    display("Error", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
                }
            } 
            else
            {
                throw new MyException(fileObject.getName());
            }
        } 
        // In case the user select a file that is not a text file.
        catch (MyException exceptionName)
        {
            display(exceptionName.toString(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Gets the files and directories of a file.
    public String listRecursive(File dir) 
    {        
        String counter = "";
        
        if (dir.isDirectory())
        {
            File[] f = dir.listFiles();
            
            for (File i : f)
            {
                // FILE.
                if (i.isFile())
                {
                    counter = counter + "\tFile: " + i.getAbsoluteFile() + i.length() + " bytes\n";
                } 
                // DIRECTORY.
                else    
                {                    
                    counter = counter + "\tDirectory: " + i.getName() + "\n";
                    
                    // Using Recursion. 
                    listRecursive(i);  
                }
            }
        }
        return counter;
    }
    
    // Read Lines Of The File.
    public void readNumberOfLInes(File f) throws IOException
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
            // In case the user cancel or exits.
            catch (IOException exceptionName)
            {
                display("Approved option was not selected", exceptionName.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
    
    // Template Method for Messages.
    public void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
    }
    
    // Method for the scroll pane.
    public void showScrollPane(String resultString, String heading, int MESSAGE_TYPE)
    {
        JTextArea textAreaObject = new JTextArea(resultString, 20, 50);
        JScrollPane scrollPaneObject = new JScrollPane(textAreaObject);
        JOptionPane.showMessageDialog(null, scrollPaneObject, heading, MESSAGE_TYPE);
    }
    
    // Method to check if an string is a substring of the other.
    public boolean isSubstring(String s1, String s2) 
    { 
        int M = s1.length(); 
        int N = s2.length(); 
      
        // A loop to slide pat[] one by one.
        for (int i = 0; i <= N - M; i++) 
        { 
            int j = 0; 
      
            //For current index i, check for pattern match.
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
    
    // Read lines of a file.
    public String readLineByLine(File f) throws IOException
    {
        // Construct the LineNumberReader object.
        LineNumberReader lnr = new LineNumberReader(new FileReader(f));
        
        // Initializing vars.
        String line = "";
        String finalString = "";

        // Keep looping until cannot read more new lines.
        while ((line = lnr.readLine()) != null)
        { 
            finalString = finalString + line + "\n"; 
        }
        
        // Return all lines.
        return finalString;
    }
}

/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/