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

    public void colorExtractorSimple() {
        if (image == null) {
            throw new RuntimeException("imagem nula");
        }
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

    private int normalizeCharacteristic(int characteristic) {
        return characteristic / (image.getColors().length * image.getColors()[0].length);
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
