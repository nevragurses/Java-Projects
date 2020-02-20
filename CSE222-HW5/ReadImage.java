import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadImage
{
    //height of image.
    public int heightArr;
    //width of image.
    public int widthArr;
    /**
     * Reads digital image.
     * @param pathOfFile path of file that is digital image in.
     * @return 2D vector that all pixels of image is in.
     * @throws IOException if an io exception occurs.
     * @throws FileNotFoundException if file is not found.
     */
    public VectorsOfImage[][] readImage(String pathOfFile) throws IOException, FileNotFoundException {
        int redColor, greenColor, blueColor;
        BufferedImage buff = ImageIO.read(new File(pathOfFile));
        int height = buff.getHeight();
        int width = buff.getWidth();
        VectorsOfImage[][] readPixel = new VectorsOfImage[buff.getWidth()][buff.getHeight()];
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                //get pixel value
                Color pixel = new Color(buff.getRGB(i, j));
                redColor = pixel.getRed();
                greenColor = pixel.getGreen();
                blueColor = pixel.getBlue();
                readPixel[i][j] = new VectorsOfImage(redColor, greenColor, blueColor);
            }
        }
        heightArr = height;
        widthArr = width;
        return readPixel;
    }












}
