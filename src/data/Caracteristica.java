/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author romulo
 */
public class Caracteristica {

    private double valor;
    private Tipo tipo;

    public Caracteristica(double valor, Tipo tipo) {
        this.valor = valor;
        this.tipo = tipo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
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
