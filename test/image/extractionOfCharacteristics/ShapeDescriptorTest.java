package image.extractionOfCharacteristics;

import image.Image;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ShapeDescriptorTest {

    @Test
    public void testGetContourImage() {
        InputStream inputStream = getClass().getResourceAsStream("/Train/bart004.bmp");
        Image image = new Image(inputStream);
        ShapeDescriptor shapeDescriptor = new ShapeDescriptor();
        shapeDescriptor.setImage(image);

        System.out.println("Perimetro: " + shapeDescriptor.getCharacteristic());
    }

}
