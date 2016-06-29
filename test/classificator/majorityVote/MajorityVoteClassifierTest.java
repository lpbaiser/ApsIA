/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import data.Conjunto;
import data.imageReader.ImageReader;
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
    }

}