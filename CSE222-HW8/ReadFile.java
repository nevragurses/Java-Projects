import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**This class reads inputs from file.*/
public class ReadFile {
    public  void read(File inputFile){
         try{
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String[] firstLine=reader.readLine().split("\\s+");
            int vertex= Integer.valueOf(firstLine[0]); //gets number of people.
            int popularityThinks=Integer.valueOf(firstLine[1]); //gets number of relations.
            PeoplePairGraph peoplePairGraph =new PeoplePairGraph(vertex);
            String line=reader.readLine();
            int i=0;
            while(i<popularityThinks){ //reads all relations.
                String[] parts = line.split("\\s+");
                int first=Integer.valueOf(parts[0]);
                int second =Integer.valueOf(parts[1]);
                EdgeInVertex edgeInVertex=new EdgeInVertex(first,second);
                peoplePairGraph.insert(edgeInVertex); //adds ordered relations in graph.
                line=reader.readLine();
                ++i;

            }
            peoplePairGraph.makeTransitive(); //makes ordered relations as transitive.
           // System.out.println("All popularity thinks with transitive feature: \n" + peoplePairGraph);
             System.out.println("OUTPUT:");
             System.out.println(peoplePairGraph.findPopuler());
            //System.out.println("Number of people who are considered popular by every other person: " + peoplePairGraph.findPopuler());

        }
            catch(Exception ex){
            ex.getStackTrace();
        }

    }
}
