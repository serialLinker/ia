/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author cwirth
 */
public class Fourmi {

    static int tour = 0;
    
    private static final double alpha = 1.5;
    private static final double beta = 1.125;

    private final Collection<Ville> parcours;
    private final Integer id;
    private Ville villeCourante;
    private final Ville villeInit;

    public Fourmi(Integer id, Ville ville) {
        this.id = id;
        villeCourante = ville;
        parcours = new ArrayList<>();
        parcours.add(ville);
        villeInit = ville;
    }

    public void reset() {
        villeCourante = villeInit;
        parcours.clear();
    }

    public void seDeplacer(Ville v) {
        villeCourante.marquerVille(v);
        villeCourante = v;
        parcours.add(v);
    }

    public Ville choisirVille() {
        Collection<Ville> villes = villeCourante.getVoisines();

        Map<Ville, Double> probas = new HashMap<>();

        double total = 0;
        for (Ville ville : villes) {
            Integer nbPheromones = villeCourante.getPheronomes(ville) + 1;
         //   System.out.println("Pheromone(" + villeCourante + ", " + ville + ") = " + (nbPheromones - 1));
            /*
             double A = Math.pow(nbPheromones, alpha) +1; // +1 pour le cas ou il n'y a pas de pheronome

             Integer distance = villeCourante.getDistance(ville);
             double B = Math.pow(distance, beta * -1);
            
             probas.put(ville, A*B);
             total += (A*B);
             */
            probas.put(ville, nbPheromones * 1.0);
            total += nbPheromones;
        }

        Map<Ville, Couple<Double, Double>> probabilites = new HashMap<>();
        double start = 0;
        for (Entry<Ville, Double> entry : probas.entrySet()) {
            double prob = entry.getValue() / total;

            Couple<Double, Double> couple = new Couple<Double, Double>(start, start + prob);
            probabilites.put(entry.getKey(), couple);
            start += prob;
        }

        if(tour%100 == 0){
          //  System.out.println(probabilites);
        }
        tour++;
        
        Double random = Math.random();

        Ville result = null;
        for (Entry<Ville, Couple<Double, Double>> entry : probabilites.entrySet()) {
            Couple<Double, Double> c = entry.getValue();
            if (random >= c.getFirst() && random <= c.getSecond()) {
                result = entry.getKey();
            }
        }
        return result;
    }

    public Ville getVilleCourante() {
        return villeCourante;
    }

    public void setVilleCourante(Ville villeCourante) {
        this.villeCourante = villeCourante;
    }

    @Override
    public String toString() {
        return "Fourmi{" + "id=" + id + '}';
    }

    private class Couple<A, B> {

        private final A first;
        private final B second;

        public Couple(A first, B second) {
            this.first = first;
            this.second = second;
        }

        public A getFirst() {
            return first;
        }

        public B getSecond() {
            return second;
        }

        @Override
        public String toString() {
            return "Couple{" + "first=" + first + ", second=" + second + '}';
        }
    }
}
