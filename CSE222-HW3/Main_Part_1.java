import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
/**
 * Driver Class for Part-1 of homework.
 */
public class Main_Part_1 {
    public static void main(String[] args) {
        try {
            Components comp = new Components();
            System.out.println(args[0]);
            System.out.println("NUMBER OF WHITE COMPONENTS:" + comp.findWhite(args[0]));
        }
        catch(FileNotFoundException ex){
            ex.getMessage();

        }
        catch(IOException ex){
            ex.getMessage();
        }
    }

}
