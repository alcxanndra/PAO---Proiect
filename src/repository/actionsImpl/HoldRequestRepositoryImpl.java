package repository.actionsImpl;

import model.Rezervare;
import repository.actions.HoldRequestRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static utils.Queries.DELETE_HOLD_REQUEST;
import static utils.Queries.INSERT_NEW_HOLD_REQUEST;

public class HoldRequestRepositoryImpl implements HoldRequestRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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

}
