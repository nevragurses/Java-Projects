import java.util.Arrays;

/**
 * My generic ArrayList class.
 * @param <E> any data structure.
 */
public  class MyArrayList<E>  {
    //Data fields.
    /*A generic array to keep values.*/
    private  E[] arr;
    /*Size of ArrayList.*/
    private int size;
    /*Capacity of ArrayList*/
    private int capacity;
    /**
     * No parameter constructor to initilize data fields.
     */
    @SuppressWarnings("unchecked")
    MyArrayList(){
        capacity=100;
        size=0;
        arr=(E[])new Object[capacity];

    }
    /** Adds new value in end of the ArrayList.
     * This method  reallocates if size is bigger than or equal to capacity.
     * @param value The given value.
     * @return Always true because value is added in last of ArrayList.
     */

    public boolean add(E value){
        if(size>=capacity){
            reallocate();
        }
        arr[size] = value;
        size++;
        return true;


    }

    /**Adds new value in given index.
     * This method reallocates if size is bigger than or equal to capacity.
     * This method does shifting from other elements in ArrayList.
     * @param index The given index.
     * @param value The given value that will added in ArrayList
     * @throws IndexOutOfBoundsException if index is bigger than size or smaller than zero.
     */
    public void add(int index,E value) throws IndexOutOfBoundsException {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("FALSE INDEX:" + index);
        }
        if (size >= capacity) {
            reallocate();
        }
        for (int i = size; i > index; i--) {
            arr[i] = arr[i - 1];
        }
        arr[index] = value;
        ++size;

    }
    /**Removes an element from given index;
     * This method does shifting to other elements.
     * @param index The given index.
     * @return Old element in that index.
     *  @throws IndexOutOfBoundsException if index is bigger than or egual to size or smaller than zero.
     */
    public E remove(int index){
        if(index<0||index>=size){
            throw new IndexOutOfBoundsException();
        }
        E temp=arr[index];
        for(int i=index;i<size;i++){
            arr[i]=arr[i+1];

        }
        size--;
        return temp;
    }

    /**
     * Sets the value in given index.
     * @param index The given index.
     * @param value The given value.
     * @return Old value in that index.
     */
    public E set(int index,E value){
        if(index<0||index>size-1){
            throw new IndexOutOfBoundsException();
        }
        E temp=arr[index];
        arr[index]=value;
        return temp;
    }
    /**
     * Gets an element from the given index.
     * @param index The given index.
     * @return element in given index.
     * @throws IndexOutOfBoundsException if index is bigger than or egual to size or smaller than zero.
     */
    public E get(int index) throws IndexOutOfBoundsException{
        if(index<0 ||index >=size){
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }
    /**
     * Reallocates capacity.
     * It increases capacity double.
     */
    @SuppressWarnings("unchecked")
    public void reallocate(){
        capacity=capacity*2;
       arr= Arrays.copyOf(arr,capacity);
    }
    /**
     * Finds index of given element.
     * @param target Given element.
     * @return index of given element.
     */
    public int indexOf(E target){
        int i=0;
        while(i<size){
            if(arr[i].equals(target)){
                return i;
            }
            else{
                i++;
            }
        }
        return -1;
    }
    /**
     * Determines ArrayList is empty or not.
     * @return result of  whether it is empty or not.
     */
    public boolean isEmpty(){
        return size==0;
    }
    /**
     * Gets size of ArrayList.
     * @return size of ArrayList.
     */
    public int size(){
        return size;
    }


    /**
     * Controls whether arraylist include given element or not.
     * @param element given element.
     * @return boolean result.
     */
    @SuppressWarnings("unchecked")
    public boolean contains(E element) {
        if(indexOf(element)==-1)
            return false;
        else
            return true;
    }
    /**
     * Removes all elements from ArrayList.
     */
    public int getCapacity(){
        return capacity;
    }
    public void clear(){
        for(int i=0;i<size;i++)
            arr[i]=null;
        size=0;
    }
    /** toString method.
     * Prints all elements in ArrayList as strings.
     * @return all elements as string.
     */
    public String toString(){
        StringBuilder strBuild = new StringBuilder();
        int i=0;
        while(i<size){
            strBuild.append(arr[i]+" ");
            i++;
        }
        return strBuild.toString();
    }




}


