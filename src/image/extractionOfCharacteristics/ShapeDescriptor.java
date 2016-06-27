package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ShapeDescriptor implements Extractor<String[][]> {

    private Image image;

    public String[][] getContourImage(Image image) {
        Color[][] imageColors = image.getColors();
        String[][] imageString = new String[imageColors.length][];
        int i = 0, j = 0;
        for (Color[] imageColor : imageColors) {
            imageString[i] = new String[imageColor.length];
            for (Color color : imageColor) {
                if (color.getRGB() != -1) {
                    imageString[i][j] = "1";
                }
                if (color.getRGB() == -1) {
                    imageString[i][j] = "0";
                }
                j++;
            }
            j = 0;
            i++;
        }
        return imageString;
    }

    @Override
    public String[][] getCharacteristic() {
        return getContourImage(image);
    }

}
