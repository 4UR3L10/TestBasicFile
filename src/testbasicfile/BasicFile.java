package testbasicfile;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BasicFile
{
    File f;

    public BasicFile()
    {
        JFileChooser choose = new JFileChooser(".");
        int status = choose.showOpenDialog(null);

        try
        {
            if (status != JFileChooser.APPROVE_OPTION)
            {
                throw new IOException();
            }

            f = choose.getSelectedFile();

            if (!f.exists())
            {
                throw new FileNotFoundException();
            }

            display(f.getName(), "FIle has been choosen",
                    JOptionPane.INFORMATION_MESSAGE);
        } 
        
        catch (FileNotFoundException e)
        {
            display("File not found ....", e.toString(), JOptionPane.WARNING_MESSAGE);

        } 
        
        catch (IOException e)
        {
            display("Approve option was not selected", e.toString(),
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void display(String msg, String s, int t)
    {
        JOptionPane.showMessageDialog(null, msg, s, t);
    }
}
