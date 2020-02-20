import com.sun.jdi.IntegerValue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Driver class to test.
 */
public class Main {
    public static void main(String[] args){
        try {
            ReadFile readFile=new ReadFile();
            File file=new File("./input.txt");
            readFile.read(file);
        }
        catch (Exception ex){
            ex.getStackTrace();
        }
    }
}
