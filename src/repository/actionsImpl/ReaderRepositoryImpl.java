package repository.actionsImpl;

import model.Cititor;
import model.Imprumut;
import model.Rezervare;
import repository.actions.ReaderRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;
import static utils.Queries.GET_READER_HOLD_REQUESTS;

public class ReaderRepositoryImpl implements ReaderRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
