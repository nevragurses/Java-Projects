import jdk.swing.interop.SwingInterOpUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**Driver class.*/
public class Main {
    //Height and width of digital image.
    public static void main(String[] args) {
        try {
            ReadImage read = new ReadImage();
            WholeThreads threads = new WholeThreads(read.readImage(args[0]), read.heightArr, read.widthArr);
            threads.doThreads();


        } catch(FileNotFoundException ex){
            final String message = ex.getMessage();

        }
        catch(IOException ex){
            ex.getMessage();
        }
    }
}

