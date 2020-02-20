import java.io.File;
import java.util.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
/**Driver class*/
public class Main {
    /**Reads input.txt file and process it.*/
    public static void read(File inputFile){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line=reader.readLine();
            NLP naturalPL =new NLP();
            naturalPL.readDataset("./dataset");
            while(line!=null){
                String[] parts = line.split("\\s+");
                if(parts[0].equals("bigram")) {
                    String biagram=parts[1];
                    System.out.println(naturalPL.bigrams(biagram));
                }
                else if(parts[0].equals("tfidf")){
                    String word=parts[1];
                    String file=parts[2];
                    System.out.println(naturalPL.tfIDF(word,file));
                }
                System.out.print("\n");
                line=reader.readLine();

            }
        }
        catch(Exception ex){
            ex.getStackTrace();
        }

    }
    public static void main( String[] args ) throws IOException {

        try {
            File file=new File("./input.txt");
            read(file);
        }
        catch (Exception ex){
            ex.getStackTrace();
        }

    }



}
