/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author romulo
 */
public class Image {

    public void readImage(InputStream inputStream) {
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color c = new Color(bufferedImage.getRGB(x, y));
                    System.out.print(c.getRed());
                    System.out.print("\t");
                    System.out.print(c.getGreen());
                    System.out.print("\t");
                    System.out.print(c.getBlue());
                    System.out.print("\t");
                    System.out.print(c.getAlpha());
                    System.out.println();
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Image.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
