package com.example.pokeapp.model;

import java.util.ArrayList;
import java.util.Arrays;

public class PokemonDetail {
    private ArrayList<Abilities> abilities;
    private String name;
    private Sprites sprites;

    public PokemonDetail(ArrayList<Abilities> abilities, String name, Sprites sprites) {
        this.abilities = abilities;
        this.name = name;
        this.sprites = sprites;
    }

    public PokemonDetail() {
    }

    public ArrayList<Abilities> getAbilities() {
        return abilities;
    }

    public void setAbilities(ArrayList<Abilities> abilities) {
        this.abilities = abilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public void setSprites(Sprites sprites) {
        this.sprites = sprites;
    }

    @Override
    public String toString() {
        return "PokemonDetail{" +
                "abilities=" + abilities +
                ", name='" + name + '\'' +
                ", sprites=" + sprites +
                '}';
    }
}
