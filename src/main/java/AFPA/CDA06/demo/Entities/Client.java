package AFPA.CDA06.demo.Entities;


import AFPA.CDA06.demo.DAO.DAOClient;
import AFPA.CDA06.demo.DAO.DAOContract;
import AFPA.CDA06.demo.Exception.ExceptionHandler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Class inherited from Society
 * set and get specific attribute of client grossSales and numberOfEmployeesOnAverage
 * Create an attribute IDClientCount, allowing us to give a unique number to each client
 */

public class Client extends Society{

    private double grossSales;
    private int numberOfEmployeesOnAverage;
    public static int IDClientCount = 0;
    private static final Logger LOGGER = LogManager.getLogger(DAOClient.class.getName());

    private int ID_Contact;
    private String contract_Name;
    private Double contract_Amount;


    public Client(Integer ID, String compagnyName, String streetNumber,
                  String streetName, String postalCode, String city,
                  String phoneNumber, String email, String comments,
                  double grossSales, int numberOfEmployeesOnAverage)
            throws ExceptionHandler {

        super( ID, compagnyName, streetNumber,
                streetName, postalCode, city,
                phoneNumber, email, comments);
        this.setGrossSales(grossSales);
        this.setNumberOfEmployeesOnAverage(numberOfEmployeesOnAverage);
        this.setID(IDClientCount);
    }

    public Client() {}
    public Client(Integer id) {}


    public void setNumberOfEmployeesOnAverage(int numberOfEmployeesOnAverage) throws ExceptionHandler{

        if(numberOfEmployeesOnAverage < 1){
            throw new ExceptionHandler("Number of employee have to be more than 1");
        }
        this.numberOfEmployeesOnAverage = numberOfEmployeesOnAverage;

    }
    public void setGrossSales(double grossSales) throws ExceptionHandler{
        if(grossSales < 200){
            throw new ExceptionHandler("Your gross sale have to be above 200");
        }
        this.grossSales = grossSales;

    }
    public static int setIDClientCount(int IDClientCount){
        Client.IDClientCount = IDClientCount;
        return IDClientCount;
    }

    public double getGrossSales() {
        return grossSales;
    }
    public int getNumberOfEmployeesOnAverage() {
        return numberOfEmployeesOnAverage;
    }
    public static int getIDClientCount(){
        return IDClientCount;
    }

    public void setID_Contact(int ID_Contact) {
        this.ID_Contact = ID_Contact;
    }
    public void setContract_Name(String contract_Name) {
        this.contract_Name = contract_Name;
    }
    public void setContract_Amount(Double contract_Amount) {
        this.contract_Amount = contract_Amount;
    }


    public int getID_Contact(){return ID_Contact;}
    public String getContract_Name(){return contract_Name;}
    public Double getContract_Amount(){return contract_Amount;}

    @Override
    public String toString() {
        return super.toString() + "\nchiffre d'affaire : " + this.grossSales +
                "\nnombre d'employé moyen : " + this.numberOfEmployeesOnAverage + "\n IDContract : " + getID_Contact()
                + " Contract name : " + getContract_Name() + " contract amount : " + getContract_Amount();
    }


    /**
     * List of contracts with an ID_Client linked.
     */
    public static ArrayList<Contract> contractList = new ArrayList<>();
    public ArrayList<Contract> getClientList() {
        return contractList;
    }


}
