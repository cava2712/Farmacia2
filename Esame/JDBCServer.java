package Esame;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.UUID;
import static spark.Spark.*;
public class JDBCServer {
    ObjectMapper om = new ObjectMapper();
    Statement statement;

    public void run()
    {
        try {
            dbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Start embedded server at this port
        port(8080);

    }

    public void dbConnection() throws SQLException {
        DBManager.setConnection(
                Utils.JDBC_Driver_SQLite,
                Utils.JDBC_URL_SQLite);
        statement = DBManager.getConnection().createStatement();

        try {
            statement.executeQuery("SELECT * FROM sausage LIMIT 1");
        } catch (SQLException e) {
            try {
                statement.executeUpdate("DROP TABLE IF EXISTS sausage");
                statement.executeUpdate("DROP TABLE IF EXISTS log");
                statement.executeUpdate("CREATE TABLE sausage (" + "id VARCHAR(50) PRIMARY KEY, " + "length REAL, "
                        + "diameter REAL, " + "weight REAL, " + "quality VARCHAR(50))");
                statement.executeUpdate(
                        "CREATE TABLE log (" + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "query VARCHAR(1024))");

                /* Examples for developing */
                statement.executeUpdate(
                        "INSERT INTO sausage (id, length, diameter, weight, quality) VALUES ('214bb0db-aa52-48be-b052-cd30f730ae79', 30.2, 30.0, 2.6, 'High')");
                statement.executeUpdate(
                        "INSERT INTO sausage (id, length, diameter, weight, quality) VALUES ('03e9e721-f241-4539-9cc7-baecd8b3a931', 40.3, 35.5, 2.2, 'Low')");
                statement.executeUpdate(
                        "INSERT INTO sausage (id, length, diameter, weight, quality) VALUES ('e1f0dcb0-181b-4463-97d7-edcfed736ae1', 35.1, 28.2, 4.3, 'High')");
            } catch (SQLException e1) {
                throw new RuntimeException();
            }
        }
    }

    public static void main(String[] args) {
        //new JDBCServer().run();
    }
}
