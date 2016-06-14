/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import image.Image;
import java.io.InputStream;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class ImageTest {

    @Test
    public void testReadImage() {
        Image image = new Image();
        InputStream inputStream = getClass().getResourceAsStream("/Train/bart001.bmp");
        image.readImage(inputStream);
    }
}
