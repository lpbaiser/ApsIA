/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import com.sun.org.apache.bcel.internal.classfile.Code;
import data.Caracteristica;
import data.Tipo;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

/**
 *
 * @author romulo
 */
public class Image {

    private final Color[][] image;

    public Image(InputStream inputStream) {
        try {
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            image = new Color[bufferedImage.getWidth()][bufferedImage.getHeight()];
            for (int x = 0; x < bufferedImage.getWidth(); x++) {
                for (int y = 0; y < bufferedImage.getHeight(); y++) {
                    Color c = new Color(bufferedImage.getRGB(x, y));
                    this.image[x][y] = c;
//                    System.out.print(c.getRed());
//                    System.out.print("\t");
//                    System.out.print(c.getGreen());
//                    System.out.print("\t");
//                    System.out.print(c.getBlue());
//                    System.out.print("\t");
//                    System.out.print(c.getAlpha());
//                    System.out.println();
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Falha ao ler imagem");
        }
    }

    public Caracteristica extrairCaracteristica(Tipo tipo) {
        switch (tipo) {
            case CONTRASTE:
                break;
            case COR:
                break;
            case HUG:
                break;
            default:
                return null;
        }
        throw new UnsupportedOperationException();
    }

    public Color[][] getImage() {
        return image;
    }
    
}
