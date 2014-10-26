/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ia.voyageur;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CYRIL
 */
public class Ville {

    private final String nom;
    private final Map<Ville, Integer> voisines;

    public Ville(String nom) {
        this.nom = nom;
        voisines = new HashMap<>();
    }

    public Integer getDistance(Ville v) {
        return voisines.get(v);
    }
    
    public Collection<Ville> getVoisines(){
        return voisines.keySet();
    }

    public void ajouterVoisine(Ville v, Integer distance) {
        voisines.put(v, distance);
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Ville{" + "nom=" + nom + '}';
    }

}
