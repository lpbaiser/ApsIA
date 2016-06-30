/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.imageReader;

import data.Conjunto;
import java.io.IOException;
import org.junit.Test;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ImageReaderTest {
    
    public ImageReaderTest() {
    }

    @Test
    public void testSomeMethod() throws IOException {
        ImageReader imageReader = new ImageReader();
        Conjunto parseFolder = imageReader.parseFolder("/Train/");
        parseFolder.serializar("testeSerializacao.teste");
    
    }
    
}
