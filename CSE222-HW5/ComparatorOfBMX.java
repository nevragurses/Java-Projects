import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.event.DocumentEvent;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * Comparator that compares vectors according to bitmix magnitude.
 * @return comparing result.
 * When I finding bitmix magnitude of a pixel,I used h(x) formula in section 3 of the attached pdf.
 */
public class ComparatorOfBMX implements Comparator<VectorsOfImage> {
    public  int compare(VectorsOfImage left,VectorsOfImage right){
        //to keep every 8bit (color) as binary of each vector.
        int BinaryRedLeft[]=new int[8];
        int BinaryGreenLeft[]=new int[8];
        int BinaryBlueLeft[]=new int[8];

        int BinaryRedRight[]=new int[8];
        int BinaryGreenRight[]=new int[8];
        int BinaryBlueRight[]=new int[8];

        //each color of two pixel converting decimal to binary form.
        convertDecimalToBinary(left.getRed(),BinaryRedLeft,8);
        convertDecimalToBinary(left.getGreen(),BinaryGreenLeft,8);
        convertDecimalToBinary(left.getBlue(),BinaryBlueLeft,8);

        convertDecimalToBinary(right.getRed(),BinaryRedRight,8);
        convertDecimalToBinary(right.getGreen(),BinaryGreenRight,8);
        convertDecimalToBinary(right.getBlue(),BinaryBlueRight,8);

        //to implementing formula,I keep one pixel as 24 bit.
        int totalArrLeft[]= new int[24];
        int totalArrRight[]=new int[24];
        int keep=0,keep2=0;
        for(int k=0;k<24;k++){
            if(k<8) {
                totalArrLeft[k] = BinaryRedLeft[k];
                totalArrRight[k]=BinaryRedRight[k];
            }
            else if(k>=8 && k<16) {
                totalArrLeft[k] = BinaryGreenLeft[keep];
                totalArrRight[k]=BinaryGreenRight[keep];
                ++keep;
            }
            else  if(k>=16 && k<24) {
                totalArrLeft[k] = BinaryBlueLeft[keep2];
                totalArrRight[k]=BinaryBlueRight[keep2];
                ++keep2;
            }
        }
        //I implemented h(x) formula.
        int i,j,l=0;
        double firstLeft=0,secondLeft=0;
        double firstRight=0,secondRight=0;
        double resultLeft=0,resultRight=0;
        for(j=1;j<=8;j++){
            firstLeft= (Math.pow(2,(3*(8-j))));
            firstRight= (Math.pow(2,(3*(8-j))));
            for(i=1;i<=3;i++){
                secondLeft= (( Math.pow(2,(3-i))*totalArrLeft[l]  ))+ secondLeft;
                secondRight=(( Math.pow(2,(3-i))*totalArrRight[l]  ))+ secondRight;
                l=l+8;
            }
            resultLeft=resultLeft + (firstLeft*secondLeft);
            resultRight=resultRight + (firstRight*secondRight);
            secondLeft=0;
            secondRight=0;
            l=l-23;
        }


        //comparing results.
        if(resultLeft>resultRight){
            return 1;
        }
        else if(resultLeft<resultRight){
            return -1;
        }
        else
            return 0;

    }

    /**
     * Converts decimal number to binary form.
     * @param number decimal number.
     * @param arr array that binary form is registed in this.
     * @param size size array.Actually bit number of binary form
     */
    public void  convertDecimalToBinary(int number,int[] arr,int size){
        int i = 0;
        while (number > 0)
        {
            arr[size-1-i] = number % 2;
            number = number / 2;
            i++;
        }

    }
}

