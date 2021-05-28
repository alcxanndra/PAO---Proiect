package repository.actions;

import model.Carte;
import model.Rezervare;

import java.util.List;

public interface BookRepository {

    List<Carte> returneazaCartiAlfabetic();
    int adaugaCarte(Carte carte);
    boolean esteCarteImprumutata(int idCarte);
    boolean esteCarteRezervata(int idCarte, String dataRezervare, int idCititorCareRezerva);
    void modificaTitluCarte(int idCarte, String titluNou);
    void modificaStatusCarte(int idCarte, int statusNou);
    void modificaSectiuneCarte(int idCarte, int idNouSectiune);
    void modificaAutorCarte(int idCarte, int idNouAutor);
    List<String> afiseazaInformatiiCarte(int idCarte);
    List<Rezervare> afiseazaRezervariCarte(int idCarte);
    void stergeCarte(int idCarte);

}
