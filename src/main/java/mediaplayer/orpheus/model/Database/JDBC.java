/**
 * The JDBC class provides functionality to connect to a SQL database using JDBC.
 * It reads database connection properties from a configuration file and creates a connection.
 *
 * @author Elias B. Therkildsen
 * @version 1.1
 * @since 22.12.2023
 */

package mediaplayer.orpheus.model.Database;

import mediaplayer.orpheus.util.AnsiColorCode;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class JDBC {

    public static JDBC instance;
    private String URL;
    private Connection connection;
    private Properties properties;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    /**
     * Constructs a JDBC instance and initializes a database connection.
     */

    public JDBC() {
         setProps();
         createConnection(getProperties());
         instance = this;
    }

    /**
     * Reads database properties from a configuration file and sets up a Properties object.
     *
     * @throws RuntimeException if an error occurs during file reading or property setting.
     */
    private void setProps() {
        System.out.printf("%s[JDBC] Trying to setup props.%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

        Properties properties = new Properties();

        // Path to the database properties file
        String DATBASE_PROPS_PATH = "src/main/java/mediaplayer/orpheus/model/Database/db.properties";

        File file = new File(DATBASE_PROPS_PATH);
        InputStream input;

        try {
            input = new FileInputStream(file);
            try {
                properties.load(input);
                properties.setProperty("user", properties.getProperty("user"));
                properties.setProperty("password", properties.getProperty("password"));
                properties.setProperty("encrypt", properties.getProperty("encrypt"));

                String DATABASE_NAME = properties.getProperty("databaseName");
                String PORT = properties.getProperty("port");
                String IP = properties.getProperty("ip");

                URL = "jdbc:sqlserver://" + IP + ":" + PORT + ";databaseName=" + DATABASE_NAME;

                this.properties = properties;

                System.out.printf("%s[JDBC] Successful in setting up props! %s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);

            } catch (IOException e) {
                System.out.printf("%s[JDBC] Error! ' %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            System.out.printf("%s[JDBC] Failed to find the file 'db.properties' %s%n", AnsiColorCode.ANSI_RED, AnsiColorCode.ANSI_RESET);
            throw new RuntimeException(e);
        }


    }

    /**
     * Closes the database connection.
     *
     * @throws RuntimeException if an error occurs while closing the connection.
     */
    public void databaseClose() {
        try {

            this.connection.close();
            this.connection = null;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("%s[JDBC] Closing connection to JDBC..%s", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
    }

    /**
     * Creates a database connection using the specified properties.
     *
     * @param properties Properties object containing database connection properties.
     * @throws RuntimeException if an error occurs while creating the connection.
     */
    private void createConnection(Properties properties) {
        try {
            this.connection = DriverManager.getConnection(URL, properties);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.printf("%s[JDBC] Creating connection.%s%n", AnsiColorCode.ANSI_YELLOW, AnsiColorCode.ANSI_RESET);
    }

    /**
     * Gets the current database connection.
     *
     * @return Connection object representing the current database connection.
     */
    public Connection getConnection() {
        return connection;
    }

    public Properties getProperties() {
        return properties;
    }

    public ResultSet executeQuery(PreparedStatement query){

        try {
            resultSet = query.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return resultSet;

    }

    public void executeUpdate(PreparedStatement query){

        try {
            query.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static JDBC get() {
        if (JDBC.instance == null) {
            JDBC.instance = new JDBC();
        }

        return JDBC.instance;
    }


}
