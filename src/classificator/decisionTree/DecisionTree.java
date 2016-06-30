/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.decisionTree;

import classificator.Classifier;
import classificator.Confusao;
import data.Caracteristica;
import data.Classe;
import data.Conjunto;
import data.Instancia;
import dt.BadDecisionException;
import dt.UnknownDecisionException;
import java.awt.Color;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class DecisionTree implements Classifier {

    private dt.DecisionTree isBart;
    private dt.DecisionTree isMarge;
    private dt.DecisionTree isHomer;
    private dt.DecisionTree isMaggie;
    private dt.DecisionTree isLisa;
    private Conjunto treino;
    private Conjunto teste;
    private String[] attributeNames;

    public DecisionTree(Conjunto treino) throws UnknownDecisionException {
        this.attributeNames = new String[]{"cor"};
        this.treino = (Conjunto) treino.clone();

        isBart = new dt.DecisionTree();
        isBart.setAttributes(attributeNames);

        isMarge = new dt.DecisionTree();
        isMarge.setAttributes(attributeNames);

        isHomer = new dt.DecisionTree();
        isHomer.setAttributes(attributeNames);

        isLisa = new dt.DecisionTree();
        isLisa.setAttributes(attributeNames);

        isMaggie = new dt.DecisionTree();
        isMaggie.setAttributes(attributeNames);

        String[] attributes;
        Caracteristica next;
        Iterator<Caracteristica> iterator;

        for (Instancia instancia : this.treino) {
            iterator = instancia.iterator();
            next = iterator.next();
            attributes = new String[1];
            attributes[0] = next.getValor().toString();
            isBart.addExample(attributes, instancia.getClasse().equals(Classe.BART));
            isHomer.addExample(attributes, instancia.getClasse().equals(Classe.HOMER));
            isLisa.addExample(attributes, instancia.getClasse().equals(Classe.LISA));
            isMarge.addExample(attributes, instancia.getClasse().equals(Classe.MARGE));
            isMaggie.addExample(attributes, instancia.getClasse().equals(Classe.MAGGIE));
        }

    }

    @Override
    public void setConjuntoTeste(Conjunto conjunto) {
        this.teste = (Conjunto) conjunto.clone();
    }

    @Override
    public void classify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Classe classify(Instancia instancia) {
        Map<String, String> data = new HashMap<>();
        String valor = instancia.iterator().next().getValor().toString();
        data.put("cor", valor);
        try {
            if (isBart.apply(data)) {
                return Classe.BART;
            }
            if (isMarge.apply(data)) {
                return Classe.MARGE;
            }
            if (isMaggie.apply(data)) {
                return Classe.MAGGIE;
            }
            if (isLisa.apply(data)) {
                return Classe.LISA;
            }
            if (isHomer.apply(data)) {
                return Classe.HOMER;
            }
        } catch (BadDecisionException ex) {
            return null;
        }
        return null;
    }

    @Override
    public Confusao getMatrizConfusao() {

        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
