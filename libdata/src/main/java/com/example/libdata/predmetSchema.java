package com.example.libdata;

public class predmetSchema {
    String naziv;
    String[] skupine;
    izvajalecSchema[] izvajalci;
    izpitSchema[] izpiti;

    public predmetSchema(String naziv, String[] skupine, izvajalecSchema[] izvajalci, izpitSchema[] izpiti) {
        this.naziv = naziv;
        this.skupine = skupine;
        this.izvajalci = izvajalci;
        this.izpiti = izpiti;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String[] getSkupine() {
        return skupine;
    }

    public void setSkupine(String[] skupine) {
        this.skupine = skupine;
    }

    public izvajalecSchema[] getIzvajalci() {
        return izvajalci;
    }

    public void setIzvajalci(izvajalecSchema[] izvajalci) {
        this.izvajalci = izvajalci;
    }

    public izpitSchema[] getIzpiti() {
        return izpiti;
    }

    public void setIzpiti(izpitSchema[] izpiti) {
        this.izpiti = izpiti;
    }
}
