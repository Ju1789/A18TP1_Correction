/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.math.BigDecimal;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author USER
 */
public class FileWriting {

    public static void populatingFileWritingJSONObject(JSONObject valeurFonciereTerrain, BigDecimal terrain, BigDecimal scolaire, BigDecimal municipale, JSONArray lotissementArray) {
        valeurFonciereTerrain.accumulate("valeur_fonciere_totale", terrain + "$");
        valeurFonciereTerrain.accumulate("taxe_scolaire", scolaire + "$");
        valeurFonciereTerrain.accumulate("taxe_municipale", municipale + "$");
        valeurFonciereTerrain.accumulate("lotissement", lotissementArray);
    }

    public static void populatingLotissementArray(JSONObject singleLot, String description, double valeurFonciere, JSONArray lotissementArray) {
        singleLot.accumulate("description", description);
        singleLot.accumulate("valeur_par_lot", valeurFonciere + "$");
        lotissementArray.add(singleLot);
        singleLot.clear();
    }
    
}
