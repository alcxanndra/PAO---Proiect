package repository.actions;

import model.Cititor;
import model.Imprumut;
import model.Rezervare;

import java.util.List;

public interface ReaderRepository {

    List<Cititor> returneazaCititoriAlfabetic();
    List<Imprumut> returneazaImprumuturiCititor(int idCititor);
    List<Rezervare> returneazaRezervariCititor(int idCititor);
    int adaugaCititor(Cititor cititor);
    void stergeCititor(int idCititor);

}
