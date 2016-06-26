package extraction.of.characteristics;

import image.Image;
import java.awt.Color;

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ColorExtractor {

    private int bartShirt = 0;
    private int bartShortsAndShoes = 0;
    private int homerBeard = 0;
    private int homerPants = 0;
    private int lisaDressAndMaggiePacifierAndMargeItems = 0;
    private int maggiePijamas = 0;
    private int margeHair = 0;
    private int margeDress = 0;

    public void colorExtractor(Image image) {
        Color[][] imageColors = image.getImage();

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

}
