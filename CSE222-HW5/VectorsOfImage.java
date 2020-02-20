import com.sun.source.tree.ReturnTree;

import javax.print.DocFlavor;

/**
 *  This class holds features of every pixel.Such that;
 *  amount of red color,blue color,green color.
 */
public class VectorsOfImage {
    //Data fields.
    int Red;
    int Green;
    int Blue;

    /**
     * No parameter constructor.
     */
    VectorsOfImage(){
        Red=0;
        Green=0;
        Blue=0;

    }

    /**
     * Constructor that initializes  data fields.
     * @param red amount of red color.
     * @param green amount of green color.
     * @param blue amount of blue color.
     */
    VectorsOfImage(int red, int green, int blue){
        this.Red=red;
        this.Blue=blue;
        this.Green=green;
    }

    /**
     * Gets amount of red color of pixel.
     * @return amount of red color.
     */
    public int getRed() {

        return Red;
    }

    /**
     * Gets amount of green color of pixel.
     * @return amount of green color.
     */
    public int getGreen(){
        return Green;
    }

    /**
     * Gets amount of blue color of every pixel.
     * @return amount of blue color.
     */
    public int getBlue(){
        return Blue;
    }

    /**
     * Overrided toString method.
     * @return features of pixels as string.
     */
    @Override
    public String toString(){
        String str="[ " + Red +" , " + Green + " , " + Blue + " ]";
        return str;
    }

}

