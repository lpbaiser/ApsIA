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
    BART, HOMER, LISA, MAGGIE, MARGE;

    public int toInt() {
        switch (this) {
            case BART:
                return 0;
            case MARGE:
                return 1;
            case HOMER:
                return 2;
            case MAGGIE:
                return 3;
            case LISA:
                return 4;
            default:
                return -1;
        }
    }

    public static Classe parseInt(int classe) {
        switch (classe) {
            case 0:
                return BART;
            case 1:
                return MARGE;
            case 2:
                return HOMER;
            case 3:
                return MAGGIE;
            case 4:
                return LISA;
            default:
                return null;
        }
    }
}
