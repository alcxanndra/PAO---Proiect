package repository.actionsImpl;

import model.Sectiune;
import repository.actions.SectionRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static utils.Queries.*;
import static utils.Queries.RETRIEVE_ALL_SECTIONS_BY_NR_OF_BOOKS;

public class SectionRepositoryImpl implements SectionRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
