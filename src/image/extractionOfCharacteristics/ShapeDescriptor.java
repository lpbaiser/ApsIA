package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ShapeDescriptor implements Extractor<Integer> {

    private Image image;

    public Boolean[][] getContourImage() {
        Color[][] imageColors = image.getColors();
        Boolean[][] imageString = new Boolean[imageColors.length][];
        int i = 0, j = 0;
        for (Color[] imageColor : imageColors) {
            imageString[i] = new Boolean[imageColor.length];
            for (Color color : imageColor) {
                if (color.getRGB() != -1) {
                    imageString[i][j] = true;
                }
                if (color.getRGB() == -1) {
                    imageString[i][j] = false;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return imageString;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    public int getPerimetro() {
        int perimetro = 0;
        for (Boolean[] booleans : getContourImage()) {
            for (Boolean aBoolean : booleans) {
                if (aBoolean) {
                    perimetro++;
                }
            }
        }
        return perimetro;
    }

    @Override
    public Integer getCharacteristic() {
        return getPerimetro();
    }

}
