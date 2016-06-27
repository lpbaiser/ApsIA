/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classificator.majorityVote;

import data.Caracteristica;
import data.Classe;
import data.Conjunto;
import data.Instancia;
import data.Tipo;
import image.Image;
import image.extractionOfCharacteristics.ColorExtractor;
import image.extractionOfCharacteristics.Extractor;
import image.extractionOfCharacteristics.ShapeDescriptor;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        InputStream inputStream = getClass().getResourceAsStream("/Train/");
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Iterator<String> iteratorNomesArquivos = bufferedReader.lines().iterator();
        Image image;
        String nomeArquivo;
        InputStream inputStreamImage;
        List<Caracteristica> caracteristicas;
        List<Instancia> instancias = new ArrayList<>();
        List<Extractor> extratores = new ArrayList<>();
        extratores.add(new ShapeDescriptor());
        extratores.add(new ColorExtractor());

        Instancia instancia;
        while (iteratorNomesArquivos.hasNext()) {
            nomeArquivo = iteratorNomesArquivos.next();
            inputStreamImage = getClass().getResourceAsStream("/Train/" + nomeArquivo);
            Classe classe = null;
            if (nomeArquivo.contains("homer")) {
                classe = Classe.HOMER;
            } else if (nomeArquivo.contains("bart")) {
                classe = Classe.BART;
            } else if (nomeArquivo.contains("lisa")) {
                classe = Classe.LISA;
            } else if (nomeArquivo.contains("maggie")) {
                classe = Classe.MAGGIE;
            } else if (nomeArquivo.contains("marge")) {
                classe = Classe.MARGE;
            }

            image = new Image(inputStreamImage);
            caracteristicas = new ArrayList<>();
            for (Extractor extrator : extratores) {
                extrator.setImage(image);
                Tipo tipo = null;
                if (extrator instanceof ColorExtractor) {
                    tipo = Tipo.COR;
                } else if (extrator instanceof ShapeDescriptor) {
                    tipo = Tipo.PERIMETER_OF_BORDER;
                }
                caracteristicas.add(new Caracteristica(extrator.getCharacteristic(), tipo));
            }

            instancia = new Instancia(caracteristicas.toArray(new Caracteristica[caracteristicas.size()]), classe);
            instancias.add(instancia);
        }
        Conjunto treino;
        try {
            treino = new Conjunto(instancias.toArray(new Instancia[instancias.size()]));
            Conjunto teste;
            majorityVoteClassifier = new MajorityVoteClassifier(treino);
        } catch (Exception ex) {
            Logger.getLogger(MajorityVoteClassifierTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
