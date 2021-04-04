package model;

import java.util.ArrayList;

public class Sectiune implements Comparable{

    private int id;
    private String nume;
    private ArrayList<Carte> cartiSectiune;

    static int nrIdCurent = 0;

    public Sectiune(){}

    public Sectiune(String nume) {

        nrIdCurent++;

        this.id = nrIdCurent;
        this.nume = nume;
        this.cartiSectiune = new ArrayList<Carte>();
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

    public static int getNrIdCurent() {
        return nrIdCurent;
    }

    public static void setNrIdCurent(int nrIdCurent) {
        Sectiune.nrIdCurent = nrIdCurent;
    }

    public ArrayList<Carte> getCartiSectiune() {
        return cartiSectiune;
    }

    public void setCartiSectiune(ArrayList<Carte> cartiSectiune) {
        this.cartiSectiune = cartiSectiune;
    }

    public String cartiToString(ArrayList<Carte> cartiScrise) {
        String listString = "";
        for (Carte c : cartiScrise) {
            listString += c.toString() + "\t";
        }
        return listString;
    }

    @Override
    public String toString() {
        return "Sectiune{" +
                "id=" + id +
                ", nume='" + nume + '\'' +
                ", cartiSectiune=" + cartiToString(cartiSectiune) +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Sectiune that = (Sectiune) o;
        return this.getNume().compareTo(that.getNume());
    }

}
