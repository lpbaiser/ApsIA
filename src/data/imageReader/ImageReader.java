package data.imageReader;

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

/**
 *
 * @author Leonardo Baiser <lpbaiser@gmail.com>
 */
public class ImageReader {

    private final List<Extractor> extratores;

    public ImageReader() {
        this.extratores = new ArrayList<>();
        extratores.add(new ShapeDescriptor());
        extratores.add(new ColorExtractor());
    }

    public Conjunto parseFolder(String folder) {
        InputStream folderStream = getClass().getResourceAsStream(folder);
        InputStreamReader inputStreamReader = new InputStreamReader(folderStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

        Iterator<String> iteratorNomesArquivos = bufferedReader.lines().iterator();
        Image image;
        String nomeArquivo;
        InputStream inputStreamImage;
        List<Caracteristica> caracteristicas;
        List<Instancia> instancias = new ArrayList<>();

        Instancia instancia;
        while (iteratorNomesArquivos.hasNext()) {
            nomeArquivo = iteratorNomesArquivos.next();
            inputStreamImage = getClass().getResourceAsStream(folder + nomeArquivo);
            Classe classe = null;
            if (nomeArquivo.startsWith("homer")) {
                classe = Classe.HOMER;
            } else if (nomeArquivo.startsWith("bart")) {
                classe = Classe.BART;
            } else if (nomeArquivo.startsWith("lisa")) {
                classe = Classe.LISA;
            } else if (nomeArquivo.startsWith("maggie")) {
                classe = Classe.MAGGIE;
            } else if (nomeArquivo.startsWith("marge")) {
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
        Conjunto conjunto;
        try {
            conjunto = new Conjunto(instancias.toArray(new Instancia[instancias.size()]));
            return conjunto;
        } catch (Exception ex) {
            throw new RuntimeException("Impossible read folder");
        }
    }
}
