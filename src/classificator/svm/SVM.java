/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.svm;

import classificator.Classifier;
import classificator.Confusao;
import data.Classe;
import data.Conjunto;
import data.Instancia;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author romulo
 */
public class SVM implements Classifier {

    private final URL trainFile;
    private final URL modelFile;
    private final URL testFile;
    private final URL predictFile;

    public SVM(Conjunto treino, int kernel) throws IOException {
        this.trainFile = getClass().getResource("arquivoTreino.SVM");
        this.modelFile = getClass().getResource("arquivoTreino.SVM.model");
        this.testFile = getClass().getResource("arquivoTest.SVM");
        this.predictFile = getClass().getResource("resultado.predict");
        
        treino.serializar(trainFile.getPath());

        String[] argsScaleTreino = {trainFile.getPath()};
        svm_scale.main(argsScaleTreino);

        String[] argsTrain = {"-s", "1", "-b", String.valueOf(kernel), trainFile.getPath(), modelFile.getPath()};
        svm_train.main(argsTrain);
    }

    @Override
    public void setConjuntoTeste(Conjunto conjunto) {
        try {
            conjunto.serializar(testFile.getPath());
            String[] argsScaleTeste = {testFile.getPath()};
            svm_scale.main(argsScaleTeste);
        } catch (IOException ex) {
            Logger.getLogger(SVM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void classify() {
        try {
            String[] argsPredict = {"-b", "1", testFile.getPath(), modelFile.getPath(), predictFile.getPath()};
            svm_predict.main(argsPredict);
        } catch (IOException ex) {
            Logger.getLogger(SVM.class.getName()).log(Level.SEVERE, null, ex);
        }
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
