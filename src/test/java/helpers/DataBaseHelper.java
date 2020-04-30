package helpers;

import ru.sbtqa.tag.qautils.properties.Props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

/**
 * Класс для работы с базой данных
 */
public class DataBaseHelper {
    private Connection connection;
    public static HashMap<String, String> user = new HashMap<String, String>();
    public static final String password = "12345678";

    private void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            connection = null;
        }
    }

    private void createConnection() throws SQLException {

        if (connection != null && !connection.isClosed()) {
            return;
        }
        try {
            Class.forName(Props.get("db.driver"));
            connection = DriverManager.
                    getConnection(Props.get("db.url"), Props.get("db.user"), Props.get("db.password"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            closeConnection();
        }
    }

    public static String getNewUserName() {
        long randomL = System.currentTimeMillis();
        String randomS = Long.toString(randomL);
        randomS = randomS.substring(randomS.length() - 10, randomS.length());
        return "AUTOSSE_ROLE" + randomS;
    }

    public void createUser(String name) throws SQLException {
        createConnection();
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("CREATE USER \"" + name + "\" IDENTIFIED BY \"" + password + "\"");
            statement.executeUpdate("GRANT SSE_ROLE TO \"" + name + "\"");
            statement.close();
        } finally {
            closeConnection();
        }
    }

    public DataBaseHelper() {
    }
}