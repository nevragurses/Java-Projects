import javax.swing.table.TableCellEditor;
import java.util.*;

/**
 * This class class keeps word names as key and File_Map class as value.
 */
public class Word_Map implements Map, Iterable
{

    //Data fields.
    final static int INITCAP=5;  //initial capacity
    int CURRCAP = INITCAP;   //current capacity
    final static float LOADFACT = 0.75f;
    private Node table[]; //Node type array that includes elements of hashMap.
    private int sizeKey=0; //size of hashMap.
    private Node  previous; //to links Nodes with each other.
    private int indexFirst; //index of first added element in hashMap.

    public Word_Map() {
        this.table = new Node[INITCAP];
    }

    /**
     * Inner class to keep key and value of  wordMap.
     */

    private static class Node {
        //Data fiels.
        private String key;  //key of hashMap.
        private File_Map value; //value of hashMap.
        private int location; //index of  hashMap.
        private Node next; //one after added element.
        private Node previous; //one before added element
        /**constructor that initilizes all data fields.*/
        private Node(String keyVal, File_Map value, Node next,Node previous, int location) {
            this.key = keyVal;
            this.value = value;
            this.next = next;
            this.previous=previous;
            this.location = location;
        }

        /**constructor  that initilizes data fields.*/
        private Node(String keyVal, File_Map value, int location) {
            this.key = keyVal;
            this.value = value;
            this.next = null;
            this.previous=null;
            this.location = location;
        }

        /**
         *Gets key of hashMap.
         * @return key of hashMap.
         */
        private String getKey() {
            return key;
        }

        /**
         * Gets value of hashMap
         * @return value of Hashmap.
         */

        private File_Map getValue() {
            return value;
        }

        /**
         * Sets value of hashMap.
         * @param value given value.
         */
        public void setValue(File_Map value) {
            this.value=value;
        }

        /**
         * ToString method.
         * @return string that included key and value of hashmap.
         */
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Word :" + key + "\n"  + value    );
            return sb.toString();
        }
    }

    /**
     * Iterator class to keep iterator over Word_Map class.
     */
    public class WorldMapIterator implements Iterator<Node>{
        //Data fiels.
        /**Reference to current node.*/
        Node forward;
        /**Reference for last returned node.*/
        Node last;

        /**Constructor that initilizes references.*/
        public WorldMapIterator(){
            forward=table[indexFirst];
            last=null;

        }
        /**
         * Determine whether next method returns a value.
         * @return  true if  next method returns a value.
         */
        public boolean hasNext(){
            return forward!=null;
        }
        /** Gets the next experiment.If there are no more experiments throws NoSuchElementException.
         *
         * @return the next experiment.
         */
        public Node next(){
            if(forward==null)
                throw new NoSuchElementException();
            last=forward;
            forward=forward.next;
            return last;
        }
    }


    @Override
    /**
     * Returns iterator over the hashMap.
     * @return iterator over the hashMap.
     */
    public Iterator iterator() {
        return new WorldMapIterator();
    }


    @Override
    /**
     * gets size of elements hashMap.
     * @return size of hashMap.
     */
    public int size() {
        return sizeKey;
    }

    /**
     * Determines whether hashMap is empty or not.
     * @return  true if hashMap is empty or false if  hashMap is not empty.
     */
    @Override
    public boolean isEmpty() {
        return (sizeKey==0);
    }

    @Override
    /**
     * Determines whether word_map contains given key or not.
     * I used table index in this method for efficiency.
     * @param key given key
     * @return true if it contains given key or false if not contain.
     */
    public boolean containsKey(Object key) {
        int index=this.location(key); //finds location of key in  table if it occur.
        if(table[index]!=null){
            return true;
        }
        return false;

    }
    @Override
    /**
     * Determines whether word_map contains given value or not.
     * @param value given value.
     * @return whether word_map contains given value or not.
     */
    public boolean containsValue(Object value) {
        Node node = table[indexFirst]; //assigns first element in a node.
        while(node!=null) { //use linked structure.
            if (node.getValue() == value)
                return true;
            node=node.next;

        }
        return false;
    }
    @Override
    /**
     * Gets value of a key.If key is not in word_map returns null.
     * I used index of table in this method because of efficiency.
     * @param key given key.
     * @return value of key.
     */
    public Object get(Object key) {
        int index=location(key); //finds index of key in table.
        if(table[index]==null)
            return null;
        return table[index].getValue();
    }

    /**
     * Finds hashCode of string.
     * @param key given string.
     * @return hashCode of string.
     */
   private int hashCode(String key){
        return key.hashCode();
    }

    /**
     * Finds index of key in hash table.
     * @param key given key.
     * @return index of key.
     */
    private int location(Object key) {
        int location = hashCode((String)key)%CURRCAP;
        //if index is negative,make it positive.
        if(location<0) {
            location = table.length + location;
        }
        int i = location;
        int stop = 0;
        /**If there is a element in index of key,use linear probing to find new index.
         * If there is an key in table that is same this key,exit loop.
         */
        while (table[i] != null && stop == 0) {
            if (table[i].getKey().equals(key)) {
                if (i < 0)
                    i = table.length + i;
                stop=1;

            }
            if(stop==0){
                i = (i + 1) % table.length;
                if (i < 0)
                    i = table.length + i;
            }
        }
        return i;
    }
    @Override
    /**
     * Adds given key and value in word_map.
     * If the map previously contained a mapping for the key, the old value is replaced.
     * This method uses linear probing to place key and value in hash table.
     * @param key given key.
     * @param value given value
     * @return the previous value associated with key, or null if there was no mapping for key.
     */
    public Object put(Object key, Object value) {
        int index=location(key); //finds index of key in table.
        if(table[index]!=null){ //if there is same key in table,replace values.
            File_Map temp= table[index].getValue();
            table[index].setValue((File_Map)value);
            return temp;
        }
        else if(table[index]==null){ //if index is empty,create node and link elements with each other.
            table[index]=new Node((String)key,(File_Map)value,null,previous,index);
            if(sizeKey!=0) {
                table[index].previous.next = table[index];
            }
            if(sizeKey==0){
                indexFirst=index;

            }
            previous=table[index];
            ++sizeKey;
            float loadFact= sizeKey/CURRCAP;
            if(loadFact>LOADFACT){//make reHashing.
                reHashing();
            }

        }
        return null;

    }
    /**
     * Rehashing of table.
     */
    private void reHashing(){
        Node temp[]=table;
        table=new Node[2*CURRCAP];
        CURRCAP=CURRCAP*2;
        previous=null;
        sizeKey=0;

        int i=0;

        Node node= temp[indexFirst];
        while(node!=null) { //elements are placing rehashing table.
            this.put(node.getKey(), node.getValue());
            node = node.next;
        }

    }
    @Override
    /*You do not need to implement remove function
    * */
    public Object remove(Object key) {
        return null;
    }

    @Override
    /**
     * Copies all of the mappings from the specified map to this word_map
     * @param m given map.
     */
    public void putAll(Map m) {
        String[] keyStr =(String[]) m.keySet().toArray(new String[0]);
        File_Map[] file_map=(File_Map[]) m.values().toArray(new File_Map[0]);

        int i=0;
        while(i<m.size()){
            this.put(keyStr[i],file_map[i]);
            ++i;

        }
    }
    @Override
    /**
     * Clears all mappings..
     */
    public void clear() {
        int i=0;
        while(i<table.length) {
            if (table[i] != null)
                table[i] = null;
            ++i;
        }
        sizeKey=0;


    }
    @Override
    /**
     * Creates set that includes the keys of this map.
     * I used linked structure to make this method.
     * @return a set that includes the keys of  this map.
     */
    public Set keySet() {
        Set keySet=new HashSet();
        int i=0;
        Node node= table[indexFirst];
        while(node!=null){
            keySet.add(node.getKey());
            node=node.next;

        }
        return keySet;
    }
    @Override
    /**
     * Creates collection that includes values of word_map.
     * I used linked structure to make  this method.
     * @return Collection that includes values of word_map.
     */
    public Collection values() {
        Collection<File_Map> collectionOverMap;
        ArrayList<File_Map> list=new ArrayList<>();
        Node node=table[indexFirst];
        while(node!=null){
            list.add(node.getValue());
            node=node.next;
        }
        collectionOverMap=list;

        return collectionOverMap;
    }

    @Override
    /*You do not need to implement entrySet function
     * */
    public Set<Entry> entrySet() {
        return null;
    }

    /**ToString method.*/
    public String toString(){
        StringBuilder sb=new StringBuilder();
        Node node=table[indexFirst];
        while(node!=null){
            sb.append(node);
            sb.append("\n");
            node=node.next;

        }
        return sb.toString();


    }
}
