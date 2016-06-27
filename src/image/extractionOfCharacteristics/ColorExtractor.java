package image.extractionOfCharacteristics;

import image.Image;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorExtractor implements Extractor<Color> {

    private Image image;
    private int bartShirt = 0;
    private int bartShortsAndShoes = 0;
    private int homerBeard = 0;
    private int homerPants = 0;
    private int lisaDressAndMaggiePacifierAndMargeItems = 0;
    private int maggiePijamas = 0;
    private int margeHair = 0;
    private int margeDress = 0;

    public ColorExtractor() {
    }

    public ColorExtractor(Image image) {
        this.image = image;
    }

    //resetar contadores ou criar contrutor
    public void colorExtractorSimple(Image image) {
        Color[][] imageColors = image.getColors();

        for (Color[] color : imageColors) {
            for (Color c : color) {
                if ((c.getRed() == 247) && (c.getGreen() == 99) && (c.getBlue() == 16)) {//camiseta laranja do Bart
                    this.bartShirt++;
                } else if ((c.getRed() == 0) && (c.getGreen() == 8) && (c.getBlue() == 132)) {//tenis e shorts do Bart
                    this.bartShortsAndShoes++;
                } else if ((c.getRed() == 189) && (c.getGreen() == 173) && (c.getBlue() == 107)) { //barba do Homer
                    this.homerBeard++;
                } else if ((c.getRed() == 0) && (c.getGreen() == 107) && (c.getBlue() == 173)) {//calça do Homer
                    this.homerPants++;
                } else if ((c.getRed() == 255) && (c.getGreen() == 0) && (c.getBlue() == 0)) {//vestido da Lisa, chupeta da Maggie e Acessórios da Marge
                    this.lisaDressAndMaggiePacifierAndMargeItems++;
                } else if ((c.getRed() == 0) && (c.getGreen() == 156) && (c.getBlue() == 222)) {//vestido da Maggie
                    this.maggiePijamas++;
                } else if ((c.getRed() == 0) && (c.getGreen() == 66) && (c.getBlue() == 132)) {//cabelo da Marge
                    this.margeHair++;
                } else if ((c.getRed() > 138 && c.getRed() < 160) && (c.getGreen() > 170 && c.getGreen() < 208) && (c.getBlue() > 20 && c.getBlue() < 40)) { //vestido da Marge
                    this.margeDress++;
                }
            }
        }

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

    public int getBartShirt() {
        return bartShirt;
    }

    public int getBartShortsAndShoes() {
        return bartShortsAndShoes;
    }

    public int getHomerBeard() {
        return homerBeard;
    }

    public int getHomerPants() {
        return homerPants;
    }

    public int getLisaDressAndMaggiePacifierAndMargeItems() {
        return lisaDressAndMaggiePacifierAndMargeItems;
    }

    public int getMaggiePijamas() {
        return maggiePijamas;
    }

    public int getMargeHair() {
        return margeHair;
    }

    public int getMargeDress() {
        return margeDress;
    }

    /**
     * Obtém a cor predominante desconsiderando a margem de erro implementada em
     * coloExtractorSimple.
     *
     * @return
     */
    public Color getPredominantColor() {
        Integer quantity;
        HashMap<Color, Integer> colorHasCounter = new HashMap<>();
        for (Color[] line : image.getColors()) {
            for (Color color : line) {
                quantity = colorHasCounter.get(color);
                if (quantity == null) {
                    quantity = 0;
                }
                quantity++;
                colorHasCounter.put(color, quantity);
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

    @Override
    public Color getCharacteristic() {
        return getPredominantColor();
    }

}
