/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator;

import data.Classe;
import data.Instancia;

/**
 *
 * @author romulo
 */
public interface Classifier {

    public abstract void classify();

    public abstract Classe classify(Instancia instancia);

    public abstract Confusao getMatrizConfusao();

}
