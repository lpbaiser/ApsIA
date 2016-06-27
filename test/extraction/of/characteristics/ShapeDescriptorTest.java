package extraction.of.characteristics;

import image.Image;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ShapeDescriptorTest {
    
    @Test
    public void testGetContourImage(){
        InputStream inputStream = getClass().getResourceAsStream("/Train/bart004.bmp");
        Image image = new Image(inputStream);
        ShapeDescriptor shapeDescriptor = new ShapeDescriptor();
        String[][] imageString =  shapeDescriptor.getContourImage(image);
        for (String[] strings : imageString) {
            for (String string : strings) {
                System.out.print(string);
            }
            System.out.println();
        }
        
    }

}
