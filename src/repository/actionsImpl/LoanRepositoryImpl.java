package repository.actionsImpl;

import model.Imprumut;
import repository.actions.LoanRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static utils.Queries.*;

public class LoanRepositoryImpl implements LoanRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
