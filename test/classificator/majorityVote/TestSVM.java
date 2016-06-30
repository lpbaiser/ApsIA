/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import classificator.svm.svm_predict;
import classificator.svm.svm_scale;
import classificator.svm.svm_train;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
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

        String[] argsTrain = {"-s", "1", "-b", "1", "-c", "10", "-g", "16", trainFile.getPath(), modelFile.getPath()};
        svm_train.main(argsTrain);

        String[] argsScale = {trainFile.getPath()};
        svm_scale.main(argsScale);

        String[] argsPredict = {"-b", "1", testFile.getPath(), modelFile.getPath(), "resultado.predict"};
        svm_predict.main(argsPredict);
    }
}
