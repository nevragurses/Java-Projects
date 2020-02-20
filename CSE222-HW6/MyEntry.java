import javax.swing.*;
import java.util.Map;

public class MyEntry<K,V> implements Map.Entry<K,V> {
    private K keyValue;
    private V value;
    MyEntry(K key,V val){
        this.keyValue=key;
        this.value=val;

    }
    MyEntry(){
        keyValue=null;
        value=null;
    }

   public K getKey(){
        return keyValue;


   }
   public V getValue(){
        return value;

   }
   public int hashCode(){
        return keyValue.hashCode();
   }
   public boolean equals(Object val){
        MyEntry entry;
        entry=(MyEntry) val;
        return ((entry.getKey() == keyValue) && (entry.getValue() == value));

   }

   public V setValue(V value) {
        V temp=this.value;
        this.value=value;
        return temp;
    }
   public String toString(){
        StringBuilder str = new StringBuilder();
        str.append(keyValue + " : " +value);
        return str.toString();

   }

}





