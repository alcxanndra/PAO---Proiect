package repository.actionsImpl;

import repository.actions.CommonRepository;
import utils.DbConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommonRepositoryImpl implements CommonRepository {

    private final DbConnection dbConnection = DbConnection.getInstance();

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
}
