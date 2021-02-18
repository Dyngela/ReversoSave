package AFPA.CDA06.demo;

import AFPA.CDA06.demo.DAO.ConnexionManager;
import AFPA.CDA06.demo.DAO.DAOClient;
import AFPA.CDA06.demo.DAO.DAOContract;
import AFPA.CDA06.demo.DAO.DAOProspect;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import AFPA.CDA06.demo.Exception.TestException;
import AFPA.CDA06.demo.Swing.MainPage;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class App {


    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        MainPage homeFrame = new MainPage();
        homeFrame.setVisible(true);
        //TestException.tester();

    }
}
