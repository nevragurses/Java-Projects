import com.sun.source.tree.NewArrayTree;
import jdk.swing.interop.SwingInterOpUtils;

/**
 * This class for all threads.
 */
public class WholeThreads {
    //Data fields.

    /**2DArray of color pixels.*/
    private VectorsOfImage[][] PriorityQ;
    /**Priority Queues that are different  comparision priority.*/
    private PriorityQueue PQEUC;
    private PriorityQueue PQLEX;
    private PriorityQueue PQBMX;
    /**Height of image.*/
    private int height;
    /**Width of image*/
    private int width;
    /**Element number of 2D array.*/
    private int size;
    /**Counter*/
    int counter;

    /**
     * Constructor that initilizes data fields.
     * @param queue 2D array of color pixels.
     * @param height height of image.
     * @param width width of image.
     */
    public WholeThreads(VectorsOfImage[][] queue,int height,int width){
        this.PriorityQ=queue;
        this.height=height;
        this.width=width;
        this.size=this.height*this.width;
        PQEUC =new PriorityQueue(new ComparatorOfEUC(),size);
        PQLEX=new PriorityQueue(new ComparatorOfLEX(),size);
        PQBMX=new PriorityQueue(new ComparatorOfBMX(),size);
    }

    /**
     * This method for thread2.
     * If reading pixel is smaller than 100 wait thread2.
     * Else notify and  remove from PQLEX the color pixels and print them on screen.
     */
    private void lex() {
        synchronized (this) {
            if(counter<100 || (PQLEX.getSize()==0 &&  counter!=size))  {
                try{
                    wait();
                }
                catch (InterruptedException e) { }
            }
            else if(counter>=100) {
                notify();
                System.out.println("Thread2-PQLEX: " + PQLEX.poll());
            }
        }
    }
    /**
     * This method for thread3.
     * If reading pixel is smaller than 100 wait thread3.
     * Else notify and  remove from PQEUC the color pixels and print them on screen.
     */
    private void euc(){
        synchronized (this) {
            if(counter<100 || (PQEUC.getSize() ==0 && counter!=size)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            else if(counter>=100){
                notify();
                System.out.println("Thread3-PQEUC: " + PQEUC.poll());
            }
        }
    }
    /**
     * This method for thread4.
     * If reading pixel is smaller than 100 wait thread4.
     * Else notify and  remove from PQEUC the color pixels and print them on screen.
     */
    private void bmx(){
        synchronized (this) {
            if(counter<100 || (PQBMX.getSize()==0 && counter!=size)) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            else if(counter>=100) {
                notify();
                System.out.println("Thread4-PQBMX: " + PQBMX.poll());
            }

        }
    }

    /**
     * This method for starting all threads.
     * Thread1 responsible for reading the pixels.
     * Every color pixel,will be inserted to each of the 3 priority queues.
     * As soon as the first 100 pixels are inserted, it will create and start the following 3 threads.
     * Then continue inserting the remaining pixels.
     * Thread2 will remove from PQLEX the color pixels and print them on screen with helper method lex.
     * Thread3  will remove from PQEUC the color pixels and print them on screen with helper method euc.
     * Thread4  will remove from PQBMX the color pixels and print them on screen with helper method bmx.
     */
    public void doThreads() {
        Thread number1 = new Thread(new Runnable() {
            public void run() {
                counter = 1;
                int i = 0, j = 0;
                while (i < width ) {
                    while (j < height ) {
                        synchronized (this) {
                            System.out.println("Thread 1: " + PriorityQ[i][j]);
                            PQEUC.offer(PriorityQ[i][j]);
                            PQBMX.offer(PriorityQ[i][j]);
                            PQLEX.offer(PriorityQ[i][j]);
                        }
                        Thread number2 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                lex();
                            }
                        });
                        Thread number3 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                euc();
                            }
                        });
                        Thread number4 = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                bmx();
                            }
                        });
                        number2.start();
                        number3.start();
                        number4.start();
                        ++j;
                        counter++;


                    }
                    ++i;
                    j = 0;


                }
            }
        });
        number1.start();
    }
}





















