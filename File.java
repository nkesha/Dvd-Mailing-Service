/**
 * Created by chris on 2017-02-20.
 */
import java.io.*;
public class File
{
    public static BufferedReader openInputFile(String fileName)
    {
        BufferedReader reader = null;
        try
        {
            reader = new BufferedReader(new FileReader(fileName));
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        return reader;
    }// openInputFile

    public static String getLine(BufferedReader inFile)
    {
        String line = null;
        try{
            line = inFile.readLine();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return line;
    }// getLine

    public static void closeFile(BufferedReader inFile)
    {
        try
        {
            inFile.close();
        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }// closeFile
}
