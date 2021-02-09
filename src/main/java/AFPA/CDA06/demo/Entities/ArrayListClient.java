package AFPA.CDA06.demo.Entities;
import java.util.ArrayList;

/**
 *     ArrayList referencing all the Client's Object
 */

public class ArrayListClient {

    public static ArrayList<Client> clientList = new ArrayList<>();
    public static ArrayList<Client> getClientList() {
        return clientList;
    }
}