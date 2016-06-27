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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class MajorityVoteClassifier implements Classifier {

    private Conjunto treino;
    private Conjunto teste;
    private Classifier knn;

    public MajorityVoteClassifier(Conjunto treino) {
        this.treino = (Conjunto) treino.clone();
        try {
            this.knn = new KNN(3, treino);
        } catch (Exception ex) {
            Logger.getLogger(MajorityVoteClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
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
