/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.votoMajoritario;

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
    private KNN knn;

    public MajorityVoteClassifier(Conjunto treino, Conjunto teste) {
        try {
            this.treino = (Conjunto) treino.clone();
            this.knn = new KNN(3, treino);
            this.knn.setConjuntoTeste(teste);
        } catch (Exception ex) {
            Logger.getLogger(MajorityVoteClassifier.class.getName()).log(Level.SEVERE, null, ex);
        }
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
