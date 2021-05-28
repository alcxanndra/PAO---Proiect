package repository.actionsImpl;

import model.Carte;
import model.Rezervare;
import repository.actions.BookRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class BookRepositoryImpl implements BookRepository {

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
}
