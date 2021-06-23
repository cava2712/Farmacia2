package Esame;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
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
    int a;
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
        // curl -X POST http://localhost:8080/password
        post("/password", (request, response) -> {
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String query = String.format("UPDATE `farmacia`.`utente` SET `password` = '%s' WHERE (`email` = '%s')",pass,email);
            try {
                a= statement.executeUpdate(query);
            }
            catch(SQLException e)
            {
                if(e.getSQLState().startsWith("23")) //23 codice errore di SQLIntegrityConstraintViolationException
                {
                    return "errore email";
                }
                else
                {
                    throw new SQLException(e);
                }
            }
            if(a==0)
            {
                return "errore email";
            }
            response.status(201);
            return "ok";
        });

        // POST - creazione utente
        // curl -X POST http://localhost:8080/crea
        post("/crea", (request, response) -> {
            String email = request.queryParams("email");
            String pass = request.queryParams("password");
            String type = "cliente";
            String cf = request.queryParams("cf");
            String nome = request.queryParams("nome");
            String cognome = request.queryParams("cognome");
            String img =request.queryParams("img");
            String dataDiNascita = request.queryParams("dataDiNascita");
            String query = String.format("INSERT INTO `farmacia`.`utente` (`cf`, `nome`, `cognome`, `types`, `password`, `email`, `img`, `dataDiNascita`) VALUES ('%s','%s','%s','%s','%s','%s','%s','%s')",cf,nome,cognome,type,pass,email,img,dataDiNascita);
            try {
                a=statement.executeUpdate(query);
            }
            catch(SQLException e)
            {
                if(e.getSQLState().startsWith("23")) //23 codice errore di SQLIntegrityConstraintViolationException
                {
                    return "doppio";
                }
                else
                {
                    throw new SQLException(e);
                }
            }
            if(a==0)
            {
                return "doppio";
            }
            response.status(201);
            return "ok";
        });

        // POST - creazione utente
        // curl -X POST http://localhost:8080/aggiorna
        post("/aggiorna", (request, response) -> {
            String email = request.queryParams("email");
            String emailp = request.queryParams("emailp");
            String pass = request.queryParams("password");
            String type = "cliente";
            String cf = request.queryParams("cf");
            String nome = request.queryParams("nome");
            String cognome = request.queryParams("cognome");
            String dataDiNascita = request.queryParams("dataDiNascita");//-----
            String query = String.format("UPDATE `farmacia`.`utente` SET `cf` = '%s', `nome` = '%s', `cognome` = '%s', `types` = '%s', `password` = '%s', `email` = '%s', `img` = '%s',`dataDiNascita` = '%s' WHERE (`email` = '%s')",cf,nome,cognome,type,pass,email,null,emailp,dataDiNascita);
            try {
                statement.executeUpdate(query);
            }
            catch(SQLException e)
            {
                    throw new SQLException(e);
            }
            response.status(201);
            return "ok";
        });
        // POST - Pagamento
        // curl -X POST http://localhost:8080/pagamento
        post("/pagamento", (request, response) -> {
            int codice = Integer.parseInt(request.queryParams("codice"));
            int qnt = Integer.parseInt(request.queryParams("quantità"));
            String query = String.format("UPDATE `farmacia`.`farmaco` SET `quantità` = quantità-%d WHERE (`codice` = '%d')",qnt,codice);
            try {
                statement.executeUpdate(query);
            }
            catch(SQLException e)
            {
                throw new SQLException(e);
            }
            response.status(201);
            return "OK";
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
            Utente u = new Utente(Types.valueOf(rs.getString("types")),rs.getString("nome"), rs.getString("cognome"),rs.getString("password"),rs.getString("email"),rs.getString("cf"),rs.getString("img"),rs.getDate("dataDiNascita"));
            response.status(201);
            return om.writeValueAsString(u);
        });
        // GET - get all utenti
        // For testing: curl -X GET http://localhost:8080/utenti
        get("/utenti", (request, response) -> {
            ResultSet rs = statement.executeQuery("SELECT * FROM utente");
            ArrayList<Utente> l = new ArrayList<>();
            while (rs.next()) {
                l.add(new Utente(Types.valueOf(rs.getString("types")),rs.getString("nome"), rs.getString("cognome"),rs.getString("password"),rs.getString("email"),rs.getString("cf"),rs.getString("img"),rs.getDate("dataDiNascita")));

            }
            return l;
        });

        // GET - get all Farmaci
        // For testing: curl -X GET http://localhost:8080/farmaci
        get("/farmaci", (request, response) -> {
            ResultSet rs = statement.executeQuery("SELECT * FROM farmaco");
            ArrayList<Farmaco> l = new ArrayList<Farmaco>();
            while (rs.next()) {
                l.add(new Farmaco(rs.getInt("codice"),rs.getInt("quantità"), rs.getString("nome"),rs.getString("marca"),rs.getString("categoria"),rs.getFloat("prezzo"),rs.getString("percorsoImg")));

            }
            return om.writeValueAsString(l);
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
