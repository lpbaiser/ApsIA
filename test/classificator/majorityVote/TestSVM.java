/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import classificator.svm.svm_predict;
import classificator.svm.svm_scale;
import classificator.svm.svm_train;
import java.net.URL;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class TestSVM {

    @Test
    public void testSVM() throws Exception {
        URL trainFile;
        URL modelFile;
        URL testFile;
        URL predictFile;

        trainFile = getClass().getResource("/classificator/majorityVote/arquivoTreino.SVM");
        modelFile = getClass().getResource("/classificator/majorityVote/arquivoTreino.SVM.model");
        testFile = getClass().getResource("/classificator/majorityVote/arquivoTeste.SVM");
        predictFile = getClass().getResource("/classificator/majorityVote/resultado.predict");

        String[] argsScaleTeste = {testFile.getPath()};
        svm_scale.main(argsScaleTeste);
        
        String[] argsScaleTreino = {trainFile.getPath()};
        svm_scale.main(argsScaleTreino);
        
        String[] argsTrainKernel1 = {"-s", "1", "-b", "1", trainFile.getPath(), modelFile.getPath()};
        svm_train.main(argsTrainKernel1);
//
//            String[] argsTrainKernel2 = {"-s", "1", "-b", "2", "-c", "10", "-g", "16", trainFile.getPath(), modelFile.getPath()};
//            svm_train.main(argsTrainKernel2);
//
//        String[] argsPredictKernel1 = {"-b", "1", testFile.getPath(), modelFile.getPath(), "resultado.predict"};
//        svm_predict.main(argsPredictKernel1);
//        
//        String[] argsPredictKernel2 = {"-b", "2", testFile.getPath(), modelFile.getPath(), "resultado.predict"};
//        svm_predict.main(argsPredictKernel2);
    }
}
