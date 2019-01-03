package com.example.shosho.almorshed.model;

public class Quran {
    private String word1;
    private String word2;
    private String meaning;
    private String souret;
    private String part;

    public Quran() {
    }

    public Quran(String word1, String word2, String meaning, String souret, String part) {
        this.word1 = word1;
        this.word2 = word2;
        this.meaning = meaning;
        this.souret = souret;
        this.part = part;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSouret() {
        return souret;
    }

    public void setSouret(String souret) {
        this.souret = souret;
    }

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    @Override
    public String toString() {
        return souret;
    }
}
