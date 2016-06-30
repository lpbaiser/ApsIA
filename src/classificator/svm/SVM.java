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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private BufferedReader bufferedReader;
    private int kernel;
    private String[] resultadoPredictVetor;

    public SVM(Conjunto treino, int kernel) throws IOException {
        this.trainFile = getClass().getResource("/classificator/svm/arquivoTreino.SVM");
        this.modelFile = getClass().getResource("/classificator/svm/arquivoTreino.SVM.model" + kernel);
        this.testFile = getClass().getResource("/classificator/svm/arquivoTest.SVM");
        this.predictFile = getClass().getResource("/classificator/svm/resultado.predict" + kernel);
        this.kernel = kernel;
        treino.serializar(trainFile.getPath());

        String[] argsScaleTreino = {trainFile.getPath()};
        svm_scale.main(argsScaleTreino);
        String[] argsTrain = {"-s", "0", "-b", "1", "-t", String.valueOf(kernel), trainFile.getPath(), modelFile.getPath()};
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
            InputStream inputStream = getClass().getResourceAsStream("/classificator/svm/resultado.predict" + kernel);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);

            bufferedReader = new BufferedReader(inputStreamReader);
            resultadoPredictVetor = bufferedReader.readLine().split(" ");
        } catch (IOException ex) {
            Logger.getLogger(SVM.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Classe classify(Instancia instancia) {
        Classe classe = null;
        try {
            String[] readLine = bufferedReader.readLine().split(" ");
            double maior = Double.MIN_VALUE;
            int index = -1;
            for (int i = 1; i < readLine.length; i++) {
                if (Double.valueOf(readLine[i]) > maior) {
                    maior = Double.valueOf(readLine[i]);
                    index = i;
                }
            }
            classe = Classe.parseInt(Integer.valueOf(resultadoPredictVetor[index]));
        } catch (IOException ex) {
            Logger.getLogger(SVM.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classe;
    }

    @Override
    public Confusao getMatrizConfusao() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
