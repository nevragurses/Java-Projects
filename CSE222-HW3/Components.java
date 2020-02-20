import java.io.*;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * This class calculates number of White Components(component of 1's) in a matris.
 */
public class Components {
    /**
     * Private class to keep positions and features of values in matris.
     */
    private static class keepPositions{
        //Data fields.
        private int row;
        private int column;
        private char data;
        private int index;
        keepPositions(int row,int column,char data,int index){
            this.row=row;
            this.column=column;
            this.data=data;
            this.index=index;
        }
        /**
         * Sets a value in matris.
         * @param newVal new value.
         */
        private void setValue(char newVal){
            data=newVal;
        }
        /**Gets row number of an value in matris.
         * @return row number.
         */
        private int getRow(){ return row;}

        /**Gets column number of an value in matris.
         * @return column number.
         */
        private int getColumn() { return column;}
        /**
         * Gets data of an value in matris.
         * @return data.
         */
        private char getData() { return data;}
        /**
         * Gets index of an value in matris.
         * @return index value.
         */
        private int getIndex() { return index;}

        /**toString method.
         * @return all features of an value in matris.
         */
        public String toString() {
            return ("Row :" + row + " Column: " + column + " Value: " + data +" Index: " + index );
        }

    }
    /**
     * This class finds number of white components(component of 1's) in matris.
     */
    public static int  findWhite(String pathOfFile) throws IOException,FileNotFoundException {
        /*Array of keepPositions class to keep features and positions of values in matris.*/
        keepPositions[] arr = new keepPositions[5000];
        /*Stack variable that is keepPositions class type to keep 1's*/
        MyStack<keepPositions> whiteStack = new MyStack<keepPositions>();
        //Reading given matris in file.

        File file = new File(pathOfFile);
        FileInputStream read = new FileInputStream(file);
        DataInputStream read2 = new DataInputStream(read);
        String str = read2.readLine();
        int rowNum = 0, columnNum = 0;
        int j = 0, z = 0, c = 0;
        while (str != null) {
            System.out.println(str);
            for (int i = 0; i < str.length(); i++) {
                if (str.charAt(i) != ' ') {
                    arr[z] = new keepPositions(j, c, str.charAt(i), z);
                    ++c;
                    ++z;
                }
            }
            c = c - 1;
            columnNum = c;
            c = 0;
            j++;
            str = read2.readLine();

        }
        rowNum = j - 1;
        int arrSize = z;
        int componentsNum = 0, in = 0, out, whiteNumber = 0;
        //This loop finds number of white components.
        while (in < arrSize) {
            if (arr[in].getData() == '1') {
                whiteStack.push(arr[in]);
                keepPositions keep = whiteStack.peek();
                arr[in].setValue('0');
                while (whiteStack.isEmpty() == false) {
                    out = 0;
                    keepPositions peek = whiteStack.peek();
                    //Controls right adjacent of peek data of stack if that is 1,push it stack .
                    if ((peek.getIndex() + 1) <= arrSize - 1 && ((peek.getIndex() + 1) % (columnNum + 1)) != 0 && arr[peek.getIndex() + 1].getData() == '1') {
                        whiteStack.push(arr[peek.getIndex() + 1]);
                        arr[peek.getIndex() + 1].setValue('0');
                        out = 1;
                    }
                    //Controls bottom adjacent of peek data of stack if that is 1,push it stack .
                    if ((peek.getIndex() + columnNum + 1) <= arrSize - 1 && arr[peek.getIndex() + columnNum + 1].getData() == '1') {
                        whiteStack.push(arr[peek.getIndex() + columnNum + 1]);
                        arr[peek.getIndex() + columnNum + 1].setValue('0');
                        out = 1;
                    }
                    //Controls top adjacent of peek data of stack if that is 1,push it stack .
                    if ((peek.getIndex() - columnNum - 1) >= 0 && arr[peek.getIndex() - columnNum - 1].getData() == '1') {
                        whiteStack.push(arr[peek.getIndex() - columnNum - 1]);
                        arr[peek.getIndex() - columnNum - 1].setValue('0');
                        out = 1;
                    }
                    //Controls left adjacent of peek data of stack if that is 1,push it stack .
                    if ((peek.getIndex() - 1) >= 0 && arr[peek.getIndex() - 1].getData() == '1') {
                        whiteStack.push(arr[peek.getIndex() - 1]);
                        arr[peek.getIndex() - 1].setValue('0');
                        out = 1;
                    }
                    //If there is no adjacent of a stack, pop it and increase white number of white component.
                    if (out != 1) {
                        whiteStack.pop();
                        ++whiteNumber;
                    }
                }
                whiteNumber = 0;
                in++;
                componentsNum++;
            } else {
                in++;
            }
        }
        return componentsNum;
    }
}
