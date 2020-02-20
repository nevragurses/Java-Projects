import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * This class for  a group of people
 * in which an ordered popularity relation is defined between person pairs.
 */
public class PeoplePairGraph {
    //Data fields.
    /**EdgeInVertex type ArrayList array for ordered popularity relations.*/
    private MyArrayList<EdgeInVertex>[] vertexArr;
    /** number of people.*/
    private int numVertex;

    /**
     * Constructor that initializes data fields.
     * @param vertexNum number of people.
     */
    @SuppressWarnings("unchecked")
    public PeoplePairGraph(int vertexNum) {
        numVertex = vertexNum;
        vertexArr = new MyArrayList[numVertex + 1];
        int i=0;
        while(i<numVertex+1){
            vertexArr[i] = new MyArrayList<EdgeInVertex>();
            ++i;
        }
    }

    /**
     *  Adds a popularity relation in ArrayList array.
     * @param edge the edge that includes source and destination.So, ordered  people pairs.
     */
    public void insert (EdgeInVertex edge){
        if(!vertexArr[edge.getSource()].contains(edge))
            vertexArr[edge.getSource()].add(edge);

    }
    /**
     * Makes popularity relations as transitive.
     * For example if  the relations (P1,P2) and (P2,P3) exist,
     * than (P1,P3) also exist event if it is not specified by the input pairs.
     */
    public void makeTransitive() {
        int i = 0, j = 0;
        while (i < vertexArr.length) {
            if (!vertexArr[i].isEmpty()) {
                while (j < vertexArr[i].size()) {
                        int z=0;
                        if( vertexArr[i].get(j).getDestination()<vertexArr.length &&! vertexArr[vertexArr[i].get(j).getDestination()].isEmpty() ) {
                           while (z < vertexArr[vertexArr[i].get(j).getDestination()].size()) {
                               EdgeInVertex temp=new EdgeInVertex(i,vertexArr[vertexArr[i].get(j).getDestination()].get(z).getDestination());
                               if(!vertexArr[i].contains(temp)) {
                                   vertexArr[i].add(temp);
                               }
                               ++z;
                            }
                        }
                    ++j;
                }
            }
            ++i;
            j=0;
        }
    }
    /**
     *  Number of people who are considered popular by every other person.
     * @return number of people.
     */
    public int findPopuler(){
        int i=0,j=1;
        int counter=0;
        int counterPopuler=0;
        while(i<vertexArr.length){
            while(j<vertexArr.length){
                if(i!=j) {
                    if (vertexArr[j].contains(new EdgeInVertex(j,i))) {
                        counter++;
                    }
                }
                ++j;
            }
            if(counter==numVertex-1 ) {
                counterPopuler++;
            }
            ++i;
            j=0;
            counter=0;

        }
        return counterPopuler;
    }
    /**
     * controls whether  there exist a popularity relation such that  (P1,P2) or not.
     * @param source person who thinks popularity of other person.
     * @param dest given other person who is thought popular.
     * @return   whether  there exist a popularity relation such that  (P1,P2) or not.
     */
    public boolean isEdge(int source, int dest) {
        EdgeInVertex temp=new EdgeInVertex(source,dest);
        return vertexArr[source].contains(temp);
    }
    /**
     * Gets number of people.
     * @return number of people.
     */
    public int getNumVertex() {
        return numVertex;
    }

    /**
     * returns relations in given two people if it is exist.
     * @param source given source people.
     * @param destination given destination people.
     * @return relation if it is exist or null if it is not exist.
     */
    public  EdgeInVertex  getEdge(int source,int destination){
        EdgeInVertex temp=new EdgeInVertex(source,destination);
        if(vertexArr[source].contains(temp)){
            return temp;

        }
        return null;
    }
    /**
     * Gives all peoples who are considered popular by given person.
     * @param source given person.
     * @return  all peoples who are considered popular by given person as ArrayList.
     */
    public MyArrayList<EdgeInVertex> getAdjacent(int source) {
        MyArrayList<EdgeInVertex> temp=new MyArrayList<>();
        for (int i = 0; i < vertexArr.length; i++) {
            if (i==source) {
                temp=vertexArr[i];
            }
        }
        return temp;
    }

    /**
     * gives all ordered popularity relations.
     * @return all ordered popularity relations as string.
     */
    public String toString(){
        StringBuilder str=new StringBuilder();
        int i=0;
        while(i<vertexArr.length ){
            if(!vertexArr[i].isEmpty()) {
                for(int j=0;j<vertexArr[i].size();j++) {
                    str.append(  vertexArr[i].get(j) +" \n");
                }
            }
            ++i;
        }
        return str.toString();
    }
}