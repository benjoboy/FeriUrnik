package com.example.libdata;

public class prostorSchema {
    String naslov;
    String tip;
    Number[] zgornjiLevi;
    Number[] zgornjiDesni;
    lokacijaSchema lokacija;

    public prostorSchema(String naslov, String tip, Number[] zgornjiLevi, Number[] zgornjiDesni, lokacijaSchema lokacija) {
        this.naslov = naslov;
        this.tip = tip;
        this.zgornjiLevi = zgornjiLevi;
        this.zgornjiDesni = zgornjiDesni;
        this.lokacija = lokacija;
    }

    public prostorSchema(String naslov, String tip, lokacijaSchema lokacija) {
        this.naslov = naslov;
        this.tip = tip;
        this.lokacija = lokacija;
    }

    public prostorSchema(String naslov, String tip) {
        this.naslov = naslov;
        this.tip = tip;
    }

    public String getNaslov() {
        return naslov;
    }

    public void setNaslov(String naslov) {
        this.naslov = naslov;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public Number[] getZgornjiLevi() {
        return zgornjiLevi;
    }

    public void setZgornjiLevi(Number[] zgornjiLevi) {
        this.zgornjiLevi = zgornjiLevi;
    }

    public Number[] getZgornjiDesni() {
        return zgornjiDesni;
    }

    public void setZgornjiDesni(Number[] zgornjiDesni) {
        this.zgornjiDesni = zgornjiDesni;
    }

    public lokacijaSchema getLokacija() {
        return lokacija;
    }

    public void setLokacija(lokacijaSchema lokacija) {
        this.lokacija = lokacija;
    }
}
