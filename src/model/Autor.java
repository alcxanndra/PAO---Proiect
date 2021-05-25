package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Autor extends Persoana implements Comparable{

    public Autor(){
        super();
    }

    public Autor(String prenume, String nume) {
        super(prenume, nume);
    }

    public Autor(int id, String prenume, String nume) {
        super(id, prenume, nume);
    }


    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                '}';
    }

    public int compareTo(Object o) {
        Autor that = (Autor) o;
        if (this.getNume().equals(that.getNume())){
            return this.getPrenume().compareTo(that.getPrenume());
        }
        else {
            return this.getNume().compareTo(that.getNume());
        }
    }
}
