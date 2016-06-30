package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ShapeDescriptor implements Extractor<Integer> {

    private Image image;

    private Integer[][] getContourImage() {
        Color[][] imageColors = image.getColors();
        Integer[][] imageString = new Integer[imageColors.length][];

        for (int i = 0; i < imageColors.length; i++) {
            imageString[i] = new Integer[imageColors[i].length];
            for (int j = 0; j < imageColors[i].length; j++) {
                if (imageColors[i][j].getRGB() != -1) {
                    if (hasNeightbor(imageColors, i, j)) {
                        imageString[i][j] = 1;
                    }
                }
            }
        }
        return imageString;
    }

    private Boolean hasNeightbor(Color[][] imageColors, int i, int j) {

        if (j > 0 && imageColors[i][j - 1] != null && imageColors[i][j - 1].getRGB() == -1) {
            return true;
        } else if (imageColors[i].length < (j + 1) && imageColors[i][j + 1] != null && imageColors[i][j + 1].getRGB() == -1) {
            return true;
        } else if (i > 0 && imageColors[i - 1][j] != null && imageColors[i - 1][j].getRGB() == -1) {
            return true;
        } else if (imageColors.length < (i + 1) && imageColors[i + 1][j] != null && imageColors[i + 1][j].getRGB() == -1) {
            return true;
        }
        return false;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    private int getPerimetro() {
        int perimetro = 0;
        for (Integer[] integers : getContourImage()) {
            for (Integer aInteger : integers) {
                if (aInteger != null && aInteger == 1) {
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
