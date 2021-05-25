package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Carte {

    private int id;
    private String titlu;
    private int idSectiune;
    private int idAutor;
    private boolean esteImprumutata;

    public Carte(int id, String titlu, int idSectiune, int idAutor, boolean esteImprumutata) {
        this.id = id;
        this.titlu = titlu;
        this.idSectiune = idSectiune;
        this.idAutor = idAutor;
        this.esteImprumutata = esteImprumutata;
    }

    public Carte(String titlu, int idSectiune, int idAutor) {
        this.titlu = titlu;
        this.idSectiune = idSectiune;
        this.idAutor = idAutor;
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

    public int getIdSectiune() {
        return idSectiune;
    }

    public void setSectiune(int idSectiune) {
        this.idSectiune = idSectiune;
    }

    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public boolean isEsteImprumutata() {
        return esteImprumutata;
    }

    public void setEsteImprumutata(boolean esteImprumutata) {
        this.esteImprumutata = esteImprumutata;
    }

    @Override
    public String toString() {
        return "Carte{" +
                "id=" + id +
                ", titlu='" + titlu + '\'' +
                ", idSectiune=" + idSectiune +
                ", idAutor=" + idAutor +
                ", esteImprumutata=" + esteImprumutata +
                '}';
    }
}
