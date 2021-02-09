package AFPA.CDA06.demo.Entities;


import AFPA.CDA06.demo.Exception.ExceptionHandler;

/**
 * Class inherited from Society
 * set and get specific attribute of client grossSales and numberOfEmployeesOnAverage
 * Create an attribute IDClientCount, allowing us to give a unique number to each client
 */

public class Client extends Society{

    private double grossSales;
    private int numberOfEmployeesOnAverage;
    public static int IDClientCount = 0;

    public Client(int ID, String compagnyName, String streetNumber,
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
        IDClientCount++;

    }

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

    @Override
    public String toString() {
        return super.toString() + "\nchiffre d'affaire : " + this.grossSales +
                "\nnombre d'employé moyen : " + this.numberOfEmployeesOnAverage + "\n";
    }
}
