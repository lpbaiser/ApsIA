/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.awt.Color;

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

    @Override
    public String toString() {
        if (valor instanceof Color) {
            Color c = (Color) valor;
            return "" + c.getRGB();
        } else {
            throw new RuntimeException("Método não suporta a classe");
        }
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
