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

    static double prixTerrainMin;
    static double prixTerrainMax;
    static double prixTerrainMoyenne;
    static String description;
    static int typeTerrain;
    static double superficie;
    static int nbDroitPassage;
    static double montantServiceMin;
    static double montantServiceMax;
    static double taxeMunicipale;
    static double taxeScolaire;

    



    int nbServiceBase = 2;
    static double tauxTaxeMunicipale = 0.025;
    static double tauxTaxeScolaire = 0.012;
    static double montantFixe = 733.77;
    static double montantPassageBase = 500.00;
    static double valeurFonciere = 0;
    static double montantService = 0;
    static double droitPassage = 0;
    static double valeurLot = 0;
    static double valeurFonciere3 = 0;
    static double valeurFonciere1 = 0;
    static double valeurFonciere2 = 0;
    static double valeurFonciereHT;
    static double valeurFonciereTotale =0;


   
   
public static double calculerTaxeScolaire(double valeurFonciereTotale, double tauxTaxeScolaire) {
        double taxeScolaire = Math.round(valeurFonciereTotale * tauxTaxeMunicipale);
        return taxeScolaire;
    }

    public static double calculerTaxeMunicipale(double valeurFonciereTotale, double tauxTaxeScolaire) {
        double taxeMunicipale = Math.round(valeurFonciereTotale * tauxTaxeScolaire);
        return taxeMunicipale;
    }
   
  
    public static double nombreDecimal(double nb) {
        double nombre = (Math.round(nb * 100)) / 100.00;
        return nombre;
    }

    public static BigDecimal arrondirAu5sous(String nb) {
        BigDecimal amount = new BigDecimal(nb);

        BigDecimal resultat = new BigDecimal(Math.ceil(amount.doubleValue() * 20) / 20);

        return resultat;
    }


    public static BigDecimal arrondirTaxeScolaire() {
        double tScolaire = Utilities.nombreDecimal(Utilities.calculerTaxeScolaire(valeurFonciereTotale, tauxTaxeScolaire));
        BigDecimal scolaire = Utilities.arrondirAu5sous(String.valueOf(tScolaire));
        BigDecimal taxeScolaireLot = scolaire.setScale(2, RoundingMode.HALF_UP);
        return taxeScolaireLot;
    }

  

    public static BigDecimal arrondirTaxeMunicipale() {
        double tMunicipale = Utilities.calculerTaxeMunicipale(valeurFonciereTotale, tauxTaxeScolaire);
        BigDecimal municipale = Utilities.arrondirAu5sous(String.valueOf(tMunicipale));
        //System.out.println("BigDecimal municipale: " + municipale);
        BigDecimal taxeMunicipaleLot = municipale.setScale(2, RoundingMode.HALF_UP);
        return taxeMunicipaleLot;
    }



    

}
