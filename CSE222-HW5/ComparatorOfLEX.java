import java.util.Comparator;

/**
 * Comparator that compares vectors(colors) with standard lexicographical comparison from discrete math.
 * If amount of red color of left vector bigger than other returns 1.
 * If amount red color  of left vector equal to other vector,Controls green  color.
 * If amount red and green of left vector equal to other vector,Controls blue  color.
 * @return comparing result.
 */
public class ComparatorOfLEX  implements Comparator <VectorsOfImage> {
    public   int compare(VectorsOfImage left,VectorsOfImage right){
        if(left.getRed()> right.getRed()){
            return 1;
        }
        else if(left.getRed() == right.getRed() && left.getGreen() > right.getGreen()){
            return 1;
        }
        else if(left.getRed()==right.getRed() && left.getGreen()==right.getGreen() && left.getBlue()>right.getBlue()){
            return 1;
        }
        else if(left.getRed()==right.getRed() && left.getGreen()==right.getGreen() && left.getBlue()==right.getBlue()){
            return 0;
        }
        else {
            return -1;
        }


    }
}

