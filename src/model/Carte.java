package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Carte {

    private int id;
    private String titlu;
    private Sectiune sectiune;
    private Autor autor;
    private boolean esteImprumutata;
    private ArrayList<Rezervare> rezervariCarte;

    static int nrIdCurent = 0;

    public Carte(String titlu, Sectiune sectiune, Autor autor, boolean esteImprumutata) {

        nrIdCurent ++;

        this.id = nrIdCurent;
        this.titlu = titlu;
        this.sectiune = sectiune;
        this.autor = autor;
        this.esteImprumutata = esteImprumutata;
        this.rezervariCarte = new ArrayList();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public Sectiune getSectiune() {
        return sectiune;
    }

    public void setSectiune(Sectiune sectiune) {
        this.sectiune = sectiune;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public boolean isEsteImprumutata() {
        return esteImprumutata;
    }

    public void setEsteImprumutata(boolean esteImprumutata) {
        this.esteImprumutata = esteImprumutata;
    }

    public ArrayList<Rezervare> getRezervariCarte() {
        return rezervariCarte;
    }

    public void setRezervariCarte(ArrayList<Rezervare> rezervariCarte) {
        this.rezervariCarte = rezervariCarte;
    }

    public static int getNrIdCurent() {
        return nrIdCurent;
    }

    public static void setNrIdCurent(int nrIdCurent) {
        Carte.nrIdCurent = nrIdCurent;
    }

    public String rezervariToString() {
        String s = "";
        for (Rezervare r : rezervariCarte) {
            s = s + r;
        }
        return s;
    }

    public boolean isEsteRezervata(Date dataDeVerificat, Cititor cititor) {

        for (Rezervare r : rezervariCarte) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String dataRezervareStr = dateFormat.format(r.getDataRezervare());
            String dataDeVerificatStr = dateFormat.format(dataDeVerificat);
            if (dataRezervareStr.equals(dataDeVerificatStr) && r.getCititor().equals(cititor) == false){
                return true;
            }
        }

        return false;
    }



    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", sectiune=" + sectiune.getNume() +
                ", autor=" + autor.getPrenume() + " " + autor.getNume() +
                ", esteImprumutata=" + esteImprumutata +
                '}';
    }
}
