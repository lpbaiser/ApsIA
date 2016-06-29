/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import classificator.Confusao;
import data.Conjunto;
import data.imageReader.ImageReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import libsvm.svm;
import libsvm.svm_model;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class MajorityVoteClassifierTest {

    MajorityVoteClassifier majorityVoteClassifier;

    public MajorityVoteClassifierTest() {
    }

    @Test
    public void testSomeMethod() {
        ImageReader imageReader = new ImageReader();
        Conjunto treino = imageReader.parseFolder("/Train/");
        majorityVoteClassifier = new MajorityVoteClassifier(treino);
        Conjunto test = imageReader.parseFolder("/Valid/");
        majorityVoteClassifier.setConjuntoTeste(test);

        majorityVoteClassifier.classify();

        Confusao matrizConfusao = majorityVoteClassifier.getMatrizConfusao();

        for (int[] is : matrizConfusao.getMatrizConfusao()) {
            for (int i : is) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

}
