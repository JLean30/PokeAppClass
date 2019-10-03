package com.jlean.pokemon.model;

import java.util.ArrayList;

public class SinglePokemonResponse {

    private String name;
    private ArrayList<Abilities> abilities;
    private Sprites sprites;

    public SinglePokemonResponse(String name, ArrayList<Abilities> abilities, Sprites sprites) {
        this.name = name;
        this.abilities = abilities;
        this.sprites = sprites;
    }

    public SinglePokemonResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        return "SinglePokemonResponse{" +
                "name='" + name + '\'' +
                ", abilities=" + abilities +
                ", sprites=" + sprites +
                '}';
    }
}
