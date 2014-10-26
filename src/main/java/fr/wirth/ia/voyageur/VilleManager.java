/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ia.voyageur;

/**
 *
 * @author CYRIL
 */
public abstract class VilleManager {

    public void ajouterVoisine(Ville depart, Ville arrivee, Integer distance) {
        depart.ajouterVoisine(arrivee, distance);
        arrivee.ajouterVoisine(depart, distance);
    }

}
