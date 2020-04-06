package com.example.libdata;

import java.util.Date;

public class izpitSchema {
    Date datum;
    Date prijava;
    prostorSchema prostor;

    public izpitSchema(Date datum, Date prijava, prostorSchema prostor) {
        this.datum = datum;
        this.prijava = prijava;
        this.prostor = prostor;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Date getPrijava() {
        return prijava;
    }

    public void setPrijava(Date prijava) {
        this.prijava = prijava;
    }

    public prostorSchema getProstor() {
        return prostor;
    }

    public void setProstor(prostorSchema prostor) {
        this.prostor = prostor;
    }
}
