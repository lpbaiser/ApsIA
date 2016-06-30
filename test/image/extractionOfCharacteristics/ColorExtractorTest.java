package image.extractionOfCharacteristics;

import image.Image;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorExtractorTest {

    InputStream inputStream = getClass().getResourceAsStream("/Train/bart001.bmp");
    Image image = new Image(inputStream);
    PredominantColor colorExtractor = new PredominantColor();
    HistogramColorGray histogramColorGray = new HistogramColorGray();

    @Test
    public void testColorExtractor() {
        colorExtractor.setImage(image);
        colorExtractor.getCharacteristic();
    }

    @Test
    public void getHistogramColorGray() {
        histogramColorGray.setImage(image);
        for (Float integer : histogramColorGray.getCharacteristic()) {
            System.out.print(integer + " ");
        }
    }

}
