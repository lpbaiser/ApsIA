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
import java.util.HashMap;
import java.util.Map;
import libsvm.svm;

/**
 *
 * @author romulo
 */
public class MajorityVoteClassifier implements Classifier {

    private Conjunto treino;
    private Conjunto teste;
    private final Classifier knn;
    private Confusao confusao;
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
        decisionTree.
    }

    @Override
    public void setConjuntoTeste(Conjunto conjunto) {
        this.teste = (Conjunto) conjunto.clone();
        this.knn.setConjuntoTeste(teste);
    }

    @Override
    public void classify() {
        Classe classifiedAs;
        this.confusao = new Confusao(this.treino.getQuantidadeClasses(), this.teste.getQuantidadeInstancias());
        for (Instancia instancia : teste) {
            classifiedAs = classify(instancia);
            confusao.registrarConfusao(instancia.getClasse(), classifiedAs);
        }
    }

    @Override
    public Classe classify(Instancia instancia) {
        HashMap<Classe, Integer> classeHasVotos;
        Classe classifierClassifyAs;
        Classe classifiedAs;
        Integer votes;
        classeHasVotos = new HashMap<>();
        votes = 0;

        classifierClassifyAs = knn.classify(instancia);

        votes = classeHasVotos.get(classifierClassifyAs);
        if (votes == null) {
            votes = 0;
        }
        votes++;
        classeHasVotos.put(classifierClassifyAs, votes);

        classifiedAs = getMajorityClasse(classeHasVotos);

        return classifiedAs;
    }

    private Classe getMajorityClasse(HashMap<Classe, Integer> classeHasVotos) {
        Map.Entry<Classe, Integer> majority = classeHasVotos.entrySet().iterator().next();
        for (Map.Entry<Classe, Integer> entry : classeHasVotos.entrySet()) {
            if (majority.getValue() < entry.getValue()) {
                majority = entry;
            }
        }
        return majority.getKey();
    }

    @Override
    public Confusao getMatrizConfusao() {
        return this.confusao;
    }

}
