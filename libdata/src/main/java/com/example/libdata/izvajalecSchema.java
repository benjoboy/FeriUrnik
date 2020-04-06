package com.example.libdata;

import java.util.Date;

public class izvajalecSchema {
    String ime;
    String priimek;
    String email;
    String naziv;
    Date zacetekGovorilne;
    Date konecGovorilne;
    prostorSchema prostor;

    public izvajalecSchema(String ime, String priimek, String email, String naziv, Date zacetekGovorilne, Date konecGovorilne, prostorSchema prostor) {
        this.ime = ime;
        this.priimek = priimek;
        this.email = email;
        this.naziv = naziv;
        this.zacetekGovorilne = zacetekGovorilne;
        this.konecGovorilne = konecGovorilne;
        this.prostor = prostor;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPriimek() {
        return priimek;
    }

    public void setPriimek(String priimek) {
        this.priimek = priimek;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Date getZacetekGovorilne() {
        return zacetekGovorilne;
    }

    public void setZacetekGovorilne(Date zacetekGovorilne) {
        this.zacetekGovorilne = zacetekGovorilne;
    }

    public Date getKonecGovorilne() {
        return konecGovorilne;
    }

    public void setKonecGovorilne(Date konecGovorilne) {
        this.konecGovorilne = konecGovorilne;
    }

    public prostorSchema getProstor() {
        return prostor;
    }

    public void setProstor(prostorSchema prostor) {
        this.prostor = prostor;
    }
}
