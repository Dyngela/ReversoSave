package AFPA.CDA06.demo.Entities;

import java.util.ArrayList;

public class ArrayListClientDAO {
    /**
     * ArrayList to stock all the society name of clients from the database for display purposes
     */
    public static ArrayList<Client> clientArrayList = new ArrayList<>();
    public static ArrayList<Client> getClientListDAO() {
        return clientArrayList;
    }

}
