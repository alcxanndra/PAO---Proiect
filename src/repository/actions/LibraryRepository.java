package repository.actions;

import model.*;

import java.util.HashMap;
import java.util.List;

public interface LibraryRepository {

    List<Carte> returneazaCartiAlfabetic();

    List<Autor> returneazaAutoriAlfabetic();

    List<Sectiune> returneazaSectiuniAlfabetic();

    HashMap<Sectiune, Integer> returneazaSectiuniDupaNrCarti();

    List<AngajatBiblioteca> returneazaAngajatiAlfabetic();

    List<AngajatBiblioteca> returneazaAngajatiDupaSalariu();

    List<Cititor> returneazaCititoriAlfabetic();

    List<Imprumut> returneazaImprumuturiCititor(int idCititor);

    List<Rezervare> returneazaRezervariCititor(int idCititor);

    int gasesteIdAutor(String prenumeAutor, String numeAutor);

    int gasesteIdSectiune(String titluSectiune);

    int adaugaCarte(Carte carte);

    int adaugaAutor(Autor autor);

    int adaugaSectiune(Sectiune sectiune);

    int adaugaCititor(Cititor cititor);

    int adaugaAngajatBiblioteca(AngajatBiblioteca angajat);

    int adaugaImprumut(Imprumut imprumut);

    int adaugaRezervare(Rezervare rezervare);

    boolean esteCarteImprumutata(int idCarte);

    boolean esteCarteRezervata(int idCarte, String dataRezervare, int idCititorCareRezerva);

    int cautaEntitate(int idEntitate, String getQuery);

    int cautaImprumut(int idCititor, int idCarte);

    List<Carte> cautaCartiAutor(int idAutor);

    void modificaTitluCarte(int idCarte, String titluNou);

    void modificaStatusCarte(int idCarte, int statusNou);

    void modificaSectiuneCarte(int idCarte, int idNouSectiune);

    void modificaAutorCarte(int idCarte, int idNouAutor);

    void modificaNumeSectiune(int idSectiune, String titluNou);

    void modificaSalariuAngajat(int idAngajat, double salariuNou);

    void modificaDataActualaReturnareImprumut(int idImprumut, String dataActualaReturnare);

    List<String> afiseazaInformatiiCarte(int idCarte);

    List<Rezervare> afiseazaRezervariCarte(int idCarte);

    void stergeCarte(int idCarte);

    void stergeAutor(int idAutor);

    void stergeSectiune(int idSectiune);

    void stergeCititor(int idCititor);

    void stergeAngajatBiblioteca(int idAngajat);

    void stergeRezervare(int idRezervare);

}