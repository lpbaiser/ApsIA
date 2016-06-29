package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorConvertOp;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorDescriptor implements Extractor<Color> {

    private Image image;
    private int bartShirt;
    private int bartShortsAndShoes;
    private int homerBeard;
    private int homerPants;
    private int lisaDressAndMaggiePacifierAndMargeItems;
    private int maggiePijamas;
    private int margeHair;
    private int margeDress;

    @Override
    public void setImage(Image image) {
        this.image = image;
        this.bartShirt = 0;
        this.bartShortsAndShoes = 0;
        this.homerBeard = 0;
        this.homerPants = 0;
        this.lisaDressAndMaggiePacifierAndMargeItems = 0;
        this.maggiePijamas = 0;
        this.margeHair = 0;
        this.margeDress = 0;
    }

    @Deprecated
    private List<Color[][]> separateImage(Color[][] imageColors) {
        int tam = imageColors.length;
        int parte = imageColors.length / 3;
        Color[][] imageColorsTop = null;
        Color[][] imageColorsMiddle = null;
        Color[][] imageColorsBottom = null;
        List<Color[][]> matrixImageSeparate = new ArrayList<>();
        int linha = 0;
        for (Color[] colors : imageColorsBottom) {
            if (linha < parte) {
                imageColorsTop[linha] = colors;
            } else if (linha >= parte && linha <= parte * 2) {
                imageColorsMiddle[linha] = colors;
            } else {
                imageColorsTop[linha] = colors;
            }
            linha++;
        }

        matrixImageSeparate.add(imageColorsTop);
        matrixImageSeparate.add(imageColorsMiddle);
        matrixImageSeparate.add(imageColorsMiddle);

        return matrixImageSeparate;
    }

    private int normalizeCharacteristic(int characteristic) {
        return characteristic / (image.getColors().length * image.getColors()[0].length);
    }

    /**
     * Obtém a cor predominante desconsiderando a margem de erro implementada em
     * coloExtractorSimple.
     *
     * @return
     */
    public Color getPredominantColor() {
        if (image == null) {
            throw new RuntimeException("imagem nula");
        }

        Integer quantity;
        HashMap<Color, Integer> colorHasCounter = new HashMap<>();

        colorHasCounter.put(new Color(247, 99, 16), 0);//camiseta laranja do Bart
        colorHasCounter.put(new Color(0, 8, 132), 0);//tenis e shorts do Bart
        colorHasCounter.put(new Color(189, 173, 107), 0);//barba do Homer
        colorHasCounter.put(new Color(0, 107, 173), 0);//calça do Homer
        colorHasCounter.put(new Color(255, 0, 0), 0);//vestido da Lisa, chupeta da Maggie e Acessórios da Marge
        colorHasCounter.put(new Color(0, 156, 222), 0);//vestido da Maggie
        colorHasCounter.put(new Color(0, 66, 132), 0);//cabelo da Marge
        colorHasCounter.put(new Color(149, 189, 30), 0);//vestido da Marge
        //R 138 e 160; G 170 e 208; B 20 e 40

        for (Color[] line : image.getColors()) {
            for (Color color : line) {
                for (Map.Entry<Color, Integer> entry : colorHasCounter.entrySet()) {
                    if (compare(entry.getKey(), color) == 0) {
                        quantity = colorHasCounter.get(color);
                        if (quantity != null) {
                            quantity++;
                            colorHasCounter.put(color, quantity);
                        }
                    }
                }
            }
        }
        Map.Entry<Color, Integer> predominantColor = colorHasCounter.entrySet().iterator().next();
        for (Map.Entry<Color, Integer> entry : colorHasCounter.entrySet()) {
            if (entry.getValue() > predominantColor.getValue()) {
                predominantColor = entry;
            }
        }
        return predominantColor.getKey();
    }

    public int[] getHistogramColorGray() {
        Color colors[][] = image.getColors();
        int[] histogram = new int[256];

        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors[i].length; j++) {
                Color c = new Color(colors[i][j].getRGB());
                int grayTone = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                histogram[grayTone]++;
//                c = new Color(grayTone, grayTone, grayTone);
//                colors[i][j] = c;
            }
        }
//        BufferedImage bufferedImage = new BufferedImage(colors.length, colors[0].length, BufferedImage.TYPE_INT_RGB);
//
//        for (int x = 0; x < colors.length; x++) {
//            for (int y = 0; y < colors[x].length; y++) {
//                bufferedImage.setRGB(x, y, colors[x][y].getRGB());
//            }
//        }

//        try {
//            ImageIO.write(bufferedImage, "JPG", new File("/home/leonardo/Dropbox/IA/ApsIA/teste.jpg"));
//        } catch (IOException ex) {
//            Logger.getLogger(ColorDescriptor.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return histogram;
    }

    public int getBartShirt() {
        return normalizeCharacteristic(bartShirt);
    }

    public int getBartShortsAndShoes() {
        return normalizeCharacteristic(bartShortsAndShoes);
    }

    public int getHomerBeard() {
        return normalizeCharacteristic(homerBeard);
    }

    public int getHomerPants() {
        return normalizeCharacteristic(homerPants);
    }

    public int getLisaDressAndMaggiePacifierAndMargeItems() {
        return normalizeCharacteristic(lisaDressAndMaggiePacifierAndMargeItems);
    }

    public int getMaggiePijamas() {
        return normalizeCharacteristic(maggiePijamas);
    }

    public int getMargeHair() {
        return normalizeCharacteristic(margeHair);
    }

    public int getMargeDress() {
        return normalizeCharacteristic(margeDress);
    }

    private int compare(Color gave, Color expected) {
        int tolerance = 20;
        Color base = new Color(gave.getRGB() - tolerance);
        Color topo = new Color(gave.getRGB() + tolerance);
        if (gave.getRGB() < base.getRGB()) {
            return -1;
        } else if (gave.getRGB() > topo.getRGB()) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public Color getCharacteristic() {
        return getPredominantColor();
    }

}