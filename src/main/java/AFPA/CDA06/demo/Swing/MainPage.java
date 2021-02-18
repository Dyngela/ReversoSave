package AFPA.CDA06.demo.Swing;

import AFPA.CDA06.demo.DAO.DAOClient;
import AFPA.CDA06.demo.DAO.DAOContract;
import AFPA.CDA06.demo.DAO.DAOProspect;
import AFPA.CDA06.demo.Entities.*;
import AFPA.CDA06.demo.Exception.ExceptionHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.sql.SQLException;


import static AFPA.CDA06.demo.Entities.ArrayListClientDAO.clientArrayList;
import static AFPA.CDA06.demo.Entities.ArrayListContractDAO.clientWithContractList;
import static AFPA.CDA06.demo.Entities.ArrayListProspectDAO.prospectedArrayList;


/**
 * @author Erwan
 * Main UI class, all frames and components are initialize from here
 */


public class MainPage extends JFrame {

    /**
     * List of UI components
     **/
    private JPanel contentPane;
    private JButton buttonHomePage;
    private JButton buttonModify;
    private JButton buttonOK;
    private JComboBox<Serializable> comboBox;
    private JButton buttonCustomersManagement;
    private JButton buttonDisplay;
    private JButton buttonDelete;
    private JButton buttonProspectsManagement;
    private JButton buttonCreateANew;
    private JButton buttonCancel;
    private JButton buttonCancelRight;
    private JLabel comboBoxErrorLabel;
    private JButton buttonDisplayContract;
    private JButton buttonExit;
    private final Client emptyObjectClient = null;
    private final Prospected emptyObjectProspect = null;

    private static final Logger LOGGER = LogManager.getLogger(MainPage.class.getName());

    /**
     * Constructor.
     * call screenSetting() and baseScreen() to initialize window.
     * set cancel, cancelRight and Home page comun to client and prospect section.
     * set custommer management and prospect management buttons.
     **/
    public MainPage() {

        super("Home page");
        screenSetting();
        baseScreen();

        //button shared by custommer and prospect
        buttonHomePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.backToBaseScreen(event);
            }
        });

        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.exit(event);
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.backToBaseScreen(event);
            }
        });
        buttonCancelRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.backToTreatementSelection(event);
            }
        });

        buttonCustomersManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.customersManagementListener(event);
            }
        });
        buttonProspectsManagement.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.prospectManagementListener(event);
            }
        });
        buttonOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.buttonOKListenner(event);
            }
        });



        buttonCreateANew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.createANew(event);
            }
        });
        buttonModify.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.modify(event);
            }
        });
        buttonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.delete(event);
            }
        });
        buttonDisplay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {

                try {
                    MainPage.this.display(event);
                } catch (Exception e) {
                    e.printStackTrace();
                    LOGGER.fatal("Error in main page display " + e);
                }

            }
        });
        buttonDisplayContract.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                MainPage.this.displayContract(event);
            }
        });

    }



    //Prospect button section.

    /**
     * First button to choose, either prospect or client management. That set all the components and call
     * all the method of that section
     **/
    private void customersManagementListener(ActionEvent event) {

        baseScreen();
        emptyComboBox();
        try {

            //DAOClient.findAll() and DAOContract.findAll() are used to filled the arrayList of client and contract.
            //So we can use it with comboBox to display what we want and being able to select client or contract needed
            //We also sort it alphabeticly so it appears right in the comboBox
            DAOClient.findAll();
            clientArrayList.sort(new SortByName.SortByNameThenGrossSale());

            DAOContract.findAll();
            clientWithContractList.sort(new SortByName.SortByNameThenGrossSale());
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.fatal("Error retriving data with find all contract or client in the MainPage " + exception);
        }

        comboBoxErrorLabel.setVisible(false);

        buttonProspectsManagement.setEnabled(false);

        buttonCreateANew.setText("Create a new customer");
        buttonCreateANew.setEnabled(true);

        buttonModify.setText("Modify a customer");
        buttonModify.setEnabled(true);

        buttonDelete.setText("Delete a customer");
        buttonDelete.setEnabled(true);

        buttonDisplay.setText("Display customer list");
        buttonDisplay.setEnabled(true);

        buttonCancel.setText("Cancel");
        buttonCancel.setEnabled(true);

        buttonDisplayContract.setText("Client's contract");
        buttonDisplayContract.setEnabled(true);

        comboBoxErrorLabel.setText("No customer found!");
        comboBoxErrorLabel.setVisible(false);

        if(clientWithContractList.isEmpty()){
            buttonDisplayContract.setEnabled(false);
        }
//        Checking if the list is empty, you can't display client's list or delete/modify one
        if (clientArrayList.isEmpty()) {
            emptyComboBox();
            comboBoxErrorLabel.setVisible(true);
            buttonDisplay.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonModify.setEnabled(false);
            buttonDisplayContract.setEnabled(false);
        }

    }

    /**
     * First button to choose, either prospect or client management. That set all the components and call
     * all the method of that section
     **/
    private void prospectManagementListener(ActionEvent event) {

        baseScreen();
        emptyComboBox();
        try {

            //DAOProspect.findAll() is used to filled the arrayList of prospect
            //So we can use it with comboBox to display what we want and being able to select prospect needed
            //We also sort it alphabeticly so it appears right in the comboBox
            DAOProspect.findAll();
            prospectedArrayList.sort(new SortByName.SortByNameThenInterested());
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.fatal("Error retriving data with find all prospect in the MainPage " + exception);
        }
        comboBoxErrorLabel.setVisible(false);

        buttonCustomersManagement.setEnabled(false);


        buttonCreateANew.setText("Create a new prospect");
        buttonCreateANew.setEnabled(true);

        buttonModify.setText("Modify a prospect");
        buttonModify.setEnabled(true);

        buttonDelete.setText("Delete a prospect");
        buttonDelete.setEnabled(true);

        buttonDisplay.setText("Display prospect list");
        buttonDisplay.setEnabled(true);

        buttonCancel.setText("Cancel");
        buttonCancel.setVisible(true);
        buttonCancel.setEnabled(true);

        buttonDisplayContract.setVisible(false);
        buttonDisplayContract.setEnabled(false);

        comboBoxErrorLabel.setText("No prospect found!");
        comboBoxErrorLabel.setVisible(false);


//        Checking if the list is empty, you can't display prospect's list or delete/modify one
        if (prospectedArrayList.isEmpty()) {
            emptyComboBox();
            comboBoxErrorLabel.setVisible(true);
            buttonDisplay.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonModify.setEnabled(false);
        }

    }


    /**
     * method to create a new prospect or client.
     * If prospect management have been selected it'll create a prospect
     * If customer management have been selected it'll create a client
     * Close that frame and open CreateModifyDelete frame.
     **/
    private void createANew(ActionEvent event) {

        if (event.getActionCommand().equals("Create a new prospect")) {
            CreateModifyDeleteFrame createProspect = new CreateModifyDeleteFrame
                    ("Create", emptyObjectProspect);
            this.dispose();
            createProspect.setVisible(true);
        } else if (event.getActionCommand().equals("Create a new customer")) {
            CreateModifyDeleteFrame createClient = new CreateModifyDeleteFrame(emptyObjectClient, "Create");
            this.dispose();
            createClient.setVisible(true);
        } else {
            LOGGER.error("Problem with create a new");
            System.exit(1);
        }
    }


    /**
     * method to modify a prospect or a client according to the first management's button choice
     * if modify button is pressed.
     * Close that frame and open CreateModifyDelete frame.
     * with the selected object in the combo box as parameter.
     **/
    private void modify(ActionEvent event) {
        if (event.getActionCommand().equals("Modify a prospect")) {
            emptyComboBox();
            comboBoxManagementProspect();
            buttonCreateANew.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonDisplay.setEnabled(false);
            comboBox.setEnabled(true);
            buttonCancelRight.setEnabled(true);
            buttonOK.setEnabled(true);
            buttonOK.setText("Confirm Modify prospect's selected");


        } else if (event.getActionCommand().equals("Modify a customer")) {
            emptyComboBox();
            comboBoxManagementClient();
            buttonCreateANew.setEnabled(false);
            buttonDelete.setEnabled(false);
            buttonDisplay.setEnabled(false);
            comboBox.setEnabled(true);
            buttonCancelRight.setEnabled(true);
            buttonOK.setEnabled(true);
            buttonOK.setText("Confirm Modify client's selected");
            buttonCancelRight.setEnabled(true);
        } else {
            LOGGER.error("Problem with Modify from MainPage");
            System.exit(1);
        }
    }


    /**
     * method to delete a prospect or a client according to the first management's button choice
     * if delete button is pressed.
     * Close that frame and open CreateModifyDelete frame.
     * with the selected object in the combo box as parameter.
     **/
    private void delete(ActionEvent event) {


        if (event.getActionCommand().equals("Delete a prospect")) {

            //emptyComboBox();
            comboBoxManagementProspect();
            buttonCreateANew.setEnabled(false);
            buttonModify.setEnabled(false);
            buttonDisplay.setEnabled(false);
            comboBox.setEnabled(true);
            buttonCancelRight.setEnabled(true);
            buttonOK.setEnabled(true);
            buttonOK.setText("Confirm Delete prospect's selected");


        } else if (event.getActionCommand().equals("Delete a customer")) {

            //emptyComboBox();
            comboBoxManagementClient();
            buttonCreateANew.setEnabled(false);
            buttonModify.setEnabled(false);
            buttonDisplay.setEnabled(false);
            comboBox.setEnabled(true);
            buttonOK.setEnabled(true);
            buttonCancelRight.setEnabled(true);
            buttonOK.setText("Confirm Delete client's selected");

        } else {
            LOGGER.error("Problem with delete from MainPage");
            System.exit(1);
        }

    }

    /**
     * method to display the list of all the prospects or a client according to the first management's button choice
     * if display button is pressed.
     * Close that frame and open DisplayFrame frame.
     **/
    private void display(ActionEvent event) throws Exception {

        if (event.getActionCommand().equals("Display prospect list")) {

            String identifierProspectedDisplay = null;
            DisplayFrame displayProspectList = null;

                displayProspectList = new DisplayFrame(identifierProspectedDisplay);

            displayProspectList.setVisible(true);
            this.dispose();
        } else if (event.getActionCommand().equals("Display customer list")) {
            DisplayFrame displayClientList = new DisplayFrame();
            displayClientList.setVisible(true);
            this.dispose();
        } else {
            LOGGER.error("Problem with Display");
            System.exit(1);
        }
    }

    private void displayContract(ActionEvent event) {
        emptyComboBox();
        comboBoxManagementContract();
        buttonCreateANew.setEnabled(false);
        buttonModify.setEnabled(false);
        buttonDelete.setEnabled(false);
        buttonDisplay.setEnabled(false);
        comboBox.setEnabled(true);
        buttonCancelRight.setEnabled(true);
        buttonOK.setEnabled(true);
        buttonOK.setText("Confirm");

        comboBoxManagementContract();
    }



    /**
     * Method called in the constructor, shared by client and prospect.
     * it set disable the comboBox, the OK button and the cancel button on the right.
     * triggerred if cancel button is pressed.
     **/
    private void backToTreatementSelection(ActionEvent event) {
        emptyComboBox();
        comboBox.setEnabled(false);
        buttonCancelRight.setEnabled(false);
        buttonOK.setEnabled(false);
        buttonCreateANew.setEnabled(true);
        buttonModify.setEnabled(true);
        buttonDelete.setEnabled(true);
        buttonDisplay.setEnabled(true);
        buttonCancel.setEnabled(true);
        buttonDisplayContract.setEnabled(false);
    }


    /**
     * Method called in constructor, shared by client and prospect.
     * call the function baseScreen
     * triggered if cancel button below create, delete, modify and display is pressed.
     * or if home page button is pressed.
     * function shared by all the frame.
     **/
    private void backToBaseScreen(ActionEvent event) {

        emptyComboBox();
        baseScreen();

    }

    private void buttonOKListenner(ActionEvent event) {
        switch (event.getActionCommand()) {
            case "Confirm Modify prospect's selected":
                CreateModifyDeleteFrame modifyProspect = new CreateModifyDeleteFrame("Modify", ArrayListProspectDAO.
                        getProspectListDAO().get(comboBox.getSelectedIndex()));
                this.dispose();
                modifyProspect.setVisible(true);
                break;
            case "Confirm Modify client's selected":
                CreateModifyDeleteFrame modifyClient = new CreateModifyDeleteFrame(ArrayListClientDAO.
                        getClientListDAO().get(comboBox.getSelectedIndex()), "Modify");
                this.dispose();
                modifyClient.setVisible(true);
                break;
            case "Confirm Delete prospect's selected":
                CreateModifyDeleteFrame deleteProspect = new CreateModifyDeleteFrame("Delete", ArrayListProspectDAO.
                        getProspectListDAO().get(comboBox.getSelectedIndex()));
                this.dispose();
                deleteProspect.setVisible(true);
                break;
            case "Confirm Delete client's selected":
                CreateModifyDeleteFrame deleteClient = new CreateModifyDeleteFrame(ArrayListClientDAO.
                        getClientListDAO().get(comboBox.getSelectedIndex()), "Delete");
                this.dispose();
                deleteClient.setVisible(true);
                break;
            case "Confirm":
                ContractListFrame watchContract = new ContractListFrame(ArrayListClientDAO.
                        getClientListDAO().get(comboBox.getSelectedIndex()));
                this.dispose();
                watchContract.setVisible(true);
                break;
            default:
                System.out.println("Problem button ok listenner");
                System.exit(1);
        }

    }


    //Methods with no button associated.

    /**
     * Simply remove all the items from the comboBox.
     * Usefull when you switch from one section for an another.
     * e.g. You switch from client management to prospect management.
     **/
    private void emptyComboBox() { comboBox.removeAllItems(); }


    /**
     * Method use in deleteAClient and modifyAClient.
     * it fills the comboBox with the compagny's name of each object
     * in your ArrayList clientList according to their names then their gross sale
     * also set to visible comboBox and cancel/ok button of the right.
     **/
    private void comboBoxManagementClient() {

        comboBox.setVisible(true);
        buttonCancelRight.setVisible(true);
        buttonOK.setVisible(true);

        for (Client client : clientArrayList) {
            comboBox.addItem(client.getCompagnyName());
        }
    }

    private void comboBoxManagementContract() {

        comboBox.setVisible(true);
        buttonCancelRight.setVisible(true);
        buttonOK.setVisible(true);
        emptyComboBox();
        for (Client client : clientWithContractList) {
            comboBox.addItem(client.getCompagnyName());
        }
    }



    /**
     * Method use in deleteAProspect and modifyAProspect.
     * it fills the comboBox with the compagny's name of each object
     * in your ArrayList prospectList.
     * also set to visible comboBox and cancel/ok button of the right.
     **/
    public void comboBoxManagementProspect() {

        comboBox.setVisible(true);
        buttonCancelRight.setVisible(true);
        buttonOK.setVisible(true);

        for (Prospected prospect : prospectedArrayList) {
            comboBox.addItem(prospect.getCompagnyName());
        }
    }


    /**
     * Method use on first place, in the constructor.
     * set the window's dimension and basic setting, such as
     * red cross behavior, close the apps
     * minimal size, and location of the window.
     * set the panel contentPane, who has all our visual element into.
     **/
    public void screenSetting() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1200, 800));
        Dimension minSize = new Dimension(800, 600);
        this.setMinimumSize(minSize);
        this.setLocationRelativeTo(null);
        add(contentPane);
    }


    /**
     * Works in pair with screenSetting. Called in the constructor right after.
     * It sets the name of the button create, modify, delete and display,
     * and disable all the buttons but client, prospect management and home page.
     * also set unvisible error label of the comboBox
     **/
    public void baseScreen() {
        buttonCreateANew.setEnabled(false);
        buttonCreateANew.setText("Create a new");

        buttonModify.setEnabled(false);
        buttonModify.setText("Modify");

        buttonDelete.setEnabled(false);
        buttonDelete.setText("Delete");

        buttonDisplay.setEnabled(false);
        buttonDisplay.setText("Display");

        comboBoxErrorLabel.setVisible(false);

        buttonDisplayContract.setEnabled(false);

        buttonCancel.setEnabled(false);
        comboBox.setEnabled(false);
        buttonOK.setEnabled(false);
        buttonCancelRight.setEnabled(false);

        buttonCustomersManagement.setEnabled(true);
        buttonProspectsManagement.setEnabled(true);

    }

    /**
     * Method use to close properly application.
     */
    private void exit(ActionEvent event) {
        this.dispose();
        System.exit(0);
    }
}


