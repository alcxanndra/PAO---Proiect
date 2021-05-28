package repository.actionsImpl;

import model.AngajatBiblioteca;
import repository.actions.LibraryEmployeeRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static utils.Queries.*;

public class LibraryEmployeeRepositoryImpl implements LibraryEmployeeRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
