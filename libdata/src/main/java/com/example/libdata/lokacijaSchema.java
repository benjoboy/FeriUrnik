package com.example.libdata;

import java.nio.Buffer;

public class lokacijaSchema {
    String zgradba;
    String nadstropje;
    Buffer zemljevid;

    public lokacijaSchema(String zgradba, String nadstropje) {
        this.zgradba = zgradba;
        this.nadstropje = nadstropje;
    }

    public lokacijaSchema(String zgradba, String nadstropje, Buffer zemljevid) {
        this.zgradba = zgradba;
        this.nadstropje = nadstropje;
        this.zemljevid = zemljevid;
    }

    public void setZgradba(String zgradba) {
        this.zgradba = zgradba;
    }

    public void setNadstropje(String nadstropje) {
        this.nadstropje = nadstropje;
    }

    public void setZemljevid(Buffer zemljevid) {
        this.zemljevid = zemljevid;
    }

    public String getZgradba() {
        return zgradba;
    }

    public String getNadstropje() {
        return nadstropje;
    }

    public Buffer getZemljevid() {
        return zemljevid;
    }
}
