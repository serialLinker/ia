/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ia.voyageur.fourmi;

import fr.wirth.ia.voyageur.Ville;
import fr.wirth.ia.voyageur.VilleManager;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author CYRIL
 */
public class FourmiVilleManager extends VilleManager {

    private final Map<Ville, Map<Ville, List<Pheronome>>> pheromones;

    public static final FourmiVilleManager INSTANCE = new FourmiVilleManager();

    private FourmiVilleManager() {
        pheromones = new HashMap<>();
    }

    @Override
    public void ajouterVoisine(Ville depart, Ville arrivee, Integer distance) {
        super.ajouterVoisine(depart, arrivee, distance);
        initPheromone(depart, arrivee);
        initPheromone(arrivee, depart);
    }

    private void initPheromone(Ville depart, Ville arrivee) {
        if (!pheromones.containsKey(depart)) {
            pheromones.put(depart, new HashMap<Ville, List<Pheronome>>());
        }
        if (!pheromones.get(depart).containsKey(arrivee)) {
            pheromones.get(depart).put(arrivee, new ArrayList<Pheronome>());
        }
    }

    public void marquerVille(Ville depart, Ville arrivee) {
        pheromones.get(depart).get(arrivee).add(new Pheronome());
    }

    public Integer getPheronomoes(Ville depart, Ville arrivee) {
        return pheromones.get(depart).get(arrivee).size();
    }

    public void evaporer() {
        for (Map<Ville, List<Pheronome>> separations : pheromones.values()) {
            for (List<Pheronome> separation : separations.values()) {
                Iterator<Pheronome> it = separation.iterator();
                while (it.hasNext()) {
                    Pheronome phero = it.next();
                    if (phero.nextStep()) {
                        it.remove();
                    }
                }
            }
        }
    }

    private Ville getVillePlusMarquee(Ville depart, List<Ville> parcours) {
        Ville res = null;
        Integer nbPhero = 0;
        Map<Ville, List<Pheronome>> dep = pheromones.get(depart);
        for (Entry<Ville, List<Pheronome>> entry : dep.entrySet()) {
            if(!parcours.contains(entry.getKey()) && (res == null || nbPhero < entry.getValue().size())){
                res = entry.getKey();
                nbPhero = entry.getValue().size();
            }
        }
        
        return res;
    }
    
    public void printCheminLePlusCourt(Ville depart, Ville arrivee) {
        List<Ville> parcours = new ArrayList<>();
        
        StringBuilder sb = new StringBuilder(depart.getNom());
        sb.append(" ");
        Ville current = depart;
        while (!arrivee.equals(current)) {
            parcours.add(current);
            current = getVillePlusMarquee(current, parcours);
            System.out.println("depart : " + current.getNom());
            
            sb.append(current.getNom());
            sb.append(" ");
        }
        System.out.println(sb.toString());
    }
    

}
