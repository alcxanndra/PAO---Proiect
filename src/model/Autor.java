package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Autor extends Persoana implements Comparable{

    private ArrayList<Carte> cartiScrise;

    public Autor(){
        super();
    }

    public Autor(String prenume, String nume) {
        super(prenume, nume);
        this.cartiScrise = new ArrayList<>();
    }

    public ArrayList<Carte> getCartiScrise() {
        return cartiScrise;
    }

    public void setCartiScrise(ArrayList<Carte> cartiScrise) {
        this.cartiScrise = cartiScrise;
    }

    public List<String> cartiToString(ArrayList<Carte> cartiScrise) {
//        String str = "";
//        for (int i = 0; i < cartiScrise.size(); i++){
//            str += cartiScrise.get(i) + "\t";
//        }
//        return str;
        List<String> strings = new ArrayList<>(cartiScrise.size());
        for (Carte carte : cartiScrise) {
            strings.add(Objects.toString(carte, null));
        }
        return strings;
    }

    @Override
    public String toString() {
        return "Autor{" +
                ", id=" + id +
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
