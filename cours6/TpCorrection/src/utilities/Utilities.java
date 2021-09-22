/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import control.AppCtr;
import control.FileWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Terrain;
//import modele.EvaluationFonciere;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author USER
 */
public class Utilities {

 

    private static final double TAUX_TAXE_MUNICIPALE = 0.025;
    private static final double TAUX_TAXE_SCOLAIRE = 0.012;
    int nbServiceBase = 2;

    public static double calculerTaxeScolaire(double valeurFonciereTotale) {
        double taxeScolaire = valeurFonciereTotale * TAUX_TAXE_SCOLAIRE;
        return taxeScolaire;
    }

    public static double calculerTaxeMunicipale(double valeurFonciereTotale) {
        double taxeMunicipale = valeurFonciereTotale * TAUX_TAXE_MUNICIPALE;
        return taxeMunicipale;
    }

}
