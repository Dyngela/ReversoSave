package AFPA.CDA06.demo.Exception;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestException {

    public static void tester(){
        System.out.println("Cas ou aucune exception n'est levé");

        //cas client ou c'est ok, aucune erreur
        String[][] tabClientOK = {{"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                "07 70 17 98 18", "Creative.assembly@CA.com",
                "Comments are there", "1200", "100"}};

        for (int i = 0; i < tabClientOK.length; i++) {
            checkAnomalies(EnumTestException.Client.toString(), i, tabClientOK[i]);
        }

        //cas oiseau ou c'est ok
        String[][] tabProspectedOK = {{"1", "Batigere", "42", "boulevard Stan", "54000",
                "Nancy-centre", "01 17 85 96 52",
                "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"}};

        for (int i = 0; i < tabProspectedOK.length; i++) {
            checkAnomalies(EnumTestException.Prospected.toString(), i, tabProspectedOK[i]);
        }

        System.out.println(" ");
        System.out.println("Cas ou client renvoit une erreur:");

        //cas ou client renvoit une erreur
        String[][] tabClientNotOK = {
                {"1", "", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assemblyarobaseCA.com",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "",
                        "Comments are there", "1200", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "199", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", " ", "100"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", "0"},
                {"1", "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                        "07 70 17 98 18", "Creative.assembly@CA.com",
                        "Comments are there", "1200", " "},
        };

        for (int i = 0; i < tabClientNotOK.length; i++) {
            checkAnomalies(EnumTestException.Client.toString(), i, tabClientNotOK[i]);
        }

        System.out.println(" ");
        System.out.println("Cas ou prospect renvoit une erreur:");

        // cas ou prospect renvoit une erreur
        String[][] tabProspectedNotOk = {
                {"0", "", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"1", "Batigere", "", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"2", "Batigere", "42", "", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"3", "Batigere", "42", "boulevard Stan", "",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"4", "Batigere", "42", "boulevard Stan", "54000",
                        "", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"5", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 52",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"6", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "",
                        "batigere.name@batigere.com", "Comments are there", "10/10/2010", "yes"},
                {"7", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.nameAROBASEbatigere.com", "Comments are there",
                        "10/10/2010", "yes"},
                {"8", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        " ", "Comments are there",
                        "10/10/2010", "yes"},
                {"9", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there",
                        "le 6 janvier 2010", "yes"},
                {"10", "Batigere", "42", "boulevard Stan", "54000",
                        "Nancy-centre", "01 17 85 96 52",
                        "batigere.name@batigere.com", "Comments are there",
                        " ", "yes"}
        };

        for (int i = 0; i < tabProspectedNotOk.length; i++) {
            checkAnomalies(EnumTestException.Prospected.toString(), i, tabProspectedNotOk[i]);
        }

    }

    public static void checkAnomalies(String type, int i, String[] arrayLign) {
        try {
            switch (type) {
                case "Client":
                    Client client = new Client(Integer.parseInt(arrayLign[0]), arrayLign[1],
                            arrayLign[2], arrayLign[3],arrayLign[4],
                            arrayLign[5], arrayLign[6], arrayLign[7], arrayLign[8],
                            Double.parseDouble(arrayLign[9]),Integer.parseInt(arrayLign[10]));
                    System.out.println("cas client ok : " + client);
                    break;
                case "Prospected":

                    //parsing of the date to not make a date exception for each case
                    LocalDate dateToBeChanged = null;
                    try {
                        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        dateToBeChanged = LocalDate.parse(arrayLign[9], dateFormatter);
                    }catch (DateTimeException pe){
                        System.out.println("cas " + i + " Exception levée par DateTimeException : Wrong date format");
                    }
                    Prospected prospected = new Prospected(Integer.parseInt(arrayLign[0]),
                            arrayLign[1], arrayLign[2], arrayLign[3], arrayLign[4],
                            arrayLign[5], arrayLign[6], arrayLign[7], arrayLign[8],
                            dateToBeChanged, arrayLign[10]);
                    System.out.println("cas prospect ok : " + prospected);
                    break;
            }
        } catch (ExceptionHandler eh) {
            System.out.println("cas " + i + " Exception levée par ExceptionHandler : " + eh.getMessage());
        } catch (NumberFormatException nfe) {
            System.out.println("cas " + i + " Exception levée par NumberFormatException : Empty string");
        } catch (Exception e) {
            System.out.println("cas " + i + " une erreur inconnue est survenue");
            e.printStackTrace();
        }

    }
}
