package extraction.of.characteristics;

import image.Image;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorExtractorTest {
    
    @Test
    public void testColorExtractor(){
        InputStream inputStream = getClass().getResourceAsStream("/Train/bart001.bmp");
        Image image = new Image(inputStream);
        ColorExtractor colorExtractor = new ColorExtractor();
        colorExtractor.colorExtractorSimple(image);
    }

}
