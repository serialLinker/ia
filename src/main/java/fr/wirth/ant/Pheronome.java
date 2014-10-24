/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.wirth.ant;

/**
 *
 * @author cwirth
 */
public class Pheronome {

    private static final Integer LIFE_TIME = 5;
    private Integer remainingLife;

    public Pheronome() {
        this.remainingLife = LIFE_TIME;
    }

    public boolean nextStep() {
        remainingLife--;
        return remainingLife == 0;
    }
}
