package model;

import java.util.ArrayList;

public class Cititor extends Persoana {

    public Cititor(){}

    public Cititor(String prenume, String nume) {
        super(prenume, nume);
    }

    public Cititor(int id, String prenume, String nume) {
        super(id, prenume, nume);
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
