package repository.actionsImpl;

import model.Autor;
import model.Carte;
import repository.actions.AuthorRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class AuthorRepositoryImpl implements AuthorRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
