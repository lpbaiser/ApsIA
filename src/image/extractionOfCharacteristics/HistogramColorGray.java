package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class HistogramColorGray implements Extractor<Color> {

    private Image image;

    public List<Float> getHistogramColorGray() {
        Color colors[][] = image.getColors();
        Integer[] histogram = new Integer[256];
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = 0;
        }

        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                Color c = new Color(colors[i][j].getRGB());
                int grayTone = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                histogram[grayTone]++;
            }
        }
        return normalizeHistogram(histogram, colors.length*colors[0].length);
//return histogram;
    }

    private List<Float> normalizeHistogram(Integer[] histogram, float matrixColorSize) {
        List<Float> normalizeHistogram = new ArrayList<>();
        for (Integer integer : histogram) {
            float normal = integer / matrixColorSize;
            normalizeHistogram.add(normal);
        }
        return normalizeHistogram;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    
    
//    @Override
//    public List<Integer> getCharacteristic() {
//        return getHistogramColorGray();
//    }

    @Override
    public Color getCharacteristic() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
