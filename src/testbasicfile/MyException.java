/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/
package testbasicfile;

public class MyException extends Exception
{
    String filename;

    public MyException(String filename)
    {
        this.filename = filename;
    }

    @Override
    public String toString()
    {
        return "If you dont select a text file (.txt) the program is going to crash later.\n" + "Ivalid Type Of File: " + filename + "\n!!!Select Another File!!!";
    }
}

/*
    Author: Aurelio Martinez 
    Class:  COP3337-U02C-1195   
    Copyright© Aurelio Martinez
*/