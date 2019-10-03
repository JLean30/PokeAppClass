package com.jlean.pokemon.model;

public class Pokemon {
    private int aidi;
    private String name;
    private String url;

    public Pokemon() {

    }

    public Pokemon(int aidi, String name, String url) {
        this.aidi = aidi;
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAidi() {
        String[] urlParts = url.split("/");
        return Integer.parseInt(urlParts[urlParts.length -1]);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getUrl() {
        return aidi;
    }

    public void setAidi(int aidi) {
        this.aidi = aidi;
    }
    @Override
    public String toString() {
        return "Pokemon{" +
                "aidi=" + aidi +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

}
