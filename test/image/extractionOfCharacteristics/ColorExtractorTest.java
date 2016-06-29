package image.extractionOfCharacteristics;

import image.Image;
import java.io.InputStream;
import java.util.Arrays;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorExtractorTest {

    InputStream inputStream = getClass().getResourceAsStream("/Train/bart001.bmp");
    Image image = new Image(inputStream);
    ColorDescriptor colorExtractor = new ColorDescriptor();

//    @Test
    public void testColorExtractor() {
        colorExtractor.setImage(image);
        colorExtractor.getCharacteristic();
    }

    @Test
    public void getHistogramColorGray() {
        colorExtractor.setImage(image);
        System.out.println("Histogram: " + Arrays.toString(colorExtractor.getHistogramColorGray()));
    }

}
