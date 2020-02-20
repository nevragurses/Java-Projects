import javax.swing.*;
import java.lang.invoke.StringConcatFactory;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * File map class  that keeps fileNames as key and indexes that are in arraylist as value.
 */
public class File_Map implements Map
{

   private ArrayList<String> fnames; //arraylist to keep all filenames as key of map.
   private ArrayList<List<Integer>> occurances; //index lists as value of map.


    /**
     * Constructor.
     */
   File_Map(){
       fnames=new ArrayList<>();
       occurances=new ArrayList<>();
   }

    /**
     * gets size of File_Map
     * @return size of File_Map
     */
    @Override
    public int size() {
        return fnames.size();
    }

    /**
     * Determines whether file_map is empty or not.
     * @return whether file_map is empty or not.
     */
    @Override
    public boolean isEmpty() {
        if(fnames.size() ==0)
            return true;
        return false;
    }

    @Override
    /**
     * Determines whether file_map contains given key or not.
     * @param key given key.
     * @return whether file_map contains given key or not.
     */
    public boolean containsKey(Object key) {
       int i=0;
       while(i<fnames.size()){
           if(fnames.get(i)==key){
               return true;
           }
           ++i;

       }
       return false;

    }

    @Override
    /**
     * Determines whether file_map contains given value or not.
     * @param value given value.
     * @return whether file_map contains given value or not.
     */
    public boolean containsValue(Object value) {
        int i=0;
        while(i<occurances.size()){
            if(value==occurances.get(i)){
                return true;
            }
            ++i;
        }
        return false;
    }

    @Override
    /**
     * Gets value of a key.If key is not in file_map returns null.
     * @param key given key.
     * @return value of key.
     */
    public Object get(Object key) {
        int index=fnames.indexOf(key);
        if(index==-1){
            return null;
        }
        List<Integer> returnedVal=occurances.get(index);
        return returnedVal;
    }


    @Override
    /**
     * Adds given key and value in file_map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * @param key given key.
     * @param value given value
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public Object put(Object key, Object value) {
        String castedKey=(String)key;
        List<Integer> castedVal=(List<Integer>)value;
        if(fnames.indexOf(castedKey)==-1 ) {
            fnames.add(castedKey);
            occurances.add(castedVal);
        }
        else if(fnames.indexOf(castedKey)!=-1){
            int index=fnames.indexOf(castedKey);
            List<Integer> returned= occurances.get(index);
            occurances.set(index,castedVal);
            return returned;

        }
        return null;
    }


    @Override
    /**
     * Removes the  given key mapping.
     * @param key given key.
     * @return value of deleted key.
     */
    public Object remove(Object key) {
        if(fnames.indexOf((String)key)==-1){
            return null;
        }
        int index=fnames.indexOf((String) key);
        String deletedKey=fnames.remove(index);
        List<Integer> deletedVal=occurances.remove(index);

        return deletedVal;
    }


    @Override
    /**
     * Copies all of the mappings from the specified map to this file_map
     * @param m given map.
     */
    public void putAll(Map m) {
        String[] keyStr =(String[]) m.keySet().toArray(new String[0]);
        List<Integer>  valueStr =new ArrayList<>();
        valueStr.addAll(m.values());
        int i=0;
        while(i<m.size()){
            this.put(keyStr[i],valueStr.get(i));
            ++i;

        }
    }


    @Override
    /**
     * Clears all mappings..
     */
    public void clear() {
        fnames.clear();
        occurances.clear();
    }


    @Override
    /**
     * Creates set that includes the keys of this map.
     * @return a set that includes the keys of  this map.
     */
    public Set keySet() {
        Set keySet=new HashSet();
        int i=0;
        while(i<fnames.size()){
            keySet.add(fnames.get(i));
            ++i;

        }
        return keySet;
    }

    @Override
    /**
     * Creates collection that includes values of file_map.
     * @return Collection that includes values of file_map.
     */
    public Collection values() {
       Collection<List<Integer>> collectionOverMap;
       ArrayList<List<Integer>> list= occurances;
       collectionOverMap=list;

       return collectionOverMap;
    }

    @Override
    /**
     * Creates a set that includes the mappings contained in this map.
     * @return a set that includes the mappings contained in this map.
     */
    public Set<Entry> entrySet() {
        Set<Map.Entry> entry=new HashSet<Map.Entry>();
        int i=0;
        while(i<fnames.size()){
            MyEntry<String,List<Integer>> temp = new MyEntry(fnames.get(i),occurances.get(i));
            entry.add(temp);
            ++i;
        }

        return entry;

    }

    @Override
    /**ToString method*/
    public String toString(){
        StringBuilder sb=new StringBuilder();
        int i=0;
        while(i<fnames.size()) {
            sb.append("TextFile: " + fnames.get(i) + " Index :" + occurances.get(i));
            sb.append("\n");
            ++i;
        }
        return sb.toString();
    }
}
