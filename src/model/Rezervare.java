package model;

import java.util.Date;

public class Rezervare {

    private Cititor cititor;
    private Carte carte;
    private Date dataRezervare;

    public Rezervare(Cititor cititor, Carte carte, Date dataRezervare) {
        this.cititor = cititor;
        this.carte = carte;
        this.dataRezervare = dataRezervare;
    }

    public Cititor getCititor() {
        return cititor;
    }

    public void setCititor(Cititor cititor) {
        this.cititor = cititor;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
    }

    public Date getDataRezervare() {
        return dataRezervare;
    }

    public void setDataRezervare(Date dataRezervare) {
        this.dataRezervare = dataRezervare;
    }

    @Override
    public String toString() {
        return "Rezervare{" +
                "cititor=" + cititor +
                ", carte=" + carte +
                ", dataRezervare=" + dataRezervare +
                '}';
    }
}
