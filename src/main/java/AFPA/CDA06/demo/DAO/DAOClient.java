package AFPA.CDA06.demo.DAO;

import AFPA.CDA06.demo.Entities.ArrayListClientDAO;
import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Contract;


import java.sql.*;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static AFPA.CDA06.demo.Entities.ArrayListClientDAO.clientArrayList;


public class DAOClient implements DAO{

    private static final Logger LOGGER = LogManager.getLogger(DAOClient.class.getName());
    static Connection connection = null;

    /**
     * Method to list every client. It's temporary stocked in an arrayList,
     * clientArrayList, for display purposes in the MainPage comboBox.
     * @throws Exception
     */
    public static void findAll() throws Exception {

        try{
            connection = ConnexionManager.getConnection();

            String query = "Select * from client";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setString(1, "Client");
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            clientArrayList.clear();

            while(resultSet.next()) {
                Client client = new Client();
                client.setID(resultSet.getInt("ID"));
                client.setCompagnyName(resultSet.getString("Society_Name"));
                client.setStreetNumber(resultSet.getString("Street_Number"));
                client.setStreetName(resultSet.getString("Street_Name"));
                client.setPostalCode(resultSet.getString("Postal_Code"));
                client.setCity(resultSet.getString("City"));
                client.setPhoneNumber(resultSet.getString("Phone_Number"));
                client.setEmail(resultSet.getString("Email"));
                client.setComments(resultSet.getString("Comments"));
                client.setGrossSales(resultSet.getDouble("Gross_Sale"));
                client.setNumberOfEmployeesOnAverage(resultSet.getInt("Number_Of_Employee"));

                clientArrayList.add(client);
                }


        } catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Error findall client" + e);
        }

    }

    /**
     * Method to find every info of a client according to his society name
     * @param Society_Name name of the client
     */
    public static void find(String Society_Name){
        try{
            connection = ConnexionManager.getConnection();

            if(Society_Name != null){
                PreparedStatement statement = connection.prepareStatement
                        ("Select ID, Society_Name, Street_Number, Street_Name, Postal_Code,\" +\n" +
                                "                    \" City, Phone_Number, Email, Comments, Gross_Sale," +
                                " Number_Of_Employee, ID_Contract, Contract_Name, Contract_Amount from Client " +
                                "inner join contract on Client.ID = Contract.ID_Client " +
                                "where Society_Name = ?");
                statement.setString(1, Society_Name);

                statement.execute();
                ResultSet resultSet = statement.executeQuery();

                Client client = new Client();
                while(resultSet.next()) {
                    client.setID(resultSet.getInt("ID"));
                    client.setCompagnyName(resultSet.getString("Society_Name"));
                    client.setStreetNumber(resultSet.getString("Street_Number"));
                    client.setStreetName(resultSet.getString("Street_Name"));
                    client.setPostalCode(resultSet.getString("Postal_Code"));
                    client.setCity(resultSet.getString("City"));
                    client.setPhoneNumber(resultSet.getString("Phone_Number"));
                    client.setEmail(resultSet.getString("Email"));
                    client.setComments(resultSet.getString("Comments"));
                    client.setGrossSales(resultSet.getDouble("Gross_Sale"));
                    client.setNumberOfEmployeesOnAverage(resultSet.getInt("Number_Of_Employee"));

                    client.setID_Contact(resultSet.getInt("ID_Contract"));
                    client.setContract_Name(resultSet.getString("Contract_Name"));
                    client.setContract_Amount(resultSet.getDouble("Contract_Amount"));
                    System.out.println(client);
                }
            }else {
                System.out.println("Dysfonctionnement find client");
                LOGGER.error("Error in find client, didn't work as expected");
            }
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Fatal error find client " + e);
        }
    }

    /**
     * Method to create a client and insert it into database.
     * @param client The client we want to insert into database
     */
    public static void create(Client client) { //insert enregistrement

        try {

            connection = ConnexionManager.getConnection();
            String query = "Insert into Client (Society_Name, Street_Number, Street_Name, Postal_Code," +
                    " City, Phone_Number, Email, Comments, Gross_Sale, Number_Of_Employee) values " +
                    "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getCompagnyName());
            statement.setString(2, client.getStreetNumber());
            statement.setString(3, client.getStreetName());
            statement.setString(4, client.getPostalCode());
            statement.setString(5, client.getCity());
            statement.setString(6, client.getPhoneNumber());
            statement.setString(7, client.getEmail());
            statement.setString(8, client.getComments());
            statement.setDouble(9, client.getGrossSales());
            statement.setInt(10, client.getNumberOfEmployeesOnAverage());
            statement.executeUpdate();
            LOGGER.info("Création du client réussit !");


            String querybis = "insert into ID_Client (id) values (1)";
            PreparedStatement statementbis = connection.prepareStatement(querybis);
            statementbis.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("La création à échouer " + e);
        }
    }

    /**
     * Method to update a client's information
     * @param client Object containing all the information to update
     * @param ID_Client ID of the client to update
     */
    public static void save(Client client, int ID_Client){ //update enregistrement
        try {
            connection = ConnexionManager.getConnection();

            String query = "UPDATE Client\n " +
                    " set Society_Name = ? , \n" +
                    "  Street_Number = ?, \n" +
                    "  ID = ID, \n" +
                    "  Street_Name = ?, \n" +
                    "  Postal_Code = ?, \n" +
                    "  City = ?, " +
                    "  Phone_Number = ?, \n" +
                    "  Email = ?, \n" +
                    "  Comments = ?, \n" +
                    "  Gross_Sale = ?, \n" +
                    "  Number_Of_Employee = ? \n" +
                    " Where ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, client.getCompagnyName());
            statement.setString(2, client.getStreetNumber());
            statement.setString(3, client.getStreetName());
            statement.setString(4, client.getPostalCode());
            statement.setString(5, client.getCity());
            statement.setString(6, client.getPhoneNumber());
            statement.setString(7, client.getEmail());
            statement.setString(8, client.getComments());
            statement.setDouble(9, client.getGrossSales());
            statement.setInt(10, client.getNumberOfEmployeesOnAverage());
            statement.setInt(11, ID_Client);

            statement.executeUpdate();
            LOGGER.info("Update client réussit !");

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("L'update client à échoué " + e);
        }
    }

    /**
     * Method to delete a client according to his ID
     * @param client object to be deleted
     */
    public static void delete(Client client){ //delete enregistrement
            try {
                connection = ConnexionManager.getConnection();
                String query = "delete from client where ID = ?";
                PreparedStatement statement = connection.prepareStatement(query);
                statement.setInt(1, client.getID());
                LOGGER.info("Suppression réussit !");
                statement.execute();
            }catch (Exception e){
                e.printStackTrace();
                LOGGER.fatal("La suppression à échoué" + e);
            }
        }

    /**
     * Mehod to create or update a client, according to his ID. If ID exist it updates.
     * If ID give in parameter is higher than the last id of the table it create a client
     * Always with client given in parameter
     * @param client Object created or updated
     * @param ID ID of client, deciding if either it create or update
     */
    public static void createAndSave(Client client, int ID){
        try {
            connection = ConnexionManager.getConnection();
            String query = "Select MAX(ID) as maxID from CLIENT";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            int maxID = 0;
            while (resultSet.next()) {
                maxID = resultSet.getInt("maxID");
            }
            if (maxID < ID) { //Si c'est un ID plus grand et donc non répertorié, on créé un nouvel enregistrement en laissant l'auto increment gérer l'ID

                String queryCreate = "Insert into Client (Society_Name, Street_Number, Street_Name, Postal_Code," +
                        " City, Phone_Number, Email, Comments, Gross_Sale, Number_Of_Employee) values " +
                        "('" + client.getCompagnyName() +
                        "' ,'" + client.getStreetNumber() +
                        "' ,'" + client.getStreetName() +
                        "' ,'" + client.getPostalCode() +
                        "' ,'" + client.getCity() +
                        "' ,'" + client.getPhoneNumber() +
                        "' ,'" + client.getEmail() +
                        "' ,'" + client.getComments() +
                        "' ,'" + client.getGrossSales() +
                        "' ,'" + client.getNumberOfEmployeesOnAverage() + "')";
                PreparedStatement statementCreate = connection.prepareStatement(queryCreate);
                LOGGER.info("Création createandsave réussit !");
                statementCreate.executeUpdate();
            } else {//sinon on le modifie
                String queryUpdate = "UPDATE Client\n " +
                        " set Society_Name = '" + client.getCompagnyName() + "', \n" +
                        "  ID = ID, \n" +
                        "  Street_Number = '" + client.getStreetNumber() + "', \n" +
                        "  Street_Name = '" + client.getStreetName() + "', \n" +
                        "  Postal_Code = '" + client.getPostalCode() + "', \n" +
                        "  City = '" + client.getCity() + "', " +
                        "  Phone_Number = '" + client.getPhoneNumber() + "', \n" +
                        "  Email = '" + client.getEmail() + "', \n" +
                        "  Comments = '" + client.getComments() + "', \n" +
                        "  Gross_Sale = " + client.getGrossSales() + ", \n" +
                        "  Number_Of_Employee = " + client.getNumberOfEmployeesOnAverage() + " \n" +
                        " Where ID = '" + ID + "'";
                PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate);

                statementUpdate.executeUpdate();
                LOGGER.info("Update create and save réussit !");
            }
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Echec de CreateAndSave method" + e);
        }
    }

    /**
     * Method to return the max id from client. It doesn't care if last id get deleted,
     * it'll still give last ID passing by an another table who stock that very ID
     * @return Return the last ID of client table
     */
    public static Integer maxIDClient(){
        try {
            connection = ConnexionManager.getConnection();
            String query = "Select MAX(ID_Client) as maxID from id_client";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            int maxIDClient = 0;
            while (resultSet.next()) {
                maxIDClient = resultSet.getInt("maxID");
            }
            LOGGER.info("maxIDClient renvoyé : " + maxIDClient);
            return maxIDClient;
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Problème avec maxIDClient " + e);
        }
        return null;
    }

}
