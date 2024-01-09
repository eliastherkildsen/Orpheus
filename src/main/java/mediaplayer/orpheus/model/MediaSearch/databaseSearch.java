package mediaplayer.orpheus.model.MediaSearch;

import mediaplayer.orpheus.model.Database.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class databaseSearch {

    // creating JDBC connection
    private static JDBC jdbc = new JDBC();
    private static Connection connection = jdbc.getConnection();
    public ResultSet searchInput(String table, String field, String searchCriteria){

        ResultSet resultSet = null;
        PreparedStatement preparedStatement;

        try {

            preparedStatement = connection.prepareCall("SELECT * FROM " + table + " WHERE " + field + " == " + searchCriteria);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet);
            }
        }catch (Exception e){}

        jdbc.databaseClose();
        return resultSet;
    }


}
