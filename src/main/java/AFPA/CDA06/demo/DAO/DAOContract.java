package AFPA.CDA06.demo.DAO;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Contract;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static AFPA.CDA06.demo.Entities.ArrayListContractDAO.clientWithContractList;
import static AFPA.CDA06.demo.Entities.Client.contractList;

public class DAOContract {

    private static final Logger LOGGER = LogManager.getLogger(DAOClient.class.getName());
    static Connection connection = null;

    /**
     * Method invoc to select all the contracts of a given client.
     * @param ID_Client the ID of client we want to see contract from
     */
    public static void findByIDClient(Integer ID_Client) throws ExceptionHandler {

        try {
            connection = ConnexionManager.getConnection();
            System.out.println("Entré findByIDClient");

            String query = "Select * from Contract " +
                    "inner join Client on Client.ID = Contract.ID_Client " +
                    "where " + ID_Client + " = Contract.ID_Client";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.execute();

            ResultSet resultSet = statement.executeQuery();
            contractList.clear();

            while (resultSet.next()) {
                Contract contract = new Contract();
                contract.setIDContract(resultSet.getInt("ID_Contract"));
                contract.setID(resultSet.getInt("ID_Client"));
                contract.setContractName(resultSet.getString("Contract_Name"));
                contract.setContractAmount(resultSet.getDouble("Contract_Amount"));
                contractList.add(contract);
            }
            LOGGER.info("FindIDByClient from daocontract done properly");
        }catch (Exception E){
            E.printStackTrace();
            LOGGER.error("Error FindByIdClient from DAOContract " + E);
            throw new ExceptionHandler("Error FindByIdClient from DAOContract");
        }
    }

    /**
     * Method to list every Client who has a contract linked. It's temporary stocked in an arrayList, clientWithContractList,
     * for display purposes in the MainPage comboBox, and in ContractListFrame.
     */
    public static void findAll() throws ExceptionHandler {
        try{
            connection = ConnexionManager.getConnection();

            String query = "Select distinct Society_Name from contract " +
                    "inner join Client " +
                    "on client.ID = contract.ID_Client";
            PreparedStatement statement = connection.prepareStatement(query);
            //statement.setInt(1, client.getID());
            statement.execute();
            ResultSet resultSet = statement.executeQuery();
            clientWithContractList.clear();

            while(resultSet.next()){
                Client client = new Client();
                client.setCompagnyName(resultSet.getString("Society_Name"));
                clientWithContractList.add(client);
            }
            LOGGER.info("Finall from daocontract done properly");

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.error("Error findall contract" + e);
            throw new ExceptionHandler("Error findall contract");

        }
    }
}
