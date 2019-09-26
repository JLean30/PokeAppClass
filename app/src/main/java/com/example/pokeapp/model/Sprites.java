package com.example.pokeapp.model;

public class Sprites {
    private String back_default;

    public Sprites(String back_default) {
        this.back_default = back_default;
    }

    public Sprites() {
    }

    public String getBack_default() {
        return back_default;
    }

    public void setBack_default(String back_default) {
        this.back_default = back_default;
    }

    @Override
    public String toString() {
        return "Sprites{" +
                "back_default='" + back_default + '\'' +
                '}';
    }
}
