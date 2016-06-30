package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class HistogramColorGray implements Extractor<float[]> {

    private Image image;

    private float[] getHistogramColorGray() {
        Color colors[][] = image.getColors();
        float[] histogram = new float[256];

        for (Color[] color : colors) {
            for (Color c : color) {
                int grayTone = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                histogram[grayTone]++;
            }
        }
        return normalizeHistogram(histogram, colors.length * colors[0].length);
    }

    private float[] normalizeHistogram(float[] histogram, float matrixColorSize) {
        for (int i = 0; i < histogram.length; i++) {
            float normal = histogram[i] / matrixColorSize;
            histogram[i] = normal;
        }
        return histogram;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public float[] getCharacteristic() {
        return getHistogramColorGray();
    }

}
