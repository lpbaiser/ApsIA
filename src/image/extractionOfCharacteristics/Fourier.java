/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image.extractionOfCharacteristics;

import JFFT.FFT;
import JFFT.NumComplex;
import image.Image;

/**
 *
 * @author romulo
 */
public class Fourier implements Extractor<NumComplex[]> {

    private Image image;
    private FFT fft;
    private HistogramColorGray histogramColorGray;

    public Fourier() {
        this.histogramColorGray = new HistogramColorGray();
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
        histogramColorGray.setImage(this.image);
    }

    @Override
    public NumComplex[] getCharacteristic() {
        double[] histogram = new double[256];
        float[] characteristic = histogramColorGray.getCharacteristic();
        for (int i = 0; i < histogram.length; i++) {
            histogram[i] = characteristic[i];

        }
        fft = new FFT(histogram);
        return fft.getFFT();
    }

}
