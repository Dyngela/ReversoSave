package AFPA.CDA06.demo.Entities;

import AFPA.CDA06.demo.Exception.ExceptionHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Mother's class of Client and Prospected.
 * set and get attribute of Societys
 * Setter restricts user inputs
 */

public abstract class Society {

    private int ID;
    private String compagnyName;
    private String streetNumber;
    private String streetName;
    private String postalCode;
    private String city;
    private String phoneNumber;
    private String email;
    private String comments;
    private final String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    Pattern pattern = Pattern.compile(regex);



    public Society(){}

    public Society(int ID, String compagnyName, String streetNumber,
                   String streetName, String postalCode, String city,
                   String phoneNumber, String email, String comments)
            throws ExceptionHandler {

        this.setID(ID);
        this.setCompagnyName(compagnyName);
        this.setStreetNumber(streetNumber);
        this.setStreetName(streetName);
        this.setPostalCode(postalCode);
        this.setCity(city);
        this.setPhoneNumber(phoneNumber);
        this.setEmail(email);
        this.setComments(comments);

    }

    public void setID(int ID) {
        this.ID = ID;

    }
    public void setCompagnyName(String compagnyName) throws ExceptionHandler{
        if(compagnyName == null || compagnyName.isEmpty()){
            throw new ExceptionHandler("Society's name isn't filled.");
        }
        this.compagnyName = compagnyName;

    }
    public void setStreetNumber(String streetNumber)throws ExceptionHandler {
        if(streetNumber == null || streetNumber.isEmpty()){
            throw new ExceptionHandler("Street's number unfilled");
        }
        this.streetNumber = streetNumber;

    }
    public void setStreetName(String streetName)throws ExceptionHandler {
        if(streetName == null || streetName.isEmpty()){
            throw new ExceptionHandler("Street's name unfilled");
        }
        this.streetName = streetName;

    }
    public void setPostalCode(String postalCode)throws ExceptionHandler {
        if(postalCode == null || postalCode.isEmpty()){
            throw new ExceptionHandler("Postal code's unfilled");
        }
        this.postalCode = postalCode;

    }
    public void setCity(String city)throws ExceptionHandler {
        if(city == null || city.isEmpty()){
            throw new ExceptionHandler("City's unfilled");
        }
        this.city = city;

    }
    public void setPhoneNumber(String phoneNumber)throws ExceptionHandler {
        if( phoneNumber == null || phoneNumber.isEmpty() ){
            throw new ExceptionHandler("Phone number's field isn't filled");
        }
        if(phoneNumber.length() < 10){
            throw new ExceptionHandler("Wrong phone number, there isn't enough digit");
        }
        this.phoneNumber = phoneNumber;

    }
    public void setEmail(String email) throws ExceptionHandler{
        if(email == null || email.isEmpty()){
            throw new ExceptionHandler("Mail field isn't filled");
        }
        Matcher matcher = pattern.matcher(email);
        if(!matcher.matches()){
            throw new ExceptionHandler("Mail adress doesn't contain '@'.");
        }
        this.email = email;

    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getID() {
        return ID;
    }
    public String getCompagnyName() {
        return compagnyName;
    }
    public String getStreetNumber() {
        return streetNumber;
    }
    public String getStreetName() {
        return streetName;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public String getCity() {
        return city;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getEmail() {
        return email;
    }
    public String getComments() {
        return comments;
    }

    @Override
    public String toString() {
        return "Mon ID est " + this.ID + " mon nom de société est " + this.compagnyName +
                " mon siège social est au " + this.streetNumber + " " + this.streetName +
                " à " + this.city + " " + this.postalCode + "\nmon num de tel est: " +
                this.phoneNumber + " et mon mail: " + this.email + "\nmes commentaires: " +
                this.comments;
    }
}
