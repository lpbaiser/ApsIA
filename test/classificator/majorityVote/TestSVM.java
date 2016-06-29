/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import classificator.svm.svm_train;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class TestSVM {

    @Test
    public void testSVM() {
        //        <label>  < index1 >:<value1 > <index2>:<value2 > ...
        try {
            InputStream inputStream = getClass().getResourceAsStream("/classificator/majorityVote/arquivoTreino.SVM");
            InputStreamReader insInputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(insInputStreamReader);
            String[] args = {"/data/documents/workspace/ApsIA/test/classificator/majorityVote/arquivoTreino.SVM"};
            svm_train.main(args);
            
        } catch (IOException ex) {
            Logger.getLogger(MajorityVoteClassifierTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
