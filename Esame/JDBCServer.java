package Esame;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.TimeZone;
import java.net.*;
import java.util.UUID;
import static spark.Spark.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class JDBCServer {
    static Logger logger = LoggerFactory.getLogger(JDBCServer.class);
    ObjectMapper om = new ObjectMapper();
    Statement statement;
    public static final String JDBC_Driver_MySQL = "com.mysql.cj.jdbc.Driver";
    public static final String JDBC_URL_MySQL = "jdbc:mysql://localhost:3306/farmacia?user=root&password=davide&serverTimezone=" +
            TimeZone.getDefault().getID();

    public void run()
    {
        try {
            dbConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Start embedded server at this port
        port(8080);

        // POST - creazione utente
        // curl -X POST http://localhost:8080/crea
        post("/crea", (request, response) -> {
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String type = "cliente";
            String cf = request.queryParams("cf");
            String nome = request.queryParams("nome");
            String cognome = request.queryParams("cognome");
            String query = String.format("INSERT INTO `farmacia`.`utente` (`cf`, `nome`, `cognome`, `types`, `password`, `email`) VALUES ('%s','%s','%s','%s','%s','%s')",cf,nome,cognome,type,pass,email);
            statement.executeUpdate(query);

            response.status(201);
            return "ok";
        });
        // POST - ritorno utente
        // curl -X POST http://localhost:8080/login
        post("/login", (request, response) -> {
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String type = request.queryParams("types");
            String query = String.format("SELECT * FROM utente WHERE email = '%s' AND password = '%s' AND types = '%s' ", email,pass,type);
            ResultSet rs=statement.executeQuery(query);
            if(!rs.next())
            {
                response.status(404);
                return "failed";
            }
            Utente u = new Utente(Types.valueOf(rs.getString("types")),rs.getString("nome"), rs.getString("cognome"),rs.getString("password"),rs.getString("email"),rs.getString("cf"));
            response.status(201);
            return om.writeValueAsString(u);
        });
        // GET - get all
        // For testing: curl -X GET http://localhost:8080/utenti
        get("/utenti", (request, response) -> {
            ResultSet rs = statement.executeQuery("SELECT * FROM utente");
            ArrayList<Utente> l = new ArrayList<>();
            while (rs.next()) {
                l.add(new Utente(Types.valueOf(rs.getString("types")),rs.getString("nome"), rs.getString("cognome"),rs.getString("password"),rs.getString("email"),rs.getString("cf")));

            }
            return l;
        });
    }

    public void dbConnection() throws SQLException {
        DBManager.setConnection(
                JDBC_Driver_MySQL,
                JDBC_URL_MySQL);
        statement = DBManager.getConnection().createStatement();
        /*
        try {
            ResultSet rs = statement.executeQuery("SELECT * FROM utente");
            while (rs.next()) {
                System.out.println(rs.getString("nome"));
            }

        }
        catch (SQLException e)
            {
                throw new RuntimeException();
            }
         */
    }


    public static void main(String[] args) {
        new JDBCServer().run();
    }
}
