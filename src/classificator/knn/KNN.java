/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.knn;

import data.Instancia;
import data.Conjunto;
import data.Classe;
import data.Caracteristica;
import data.Tipo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author romulo
 */
public class KNN {

    private final int k;
    private final Conjunto treino;
    private Conjunto teste;
    private Confusao confusao;
    private final boolean normalizar;

    public KNN(int k, InputStream treinoInputStream, int porcentagem, boolean normalizar) throws Exception {
        this.k = k;
        this.treino = parseInputStream(treinoInputStream);
        this.treino.separarInstancias(porcentagem);
        this.normalizar = normalizar;
        if (this.normalizar) {
            this.treino.normalizarMinMax();
        }
    }

    public KNN(int k, Conjunto treino, int porcentagem, boolean normalizar) throws Exception {
        this.k = k;
        this.treino = (Conjunto) treino.clone();
        this.treino.separarInstancias(porcentagem);
        this.normalizar = normalizar;
        if (this.normalizar) {
            this.treino.normalizarMinMax();
        }
    }

    public void setConjuntoTeste(Conjunto teste) throws Exception {
        this.teste = (Conjunto) teste.clone();
        if (this.normalizar) {
            this.teste.normalizarMinMax();
        }
    }

    public void setConjuntoTeste(InputStream testeInputStream) throws Exception {
        this.teste = parseInputStream(testeInputStream);
        if (this.normalizar) {
            this.teste.normalizarMinMax();
        }
    }

    private Conjunto parseInputStream(InputStream inputStream) throws Exception {
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String strInstancia;
        List<Instancia> instancias;
        String[] strCaracteristicas;
        Caracteristica[] caracteristicas;
        Conjunto conjunto;

        if (inputStream == null) {
            throw new Exception("Arquivo não encontrado");
        }

        conjunto = null;
        instancias = new ArrayList();
        inputStreamReader = new InputStreamReader(inputStream);
        bufferedReader = new BufferedReader(inputStreamReader);
        try {
            while ((strInstancia = bufferedReader.readLine()) != null) {
                strCaracteristicas = strInstancia.replace(" ", "").split(",");

                caracteristicas = new Caracteristica[strCaracteristicas.length - 1];
                for (int i = 0; i < strCaracteristicas.length - 1; i++) {
                    caracteristicas[i] = new Caracteristica(Double.parseDouble(strCaracteristicas[i]), Tipo.CONTRASTE);
                }
                instancias.add(new Instancia(caracteristicas, Classe.BART));
            }
            conjunto = new Conjunto(instancias.toArray(new Instancia[instancias.size()]));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return conjunto;
    }

    public void classify() {
        Classe classifiedAs;

        this.confusao = new Confusao(this.treino.getQuantidadeClasses(), this.teste.getQuantidadeInstancias());

        for (Instancia instanciaTeste : this.teste) {
            classifiedAs = classify(instanciaTeste);
            this.confusao.registrarConfusao(instanciaTeste.getClasse(), classifiedAs);
            instanciaTeste.setClasse(classifiedAs);
        }
    }

    public Classe classify(Instancia instancia) {
        List<Distancia> distancias;
        Distancia distancia;
        int[] votos;

        try {
            distancias = new ArrayList();
            for (Instancia instanciaTreino : this.treino) {
                distancia = new Distancia(instancia, instanciaTreino);
                distancia.calculateEuclidienDistance();
                distancias.add(distancia);

            }
            Collections.sort(distancias);
            votos = new int[this.treino.getQuantidadeClasses()];
            if (distancias.size() <= k) {
                throw new Exception("Vetor de distâncias não preenchido");
            }
            for (int i = 0; i < k; i++) {
                votos[distancias.get(0).getTo().getClasse().toInt()]++;
                distancias.remove(0);
            }
            return Classe.BART;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public Confusao getMatrizConfusao() {
        return confusao;
    }
}
