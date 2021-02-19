package AFPA.CDA06.demo.DAO;

import AFPA.CDA06.demo.Entities.ArrayListProspectDAO;
import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import AFPA.CDA06.demo.Utilities.Utilities;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


import static AFPA.CDA06.demo.Entities.ArrayListProspectDAO.prospectedArrayList;

public class DAOProspect implements DAO{

    private static final Logger LOGGER = LogManager.getLogger(DAOProspect.class.getName());
    static Connection connection = null;

    /**
     * Method to list every prospect. It's temporary stocked in an arrayList, prospectedArrayList,
     * for display purposes in the MainPage comboBox.
     * @throws Exception
     */
    public static void findAll() throws Exception {

        try{

            connection = ConnexionManager.getConnection();

            if(connection != null){
                PreparedStatement statement = connection.prepareStatement
                        ("Select * from Prospect");
                //statement.setString(1, "Prospect");
                statement.execute();
                ResultSet resultSet = statement.executeQuery();
                prospectedArrayList.clear();

                while(resultSet.next()) {
                    Prospected prospect = new Prospected();
                    prospect.setID(resultSet.getInt("ID"));
                    prospect.setCompagnyName(resultSet.getString("Society_Name"));
                    prospect.setStreetNumber(resultSet.getString("Street_Number"));
                    prospect.setStreetName(resultSet.getString("Street_Name"));
                    prospect.setPostalCode(resultSet.getString("Postal_Code"));
                    prospect.setCity(resultSet.getString("City"));
                    prospect.setPhoneNumber(resultSet.getString("Phone_Number"));
                    prospect.setEmail(resultSet.getString("Email"));
                    prospect.setComments(resultSet.getString("Comments"));
                    prospect.setProspectingDate(Utilities.asLocalDate(resultSet.getDate("Prospecting_Date")));
                    prospect.setProspectedInterested(resultSet.getString("Interested"));
                    prospectedArrayList.add(prospect);
                }
            }else {
                LOGGER.error("Error findAll prospect");
            }
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Erreur fatal findall prospect " + e);
            throw new ExceptionHandler("Erreur fatal findall prospect");
        }
    }

    /**
     * Method to find every info of a prospect according to his society name
     * @param Society_Name name of the prospect
     */
    public static void find(String Society_Name) throws ExceptionHandler {
        try{

            connection = ConnexionManager.getConnection();

            if(Society_Name != null){
                PreparedStatement statement = connection.prepareStatement
                        ("Select * from Prospect where Society_Name = ?");
                statement.setString(1, Society_Name);
                statement.execute();
                ResultSet resultSet = statement.executeQuery();
                Prospected prospect = new Prospected();
                while(resultSet.next()) {
                    prospect.setID(resultSet.getInt("ID"));
                    prospect.setCompagnyName(resultSet.getString("Society_Name"));
                    prospect.setStreetNumber(resultSet.getString("Street_Number"));
                    prospect.setStreetName(resultSet.getString("Street_Name"));
                    prospect.setPostalCode(resultSet.getString("Postal_Code"));
                    prospect.setCity(resultSet.getString("City"));
                    prospect.setPhoneNumber(resultSet.getString("Phone_Number"));
                    prospect.setEmail(resultSet.getString("Email"));
                    prospect.setComments(resultSet.getString("Comments"));
                    prospect.setProspectingDate(Utilities.asLocalDate(resultSet.getDate("Prospecting_Date")));
                    prospect.setProspectedInterested(resultSet.getString("Interested"));
                    System.out.println(prospect);
                }
            }else {
                LOGGER.error("Error find prospect");
            }
        } catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Erreur fatal find prospect " + e);
            throw new ExceptionHandler("Erreur fatal find prospect");
        }
    }

    /**
     * Method to create a prospect and insert it into database.
     * @param prospect The prospect we want to insert into database
     */
    public static void create(Prospected prospect) throws ExceptionHandler { //insert enregistrement

        try {
            connection = ConnexionManager.getConnection();

            String interested = prospect.getProspectedInterested();
            int interestInt = -1;
            if(interested.equals("Yes")){
                interestInt = 0;
            }else{
                interestInt = 1;
            }
            PreparedStatement statement = connection.prepareStatement
                    ("Insert into Prospect (Society_Name, Street_Number, Street_Name, Postal_Code," +
                            " City, Phone_Number, Email, Comments, Prospecting_Date, Interested) values " +
                            "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
                    );
            statement.setString(1, prospect.getCompagnyName());
            statement.setString(2, prospect.getStreetNumber());
            statement.setString(3, prospect.getStreetName());
            statement.setString(4, prospect.getPostalCode());
            statement.setString(5, prospect.getCity());
            statement.setString(6, prospect.getPhoneNumber());
            statement.setString(7, prospect.getEmail());
            statement.setString(8, prospect.getComments());
            statement.setString(9, String.valueOf(prospect.getProspectingDate()));
            statement.setInt(10, interestInt);
            statement.executeUpdate();
            LOGGER.info("Création prospect réussit !");

            String querybis = "insert into ID_Prospect (id) values (1)";
            PreparedStatement statementbis = connection.prepareStatement(querybis);
            statementbis.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("Error create prospect " + e);
            throw new ExceptionHandler("Error create prospect ");
        }
    }

    /**
     * Method to update a prospect's information
     * @param prospect Object containing all the information to update
     * @param ID_Prospect ID of the prospect to update
     */
    public static void save(Prospected prospect, int ID_Prospect) throws ExceptionHandler { //update enregistrement
        try {
            connection = ConnexionManager.getConnection();

            String interested = prospect.getProspectedInterested();
            int interestInt = -1;
            if(interested.equals("Yes")){
                interestInt = 0;
            }else if (interested.equals("No")){
                interestInt = 1;
            }
            PreparedStatement statement = connection.prepareStatement("UPDATE Prospect\n " +
                    " set Society_Name = ? , \n" +
                    "  Street_Number = ?, \n" +
                    "  ID = ID, \n" +
                    "  Street_Name = ?, \n" +
                    "  Postal_Code = ?, \n" +
                    "  City = ?, " +
                    "  Phone_Number = ?, \n" +
                    "  Email = ?, \n" +
                    "  Comments = ?, \n" +
                    "  Prospecting_Date = ?, \n" +
                    "  Interested = ?\n" +
                    " Where ID = ?"
            );

            statement.setString(1, prospect.getCompagnyName());
            statement.setString(2, prospect.getStreetNumber());
            statement.setString(3, prospect.getStreetName());
            statement.setString(4, prospect.getPostalCode());
            statement.setString(5, prospect.getCity());
            statement.setString(6, prospect.getPhoneNumber());
            statement.setString(7, prospect.getEmail());
            statement.setString(8, prospect.getComments());
            statement.setString(9, String.valueOf(prospect.getProspectingDate()));
            statement.setInt(10, interestInt);
            statement.setInt(11, ID_Prospect);

            statement.executeUpdate();
            LOGGER.info("Update réussit !");
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.fatal("L'update à échoué " + e);
            throw new ExceptionHandler("L'update à échoué");
        }
    }

    /**
     * Method to delete a prospect according to his ID
     * @param prospected object to be deleted
     */
    public static void delete(Prospected prospected) throws ExceptionHandler { //delete enregistrement
        try {

            connection = ConnexionManager.getConnection();
            String query = "delete from prospect where ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prospected.getID());
            statement.executeUpdate();
            LOGGER.info("Suppression réussit !");

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("La suppression à échoué " + e);
            throw new ExceptionHandler("La suppression à échoué");

        }
    }

    /**
     * Mehod to create or update a prospect, according to his ID. If ID exist it updates.
     * If ID give in parameter is higher than the last id of the table it create a prospect
     * Always with prospect given in parameter
     * @param prospect Object created or updated
     * @param ID ID of prospect, deciding if either it create or update
     */
    public static void createAndSave(Prospected prospect, Integer ID) throws ExceptionHandler {
        try {

            connection = ConnexionManager.getConnection();
            String query = "Select MAX(?) as maxID from Prospect";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, prospect.getID());
            //statement.setString(2, "Prospect");

            statement.execute();
            ResultSet resultSet = statement.executeQuery();

            String interested = prospect.getProspectedInterested();
            int interestInt = -1;
            if(interested.equals("Yes")){ interestInt = 0; }
            else{ interestInt = 1; }

            int maxID = 0;
            while (resultSet.next()) {
                maxID = resultSet.getInt("maxID");
            }
            if (maxID < ID) { //Si c'est un ID plus grand et donc non répertorié, on créé un nouvel enregistrement en laissant l'auto increment gérer l'ID

                String quertCreate = "Insert into Prospect (Society_Name, Street_Number, Street_Name, Postal_Code," +
                        " City, Phone_Number, Email, Comments, Prospecting_Date, Interested) values " +
                        "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement statementCreate = connection.prepareStatement(quertCreate);

                statementCreate.setString(1, prospect.getCompagnyName());
                statementCreate.setString(2, prospect.getStreetNumber());
                statementCreate.setString(3, prospect.getStreetName());
                statementCreate.setString(4, prospect.getPostalCode());
                statementCreate.setString(5, prospect.getCity());
                statementCreate.setString(6, prospect.getPhoneNumber());
                statementCreate.setString(7, prospect.getEmail());
                statementCreate.setString(8, prospect.getComments());
                statementCreate.setString(9, String.valueOf(prospect.getProspectingDate()));
                statementCreate.setInt(10, interestInt);

                System.out.println("Création réussit !");
                statementCreate.executeUpdate();
            } else {//sinon on le modifie
                String queryUpdate = "UPDATE Prospect\n " +
                        " set Society_Name = ? , \n" +
                        "  Street_Number = ?, \n" +
                        "  ID = ID, \n" +
                        "  Street_Name = ?, \n" +
                        "  Postal_Code = ?, \n" +
                        "  City = ?, " +
                        "  Phone_Number = ?, \n" +
                        "  Email = ?, \n" +
                        "  Comments = ?, \n" +
                        "  Prospecting_Date = ?, \n" +
                        "  Interested = ?,\n" +
                        " Where ID = ?";
                PreparedStatement statementUpdate = connection.prepareStatement(queryUpdate);
                statementUpdate.setString(1, prospect.getCompagnyName());
                statementUpdate.setString(2, prospect.getStreetNumber());
                statementUpdate.setString(3, prospect.getStreetName());
                statementUpdate.setString(4, prospect.getPostalCode());
                statementUpdate.setString(5, prospect.getCity());
                statementUpdate.setString(6, prospect.getPhoneNumber());
                statementUpdate.setString(7, prospect.getEmail());
                statementUpdate.setString(8, prospect.getComments());
                statementUpdate.setString(9, String.valueOf(prospect.getProspectingDate()));
                statementUpdate.setInt(10, interestInt);
                statementUpdate.setInt(11, prospect.getID());

                statementUpdate.executeUpdate();
                System.out.println("Update réussit !");
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Echec de CreateAndSave method");
            throw new ExceptionHandler("Echec de CreateAndSave method");
        }
    }

    /**
     * Method to return the max id from Prospect table. It doesn't care if last id get deleted,
     * it'll still give last ID passing by an another table who stock that very ID
     * @return Return the last ID of prospect table
     */
    public static Integer maxIDProspect() throws ExceptionHandler {
        try {
            connection = ConnexionManager.getConnection();
            String query = "Select MAX(ID_Prospect) as maxID from ID_Prospect";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            int maxIDProspect = 0;
            while (resultSet.next()) {
                maxIDProspect = resultSet.getInt("maxID");
            }
            LOGGER.info("MaxID_Prospect renvoyé : " + maxIDProspect);
            return maxIDProspect;
        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Problème avec maxID_Prospect " + e);
            throw new ExceptionHandler("Problème avec maxID_Prospect");

        }
    }
}


