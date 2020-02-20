import java.util.Comparator;

/**
 * Comparator that compares L2 norm of vectors
 * @return comparing result.
 */
public class ComparatorOfEUC implements Comparator<VectorsOfImage> {
    public int compare(VectorsOfImage left,VectorsOfImage right){
        double powRedLeft=Math.pow(left.getRed(),2);
        double powGreenLeft=Math.pow(left.getGreen(),2);
        double powBlueLeft=Math.pow(left.getBlue(),2);
        double eucLeft=Math.sqrt(powRedLeft+powGreenLeft+powBlueLeft);

        double powRedRight=Math.pow(right.getRed(),2);
        double powGreenRight=Math.pow(right.getGreen(),2);
        double powBlueRight=Math.pow(right.getBlue(),2);
        double eucRight=Math.sqrt(powRedRight+powGreenRight+powBlueRight);

        if(eucLeft>eucRight){
            return 1;
        }
        else if(eucLeft<eucRight){
            return -1;
        }
        else
            return 0;

    }
}

