/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author USER
 */
public class Rounding {

    public static BigDecimal roundingSchoolTax(double valeurTerrain) {
        double taxeScolaire;
        taxeScolaire = Utilities.calculerTaxeScolaire(valeurTerrain);
        String tScolaire = String.valueOf(nombreDecimal(taxeScolaire));
        BigDecimal scolaire = arrondirAu5sous(tScolaire).setScale(2, RoundingMode.HALF_UP);
        return scolaire;
    }

    public static BigDecimal roundingLotTotalValue(double valeurTerrain) {
        double valeurTerrainTotal = nombreDecimal(valeurTerrain);
        String valeurFonciereTotale = String.valueOf(valeurTerrainTotal);
        BigDecimal terrain = arrondirAu5sous(valeurFonciereTotale).setScale(2, RoundingMode.HALF_UP);
        return terrain;
    }

    public static BigDecimal roundingMunicipalTax(double valeurTerrain) {
        double taxeMunicipale;
        taxeMunicipale = Utilities.calculerTaxeMunicipale(valeurTerrain);
        String tMunicipale = String.valueOf(nombreDecimal(taxeMunicipale));
        BigDecimal municipale = arrondirAu5sous(tMunicipale).setScale(2, RoundingMode.HALF_UP);
        return municipale;
    }

    public static double nombreDecimal(double nb) {
        double nombre = (Math.round(nb * 100)) / 100.0;
        return nombre;
    }

    public static BigDecimal arrondirAu5sous(String nb) {
        BigDecimal amount = new BigDecimal(nb);
        BigDecimal resultat = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20);
        return resultat;
    }
    
}
