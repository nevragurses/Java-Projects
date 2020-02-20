import java.io.FileReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.DataInputStream;
/**
 * Driver Class for Part-2 of homework.
 */
public class Main_Part_2 {

    public static void main(String[] args) {
        try {
            ConvertInfixToPostfix cv = new ConvertInfixToPostfix();
            String str = cv.readFile(args[0]);
            System.out.println("INFIX EXPRESSION:" + str);
            CalculatePostfix calc = new CalculatePostfix();
            System.out.println("POSTFIX EXPRESSION OF INFIX EXPRESSION: " +cv.convert(str));
            System.out.println("RESULT OF POSTFIX EXPRESSION: " +calc.calculate(cv.convert(str)));
        }
        catch(FileNotFoundException ex){
            ex.getMessage();

        }
        catch(IOException ex) {
            ex.getMessage();
        }
        /*try {
            Components comp = new Components();
            System.out.println(args[0]);
            System.out.println(comp.findWhite(args[0]));
        }
        catch(FileNotFoundException ex){
            ex.getMessage();

        }
        catch(IOException ex){
            ex.getMessage();
        }*/


    }

}
