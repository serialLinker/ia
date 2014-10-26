/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ia.voyageur.fourmi;

import fr.wirth.ia.voyageur.Ville;
import fr.wirth.ia.voyageur.VilleManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author CYRIL
 */
public class Launch {

    private static final Integer NB_FOURMIS = 2;
    private static final Integer NB_TOUR = 5;

    @Test
    public void doIt() {
        List<Ville> villes = generationVilles();
        Ville depart = villes.get(0);
        Ville arrivee = villes.get(villes.size() - 1);

        Assert.assertEquals("A", depart.getNom());
        Assert.assertEquals("E", arrivee.getNom());

        List<Fourmi> fourmis = generateFourmis(depart);

        for (int tour = 0; tour < NB_TOUR; tour++) {
            FourmiVilleManager.INSTANCE.evaporer();

            for (Fourmi f : fourmis) {

                if (f.getVilleCourante().equals(arrivee)) {
                    f.reset();
                } else {
                    Ville next = f.choisirVille();
                    f.seDeplacer(next);
                }
            }
        }

        FourmiVilleManager.INSTANCE.printCheminLePlusCourt(depart, arrivee);

    }

    private List<Ville> generationVilles() {
        Ville a = new Ville("A");
        Ville b = new Ville("B");
        Ville c = new Ville("C");
        Ville d = new Ville("D");
        Ville e = new Ville("E");

        List<Ville> villes = Arrays.asList(a, b, c, d, e);

        FourmiVilleManager.INSTANCE.ajouterVoisine(a, b, 1);
        FourmiVilleManager.INSTANCE.ajouterVoisine(a, c, 1);

        FourmiVilleManager.INSTANCE.ajouterVoisine(b, e, 1);
        FourmiVilleManager.INSTANCE.ajouterVoisine(c, d, 1);

        FourmiVilleManager.INSTANCE.ajouterVoisine(d, e, 1);

        return villes;
    }

    private List<Fourmi> generateFourmis(Ville villeDepart) {
        List<Fourmi> fourmis = new ArrayList<>();
        for (int i = 0; i < NB_FOURMIS; i++) {
            fourmis.add(new Fourmi(i, villeDepart));
        }
        return fourmis;
    }

}
