/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;

/**
 *
 * @author jveret
 */
public class Terrain {
    private int typeTerrain;
    private double prixTerrainMin;
    private double prixTerrainMax;
    private List<Lot> listeLots;

    public Terrain() {
    }

    public Terrain(int typeTerrain, double prixTerrainMin, double prixTerrainMax, List<Lot> listeLots) {
        this.typeTerrain = typeTerrain;
        this.prixTerrainMin = prixTerrainMin;
        this.prixTerrainMax = prixTerrainMax;
        this.listeLots = listeLots;
    }

    public List<Lot> getListeLots() {
        return listeLots;
    }

    public void setListeLots(List<Lot> listeLots) {
        this.listeLots = listeLots;
    }

    public int getTypeTerrain() {
        return typeTerrain;
    }

    public void setTypeTerrain(int typeTerrain) {
        this.typeTerrain = typeTerrain;
    }

    public double getPrixTerrainMin() {
        return prixTerrainMin;
    }

    public void setPrixTerrainMin(double prixTerrainMin) {
        this.prixTerrainMin = prixTerrainMin;
    }

    public double getPrixTerrainMax() {
        return prixTerrainMax;
    }

    public void setPrixTerrainMax(double prixTerrainMax) {
        this.prixTerrainMax = prixTerrainMax;
    }
    
    
    
}
