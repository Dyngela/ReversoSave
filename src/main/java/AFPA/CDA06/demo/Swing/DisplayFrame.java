package AFPA.CDA06.demo.Swing;

import AFPA.CDA06.demo.DAO.DAOClient;
import AFPA.CDA06.demo.DAO.DAOProspect;
import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

import static AFPA.CDA06.demo.Entities.ArrayListClientDAO.clientArrayList;
import static AFPA.CDA06.demo.Entities.ArrayListProspectDAO.prospectedArrayList;


public class DisplayFrame extends JFrame {

    private JPanel contentPane;
    private JButton buttonHomePage;
    private JTable showTable;
    private JScrollPane scrollPane;
    private JButton buttonExit;

    private static final Logger LOGGER = LogManager.getLogger(DisplayFrame.class.getName());


    /**
     *      Titles of our columns,
     *      headerClient, if we display clientList.
     *      headerProspect if we display prospectList.
     */
    String[] headerCLient = {"ID", "Compagny name", "City", "Street's name", "Street's number",
            "Postal code",   "Email", "Phone number", "Gross sale",
            "Number of employee on average" };
    String[] headerProspect = {"ID","Compagny name", "City", "Street's name", "Street's number",
            "Postal code",   "Email", "Phone number", "Interested", "Date of prospection" };


    /**
     *      Constructor of the frame to display the clients list
     *      call super to set the title of the screen and screenSetting()'s function
     *      to display the window properly
     *      Affect function to button Home page
     */
    public DisplayFrame(){

        super("Client list display");
        screenSetting();
        buttonHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DisplayFrame.this.backToBaseScreen(event);
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DisplayFrame.this.exit(event);
            }
        });
        try {
            ClientDisplayTable();
        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }


    /**
     *      Constructor of the frame to display prospects list
     *      Parameter isn't use as such. it's there for constructors difference
     *      call super to set the title of the screen and screenSetting()'s function
     *      to display the window properly
     *      Affect function to button Home page
     *      @param displayProspectList Unused param. Here for constructor differenciation.
     */
    public DisplayFrame(String displayProspectList) throws Exception {

        super("Prospect list display");
        screenSetting();
        buttonHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DisplayFrame.this.backToBaseScreen(event);
            }
        });
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                DisplayFrame.this.exit(event);
            }
        });
        ProspectDisplayTable();

    }


    /**
     *      Method called in constructor
     *      display a JTable using headerClient for columns name and Client's attribute for the rows.
     */
    public void ClientDisplayTable() throws Exception {

        DefaultTableModel tab = new DefaultTableModel(new Object[][]{}, headerCLient);
        clientArrayList.clear();
        DAOClient.findAll();
        for (Client client : clientArrayList) {
            tab.addRow(new Object[] {
                    client.getID(),
                    client.getCompagnyName(),
                    client.getCity(),
                    client.getStreetName(),
                    client.getStreetNumber(),
                    client.getPostalCode(),
                    client.getEmail(),
                    client.getPhoneNumber(),
                    client.getGrossSales(),
                    client.getNumberOfEmployeesOnAverage()});
        }
        showTable.setModel(tab);
        LOGGER.info("Correct Display of client table");
    }


    /**
     *      Method called in constructor
     *      display a JTable using headerProspect for columns name and prospect's attribute for the rows.
     */
    private void ProspectDisplayTable() throws Exception {

        DefaultTableModel tab = new DefaultTableModel(new Object[][]{}, headerProspect);
        prospectedArrayList.clear();
        DAOProspect.findAll();
        for(Prospected prospect : prospectedArrayList){

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
            LocalDate dateToBeChanged = prospect.getProspectingDate();
            String dateOfProspection = dateFormatter.format(dateToBeChanged);

            tab.addRow(new Object[] {
                    prospect.getID(),
                    prospect.getCompagnyName(),
                    prospect.getCity(),
                    prospect.getStreetName(),
                    prospect.getStreetNumber(),
                    prospect.getPostalCode(),
                    prospect.getEmail(),
                    prospect.getPhoneNumber(),
                    prospect.getProspectedInterested(),
                    dateOfProspection
            });
        }
        showTable.setModel(tab);
        LOGGER.info("Correct Display of prospect table");
    }


    /**
     *      Method use on first place, in the constructor.
     *      set the window's dimension and basic setting, such as
     *      red cross behavior, close the apps
     *      minimal size, and location of the window.
     *      set the panel contentPane, who has all our visual element into.
     */
    public void screenSetting(){
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1200, 800));
        Dimension minSize = new Dimension(800, 600);
        this.setMinimumSize(minSize);
        this.setLocationRelativeTo(null);
        add(contentPane);
        this.setVisible(true);
    }


    /**
     *      Method affected to Home page button.
     *      close that frame and open the MainPage frame, which is the menu's frame.
     */
    private void backToBaseScreen(ActionEvent event) {
        MainPage frame = new MainPage();
        this.dispose();
        frame.setVisible(true);
    }

    /**
     * Method use to close properly application.
     */
    private void exit(ActionEvent event) {
        this.dispose();
        System.exit(0);
    }
}
