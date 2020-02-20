import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is a Iterator class to traverse a 2D array as spirally.
 */
public class Traverse2DArray implements Iterator {
    //Data fields.
    /* 2D array.*/
    int arr[][];
    /**row and column number*/
    int row,column;
    /**first row and first column of each recursive part.*/
    int startRow,startColumn;
    int i=0,j=0,count=0;
    /*Total size of elements of array*/
    int size;

    /**
     * Constructor to initilize data fields.
     * @param arr 2D array.
     * @param column Column number.
     * @param row Row number.
     */
    Traverse2DArray(int[][] arr,int column,int row) {
        this.arr=arr;
        this.column=column;
        this.row=row;
        startRow=0;
        startColumn=0;
        size=column*row;
    }

    /**
     * Sets new column number.
     * @param column given column number.
     */
    public void setColumn(int column) {
        this.column=column;
    }
    /**
     * Sets row column number.
     * @param row given column number.
     */
    public void setRow(int row){
        this.row=row;
    }

    /**
     * Controls if there are more elements in array or not.
     * @return  whether  there are more elements in array or not.
     */
    @Override
    public boolean hasNext() {
        return count<size;
    }

    /**Gets the next element of array as spirally.If there are no more experiments throws NoSuchElementException.
     * This is a recursive method.Each traversing out rectangle or square ,this method is called again
     * and  elements of smaller squares or rectangles is returned by recurrence.
     * @return  next element of array as spirally.
     * @throws NoSuchElementException if there are no more elements.
     */
    @Override
    public Integer next() throws NoSuchElementException{
        if(!hasNext()){
            throw new NoSuchElementException();
        }
        else {
            /*Traverse left to right.*/
            if (j == startRow && i < column) {
                int keep = arr[j][i];
                i++;
                count ++;
                return keep;
            }
            /*Traverse top to bottom.*/
            else if (i == column && j + 1 < row) {
                int keep = arr[j + 1][i - 1];
                ++j;
                count ++;
                return keep;
            }
            /*Traverse right to left.*/
            else if (j + 1 == row && i > startColumn + 1) {
                int keep = arr[j][i - 2];
                --i;
                count ++;
                return keep;
            }
            /*Traverse bottom to top.*/
            else if (i == startColumn + 1 && j-1 > startRow) {
                int keep = arr[j - 1][i - 1];
                --j;
                count ++;
                return keep;
            }
            /*After traversing out square or rectangle,new positions and row and column number
              of sub rectangles  is updated.And then this method is called again.
             */
            else {
                startColumn++;
                startRow++;
                setRow(row - 1);
                setColumn(column -1);
                j = startRow;
                i = startColumn;
                return next();
            }
        }
    }

    /**
     * This method provides traversing the given 2D array as
     * spirally clockwise starting at the top left element by calling  iterator as recursive.
     *
     */
    public  int  TraverseWithIterator(){
        if(this.hasNext()==false){
            return 0;
        }
        else{
            System.out.printf("%d ",this.next()," ");
            return TraverseWithIterator();
        }
    }

}

