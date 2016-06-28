/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.imageReader;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ImageReaderTest {
    
    public ImageReaderTest() {
    }

    @Test
    public void testSomeMethod() {
        ImageReader imageReader = new ImageReader();
        imageReader.parseFolder("/Train/");
    }
    
}
