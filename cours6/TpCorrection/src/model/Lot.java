/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author jveret
 */
public class Lot {
   private String description;
   private int nbDroitPassage;
   private int nbService;
   private double superficie;
   private Date dateMesure;

    public Lot() {
    }

    public Lot(String description, int nbDroitPassage, int nbService, double superficie, Date dateMesure) {
        this.description = description;
        this.nbDroitPassage = nbDroitPassage;
        this.nbService = nbService;
        this.superficie = superficie;
        this.dateMesure = dateMesure;
    }

    public Date getDateMesure() {
        return dateMesure;
    }

    public void setDateMesure(Date dateMesure) {
        this.dateMesure = dateMesure;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbDroitPassage() {
        return nbDroitPassage;
    }

    public void setNbDroitPassage(int nbDroitPassage) {
        this.nbDroitPassage = nbDroitPassage;
    }

    public int getNbService() {
        return nbService;
    }

    public void setNbService(int nbService) {
        this.nbService = nbService;
    }

    public double getSuperficie() {
        return superficie;
    }

    public void setSuperficie(double superficie) {
        this.superficie = superficie;
    }
   
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
