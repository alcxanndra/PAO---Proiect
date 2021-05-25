package repository.actionsImpl;

import model.*;
import repository.actions.LibraryRepository;
import utils.DbConnection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import static utils.Queries.*;


public class LibraryRepositoryImpl implements LibraryRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

    @Override
    public List<Carte> returneazaCartiAlfabetic() {
        List<Carte> bookList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Carte book = new Carte(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getInt(4), resultSet.getBoolean(5));
                bookList.add(book);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    @Override
    public List<Autor> returneazaAutoriAlfabetic() {
        List<Autor> authorList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_AUTHORS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Autor autor = new Autor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                authorList.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

    @Override
    public List<Sectiune> returneazaSectiuniAlfabetic() {
        List<Sectiune> sectionList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_SECTIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sectiune s = new Sectiune(resultSet.getInt(1), resultSet.getString(2));
                sectionList.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public LinkedHashMap<Sectiune, Integer> returneazaSectiuniDupaNrCarti() {
        LinkedHashMap<Sectiune, Integer> sectionList = new LinkedHashMap<Sectiune, Integer>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_SECTIONS_BY_NR_OF_BOOKS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Sectiune s = new Sectiune(resultSet.getInt(1), resultSet.getString(2));
                sectionList.put(s, resultSet.getInt(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sectionList;
    }

    @Override
    public List<AngajatBiblioteca> returneazaAngajatiAlfabetic() {
        List<AngajatBiblioteca> employeeList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_EMPLOYEES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AngajatBiblioteca a = new AngajatBiblioteca(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
                employeeList.add(a);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public List<AngajatBiblioteca> returneazaAngajatiDupaSalariu() {
        List<AngajatBiblioteca> employeeList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_EMPLOYEES_BY_SALARY_DESC);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                AngajatBiblioteca a = new AngajatBiblioteca(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4));
                employeeList.add(a);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public List<Cititor> returneazaCititoriAlfabetic() {
        List<Cititor> readerList = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_ALL_READERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Cititor c = new Cititor(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                readerList.add(c);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return readerList;
    }

    @Override
    public List<Imprumut> returneazaImprumuturiCititor(int idCititor) {
        List<Imprumut> imprumuturiCititor = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_READER_LOANS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Imprumut i = new Imprumut(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDate(4),
                        resultSet.getDate(5), resultSet.getDate(6));
                imprumuturiCititor.add(i);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return imprumuturiCititor;
    }

    @Override
    public List<Rezervare> returneazaRezervariCititor(int idCititor) {
        List<Rezervare> rezervariCititor = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_READER_HOLD_REQUESTS);
            preparedStatement.setString(1,String.valueOf(idCititor));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rezervare r = new Rezervare(resultSet.getInt(1), resultSet.getInt(2),
                        resultSet.getInt(3), resultSet.getDate(4));
                rezervariCititor.add(r);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rezervariCititor;
    }

    @Override
    public int gasesteIdAutor(String prenumeAutor, String numeAutor){
        int idAutor = -1;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_AUTHOR_ID_BY_NAME);
            preparedStatement.setString(1, prenumeAutor);
            preparedStatement.setString(2, numeAutor);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idAutor = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return idAutor;
    }

    @Override
    public int gasesteIdSectiune(String titluSectiune){
        int idSectiune = -1;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_SECTION_ID_BY_NAME);
            preparedStatement.setString(1, titluSectiune);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idSectiune = resultSet.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return idSectiune;
    }

    @Override
    public int adaugaCarte(Carte carte) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_BOOK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, carte.getTitlu());
            preparedStatement.setString(2, String.valueOf(carte.getIdAutor()));
            preparedStatement.setString(3, String.valueOf(carte.getIdSectiune()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea cărții în sistem!");
        }
    }

    @Override
    public int adaugaAutor(Autor autor) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, autor.getPrenume());
            preparedStatement.setString(2, autor.getNume());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea autorului în sistem!");
        }
    }

    @Override
    public int adaugaSectiune(Sectiune sectiune) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_SECTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, sectiune.getNume());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea secțiunii în sistem!");
        }
    }

    @Override
    public int adaugaAngajatBiblioteca(AngajatBiblioteca angajat) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_LIBRARY_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, angajat.getPrenume());
            preparedStatement.setString(2, angajat.getNume());
            preparedStatement.setString(3, String.valueOf(angajat.getSalariu()));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea angajatului în sistem!");
        }
    }

    @Override
    public int adaugaCititor(Cititor cititor) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_READER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, cititor.getPrenume());
            preparedStatement.setString(2, cititor.getNume());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea cititorului în sistem!");
        }
    }

    @Override
    public int adaugaRezervare(Rezervare rezervare){
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_HOLD_REQUEST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(rezervare.getIdCititor()));
            preparedStatement.setString(2, String.valueOf(rezervare.getIdCarte()));
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataRezervare = dateFormat.format(rezervare.getDataRezervare());
            preparedStatement.setString(3, dataRezervare);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("A survenit o eroare la adăugarea rezervării în sistem!");
        }
    }

    @Override
    public int adaugaImprumut(Imprumut imprumut) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(INSERT_NEW_LOAN, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(imprumut.getIdCititor()));
            preparedStatement.setString(2, String.valueOf(imprumut.getIdCarte()));
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dataImprumut = dateFormat.format(imprumut.getDataImprumut());
            String dataDeadlineReturnare = dateFormat.format(imprumut.getDataDeadlineReturnare());
            preparedStatement.setString(3, dataImprumut);
            preparedStatement.setString(4, dataDeadlineReturnare);

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            return Integer.parseInt(resultSet.getString(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la adăugarea împrumutului în sistem!");
        }
    }

    @Override
    public void stergeCarte(int idCarte) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_BOOK, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idCarte));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a întâmpinat o problemă la ștergerea cărții din sistem!");
        }
    }

    @Override
    public void stergeAutor(int idAutor) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_AUTHOR, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idAutor));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e) {
            throw new RuntimeException("Autorul cerut nu poate fi șters deoarece există cărți scrise de acesta în sistem!");
        }
    }

    @Override
    public void stergeSectiune(int idSectiune) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_SECTION, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idSectiune));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e) {
            throw new RuntimeException("Secțiunea introdusă nu poate fi ștearsă din sistem deoarece există cărți care aparțin de ea!");
        }
    }

    @Override
    public void stergeAngajatBiblioteca(int idAngajat) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_EMPLOYEE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idAngajat));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e) {
            throw new RuntimeException("Angajatul cu ID-ul introdus nu poate fi șters din sistem deoarece există entități care depind de el!");
        }
    }

    @Override
    public void stergeCititor(int idCititor) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_READER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idCititor));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        }catch (SQLException e) {
            throw new RuntimeException("Cititorul cu ID-ul introdus nu poate fi șters din sistem deoarece există entități care depind de el!");
        }
    }

    @Override
    public void stergeRezervare(int idRezervare) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(DELETE_HOLD_REQUEST, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(idRezervare));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("S-a produs o eroare la încercarea ștergerii rezervării din sistem!");
        }
    }

    @Override
    public boolean esteCarteImprumutata(int idCarte) {
        boolean esteImprumutata = false;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_BOOK_LOAN_STATUS);
            preparedStatement.setString(1, String.valueOf(idCarte));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getBoolean(1) == true){
                    esteImprumutata = true;
                }

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return esteImprumutata;
    }

    @Override
    public boolean esteCarteRezervata(int idCarte, String dataRezervare, int idCititorCareRezerva) {
        boolean esteRezervata = false;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_BOOK_RESERVED_STATUS);
            preparedStatement.setString(1, String.valueOf(idCarte));
            preparedStatement.setString(2, dataRezervare);
            preparedStatement.setString(3, String.valueOf(idCititorCareRezerva));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                    esteRezervata = true;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return esteRezervata;
    }

    @Override
    public List<String> afiseazaInformatiiCarte(int idCarte) {
        List<String> infoCarte = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_BOOK_INFO);
            preparedStatement.setString(1, String.valueOf(idCarte));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                infoCarte.add(resultSet.getString(1));
                infoCarte.add(resultSet.getString(2));
                infoCarte.add(resultSet.getString(3));
                infoCarte.add(resultSet.getString(4));

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return infoCarte;
    }

    @Override
    public List<Rezervare> afiseazaRezervariCarte(int idCarte) {
        List<Rezervare> rezervariCarte = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(GET_BOOK_HOLD_REQUESTS);
            preparedStatement.setString(1, String.valueOf(idCarte));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Rezervare r = new Rezervare(resultSet.getInt(1), resultSet.getInt(3), resultSet.getInt(2),
                        resultSet.getDate(4));
                rezervariCarte.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rezervariCarte;
    }

    @Override
    public int cautaEntitate(int idEntitate, String getQuery) {
        int existaEntitate = -1;

        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(getQuery);
            preparedStatement.setString(1, String.valueOf(idEntitate));

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                existaEntitate = 1;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return existaEntitate;
    }

    @Override
    public int cautaImprumut(int idCititor, int idCarte) {
        int idImprumut = -1;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_LOAN_BY_READER_BOOK);
            preparedStatement.setString(1, String.valueOf(idCititor));
            preparedStatement.setString(2, String.valueOf(idCarte));

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                idImprumut = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return idImprumut;
    }

    @Override
    public List<Carte> cautaCartiAutor(int idAutor) {
        List<Carte> cartiAutor = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(RETRIEVE_AUTHOR_BOOKS);
            preparedStatement.setString(1, String.valueOf(idAutor));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Carte c = new Carte(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3),
                        resultSet.getInt(4), resultSet.getBoolean(5));
                cartiAutor.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cartiAutor;
    }

    @Override
    public void modificaTitluCarte(int idCarte, String titluNou) {
        ResultSet resultSet;
        try {
            modificaParamCarte(idCarte, titluNou, UPDATE_BOOK_TITLE);
            System.out.println("Titlul cărții cu id-ul " + idCarte + " a fost modificat cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificaDataActualaReturnareImprumut(int idImprumut, String dataActualaReturnare) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(UPDATE_LOAN_RETURN_DATE, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, dataActualaReturnare);
            preparedStatement.setString(2, String.valueOf(idImprumut));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            System.out.println("Data actuală de returnare a împrumutului cu id-ul " + idImprumut + " a fost actualizată cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificaSectiuneCarte(int idCarte, int idNouSectiune) {
        ResultSet resultSet;
        try {
            modificaParamCarte(idCarte, String.valueOf(idNouSectiune), UPDATE_BOOK_SECTION);
            System.out.println("Secțiunea cărții cu id-ul " + idCarte + " a fost modificată cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificaStatusCarte(int idCarte, int statusNou) {
        ResultSet resultSet;
        try {
            modificaParamCarte(idCarte, String.valueOf(statusNou), UPDATE_BOOK_STATUS);
            System.out.println("Statusul cărții cu id-ul " + idCarte + " a fost modificat cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificaAutorCarte(int idCarte, int idNouAutor) {
        ResultSet resultSet;
        try {
            modificaParamCarte(idCarte, String.valueOf(idNouAutor), UPDATE_BOOK_AUTHOR);
            System.out.println("Autorul cărții cu id-ul " + idCarte + " a fost modificat cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void modificaParamCarte(int idCarte, String paramNou, String updateQuery) throws SQLException {
        ResultSet resultSet;
        PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(updateQuery, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, paramNou);
        preparedStatement.setString(2, String.valueOf(idCarte));

        preparedStatement.executeUpdate();
        resultSet = preparedStatement.getGeneratedKeys();
        resultSet.next();
    }

    @Override
    public void modificaNumeSectiune(int idSectiune, String numeNou) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(UPDATE_SECTION_NAME, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, numeNou);
            preparedStatement.setString(2, String.valueOf(idSectiune));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            System.out.println("Numele secțiunii cu id-ul " + idSectiune + " a fost modificat cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void modificaSalariuAngajat(int idAngajat, double salariuNou) {
        ResultSet resultSet;
        try {
            PreparedStatement preparedStatement = dbConnection.getDBConnection().prepareStatement(UPDATE_EMPLOYEE_SALARY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, String.valueOf(salariuNou));
            preparedStatement.setString(2, String.valueOf(idAngajat));

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            resultSet.next();
            System.out.println("Salariul angajatului cu id-ul " + idAngajat + " a fost actualizat cu succes!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }


}