import jdk.swing.interop.SwingInterOpUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.File;

public class NLP
{
    //Data fields.

    public Word_Map wmap=new Word_Map(); //word map to process nlp.

    private ArrayList<String> arrayList = new ArrayList<>(); //list of all words.
    private Map<String,Integer> list = new HashMap<>(); //map structure to keep file name and word number in this file.
    private int fileNum=0; //number of file.


    /*Reads the dataset from the given dir and created a word map */
    public void readDataset(String dir) throws IOException, FileNotFoundException {
        File directory=new File(dir);
        listFilesForFolder(directory);
    }
    /*Finds all the bigrams starting with the given word*/
    public List<String> bigrams(String word) {
        ArrayList<String> biagramList = new ArrayList<>();
        for(int i=0;i<arrayList.size();++i){
            if(arrayList.get(i).equals(word)) {
               if(arrayList.get(i+1)!=null) {
                   if(!biagramList.contains(arrayList.get(i)+"  "+arrayList.get(i+1))) {
                       biagramList.add(arrayList.get(i)+"  "+arrayList.get(i+1));

                   }
               }
            }
        }
        return biagramList;

    }


    /*Calculates the tfIDF value of the given word for the given file */
     public float tfIDF(String word, String fileName)
    {
        float TF=0;
        double IDF=0;
        float TFIDF=0;
        float numberIncludedFile=0;
        int counter=0;
        if(wmap.containsKey(word)) {
            File_Map file_map = (File_Map) wmap.get(word);
            numberIncludedFile=(float)file_map.size();
            if (file_map.get(fileName) != null) {
                List<Integer> temp = (List<Integer>) file_map.get(fileName);
                float num =(float) temp.size();
                if (list.get(fileName) != null) {
                    float number =(float) list.get(fileName);
                    TF = num / number;
                }
            }


        }
        else if(!wmap.containsKey(word)){
            TF=0;
        }
        float file=(float)fileNum;
        IDF=Math.log((float)fileNum/numberIncludedFile);
        TFIDF=TF*(float)IDF;

        return TFIDF;
    }

    /*Print the WordMap by using its iterator*/
    public  void printWordMap()
    {
        Iterator iter = wmap.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

    /**
     * Finds all files in folder and then call readcontent method and read all files..
     * @param folder  given file.
     * @throws IOException if io exception occurs.
     */
    public  void listFilesForFolder(File folder) throws IOException {
        String fileName=null;
        HashMap<String, ArrayList<String>> dirFiles = new HashMap<String, ArrayList<String>>();
        if(folder.isDirectory()) {
            ArrayList<String> fileNames = new ArrayList<String>();
            for (File fileEntry : folder.listFiles()) {
                if (fileEntry.isDirectory()) {
                    listFilesForFolder(fileEntry);
                    dirFiles.put(folder.getName(), fileNames);
                } else {
                    fileName=fileEntry.getName();
                    readContent(fileEntry.getPath(),fileName); //calling readcontent method.
                    fileNames.add(fileEntry.getPath());
                }
            }
        }
    }

    /**
     * Reads all files.
     * @param str path of file.
     * @param fileName name of file.
     * @throws IOException if ıo exception occur.
     * @throws FileNotFoundException if file not found.
     */
    public  void readContent(String str,String fileName) throws IOException,FileNotFoundException {

        int counter=0;

        BufferedReader reader = new BufferedReader(new FileReader(str));
        String line=reader.readLine();

        while (line != null) {
            String aWord = line.trim().replaceAll("\\p{Punct}", "");
            String[] parts = aWord.split(" ");
            int j=0;
            for(String word: parts ) {
                if(word==null || word.trim().equals("") )
                    continue;
                if (wmap.containsKey(word)) {
                    File_Map added=new File_Map();
                    List<Integer> values= new ArrayList<>();
                    added.put(fileName,values);
                    File_Map temp=(File_Map)wmap.get(word);
                    if(temp.containsKey(fileName)) {//list that is in file map is expanded.
                        List<Integer> collect = (List<Integer>) temp.get(fileName);
                        values.addAll(collect);
                    }
                    values.add(counter);
                    added.put(fileName,values);
                    File_Map returned=((File_Map) wmap.get(word));
                    returned.putAll(added); // value of current key in word map is uploaded.
                    wmap.put(word,returned);
                    counter++;

                } else {
                    File_Map temp=new File_Map();
                    List<Integer> values= new ArrayList<>();
                    values.add(counter);
                    temp.put(fileName,values);
                    wmap.put(word,temp);
                    counter++;

                }
                arrayList.add(word); //to keep all words.


            }
            line = reader.readLine();
        }
        fileNum++;

        list.put(fileName,counter); //to keep file name and number of words in that file.
        reader.close();
    }
}



