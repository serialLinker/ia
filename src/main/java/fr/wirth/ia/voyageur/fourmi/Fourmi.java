/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ia.voyageur.fourmi;

import fr.wirth.ia.voyageur.Ville;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author CYRIL
 */
public class Fourmi {

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
        FourmiVilleManager.INSTANCE.marquerVille(villeCourante, v);
        villeCourante = v;
        parcours.add(v);
    }

    public Ville choisirVille() {
        Collection<Ville> villes = villeCourante.getVoisines();

        Map<Ville, Double> probas = new HashMap<>();

        double total = 0;
        for (Ville ville : villes) {
            Integer nbPheromones = FourmiVilleManager.INSTANCE.getPheronomoes(villeCourante, ville) + 1;

            probas.put(ville, nbPheromones * 1.0);
            total += nbPheromones;
        }

        Map<Ville, Couple<Double, Double>> probabilites = new HashMap<>();
        double start = 0;
        for (Map.Entry<Ville, Double> entry : probas.entrySet()) {
            double prob = entry.getValue() / total;

            Couple<Double, Double> couple = new Couple<>(start, start + prob);
            probabilites.put(entry.getKey(), couple);
            start += prob;
        }

        Double random = Math.random();

        Ville result = null;
        for (Map.Entry<Ville, Couple<Double, Double>> entry : probabilites.entrySet()) {
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
