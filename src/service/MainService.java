package service;

import model.*;
import static utils.Queries.*;
import repository.actions.LibraryRepository;
import repository.actionsImpl.LibraryRepositoryImpl;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainService {

    private LibraryRepository libraryRepository  = new LibraryRepositoryImpl();

    public MainService() { }

    public List<Carte> returneazaCartiAlfabetic(){
        return libraryRepository.returneazaCartiAlfabetic();
    }

    public List<Autor> returneazaAutoriAlfabetic(){
        return libraryRepository.returneazaAutoriAlfabetic();
    }

    public List<Sectiune> returneazaSectiuniAlfabetic(){
        return libraryRepository.returneazaSectiuniAlfabetic();
    }

    public HashMap<Sectiune, Integer> returneazaSectiuniDupaNrCarti(){
        return libraryRepository.returneazaSectiuniDupaNrCarti();
    }

    public List<AngajatBiblioteca> returneazaAngajatiAlfabetic(){
        return libraryRepository.returneazaAngajatiAlfabetic();
    }

    public List<AngajatBiblioteca> returneazaAngajatiDupaSalariu(){
        return libraryRepository.returneazaAngajatiDupaSalariu();
    }

    public List<Cititor> returneazaCititoriAlfabetic(){
        return libraryRepository.returneazaCititoriAlfabetic();
    }

    public List<Imprumut> returneazaImprumuturiCititor(int idCititor){
        return libraryRepository.returneazaImprumuturiCititor(idCititor);
    }

    public List<Rezervare> returneazaRezervariCititor(int idCititor){
        return libraryRepository.returneazaRezervariCititor(idCititor);
    }

    public int adaugaCarte(Carte carte){
        return libraryRepository.adaugaCarte(carte);
    }

    public int adaugaAutor(Autor autor){
        return libraryRepository.adaugaAutor(autor);
    }

    public int adaugaSectiune(Sectiune sectiune){
        return libraryRepository.adaugaSectiune(sectiune);
    }

    public int adaugaAngajatBiblioteca(AngajatBiblioteca angajat){
        return libraryRepository.adaugaAngajatBiblioteca(angajat);
    }

    public int adaugaCititor(Cititor cititor){
        return libraryRepository.adaugaCititor(cititor);
    }

    public int adaugaImprumut(Imprumut imprumut){
        return libraryRepository.adaugaImprumut(imprumut);
    }

    public int adaugaRezervare(Rezervare rezervare){
        return libraryRepository.adaugaRezervare(rezervare);
    }

    public void stergeCarte(int idCarte){
        libraryRepository.stergeCarte(idCarte);
    }

    public void stergeAutor(int idAutor){
        libraryRepository.stergeAutor(idAutor);
    }

    public void stergeSectiune(int idSectiune){
        libraryRepository.stergeSectiune(idSectiune);
    }

    public void stergeAngajatBiblioteca(int idAngajat){
        libraryRepository.stergeAngajatBiblioteca(idAngajat);
    }

    public void stergeCititor(int idCititor){
        libraryRepository.stergeCititor(idCititor);
    }

    public void stergeRezervare(int idRezervare){
        libraryRepository.stergeRezervare(idRezervare);
    }

    public int cautaCarte(int idCarte){
        return  libraryRepository.cautaEntitate(idCarte, GET_BOOK_BY_ID);
    }

    public int cautaAutor(int idAutor){
        return  libraryRepository.cautaEntitate(idAutor, GET_AUTHOR_BY_ID);
    }

    public int cautaSectiune(int idSectiune){
        return  libraryRepository.cautaEntitate(idSectiune, GET_SECTION_BY_ID);
    }

    public int cautaAngajatBiblioteca(int idAngajat){
        return libraryRepository.cautaEntitate(idAngajat, GET_EMPLOYEE_BY_ID);
    }

    public int cautaCititor(int idCititor){
        return libraryRepository.cautaEntitate(idCititor, GET_READER_BY_ID);
    }

    public int cautaImprumut(int idCititor, int idCarte){
        return libraryRepository.cautaImprumut(idCititor, idCarte);
    }

    public int cautaRezervare(int idRezervare){
        return libraryRepository.cautaEntitate(idRezervare, GET_HOLD_REQUEST_BY_ID);
    }

    public List<Carte> cautaCartiAutor(int idAutor){
        return  libraryRepository.cautaCartiAutor(idAutor);
    }

    public void modificaTitluCarte(int idCarte, String titluNou){
        libraryRepository.modificaTitluCarte(idCarte, titluNou);
    }

    public void modificaSectiuneCarte(int idCarte, int idNouSectiune){
        libraryRepository.modificaSectiuneCarte(idCarte, idNouSectiune);
    }

    public void modificaAutorCarte(int idCarte, int idNouAutor){
        libraryRepository.modificaAutorCarte(idCarte, idNouAutor);
    }

    public void modificaNumeSectiune(int idSectiune, String numeNou){
        libraryRepository.modificaNumeSectiune(idSectiune, numeNou);
    }

    public void modificaSalariuAngajat(int idAngajat, double salariuNou){
        libraryRepository.modificaSalariuAngajat(idAngajat, salariuNou);
    }

    public void modificaDataActualaReturnareImprumut(int idImprumut, String dataReturnare){
        libraryRepository.modificaDataActualaReturnareImprumut(idImprumut, dataReturnare);
    }

    public void modificaStatusCarte(int idCarte, int statusNou){
        libraryRepository.modificaStatusCarte(idCarte, statusNou);
    }


    public List<String> afiseazaInformatiiCarte(int idCarte){
        return libraryRepository.afiseazaInformatiiCarte(idCarte);
    }

    public List<Rezervare> afiseazaRezervariCarte(int idCarte){
        return libraryRepository.afiseazaRezervariCarte(idCarte);
    }

    public int gasesteIdAutor(String prenumeAutor, String numeAutor){
        return libraryRepository.gasesteIdAutor(prenumeAutor, numeAutor);
    }

    public int gasesteIdSectiune(String titluSectiune){
        return libraryRepository.gasesteIdSectiune(titluSectiune);
    }

    public boolean esteCarteImprumutata(int idCarte){
        return libraryRepository.esteCarteImprumutata(idCarte);
    }

    public boolean esteCarteRezervata(int idCarte, Date dataRezervare, int idCititorCareRezerva){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataRezervareStr = dateFormat.format(dataRezervare);
        return libraryRepository.esteCarteRezervata(idCarte, dataRezervareStr, idCititorCareRezerva);
    }
}
