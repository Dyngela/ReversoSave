package AFPA.CDA06.demo;

import AFPA.CDA06.demo.Entities.ArrayListClient;
import AFPA.CDA06.demo.Entities.ArrayListProspect;
import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import AFPA.CDA06.demo.Exception.TestException;
import AFPA.CDA06.demo.Swing.MainPage;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class App {
    static {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");

            Prospected prospected0 = new Prospected(1, "Batigere", "42", "boulevard Stan", "54000",
                    "Nancy-centre", "01 17 85 96 52",
                    "batigere.name@batigere.com", "Comments are there", LocalDate.parse("10/10/2010", formatter) , "1");

            Prospected prospected1 = new Prospected(2, "La mission locale", "75", "rue Mazagran", "54000",
                    "Nancy-laxou", "07 80 80 78 89",
                    "user.use@mde.com", "", LocalDate.parse("10/12/2018", formatter) , "0");

            Prospected prospected2 = new Prospected(3, "Le champ des Oiseaux", "1", "Oiseau street", "89500",
                    "Une ville du 89", "58 51 33 55 44",
                    "lechamp.oiseau@oiseau.com", "Commenting on birds", LocalDate.parse("01/01/2000", formatter) , "1");

            Prospected prospected3 = new Prospected(4, "ChienLand", "57", "Etein", "55000",
                    "chienCity", "0383179818",
                    "chien.land@gmail.com", "Elevage de chien", LocalDate.parse("10/02/2005", formatter) , "0");

            ArrayListProspect.prospectedList.add(prospected0);
            ArrayListProspect.prospectedList.add(prospected1);
            ArrayListProspect.prospectedList.add(prospected2);
            ArrayListProspect.prospectedList.add(prospected3);

            Client client0 = new Client(1, "Creative Assembly", "5", "rue de metz", "54000", "Nancy",
                    "07 70 17 98 18", "Creative.assembly@CA.com",
                    "Comments are there", 1200, 100);

            Client client1 = new Client(2, "Blizzard", "17", "Avenue de la libération", "75032", "Vandoeuvre",
                    "592592592932", "blibli@bli",
                    "", 15512200, 562);

            Client client2 = new Client(3, "AFPA", "5", "square herzog", "54380", "Pompey",
                    "51566899558", "sylvie.touchot@maformationenligne.bzh",
                    "commentaty", 120000, 100);

            Client client3 = new Client(4, "ZebreLand", "88", "place de la réplublique", "54000", "Nancy",
                    "25 18 85 66 52", "zebreland@laposte.com",
                    "Comments are there", 1200, 2);

            ArrayListClient.clientList.add(client0);
            ArrayListClient.clientList.add(client1);
            ArrayListClient.clientList.add(client2);
            ArrayListClient.clientList.add(client3);
        } catch (ExceptionHandler exceptionHandler) {
            exceptionHandler.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {

        MainPage homeFrame = new MainPage();
        homeFrame.setVisible(true);
        TestException.tester();

    }
}

