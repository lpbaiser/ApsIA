/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import dt.BadDecisionException;
import dt.DecisionTree;
import dt.UnknownDecisionException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author romulo
 */
public class TestDT {

    @Test
    public void test() {
        DecisionTree dt = new DecisionTree();
        dt.setAlgorithm(algorithm);
        String[] attributeNames = {"cor", "perimetro"};

        dt.setAttributes(attributeNames);
        HashMap<String, String> hashMap;
        boolean apply;
        try {
            hashMap = new HashMap<>();
            hashMap.put("cor", "vermelha");
            hashMap.put("perimetro", "rosa");
            dt.addExample(hashMap, true);
            
            apply = dt.apply(hashMap);
            Assert.assertTrue(apply);
            
            hashMap = new HashMap<>();
            hashMap.put("cor", "preta");
            hashMap.put("perimetro", "rosa");
            dt.addExample(hashMap, false);

            hashMap = new HashMap<>();
            hashMap.put("cor", "preta");
            hashMap.put("perimetro", "rosa");
            
            apply = dt.apply(hashMap);
            Assert.assertFalse(apply);
        } catch (UnknownDecisionException ex) {
            Logger.getLogger(TestDT.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadDecisionException ex) {
            Logger.getLogger(TestDT.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
