package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Imprumut {

    private Cititor cititor;
    private Carte carte;

    private Date dataImprumut;
    private Date dataDeadlineReturnare;
    private Date dataActualaReturnare;

    public Imprumut(Cititor cititor, Carte carte, Date dataImprumut, Date dataDeadlineReturnare, Date dataActualaReturnare) {
        this.cititor = cititor;
        this.carte = carte;
        this.dataImprumut = dataImprumut;
        this.dataDeadlineReturnare = dataDeadlineReturnare;
        this.dataActualaReturnare = dataActualaReturnare;
    }

    public Cititor getCititor() {
        return cititor;
    }

    public void setCititor(Cititor cititor) {
        this.cititor = cititor;
    }

    public Carte getCarte() {
        return carte;
    }

    public void setCarte(Carte carte) {
        this.carte = carte;
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

    @Override
    public String toString() {

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String dataImprumutStr = dateFormat.format(dataImprumut);
        String dataDeadlineReturnareStr = dateFormat.format(dataDeadlineReturnare);
        String dataActualaReturnareStr = dateFormat.format(dataActualaReturnare);



        return "Imprumut{" +
                "cititor=" + cititor +
                ", carte=" + carte +
                ", dataImprumut=" + dataImprumutStr +
                ", dataDeadlineReturnare=" + dataDeadlineReturnareStr +
                ", dataActualaReturnare=" + dataActualaReturnareStr +
                '}';
    }
}
