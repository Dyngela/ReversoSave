package AFPA.CDA06.demo.DAO;

import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnexionManager {

    private static Connection conn = null;
    private static final Logger LOGGER = LogManager.getLogger(ConnexionManager.class.getName());


    /**
     * Create the connection with database, it uses Singleton Design pattern
     * @return Connection to database with specification in file database.properties
     */
    public static Connection getConnection() throws SQLException, ExceptionHandler {

        try {
            final Properties dataProperties = new Properties();
            dataProperties.load(ConnexionManager.class.getClassLoader().getResourceAsStream("database.properties"));
            conn = DriverManager.getConnection(
                    dataProperties.getProperty("url"),
                    dataProperties.getProperty("login"),  dataProperties.getProperty("password"));
            LOGGER.info("Connected to database");

        }catch(IOException ie){
            throw new ExceptionHandler("Problème ConnexionManager" + ie);
        }
        return conn;
    }

    /**
     * Check if connection still being use on run time. If not close connection.
     */
    static {
        Runtime.getRuntime().addShutdownHook(new Thread()
        {
            public void run()
            {
                if (conn != null) {
                        try {
                            LOGGER.info("Database fermée");
                            conn.close();
                        } catch (SQLException ex) {
                            LOGGER.fatal("Error on runtime" + ex.getMessage());
                        }
                    }
            }
        });
    }





}
