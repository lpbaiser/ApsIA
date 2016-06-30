/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator;

import JFFT.NumComplex;
import data.Caracteristica;
import data.Instancia;
import java.awt.Color;
import java.util.Iterator;

/**
 *
 * @author romulo
 */
public class Distancia implements Comparable<Distancia> {

    private final Instancia from;
    private double distance;
    private final Instancia to;

    public Distancia(Instancia from, Instancia to) {
        this.from = from;
        this.to = to;
        this.distance = 0;
    }

    public Instancia getFrom() {
        return from;
    }

    public Instancia getTo() {
        return to;
    }

    private void setDistance(double distance) {
        this.distance = distance;
    }

    public void calculateEuclidienDistance() throws Exception {
        Iterator<Caracteristica> iteratorA;
        Iterator<Caracteristica> iteratorB;
        Caracteristica caracteristicaA;
        Caracteristica caracteristicaB;

        iteratorA = this.from.iterator();
        iteratorB = this.to.iterator();

        if (this.from.getQuantidadeCaracteristicas() != this.to.getQuantidadeCaracteristicas()) {
            throw new Exception("Vetores de tamanhos diferentes");
        }
        double sum = 0;
        double valorA = 0;
        double valorB = 0;
        while (iteratorA.hasNext() && iteratorB.hasNext()) {
            caracteristicaA = iteratorA.next();
            caracteristicaB = iteratorB.next();
            if (caracteristicaA.getClass().isPrimitive() && caracteristicaB.getClass().isPrimitive()) {
                valorA = (Double) caracteristicaA.getValor();
                valorB = (Double) caracteristicaB.getValor();
                sum += Math.pow(valorA - valorB, 2);
            } else if (caracteristicaA.getValor() instanceof Integer && caracteristicaB.getValor() instanceof Integer) {
                valorA = (Integer) caracteristicaA.getValor();
                valorB = (Integer) caracteristicaB.getValor();
                sum += Math.pow(valorA - valorB, 2);
            } else if (caracteristicaA.getValor() instanceof Color && caracteristicaB.getValor() instanceof Color) {
                valorA = ((Color) caracteristicaA.getValor()).getRGB();
                valorB = ((Color) caracteristicaB.getValor()).getRGB();
                sum += Math.pow(valorA - valorB, 2);
            } else if (caracteristicaA.getValor() instanceof float[] && caracteristicaB.getValor() instanceof float[]) {
                float[] caracteristicasA = (float[]) caracteristicaA.getValor();
                float[] caracteristicasB = (float[]) caracteristicaB.getValor();
                for (int i = 0; i < caracteristicasA.length && i < caracteristicasB.length; i++) {
                    valorA = caracteristicasA[i];
                    valorB = caracteristicasB[i];
                    sum += Math.pow(valorA - valorB, 2);
                }
            } else if (caracteristicaA.getValor() instanceof NumComplex[] && caracteristicaB.getValor() instanceof NumComplex[]) {
                NumComplex[] caracteristicasA = (NumComplex[]) caracteristicaA.getValor();
                NumComplex[] caracteristicasB = (NumComplex[]) caracteristicaB.getValor();
                for (int i = 0; i < caracteristicasA.length && i < caracteristicasB.length; i++) {
                    valorA = caracteristicasA[i].i;
                    valorB = caracteristicasB[i].i;
                    sum += Math.pow(valorA - valorB, 2);
                    valorA = caracteristicasA[i].r;
                    valorB = caracteristicasB[i].r;
                    sum += Math.pow(valorA - valorB, 2);
                }
            }
        }
        setDistance(Math.sqrt(sum));
    }

    @Override
    public int compareTo(Distancia other) {
        return Double.compare(this.distance, other.distance);
    }

}
