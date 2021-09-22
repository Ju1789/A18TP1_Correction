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

    public static BigDecimal roundingSchoolTax(double valeurTerrain, double tauxTaxeScolaire) {
        double taxeScolaire;
        taxeScolaire = Utilities.calculerTaxeScolaire(valeurTerrain, tauxTaxeScolaire);
        String tScolaire = String.valueOf(Utilities.nombreDecimal(taxeScolaire));
        BigDecimal scolaire = Utilities.arrondirAu5sous(tScolaire).setScale(2, RoundingMode.HALF_UP);
        return scolaire;
    }

    public static BigDecimal roundingLotTotalValue(double valeurTerrain) {
        double valeurTerrainTotal = Utilities.nombreDecimal(valeurTerrain);
        String valeurFonciereTotale = String.valueOf(valeurTerrainTotal);
        BigDecimal terrain = Utilities.arrondirAu5sous(valeurFonciereTotale).setScale(2, RoundingMode.HALF_UP);
        return terrain;
    }

    public static BigDecimal roundingMunicipalTax(double valeurTerrain, double tauxTaxeMunicipale) {
        double taxeMunicipale;
        taxeMunicipale = Utilities.calculerTaxeMunicipale(valeurTerrain, tauxTaxeMunicipale);
        String tMunicipale = String.valueOf(Utilities.nombreDecimal(taxeMunicipale));
        BigDecimal municipale = Utilities.arrondirAu5sous(tMunicipale).setScale(2, RoundingMode.HALF_UP);
        return municipale;
    }
    
}
