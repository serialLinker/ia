/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author cwirth
 */
public class Ville {

    private final String nom;
    private final Map<Ville, Integer> voisines;
    private final Map<Ville, List<Pheronome>> pheronomes;

    public Ville(String nom) {
        this.nom = nom;
        voisines = new HashMap<>();
        pheronomes = new HashMap<>();
    }

    public Collection<Ville> getVoisines() {
        return voisines.keySet();
    }

    public Integer getDistance(Ville v) {
        return voisines.get(v);
    }

    public void ajouterVoisine(Ville v, Integer distance) {
        voisines.put(v, distance);
        pheronomes.put(v, new ArrayList<Pheronome>());
    }

    public void marquerVille(Ville v) {
        pheronomes.get(v).add(new Pheronome());
    }

    public Integer getPheronomes(Ville v) {
        return pheronomes.get(v).size();
    }

    public void evaporer() {
        Collection<List<Pheronome>> pheromones = pheronomes.values();
        for (List<Pheronome> pheromonesVille : pheromones) {
            Iterator<Pheronome> it = pheromonesVille.iterator();
            while (it.hasNext()) {
                Pheronome p = it.next();
                if (p.nextStep()) {
              //      System.out.println("evaporation d'un pheronomone en " + this);
                    it.remove();
                }
            }
        }
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "Ville{" + "nom=" + nom + '}';
    }

    public Ville getVillePlusMarquee() {
        Ville res = null;
        Integer max = null;
        for (Entry<Ville, List<Pheronome>> entry : pheronomes.entrySet()) {
            if (res == null || entry.getValue().size() > max) {
                res = entry.getKey();
                max = entry.getValue().size();
            }
        }
        return res;
    }

}
