package AFPA.CDA06.demo.Entities;

import java.util.ArrayList;

public class ArrayListContractDAO {


    /**
     * ArrayList to stock all the society name of clients who has contract from the database for display purposes
     */
    public static ArrayList<Client> clientWithContractList = new ArrayList<>();
    public static ArrayList<Client> getClientListDAO() {
        return clientWithContractList;
    }

}
