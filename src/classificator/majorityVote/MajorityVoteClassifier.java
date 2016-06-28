/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import classificator.Classifier;
import classificator.Confusao;
import classificator.knn.KNN;
import data.Classe;
import data.Conjunto;
import data.Instancia;
import dt.DecisionTree;
import libsvm.svm;

/**
 *
 * @author romulo
 */
public class MajorityVoteClassifier implements Classifier {

    private Conjunto treino;
    private Conjunto teste;
    private final Classifier knn;
    private svm svm;
    private DecisionTree decisionTree;

    public MajorityVoteClassifier(Conjunto treino) {
        this.treino = (Conjunto) treino.clone();
        try {
            this.knn = new KNN(3, treino);
        } catch (Exception ex) {
            throw new RuntimeException("Impossível inicializar jogador");
        }
        svm = new svm();
        decisionTree = new DecisionTree();
    }

    @Override
    public void setConjuntoTeste(Conjunto conjunto) {
        this.teste = (Conjunto) conjunto.clone();
        this.knn.setConjuntoTeste(teste);

    }

    @Override
    public void classify() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Classe classify(Instancia instancia) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Confusao getMatrizConfusao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
