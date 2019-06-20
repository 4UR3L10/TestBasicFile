package testbasicfile;

import javax.swing.JOptionPane;
import java.io.IOException;

public class TestBasicFile
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        boolean done = false;
        BasicFile fileObject;
        String menu = "Enter option\n1. Open File\n2. Copy File\n3. Write File\n4. Display Input File Attributes\n5. Display Input File Content\n6. Search Iput File\n7. Quit";

        while (!done)
        {
            String s = JOptionPane.showInputDialog(menu);

            try
            {
                int i = Integer.parseInt(s);
                switch (i)
                {
                    case 1:
                        fileObject = new BasicFile();
                        break;
                    case 2:
                        //display("STILL ON MAINTANCE", "Error");
                        break;
                    case 3:
                        //display("STILL ON MAINTANCE", "Error");
                        break;
                    case 4:
                        //display("STILL ON MAINTANCE", "Error");
                        break;
                    case 5:
                        //display("STILL ON MAINTANCE", "Error");
                        break;
                    case 6:
                        //display("STILL ON MAINTANCE", "Error");
                        break;    
                    case 7:
                        done = true;
                        break;
                    default:
                        display("This option is undefine", "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }
            } 
            catch (NumberFormatException | NullPointerException e)
            {
                display("Error", e.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
//    static void display(String s, String err)
//    {
//        JOptionPane.showMessageDialog(null, s, err, JOptionPane.ERROR_MESSAGE);
//    }
    
    // Template Method for Messages.
    static void display(String message, String windowsName, int typeOfMessage)
    {
        JOptionPane.showMessageDialog(null, message, windowsName, typeOfMessage);
    }

}
