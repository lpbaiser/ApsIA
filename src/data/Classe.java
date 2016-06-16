/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

/**
 *
 * @author romulo
 */
public enum Classe {
    BART, MARGIE, HOMER, MEGGIE, LIZA;

    public int toInt() {
        switch (this) {
            case BART:
                return 0;
            case MARGIE:
                return 1;
            case HOMER:
                return 2;
            case MEGGIE:
                return 3;
            case LIZA:
                return 4;
            default:
                return -1;
        }
    }
}
