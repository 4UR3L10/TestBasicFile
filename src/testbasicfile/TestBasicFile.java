package testbasicfile;

import java.io.IOException;
import java.nio.file.Files;
import javax.swing.JOptionPane;
//import java.io.IOException; ????????????????????

public class TestBasicFile
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // Initializing variables.
        boolean done = false;        
        String menu = "Enter option\n1. Open File\n2. Copy File\n3. Write File\n4. Display Input File Attributes\n5. Display Input File Content\n6. Search Input File\n7. Search Input File [Tokenizer]\n8. Quit";
        
        // Creating BasicFile object.
        BasicFile fileObject = new BasicFile();
        
        // Loop the program until Exit has been chosen.
        while (!done)
        {
            // Getting the user inout as String.
            String tempString = JOptionPane.showInputDialog(menu);

            // Try-Catch
            try
            {
                // Converting the user input from String to int to use it in the switch statement.
                int convertedChoice = Integer.parseInt(tempString);
                
                // MENU.
                switch (convertedChoice)
                {
                    // Opening a File
                    case 1:
                        // Executing the BasicFile constructor.
                        fileObject.selecFile();
                        break;
                    case 2:                        
                        fileObject.copyFile();
                        break;
                    case 3:                        
// STILLLLLLLLLLL ON MAINTANCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE.
                        fileObject.writeOutputFile();                        
                        break;
                    case 4:                        
                        fileObject.showAttributes();
                        break;
                    case 5:                        
                        fileObject.displayFile();
                        break;
                    case 6:                        
                        fileObject.searchFile();
                        break;                         
                    case 7:
                        //display("STILL ON MAINTANCE", "!!!ATTENTION!!!", JOptionPane.WARNING_MESSAGE);
                        fileObject.searchFileTokenizer();
                        break;
                    // EXIT.    
                    case 8:
                        done = true;
                        break;    
                    // Else display message none was selected.    
                    default:
                        display("This option is undefine", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            }
            // Else there was an error about the input.
            catch (NumberFormatException | NullPointerException e)
            {
                display("Error", e.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }  
    
    // Template Method for Messages.
    static void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
    }
}

//    static void display(String s, String err)
//    {
//        JOptionPane.showMessageDialog(null, s, err, JOptionPane.ERROR_MESSAGE);
//    }
