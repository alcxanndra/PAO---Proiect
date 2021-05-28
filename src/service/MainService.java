package service;

import model.*;
import static utils.Queries.*;

import repository.actions.*;
import repository.actionsImpl.*;

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

    private AuthorRepository authorRepository  = new AuthorRepositoryImpl();
    private BookRepository bookRepository  = new BookRepositoryImpl();
    private SectionRepository sectionRepository  = new SectionRepositoryImpl();
    private ReaderRepository readerRepository  = new ReaderRepositoryImpl();
    private LibraryEmployeeRepository libEmployeeRepository = new LibraryEmployeeRepositoryImpl();
    private HoldRequestRepository holdRequestRepository = new HoldRequestRepositoryImpl();
    private LoanRepository loanRepository = new LoanRepositoryImpl();
    private CommonRepository commonRepository = new CommonRepositoryImpl();

    public MainService() { }

    public List<Carte> returneazaCartiAlfabetic(){
        return bookRepository.returneazaCartiAlfabetic();
    }

    public List<Autor> returneazaAutoriAlfabetic(){
        return authorRepository.returneazaAutoriAlfabetic();
    }

    public List<Sectiune> returneazaSectiuniAlfabetic(){
        return sectionRepository.returneazaSectiuniAlfabetic();
    }

    public LinkedHashMap<Sectiune, Integer> returneazaSectiuniDupaNrCarti(){
        return sectionRepository.returneazaSectiuniDupaNrCarti();
    }

    public List<AngajatBiblioteca> returneazaAngajatiAlfabetic(){
        return libEmployeeRepository.returneazaAngajatiAlfabetic();
    }

    public List<AngajatBiblioteca> returneazaAngajatiDupaSalariu(){
        return libEmployeeRepository.returneazaAngajatiDupaSalariu();
    }

    public List<Cititor> returneazaCititoriAlfabetic(){
        return readerRepository.returneazaCititoriAlfabetic();
    }

    public List<Imprumut> returneazaImprumuturiCititor(int idCititor){
        return readerRepository.returneazaImprumuturiCititor(idCititor);
    }

    public List<Rezervare> returneazaRezervariCititor(int idCititor){
        return readerRepository.returneazaRezervariCititor(idCititor);
    }

    public int adaugaCarte(Carte carte){
        return bookRepository.adaugaCarte(carte);
    }

    public int adaugaAutor(Autor autor){
        return authorRepository.adaugaAutor(autor);
    }

    public int adaugaSectiune(Sectiune sectiune){
        return sectionRepository.adaugaSectiune(sectiune);
    }

    public int adaugaAngajatBiblioteca(AngajatBiblioteca angajat){
        return libEmployeeRepository.adaugaAngajatBiblioteca(angajat);
    }

    public int adaugaCititor(Cititor cititor){
        return readerRepository.adaugaCititor(cititor);
    }

    public int adaugaImprumut(Imprumut imprumut){
        return loanRepository.adaugaImprumut(imprumut);
    }

    public int adaugaRezervare(Rezervare rezervare){
        return holdRequestRepository.adaugaRezervare(rezervare);
    }

    public void stergeCarte(int idCarte){
        bookRepository.stergeCarte(idCarte);
    }

    public void stergeAutor(int idAutor){
        authorRepository.stergeAutor(idAutor);
    }

    public void stergeSectiune(int idSectiune){
        sectionRepository.stergeSectiune(idSectiune);
    }

    public void stergeAngajatBiblioteca(int idAngajat){
        libEmployeeRepository.stergeAngajatBiblioteca(idAngajat);
    }

    public void stergeCititor(int idCititor){
        readerRepository.stergeCititor(idCititor);
    }

    public void stergeRezervare(int idRezervare){
        holdRequestRepository.stergeRezervare(idRezervare);
    }

    public int cautaCarte(int idCarte){
        return  commonRepository.cautaEntitate(idCarte, GET_BOOK_BY_ID);
    }

    public int cautaAutor(int idAutor){
        return  commonRepository.cautaEntitate(idAutor, GET_AUTHOR_BY_ID);
    }

    public int cautaSectiune(int idSectiune){
        return  commonRepository.cautaEntitate(idSectiune, GET_SECTION_BY_ID);
    }

    public int cautaAngajatBiblioteca(int idAngajat){
        return commonRepository.cautaEntitate(idAngajat, GET_EMPLOYEE_BY_ID);
    }

    public int cautaCititor(int idCititor){
        return commonRepository.cautaEntitate(idCititor, GET_READER_BY_ID);
    }

    public int cautaImprumut(int idCititor, int idCarte){
        return loanRepository.cautaImprumut(idCititor, idCarte);
    }

    public int cautaRezervare(int idRezervare){
        return commonRepository.cautaEntitate(idRezervare, GET_HOLD_REQUEST_BY_ID);
    }

    public List<Carte> cautaCartiAutor(int idAutor){
        return  authorRepository.cautaCartiAutor(idAutor);
    }

    public void modificaTitluCarte(int idCarte, String titluNou){
        bookRepository.modificaTitluCarte(idCarte, titluNou);
    }

    public void modificaSectiuneCarte(int idCarte, int idNouSectiune){
        bookRepository.modificaSectiuneCarte(idCarte, idNouSectiune);
    }

    public void modificaAutorCarte(int idCarte, int idNouAutor){
        bookRepository.modificaAutorCarte(idCarte, idNouAutor);
    }

    public void modificaNumeSectiune(int idSectiune, String numeNou){
        sectionRepository.modificaNumeSectiune(idSectiune, numeNou);
    }

    public void modificaSalariuAngajat(int idAngajat, double salariuNou){
        libEmployeeRepository.modificaSalariuAngajat(idAngajat, salariuNou);
    }

    public void modificaDataActualaReturnareImprumut(int idImprumut, String dataReturnare){
        loanRepository.modificaDataActualaReturnareImprumut(idImprumut, dataReturnare);
    }

    public void modificaStatusCarte(int idCarte, int statusNou){
        bookRepository.modificaStatusCarte(idCarte, statusNou);
    }

    public List<String> afiseazaInformatiiCarte(int idCarte){
        return bookRepository.afiseazaInformatiiCarte(idCarte);
    }

    public List<Rezervare> afiseazaRezervariCarte(int idCarte){
        return bookRepository.afiseazaRezervariCarte(idCarte);
    }

    public int gasesteIdAutor(String prenumeAutor, String numeAutor){
        return authorRepository.gasesteIdAutor(prenumeAutor, numeAutor);
    }

    public int gasesteIdSectiune(String titluSectiune){
        return sectionRepository.gasesteIdSectiune(titluSectiune);
    }

    public boolean esteCarteImprumutata(int idCarte){
        return bookRepository.esteCarteImprumutata(idCarte);
    }

    public boolean esteCarteRezervata(int idCarte, Date dataRezervare, int idCititorCareRezerva){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dataRezervareStr = dateFormat.format(dataRezervare);
        return bookRepository.esteCarteRezervata(idCarte, dataRezervareStr, idCititorCareRezerva);
    }
}
