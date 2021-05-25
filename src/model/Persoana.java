package model;

import java.util.Date;

public class Persoana {
    protected int id;
    protected String prenume;
    protected String nume;

    public Persoana(){}

    public Persoana(int id, String prenume, String nume){
        this.id = id;
        this.prenume = prenume;
        this.nume = nume;
    }

    public Persoana(String prenume, String nume){
        this.prenume = prenume;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
