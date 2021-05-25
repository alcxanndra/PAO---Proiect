package model;

import java.util.ArrayList;

public class Sectiune implements Comparable{

    private int id;
    private String nume;

    public Sectiune(){}

    public Sectiune(String nume) {
        this.nume = nume;
    }

    public Sectiune(int id, String nume) {
        this.id = id;
        this.nume = nume;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    @Override
    public String toString() {
        return "Sectiune{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Sectiune that = (Sectiune) o;
        return this.getNume().compareTo(that.getNume());
    }

}
