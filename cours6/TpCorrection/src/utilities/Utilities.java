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
import modele.EvaluationFonciere;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author USER
 */
public class Utilities {


    public static double calculValeurFonciereType2(Double superficie, int nbDroitPassage, int nbServices) {

        EvaluationFonciere.setPrixSuperficie((superficie * EvaluationFonciere.getPrixTerrainMax()) + EvaluationFonciere.MONTANT_FIXE);
        //rajouter formule pour arrondir a 0.5 pres;
        EvaluationFonciere.setDroitPassage(EvaluationFonciere.MONTANT_PASSAGE_BASE - (nbDroitPassage * (EvaluationFonciere.TAUX_DROIT_PASSAGE_TYPE2 * EvaluationFonciere.getPrixSuperficie())));
        EvaluationFonciere.setValeurFonciere3(EvaluationFonciere.getPrixSuperficie() + EvaluationFonciere.getDroitPassage());
        calculMontantServiceType2(superficie, nbServices);
        EvaluationFonciere.setValeurFonciere3(EvaluationFonciere.getValeurFonciere3() + EvaluationFonciere.getMontantService());
        return nombreDecimal(EvaluationFonciere.getValeurFonciere3());
    }

    private static void calculMontantServiceType2(Double superficie1, int nbServices) {
        EvaluationFonciere.setNbServicesTotal(calculTotatNbService());
        if (superficie1 <= 500) {
            EvaluationFonciere.setMontantService(EvaluationFonciere.MONTANT_SERVICE_TYPE2_INF500 * EvaluationFonciere.getNbServicesTotal());
        } else if (superficie1 > 500) {
            if (nbServices < 4) {
                EvaluationFonciere.setMontantService(EvaluationFonciere.MONTANT_MIN_SERVICE_TYPE2 * EvaluationFonciere.getNbServicesTotal());
            } else {
                EvaluationFonciere.setMontantService(EvaluationFonciere.MONTANT_MAX_SERVICE_TYPE2);
            }
        }
    }

    public static double calculValeurFonciereType1(Double superficie, int nbDroitPassage, int nbServices) {

        EvaluationFonciere.setNbServicesTotal(calculTotatNbService());

        EvaluationFonciere.setPrixSuperficie((superficie * ((EvaluationFonciere.getPrixTerrainMax() + EvaluationFonciere.getPrixTerrainMin()) / 2)) + EvaluationFonciere.MONTANT_FIXE);

        //rajouter formule pour arrondir a 0.5 pres;
        EvaluationFonciere.setDroitPassage(EvaluationFonciere.MONTANT_PASSAGE_BASE - (nbDroitPassage * (EvaluationFonciere.TAUX_DROIT_PASSAGE_TYPE1 * EvaluationFonciere.getPrixSuperficie())));

        EvaluationFonciere.setValeurFonciere2(EvaluationFonciere.getPrixSuperficie() + EvaluationFonciere.getDroitPassage());

        calculMontantServiceType1(superficie, nbServices);

        EvaluationFonciere.setValeurFonciere2(EvaluationFonciere.getValeurFonciere2() + EvaluationFonciere.getMontantService());

        return nombreDecimal(EvaluationFonciere.getValeurFonciere2());
    }

    private static void calculMontantServiceType1(Double superficie1, int nbServices) {

        EvaluationFonciere.setNbServicesTotal(calculTotatNbService());

        if (superficie1 <= 500) {
            EvaluationFonciere.setMontantService(0);
        } else if ((superficie1 > 500) || (superficie1 < 1000)) {
            EvaluationFonciere.setMontantService(EvaluationFonciere.MONTANT_SERVICE_TYPE1_SUP500_INF1000 * EvaluationFonciere.getNbServicesTotal());
        } else {
            EvaluationFonciere.setMontantService(EvaluationFonciere.MONTANT_SERVICE_TYPE1_SUP1000 * EvaluationFonciere.getNbServicesTotal());
        }
    }

    private static int calculTotatNbService() {
        return EvaluationFonciere.getNbServices() + EvaluationFonciere.NB_SERVICE_BASE;
    }

    public static double calculValeurFonciereType0(Double superficie, int nbDroitPassage, int nbServices) {

        EvaluationFonciere.setPrixSuperficie((superficie * EvaluationFonciere.getPrixTerrainMin()) + EvaluationFonciere.MONTANT_FIXE);
        //rajouter formule pour arrondir a 0.5 pres;
        EvaluationFonciere.setDroitPassage(EvaluationFonciere.MONTANT_PASSAGE_BASE - (nbDroitPassage * (EvaluationFonciere.TAUX_DROIT_PASSAGE_TYPE_0 * EvaluationFonciere.getPrixSuperficie())));
        EvaluationFonciere.setMontantService(0);
        EvaluationFonciere.setValeurFonciere1(EvaluationFonciere.getPrixSuperficie() + EvaluationFonciere.getDroitPassage());
        EvaluationFonciere.setValeurFonciere1(EvaluationFonciere.getValeurFonciere1() + EvaluationFonciere.getMontantService());
        return nombreDecimal(EvaluationFonciere.getValeurFonciere1());
    }

    public static double calculValeurFonciereTotale(double valeurFonciere1, double valeurFonciere2, double valeurFonciere3) {

        EvaluationFonciere.setValeurFonciereTotale(valeurFonciere1 + valeurFonciere2 + valeurFonciere3);
        return nombreDecimal(EvaluationFonciere.getValeurFonciereTotale());
    }

    public static double calculerTaxeMunicipale() {
        EvaluationFonciere.setTaxeScolaire(EvaluationFonciere.getValeurFonciereTotale() * EvaluationFonciere.TAUX_TAXE_MUNICIPALE);
        return nombreDecimal(EvaluationFonciere.getTaxeScolaire());
    }

    public static double calculerTaxeScolaire() {
        EvaluationFonciere.setTaxeMunicipale(EvaluationFonciere.getValeurFonciereTotale() * EvaluationFonciere.TAUX_TAXE_SCOLAIRE);
        return nombreDecimal(EvaluationFonciere.getTaxeMunicipale());
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

    public static double obtenirValeurLot3Commercial(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere3;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(2).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_services"));
        valeurFonciere3 = Utilities.calculValeurFonciereType2(superficie, nbDroitPassage, nbServices);
        return valeurFonciere3;
    }

    public static double obtenirValeurLot3TerrainResidentiel(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere3;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(2).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_services"));
        valeurFonciere3 = Utilities.calculValeurFonciereType1(superficie, nbDroitPassage, nbServices);
        return valeurFonciere3;
    }

    public static double obtenirValeurLot1Commercial(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere1;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(0).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_services"));
        valeurFonciere1 = Utilities.calculValeurFonciereType2(superficie, nbDroitPassage, nbServices);
        return valeurFonciere1;
    }

    public static double obtenirValeurLot1TerrainResidentiel(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere3;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(0).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_services"));
        valeurFonciere3 = Utilities.calculValeurFonciereType1(superficie, nbDroitPassage, nbServices);
        return valeurFonciere3;
    }

    public static double obtenirValeurLot2TerrainAgricole(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere2;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(1).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_services"));
        valeurFonciere2 = Utilities.calculValeurFonciereType0(superficie, nbDroitPassage, nbServices);
        return valeurFonciere2;
    }

    public static double obtenirValeurLot3TerrainAgricole(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere3;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(2).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(2).getString("nombre_services"));
        valeurFonciere3 = Utilities.calculValeurFonciereType0(superficie, nbDroitPassage, nbServices);
        return valeurFonciere3;
    }

    public static double obtenirValeurLot2TerrainResidentiel(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere2;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(1).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_services"));
        valeurFonciere2 = Utilities.calculValeurFonciereType1(superficie, nbDroitPassage, nbServices);
        return valeurFonciere2;
    }

    public static double obtenirValeurLot1TerrainAgricole(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere1;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(0).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(0).getString("nombre_services"));
        valeurFonciere1 = Utilities.calculValeurFonciereType0(superficie, nbDroitPassage, nbServices);
        return valeurFonciere1;
    }

    public static double obtenirValeurLot2Commercial(JSONArray ListeItemLotissement) throws NumberFormatException {
        int nbDroitPassage;
        double superficie;
        int nbServices;
        double valeurFonciere2;
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(1).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(1).getString("nombre_services"));
        valeurFonciere2 = Utilities.calculValeurFonciereType2(superficie, nbDroitPassage, nbServices);
        return valeurFonciere2;
    }

    public static void populatingWrittenArrayObj3(JSONObject singleLot, double valeurFonciere3, JSONArray lotissementArray) {
        singleLot.accumulate("description", "lot 3");
        singleLot.accumulate("valeur_par_lot", valeurFonciere3 + "$");
        lotissementArray.add(singleLot);
    }

    public static void populatingWritenArrayObj2(JSONObject singleLot, double valeurFonciere2, JSONArray lotissementArray) {
        singleLot.accumulate("description", "lot 2");
        singleLot.accumulate("valeur_par_lot", valeurFonciere2 + "$");
        lotissementArray.add(singleLot);
        singleLot.clear();
    }

    public static void ecrireFichier(JSONObject valeurFonciereTerrain, BigDecimal valeurTotaleLot, BigDecimal taxeScolaireLot, BigDecimal taxeMunicipaleLot, double valeurFonciere1, double valeurFonciere2, double valeurFonciere3) {
        valeurFonciereTerrain.accumulate("valeur_fonciere_totale", valeurTotaleLot + "$");
        valeurFonciereTerrain.accumulate("taxe_scolaire", taxeScolaireLot + "$");
        valeurFonciereTerrain.accumulate("taxe_municipale", taxeMunicipaleLot + "$");
        JSONArray lotissementArray = populatingWritenLotissementArray(valeurFonciere1, valeurFonciere2, valeurFonciere3);
        valeurFonciereTerrain.accumulate("lotissements", lotissementArray);
    }

    public static BigDecimal arrondirTaxeScolaire() {
        double tScolaire = Utilities.nombreDecimal(Utilities.calculerTaxeScolaire());
        BigDecimal scolaire = Utilities.arrondirAu5sous(String.valueOf(tScolaire));
        BigDecimal taxeScolaireLot = scolaire.setScale(2, RoundingMode.HALF_UP);
        return taxeScolaireLot;
    }

    public static String parsingArrayLotissement(JSONArray ListeItemLotissement, int i) throws NumberFormatException {
        JSONObject lotissement;
        String description;
        int nbDroitPassage;
        double superficie;
        int nbServices;
        lotissement = ListeItemLotissement.getJSONObject(i);
        description = lotissement.getString("description");
        //CALCULER la valuer par lot
        //valeur = (superficie + (prixMx+pirMin)/2) + droits de passage + montant pour les services
        nbDroitPassage = Integer.parseInt(ListeItemLotissement.getJSONObject(i).getString("nombre_droits_passage"));
        superficie = Double.parseDouble(ListeItemLotissement.getJSONObject(i).getString("superficie"));
        nbServices = Integer.parseInt(ListeItemLotissement.getJSONObject(i).getString("nombre_services"));
        return description;
    }

    public static BigDecimal arrondirTaxeMunicipale() {
        double tMunicipale = Utilities.calculerTaxeMunicipale();
        BigDecimal municipale = Utilities.arrondirAu5sous(String.valueOf(tMunicipale));
        //System.out.println("BigDecimal municipale: " + municipale);
        BigDecimal taxeMunicipaleLot = municipale.setScale(2, RoundingMode.HALF_UP);
        return taxeMunicipaleLot;
    }

    public static void afficherLotissement(JSONArray ListeItemLotissement, int i) {
        JSONObject lotissement;
        String description;
        int nbDroitPassage;
        double superficie;
        int nbServices;
        String dateMesureEnString;
        Date dateMesure;
        lotissement = ListeItemLotissement.getJSONObject(i);
        description = lotissement.getString("description");
        nbDroitPassage = lotissement.getInt("nombre_droits_passage");
        superficie = lotissement.getDouble("superficie");
        nbServices = lotissement.getInt("nombre_services");
        dateMesureEnString = lotissement.getString("date_mesure");
        //traiter la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        try {
            dateMesure = dateFormat.parse(dateMesureEnString);
        } catch (ParseException ex) {
            Logger.getLogger(AppCtr.class.getName()).log(Level.SEVERE, null, ex);
        }
        //valeur par lot
        System.out.println("lotissement ajouter " + lotissement.toString());
    }

    public static JSONArray populatingWritenLotissementArray(double valeurFonciere1, double valeurFonciere2, double valeurFonciere3) {
        JSONArray lotissementArray = new JSONArray();
        JSONObject singleLot = new JSONObject();
        populatingWritenArrayObj1(singleLot, valeurFonciere1, lotissementArray);
        populatingWritenArrayObj2(singleLot, valeurFonciere2, lotissementArray);
        populatingWrittenArrayObj3(singleLot, valeurFonciere3, lotissementArray);
        return lotissementArray;
    }

    public static void populatingWritenArrayObj1(JSONObject singleLot, double valeurFonciere1, JSONArray lotissementArray) {
        singleLot.accumulate("description", "lot 1");
        singleLot.accumulate("valeur_par_lot", valeurFonciere1 + "$");
        lotissementArray.add(singleLot);
        singleLot.clear();
    }

   public static void ecrireFichierSortie(BigDecimal valeurTotaleLot, double valeurFonciere1, double valeurFonciere2, double valeurFonciere3, String sortiePath) {
        JSONObject valeurFonciereTerrain = new JSONObject();
        BigDecimal taxeScolaireLot = Utilities.arrondirTaxeScolaire();
        BigDecimal taxeMunicipaleLot = Utilities.arrondirTaxeMunicipale();
        Utilities.ecrireFichier(valeurFonciereTerrain, valeurTotaleLot, taxeScolaireLot, taxeMunicipaleLot, valeurFonciere1, valeurFonciere2, valeurFonciere3);
        FileWriter.saveStringIntoFile(valeurFonciereTerrain.toString(), sortiePath);
    }

}
