/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author cwirth
 */
public class Main {

    public static void main(String[] args) throws Exception {
        /*
        List<Ville> villes = cas1();
        Ville depart = villes.get(0);
        Ville arrivee = villes.get(villes.size() - 1);
        List<Fourmi> fourmis = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            fourmis.add(new Fourmi(i, depart));
        }

        for (int tour = 0; tour < 10000; tour++) {
            for (Ville ville : villes) {
                ville.evaporer();
            }

            for (Fourmi f : fourmis) {

                if (f.getVilleCourante().equals(arrivee)) {
                    f.reset();
                } else {
                    Ville next = f.choisirVille();
                 //   System.out.println(f + "se deplace de : " + f.getVilleCourante() + " vers " + next);
                    f.seDeplacer(next);
                 //   Thread.sleep(500);

                }
            }
        }

        printCheminLePlusCourt(depart, arrivee);

    }

    private static void printCheminLePlusCourt(Ville depart, Ville arrivee) {
        StringBuilder sb = new StringBuilder(depart.getNom());
        sb.append(" ");
        Ville current = depart;
        while (!arrivee.equals(current)) {
            current = current.getVillePlusMarquee();
            sb.append(current.getNom());
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }

    private static List<Ville> cas1() {
        Ville a = new Ville("A");
        Ville b = new Ville("B");
        Ville c = new Ville("C");
        Ville d = new Ville("D");
        Ville e = new Ville("E");

        List<Ville> villes = Arrays.asList(a, b, c, d, e);

        a.ajouterVoisine(b, 1);
        b.ajouterVoisine(e, 1);

        a.ajouterVoisine(c, 1);
        c.ajouterVoisine(d, 1);
        d.ajouterVoisine(e, 1);

        return villes;
    }

    private static List<Ville> cas2() {
        Ville a = new Ville("A");
        Ville b = new Ville("B");
        Ville c = new Ville("C");
        Ville d = new Ville("D");

        List<Ville> villes = Arrays.asList(a, b, c, d);

        a.ajouterVoisine(b, 1);
        a.ajouterVoisine(c, 2);

        b.ajouterVoisine(d, 1);
        c.ajouterVoisine(d, 2);

        return villes;
                */
    }
}
