package model;

import java.util.ArrayList;

public class Cititor extends Persoana {

    private ArrayList<Imprumut> imprumuturiCarti;
    private ArrayList<Rezervare> rezervariCarti;

    public Cititor(){}

    public Cititor(String prenume, String nume) {

        super(prenume, nume);

        this.imprumuturiCarti = new ArrayList<>();
        this.rezervariCarti = new ArrayList<>();
    }

    public ArrayList<Imprumut> getImprumuturiCarti() {
        return imprumuturiCarti;
    }

    public void setImprumuturiCarti(ArrayList<Imprumut> imprumuturiCarti) {
        this.imprumuturiCarti = imprumuturiCarti;
    }

    public ArrayList<Rezervare> getRezervariCarti() {
        return rezervariCarti;
    }

    public void setRezervariCarti(ArrayList<Rezervare> rezervariCarti) {
        this.rezervariCarti = rezervariCarti;
    }

    @Override
    public String toString() {
        return "Cititor{" +
                "id=" + id +
                ", prenume='" + prenume + '\'' +
                ", nume='" + nume + '\'' +
                '}';
    }
}
