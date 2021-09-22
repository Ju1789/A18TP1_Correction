/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import utilities.Utilities;

/**
 *
 * @author USER
 */
public class FileWriter {
    
    public static void saveStringIntoFile (String contentToSave, String filePath)
    {
        try {
            File file = new File(filePath);
            
            FileUtils.writeStringToFile(file, contentToSave, "UTF-8");
        } catch (IOException ex) {
            Logger.getLogger(FileWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public static void ecrireFichierSortie(BigDecimal valeurTotaleLot, double valeurFonciere1, double valeurFonciere2, double valeurFonciere3, String sortiePath) {
        JSONObject valeurFonciereTerrain = new JSONObject();
        BigDecimal taxeScolaireLot = Utilities.arrondirTaxeScolaire();
        BigDecimal taxeMunicipaleLot = Utilities.arrondirTaxeMunicipale();
        Utilities.ecrireFichier(valeurFonciereTerrain, valeurTotaleLot, taxeScolaireLot, taxeMunicipaleLot, valeurFonciere1, valeurFonciere2, valeurFonciere3);
        FileWriter.saveStringIntoFile(valeurFonciereTerrain.toString(), sortiePath);
    }
    
}
