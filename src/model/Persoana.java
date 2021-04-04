package model;

import java.util.Date;

public class Persoana {
    protected int id;
    protected String prenume;
    protected String nume;


    static int nrIdCurrent = 0;

    public Persoana(){}

    public Persoana(String prenume, String nume){

        nrIdCurrent++;

        this.id = nrIdCurrent;
        this.prenume = prenume;
        this.nume = nume;

    }

    public int getId() {
        return id;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public static int getNrIdCurrent() {
        return nrIdCurrent;
    }

    public static void setNrIdCurrent(int nrIdCurrent) {
        Persoana.nrIdCurrent = nrIdCurrent;
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
