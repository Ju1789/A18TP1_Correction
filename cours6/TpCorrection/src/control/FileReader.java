/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.commons.io.IOUtils;



/**
 *
 * @author USER
 */
public class FileReader {
//    FileInputStream file = new FileInputStream(String filePath);
//        return IOUtils.toString(file,"UTF-8");
    
     public static String loadFileIntoString(String filePath) throws FileNotFoundException, IOException{
       
        FileInputStream file = new FileInputStream(filePath);
        return IOUtils.toString(file,"UTF-8");
        
    }

}
