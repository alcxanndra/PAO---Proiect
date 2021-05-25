package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Imprumut {

    private int idImprumut;
    private int idCititor;
    private int idCarte;

    private Date dataImprumut;
    private Date dataDeadlineReturnare;
    private Date dataActualaReturnare;

    public Imprumut(int idCititor, int idCarte, Date dataImprumut, Date dataDeadlineReturnare, Date dataActualaReturnare) {
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataImprumut = dataImprumut;
        this.dataDeadlineReturnare = dataDeadlineReturnare;
        this.dataActualaReturnare = dataActualaReturnare;
    }

    public Imprumut(int idImprumut, int idCititor, int idCarte, Date dataImprumut, Date dataDeadlineReturnare, Date dataActualaReturnare) {
        this.idImprumut = idImprumut;
        this.idCititor = idCititor;
        this.idCarte = idCarte;
        this.dataImprumut = dataImprumut;
        this.dataDeadlineReturnare = dataDeadlineReturnare;
        this.dataActualaReturnare = dataActualaReturnare;
    }

    public int getIdImprumut() {
        return idImprumut;
    }

    public void setIdImprumut(int idImprumut) {
        this.idImprumut = idImprumut;
    }

    public int getIdCititor() {
        return idCititor;
    }

    public void setIdCititor(int idCititor) {
        this.idCititor = idCititor;
    }

    public int getIdCarte() {
        return idCarte;
    }

    public void setIdCarte(int idCarte) {
        this.idCarte = idCarte;
    }

    public Date getDataImprumut() {
        return dataImprumut;
    }

    public void setDataImprumut(Date dataImprumut) {
        this.dataImprumut = dataImprumut;
    }

    public Date getDataDeadlineReturnare() {
        return dataDeadlineReturnare;
    }

    public void setDataDeadlineReturnare(Date dataDeadlineReturnare) {
        this.dataDeadlineReturnare = dataDeadlineReturnare;
    }

    public Date getDataActualaReturnare() {
        return dataActualaReturnare;
    }

    public void setDataActualaReturnare(Date dataActualaReturnare) {
        this.dataActualaReturnare = dataActualaReturnare;
    }
}
