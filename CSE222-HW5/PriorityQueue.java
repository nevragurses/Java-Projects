import jdk.swing.interop.SwingInterOpUtils;
/**
 *  Max priority queues for color pixels.
 *  It compares color pixels according to  different priority schemes.
 */
import java.util.*;
public class PriorityQueue {
    //Data fields.
    /**Volatile because it is using in threads.*/
    private volatile   VectorsOfImage[] arr;
    /**Volatile because it is using in threads.*/
    public volatile int  size;
    /**For comparing color pixels.*/
    Comparator<VectorsOfImage> comp;

    /**No parameter constructor.*/
    public PriorityQueue() {
        arr=new VectorsOfImage[100];
        size = 0;
        comp=null;
    }

    /**
     * Constructor that takes only capacity as parameter.
     * @param capacity of array.
     */
    public PriorityQueue(int capacity){
        arr=new VectorsOfImage[capacity];
        size =0;
        comp=null;
    }

    /**
     * Constructor that initializes all data fields.
     * @param comp given comparator type.
     * @param capacity capacity number of array.
     */
    public PriorityQueue(Comparator<VectorsOfImage> comp,int capacity) {
        arr = new VectorsOfImage[capacity];
        this.comp = comp;
        this.size=size;

    }

    /**
     * Reallocates if current size of array is bigger than capacity.
     */
    private void reallocate(){
        if(size==arr.length){
            VectorsOfImage[] temp=new VectorsOfImage[arr.length*2];
            System.arraycopy(arr, 0,temp , 0, arr.length);
            arr=temp;
        }
    }
    /**
     * Adds color pixels in max priority queue with comparing different priority.
     * @param item color pixel that is adding priority queue.
     * @return true if adding is successful.
     */
    public boolean offer(VectorsOfImage item) {
        reallocate();
        arr[size] = item;
        int indexParent = (size - 1) / 2;
        int indexSmall = size;
        VectorsOfImage parent = arr[indexParent];
        /**
         * controls whether child is bigger than its parent or not.If it is bigger,it is be new parent.
         * This control makes until controlling of root of heap.
         */
        while (comp.compare(arr[indexSmall],arr[indexParent]) > 0 && indexParent >= 0) {
            swapFamily(indexSmall, indexParent);
            indexSmall = indexParent;
            indexParent = (indexSmall - 1) / 2;
        }
        ++size;
        return true;

    }

    /**
     * Exchanges elements of heap.
     * @param indexChild index of child.
     * @param indexParent index of parent.
     */
    private void swapFamily(int indexChild, int indexParent) {
        VectorsOfImage temp = arr[indexParent];
        arr[indexParent] = arr[indexChild];
        arr[indexChild] = temp;

    }

    /**
     * Returns and Removes max priority color pixel from  priority queue.
     * If there is no element in queue,returns null.
     * @return color pixel that is removed.
     */
    public  VectorsOfImage poll(){
        if (size == 0) {
            return null;
        }
        int index = 0;
        VectorsOfImage maxPriority = arr[index];
        int leftIndex = (2 * index) + 1;
        int rightIndex = (2 * index) + 2;
        arr[index] = arr[size - 1];
        while (arr[leftIndex] != null && arr[rightIndex] != null) {
            if (comp.compare(arr[leftIndex],arr[rightIndex]) > 0) {
                swapFamily(index, leftIndex);
                index = leftIndex;
                leftIndex = (2 * index) + 1;
                rightIndex = (2 * index) + 2;
            } else {
                swapFamily(index, rightIndex);
                index = rightIndex;
                rightIndex = (2 * index) + 2;
                leftIndex = (2 * index) + 1;

            }

        }
        --size;
        return maxPriority;

    }

    /**
     * Returns max priority pixel in queue.
     * .If there is no element in queue,throws NoSuchElementException.
     * @return color pixel that is maximum priority.
     * @throws NoSuchElementException if queue is empty.
     */
    public VectorsOfImage element(){
        if(size==0){
            throw  new NoSuchElementException();
        }
        return arr[0];
    }
    /**
     * Returns max priority pixel in queue.
     * .If there is no element in queue,returns null.
     * @return color pixel that is maximum priority.
     */
    public VectorsOfImage peek(){
        if(size==0){
            return null;
        }
        return arr[0];
    }

    /**
     * Returns and removes max priority color pixel in queue.
     * Throws exception if there is no element in queue.
     * @return max priority color pixel in queue
     * @throws NoSuchElementException  if there is no element in queue.
     */
    public VectorsOfImage remove() throws NoSuchElementException{
        if(size==0){
            throw new NoSuchElementException();
        }
        int index = 0;
        VectorsOfImage maxPriority=arr[0];
        int leftIndex = (2 * index) + 1;
        int rightIndex = (2 * index) + 2;
        arr[index] = arr[size - 1];
        while (arr[leftIndex] != null && arr[rightIndex] != null) {
            if (comp.compare(arr[leftIndex], arr[rightIndex]) > 0) {
                swapFamily(index, leftIndex);
                index = leftIndex;
                leftIndex = (2 * index) + 1;
                rightIndex = (2 * index) + 2;
            } else {
                swapFamily(index, rightIndex);
                index = rightIndex;
                rightIndex = (2 * index) + 2;
                leftIndex = (2 * index) + 1;

            }

        }
        --size;
        return maxPriority;

    }

    /**
     * Gets size of queue.
     * @return size of queue.
     */
    public int getSize(){
        return size;
    }

    /**
     * Clears all elements in queue.
     */
    void clear(){
        size=0;
    }

    /**
     * ToString method.
     * @return all elements of queue as string.
     */
    @Override
    public String toString() {
        int i=0;
        StringBuilder str =new StringBuilder();
        while(i<size){
            str.append(arr[i] + " ");
            ++i;
        }
        return str.toString();

    }
}

