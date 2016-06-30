/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import JFFT.NumComplex;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author romulo
 */
public class Caracteristica<T> {

    private T valor;
    private Tipo tipo;

    public Caracteristica(T valor, Tipo tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public String[] toStringArray() {
        List<String> componentes = new ArrayList<>();
        if (valor instanceof Color) {
            Color c = (Color) valor;
            componentes.add(String.valueOf(c.getRed()));
            componentes.add(String.valueOf(c.getGreen()));
            componentes.add(String.valueOf(c.getBlue()));
        } else if (valor instanceof Integer) {
            componentes.add(String.valueOf(valor));
        } else if (valor instanceof float[]) {
            float[] caracteristicas = (float[]) valor;
            for (float caracteristica : caracteristicas) {
                componentes.add(String.valueOf(caracteristica));
            }
        } else if (valor instanceof NumComplex[]) {
            NumComplex[] caracteristicas = (NumComplex[]) valor;
            for (NumComplex caracteristica : caracteristicas) {
                componentes.add(String.valueOf(caracteristica.getImaginario()));
                componentes.add(String.valueOf(caracteristica.getReal()));
            }
        } else {
            throw new RuntimeException("Método não suporta a classe");
        }
        return componentes.toArray(new String[componentes.size()]);
    }

    public T getValor() {
        return valor;
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public void print() {
        System.out.print(this.valor + " ");
    }

    @Override
    public Object clone() {
        return new Caracteristica(this.valor, this.tipo);
    }

}
