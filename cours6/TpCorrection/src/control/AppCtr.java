/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import utilities.FileWriting;
import utilities.Rounding;
import utilities.Utilities;

public class AppCtr {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date dateMesure = new Date();
        String dateMesureEnString;
        int nbServiceBase = 2;
        double nbDroitPassage = 0;
        double tauxTaxeMunicipale = 0.025;
        double tauxTaxeScolaire = 0.012;
        double montantFixe = 733.77;
        double montantPassageBase = 500.00;
        double valeurFonciere = 0;
        double montantService = 0;
        double droitPassage = 0;
        double valeurLot = 0;
        double valeurTerrain = 0;
        double taxeMunicipale;
        double taxeScolaire;
        double superficie = 0;
        int nbServices = 0;
        //prixspuerficie = prixLot;
        final String FILEPATH = args[0];
        final String FILEPATH_WRITING = args[1];
        //lire le fichier Json 
        String myJson;
        try {
            myJson = FileReader.loadFileIntoString(FILEPATH);

            //creation des objets json
            JSONObject mainJSON = JSONObject.fromObject(myJson);
            JSONArray ListeItemLotissement = mainJSON.getJSONArray("lotissements");
            JSONObject lotissement;
            //recupere les donne du json
            int typeTerrain = mainJSON.getInt("type_terrain");
            double prixTerrainMin = mainJSON.getDouble("prix_m2_min");
            double prixTerrainMax = mainJSON.getDouble("prix_m2_max");

            String description = null;
            JSONArray lotissementArray = new JSONArray();
            JSONObject singleLot = new JSONObject();

            for (int i = 0; i < ListeItemLotissement.size(); i++) {
                lotissement = ListeItemLotissement.getJSONObject(i);
                description = lotissement.getString("description");
                nbDroitPassage = lotissement.getDouble("nombre_droits_passage");
                superficie = lotissement.getDouble("superficie");
                nbServices = lotissement.getInt("nombre_services");
                int nbServiceTotal = nbServices + nbServiceBase;
                //CALCULER la valuer par lot
                //valeur = (superficie + (prixMx+pirMin)/2) + droits de passage + montant pour les services
                if (typeTerrain == 1) {
                    
                    double prixSuperficie = (superficie * ((prixTerrainMax + prixTerrainMin) / 2)) + 733.77;
                    //rajouter formule pour arrondir a 0.5 pret;
                    droitPassage = 500 - (nbDroitPassage * (0.05 * valeurLot));
                    valeurFonciere = prixSuperficie + droitPassage;
                    if (superficie < 500) {
                        montantService = 0;
                    } else if ((superficie > 500) || (superficie < 1000)) {
                        montantService = 500 * nbServiceTotal;
                    } else {
                        montantService = 1000 * nbServiceTotal;
                    }
                    valeurFonciere += montantService;
                    
                    FileWriting.populatingLotissementArray(singleLot, description, valeurFonciere, lotissementArray);
                    
                    System.out.println(valeurFonciere);

                } else if (typeTerrain == 2) {
                    double prixSuperficie = (superficie * prixTerrainMax) + 733.77;
                    //rajouter formule pour arrondir a 0.5 pret;
                    droitPassage = 500 - (nbDroitPassage * (0.1 * valeurLot));
                    valeurFonciere = prixSuperficie + droitPassage;
                    if (superficie < 500) {
                        montantService = 500;
                    } else {
                        if (nbServiceTotal < 4) {
                            montantService = 1500 * nbServiceTotal;
                        } else {
                            montantService = 5000;
                        }
                    }
                    valeurFonciere += montantService;
                    
                    FileWriting.populatingLotissementArray(singleLot, description, valeurFonciere, lotissementArray);
                    
                    System.out.println(valeurFonciere);
                    
                } else if (typeTerrain == 0) {
                    double prixSuperficie = (superficie * prixTerrainMin) + 733.77;
                    //rajouter formule pour arrondir a 0.5 pret;
                    droitPassage = 500 - (nbDroitPassage * (0.15 * valeurLot));
                    montantService = 0;
                    valeurFonciere = prixSuperficie + droitPassage;
                    valeurFonciere += montantService;
                    
                    FileWriting.populatingLotissementArray(singleLot, description, valeurFonciere, lotissementArray);
                    
                    System.out.println(valeurFonciere);
                    
                } else {
                    System.out.println("ERROR");
                }

                valeurTerrain += valeurFonciere;
            }

            System.out.println("La valeur du terrain est :" + valeurTerrain);
            System.out.println(mainJSON);
            for (int i = 0; i < ListeItemLotissement.size(); i++) {

                lotissement = ListeItemLotissement.getJSONObject(i);
                description = lotissement.getString("description");
                nbDroitPassage = lotissement.getDouble("nombre_droits_passage");
                superficie = lotissement.getDouble("superficie");
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
            

            JSONObject valeurFonciereTerrain = new JSONObject();
            
            BigDecimal terrain = Rounding.roundingLotTotalValue(valeurTerrain);
            
            //System.out.println("valeur arrondie terrain: " + terrain);

            BigDecimal scolaire = Rounding.roundingSchoolTax(valeurTerrain);

            //System.out.println("taxe scolaire: " + scolaire);

            BigDecimal municipale = Rounding.roundingMunicipalTax(valeurTerrain);

            //System.out.println("taxe municipale: " + municipale);

            FileWriting.populatingFileWritingJSONObject(valeurFonciereTerrain, terrain, scolaire, municipale, lotissementArray);
            FileWriter.saveStringIntoFile(valeurFonciereTerrain.toString(), FILEPATH_WRITING);
        } catch (IOException ex) {
            Logger.getLogger(AppCtr.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



}
