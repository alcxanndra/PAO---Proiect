package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Rezervare {

    private int idRezervare;
    private int idCititor;
    private int idCarte;
    private Date dataRezervare;

    public Rezervare(int idRezervare, int idCititor, int idCarte, Date dataRezervare) {
        this.idRezervare = idRezervare;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataRezervare = dataRezervare;
    }

    public Rezervare(int idCititor, int idCarte, Date dataRezervare) {
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataRezervare = dataRezervare;
    }

    public int getIdRezervare() {
        return idRezervare;
    }

    public void setIdRezervare(int idRezervare) {
        this.idRezervare = idRezervare;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public void setIdCititor(int idCititor) {
        this.idCititor = idCititor;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
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
                "idRezervare=" + idRezervare +
                ", idCititor=" + idCititor +
                ", idCarte=" + idCarte +
                ", dataRezervare=" + dataRezervare +
                '}';
    }
}
