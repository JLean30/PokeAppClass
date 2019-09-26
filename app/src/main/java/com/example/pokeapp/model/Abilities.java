package com.example.pokeapp.model;

import java.util.ArrayList;

public class Abilities {
    private ArrayList<Ability> ability;
    private int slot;
    private boolean is_hidden;

    public Abilities(ArrayList<Ability> ability, int slot, boolean is_hidden) {
        this.ability = ability;
        this.slot = slot;
        this.is_hidden = is_hidden;
    }

    public Abilities() {
    }

    public ArrayList<Ability> getAbility() {
        return ability;
    }

    public void setAbility(ArrayList<Ability> ability) {
        this.ability = ability;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public boolean isIs_hidden() {
        return is_hidden;
    }

    public void setIs_hidden(boolean is_hidden) {
        this.is_hidden = is_hidden;
    }

    @Override
    public String toString() {
        return "Abilities{" +
                "ability=" + ability +
                ", slot=" + slot +
                ", is_hidden=" + is_hidden +
                '}';
    }
}
