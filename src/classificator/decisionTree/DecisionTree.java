/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.decisionTree;

import classificator.Classifier;
import classificator.Confusao;
import data.Classe;
import data.Conjunto;
import data.Instancia;

/**
 *
 * @author romulo
 */
public class DecisionTree implements Classifier {

    private dt.DecisionTree isBart;
    
    @Override
    public void setConjuntoTeste(Conjunto conjunto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
