package mediaplayer.orpheus.model.Database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseUtil {

    /**
     * Method for checking if a field is empty, if not it returns the String value of the field data.
     * @param fieldName reference to the field in the result to search for.
     * @param resultSet reference to the result to search through.
     * @return String value of the field. or "NULL" if the database field is empty.
     */
    public static String validateResultNotNull(String fieldName, ResultSet resultSet){
        try {
            return resultSet.getString(fieldName);
        } catch (SQLException e) {
            return "NULL";
        }

    }

}
