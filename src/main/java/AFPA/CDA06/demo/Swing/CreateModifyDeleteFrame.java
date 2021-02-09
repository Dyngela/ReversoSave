package AFPA.CDA06.demo.Swing;

import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Prospected;
import AFPA.CDA06.demo.Exception.ExceptionHandler;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import static AFPA.CDA06.demo.Entities.ArrayListClient.clientList;
import static AFPA.CDA06.demo.Entities.ArrayListProspect.prospectedList;

public class CreateModifyDeleteFrame extends JFrame {

    private JPanel contentPane;
    private JButton buttonHomePage;
    private JTextField compagnySNameTextField;
    private JTextField grossSaleOrDateTextField;
    private JTextField numberOfEmployeesOnTextField;
    private JTextArea commentsTextField;
    private JTextField cityTextField;
    private JTextField streetNameTextField;
    private JTextField streetNumberTextField;
    private JTextField postalCodeTextField;
    private JTextField emailTextField;
    private JTextField phoneNumberTextField;
    private JButton cancelButton;
    private JButton createDelModButton;
    private JButton buttonOK;
    private JButton buttonNo;
    private JButton buttonYes;
    private JTextField IDTextField;
    private JLabel confirmationDeletingLabel;
    private JPanel confirmationPanel;
    private Client selectedClient;
    private Prospected selectedProspect;
    private JComboBox<Serializable> comboBoxYesOrNo;
    private JLabel titleLabel;
    private JLabel numberOfEmployeeOrInterestedLabel;
    private JLabel grossSaleOrDateLabel;
    private JLabel commentLabel;



    /**
     * Enum for comboBox of prospect, either interested (Yes) or not (No)
     */
    public enum EnumYesOrNo {
        Yes,
        No
    }

    /**
     * Client constructor
     *
     * @param buttonClicked  Define which button have been clicked in the main page, either:
     *                       create, delete or modify
     *                       Act accordingly
     * @param selectedClient Client selected in the comboBox from the MainPage
     */
    public CreateModifyDeleteFrame(Client selectedClient, String buttonClicked) {

        switch (buttonClicked) {

            case "Modify":

                setTitle("Modify a client");
                screenSetting();
                IDTextField.setEditable(false);

                comboBoxYesOrNo.setVisible(false);
                confirmationPanel.setVisible(false);

                createDelModButton.setText("Save changes");
                titleLabel.setText("Modify a client:");

                // Method to fill the text area with the selected object's information
                textFieldFillingClient(selectedClient);

                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                createDelModButton.addActionListener(new modifyAClient(selectedClient));
                break;

            case "Create":

                setTitle("Creation of a new client");
                screenSetting();
                IDTextField.setEditable(false);

                comboBoxYesOrNo.setVisible(false);
                confirmationPanel.setVisible(false);

                createDelModButton.setText("Create");
                //Incrementing the ID for each client created.
                IDTextField.setText(String.valueOf(Client.setIDClientCount(Client.getIDClientCount())));

                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                createDelModButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.confirmCreationOfANewClient(event);
                    }
                });
                break;

            case "Delete":

                screenSetting();
                setTitle("Delete a client");

                confirmationPanel.setVisible(false);
                comboBoxYesOrNo.setVisible(false);

                titleLabel.setText("Delete a client:");
                createDelModButton.setText("Delete");
                //Panel of delete's confirmation
                confirmationDeletingLabel.setText("Are you sure to be willing to delete "
                        + selectedClient.getCompagnyName() +
                        " compagny's file? It can't be retrieve if deleted. ");
                confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 18));

                //fill the textfield with the selected object and set them all to uneditable
                textFieldFillingClient(selectedClient);
                setEditableFalseAll();

                createDelModButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.closeConfirmationPanel(event);
                    }
                });
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        clientList.remove(selectedClient);
                    }
                });
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                buttonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.closeConfirmationPanel(event);
                    }
                });
                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                break;

            default:

                System.out.println("Error in the client's constructor");
                System.exit(1);
        }

    }


    /**
     * Prospect constructor
     *
     * @param buttonClicked    Define which button have been clicked in the main page, either:
     *                         create, delete or modify
     * @param selectedProspect Prospect selected in the comboBox from the MainPage
     */
    public CreateModifyDeleteFrame(String buttonClicked, Prospected selectedProspect) {

        switch (buttonClicked) {

            case "Modify":

                setTitle("Modify a prospect");
                screenSetting();
                IDTextField.setEditable(false);

                numberOfEmployeesOnTextField.setVisible(false);
                comboBoxYesOrNo.setVisible(true);
                confirmationPanel.setVisible(false);
                createDelModButton.setText("Save changes");


                comboBoxYesOrNo.addItem("Yes");
                comboBoxYesOrNo.addItem("No");
                String prospectedInterested = prospectedList.get(prospectedList.indexOf(selectedProspect)).
                        getProspectedInterested(); // 0 ou 1, 0 for "Yes", 1 for "No"
                if (prospectedInterested.equals("Yes")) {
                    comboBoxYesOrNo.getModel().setSelectedItem("Yes");
                }
                if (prospectedInterested.equals("No")) {
                    comboBoxYesOrNo.getModel().setSelectedItem("No");
                }

                titleLabel.setText("Modify a prospect:");
                grossSaleOrDateLabel.setText("Date of prospection :");
                numberOfEmployeeOrInterestedLabel.setText("Interested : ");


                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                textFieldFillingProspect(selectedProspect);
                createDelModButton.addActionListener(new modifyAProspect(selectedProspect));
                break;

            case "Create":

                setTitle("Creation of a new prospect");
                screenSetting();

                comboBoxYesOrNo.setVisible(true);
                numberOfEmployeesOnTextField.setVisible(false);

                titleLabel.setText("Creation of a new prospect : ");
                IDTextField.setText(String.valueOf(Prospected.setIDProspectedCount
                        (Prospected.getIDProspectedCount())));
                grossSaleOrDateLabel.setText("Date of prospection :");
                numberOfEmployeeOrInterestedLabel.setText("Interested : ");
                createDelModButton.setText("Create");

                comboBoxYesOrNo.addItem(EnumYesOrNo.Yes);
                comboBoxYesOrNo.addItem(EnumYesOrNo.No);

                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                confirmationPanel.setVisible(false);
                createDelModButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.confirmCreationOfANewProspect(event);
                    }
                });
                IDTextField.setEditable(false);
                break;

            case "Delete":

                setTitle("Delete a client");
                screenSetting();
                setEditableFalseAll();

                comboBoxYesOrNo.setVisible(false);
                confirmationPanel.setVisible(false);
                numberOfEmployeesOnTextField.setVisible(true);

                grossSaleOrDateLabel.setText("Date of prospection :");
                titleLabel.setText("Delete a prospect:");
                numberOfEmployeeOrInterestedLabel.setText("Interested : ");
                createDelModButton.setText("Delete");
                confirmationDeletingLabel.setText("Are you sure to be willing to delete "
                        + selectedProspect.getCompagnyName() +
                        " compagny's file? It can't be retrieve if deleted. ");
                confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 18));
                createDelModButton.setText("Delete");

                textFieldFillingProspect(selectedProspect);

                createDelModButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.closeConfirmationPanel(event);
                    }
                });
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        prospectedList.remove(selectedProspect);
                    }
                });
                buttonHomePage.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                cancelButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                buttonYes.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.backToBaseScreen(event);
                    }
                });
                buttonNo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {
                        CreateModifyDeleteFrame.this.closeConfirmationPanel(event);
                    }
                });
                break;

            default:

                System.out.println("Error in the prospect's constructor");
                System.exit(1);

        }

    }


    /**
     * get the data from the ArraylistClient to fill the fields in delete section
     */
    private void textFieldFillingClient(Client selectedClient) {

        IDTextField.setText(String.valueOf(selectedClient.getID()));
        compagnySNameTextField.setText(selectedClient.getCompagnyName());
        grossSaleOrDateTextField.setText(String.valueOf(selectedClient.getGrossSales()));
        numberOfEmployeesOnTextField.setText(String.valueOf(selectedClient.getNumberOfEmployeesOnAverage()));
        commentsTextField.setText(selectedClient.getComments());
        cityTextField.setText(selectedClient.getCity());
        streetNameTextField.setText(selectedClient.getStreetName());
        streetNumberTextField.setText(selectedClient.getStreetNumber());
        postalCodeTextField.setText(selectedClient.getPostalCode());
        emailTextField.setText(selectedClient.getEmail());
        phoneNumberTextField.setText(selectedClient.getPhoneNumber());

    }

    /**
     * method for button create, confirming creation of a new client and calling backToBaseScreen()
     * to get back to main page
     */
    private void confirmCreationOfANewClient(ActionEvent event) {

        Client client;

        // getting all the information typed by user in the TextFields, and we check if they are conform
        // to our set of rules. If they are a new client is created
        try {

            int ID = Client.setIDClientCount(Client.getIDClientCount());
            String compagnyName = compagnySNameTextField.getText();
            String comment = commentsTextField.getText();
            String city = cityTextField.getText();
            String streetName = streetNameTextField.getText();
            String streetNumber = streetNumberTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String email = emailTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            double grossSale = Double.parseDouble(grossSaleOrDateTextField.getText());
            int numberOfEmployees = Integer.parseInt(numberOfEmployeesOnTextField.getText());

            client = new Client(ID, compagnyName, streetNumber, streetName, postalCode,
                    city, phoneNumber, email, comment, grossSale, numberOfEmployees);

            backToBaseScreen();
        } catch (NumberFormatException e) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Gross sale and number of employees fields have to be filled");
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);

        } catch (ExceptionHandler eh) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : " + eh.getMessage());
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);

        } catch (Exception e) {
            System.out.println("Exception inconnu");
            System.exit(1);
        }
    }

    /**
     * method for button modify, confirming modification of a client and calling backToBaseScreen()
     * to get back to main page
     */
    private void modifyClient(Client selectedClient) {

        // getting all the information typed by user in the TextFields, and we check if they are conform
        // to our set of rules. If they arethe client selected in the main page is modified
        String compagnyName = compagnySNameTextField.getText();
        double grossSale;
        int nbrEmployee;
        String comment = commentsTextField.getText();
        String city = cityTextField.getText();
        String streetName = streetNameTextField.getText();
        String streetNumber = streetNumberTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();

        try {

            grossSale = Double.parseDouble(grossSaleOrDateTextField.getText());
            nbrEmployee = Integer.parseInt(numberOfEmployeesOnTextField.getText());
            selectedClient.setCompagnyName(compagnyName);
            selectedClient.setGrossSales(grossSale);
            selectedClient.setComments(comment);
            selectedClient.setNumberOfEmployeesOnAverage(nbrEmployee);
            selectedClient.setCity(city);
            selectedClient.setStreetName(streetName);
            selectedClient.setStreetNumber(streetNumber);
            selectedClient.setPostalCode(postalCode);
            selectedClient.setEmail(email);
            selectedClient.setPhoneNumber(phoneNumber);
            backToBaseScreen();

        } catch (NumberFormatException e) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Gross sale and number of employees fields have to be filled");
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (ExceptionHandler eh) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : " + eh.getMessage());
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (Exception e) {
            System.out.println("Exception inconnu");
            System.exit(1);
        }
    }

    /**
     * inner class allowing us to pass parameter to modifyAClient to modify a client
     */
    private class modifyAClient implements ActionListener {

        private final Client client;

        public modifyAClient(Client client) {
            this.client = client;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modifyClient(client);
        }
    }

    /**
     * inner class allowing us to pass parameter to modifyAProspect to modify a prospect
     */
    private class modifyAProspect implements ActionListener {

        private final Prospected prospected;

        public modifyAProspect(Prospected prospected) {
            this.prospected = prospected;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modifyProspect(prospected);
        }
    }

    /**
     * get the data from the ArraylistProspect to fill the fields in delete section
     */
    private void textFieldFillingProspect(Prospected selectedProspect) {

        IDTextField.setText(String.valueOf(selectedProspect.getID()));
        compagnySNameTextField.setText(selectedProspect.getCompagnyName());
        cityTextField.setText(selectedProspect.getCity());
        streetNameTextField.setText(selectedProspect.getStreetName());
        streetNumberTextField.setText(selectedProspect.getStreetNumber());
        postalCodeTextField.setText(selectedProspect.getPostalCode());
        emailTextField.setText(selectedProspect.getEmail());
        phoneNumberTextField.setText(selectedProspect.getPhoneNumber());
        numberOfEmployeesOnTextField.setText(selectedProspect.getProspectedInterested());

        grossSaleOrDateTextField.setText(String.valueOf(selectedProspect.getProspectingDate()));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        LocalDate dateToBeChanged = LocalDate.parse(grossSaleOrDateTextField.getText());
        String tempDate = dateFormatter.format(dateToBeChanged);
        grossSaleOrDateTextField.setText(tempDate);

    }

    /**
     * method for button create, confirming creation of a new prospect and calling backToBaseScreen()
     * to get back to main page
     */
    private void confirmCreationOfANewProspect(ActionEvent event) {

        Prospected item;
        // getting all the information typed by user in the TextFields, and we check if they are conform
        // to our set of rules. If they are a new prospect is created
        try {
            int ID = Prospected.setIDProspectedCount(Prospected.getIDProspectedCount());
            String compagnyName = compagnySNameTextField.getText();
            String comment = commentsTextField.getText();
            String city = cityTextField.getText();
            String streetName = streetNameTextField.getText();
            String streetNumber = streetNumberTextField.getText();
            String postalCode = postalCodeTextField.getText();
            String email = emailTextField.getText();
            String phoneNumber = phoneNumberTextField.getText();
            String interested = String.valueOf(comboBoxYesOrNo.getSelectedIndex());

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
            String date = grossSaleOrDateTextField.getText();
            LocalDate dateOfProspection = LocalDate.parse(date, formatter);

            item = new Prospected(ID, compagnyName, streetNumber, streetName, postalCode,
                    city, phoneNumber, email, comment, dateOfProspection, interested);
            prospectedList.add(item);
            backToBaseScreen();

        } catch (DateTimeParseException e) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : date invalid.");
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (ExceptionHandler eh) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : " + eh.getMessage());
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (Exception e) {
            System.out.println("Exception inconnu");
            System.exit(1);
        }

    }

    /**
     * method for button modify, confirming modification of a prospect and calling backToBaseScreen()
     * to get back to main page
     */
    private void modifyProspect(Prospected selectedProspect) {

        // getting all the information typed by user in the TextFields, and we check if they are conform
        // to our set of rules. If they are a the selected prospect is modify

        String compagnyName = compagnySNameTextField.getText();
        String comment = commentsTextField.getText();
        String city = cityTextField.getText();
        String streetName = streetNameTextField.getText();
        String streetNumber = streetNumberTextField.getText();
        String postalCode = postalCodeTextField.getText();
        String email = emailTextField.getText();
        String phoneNumber = phoneNumberTextField.getText();
        String prospectingDate = grossSaleOrDateTextField.getText();

        try {
            selectedProspect.setCompagnyName(compagnyName);
            selectedProspect.setComments(comment);
            selectedProspect.setCity(city);
            selectedProspect.setStreetName(streetName);
            selectedProspect.setStreetNumber(streetNumber);
            selectedProspect.setPostalCode(postalCode);
            selectedProspect.setEmail(email);
            selectedProspect.setPhoneNumber(phoneNumber);
            LocalDate date = StringToDate(prospectingDate);
            selectedProspect.setProspectingDate(date);
            if (comboBoxYesOrNo.getSelectedIndex() == 0) {
                prospectedList.get(prospectedList.indexOf(selectedProspect)).setProspectedInterested("0");
            }
            if (comboBoxYesOrNo.getSelectedIndex() == 1) {
                prospectedList.get(prospectedList.indexOf(selectedProspect)).setProspectedInterested("1");
            } else {
                System.out.println("Error comboBox");
                System.exit(1);
            }
            backToBaseScreen();

        } catch (DateTimeParseException dfe) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : Watch out if your date is ok");
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (NumberFormatException e) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : " + e.getMessage());
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


        } catch (ExceptionHandler eh) {
            confirmationPanel.setVisible(true);
            buttonYes.setVisible(false);
            buttonNo.setVisible(false);
            confirmationDeletingLabel.setVisible(true);
            confirmationDeletingLabel.setText("Error : " + eh.getMessage());
            confirmationDeletingLabel.setFont(new Font("Serif", Font.PLAIN, 20));
            confirmationDeletingLabel.setForeground(Color.red);


            System.out.println(eh.getMessage());
        } catch (Exception e) {
            System.out.println("Exception inconnu");
            System.exit(1);
        }
    }

    /**
     * Deleting method for both client and prospect
     * closeConfirmationPanel just get rid of the panel of confirmation when deleting a file
     * setEditableFalseAll lock all the textField to be uneditable
     */
    private void closeConfirmationPanel(ActionEvent event) {
        confirmationPanel.setVisible(false);
    }

    private void setEditableFalseAll() {
        IDTextField.setEditable(false);
        compagnySNameTextField.setEditable(false);
        grossSaleOrDateTextField.setEditable(false);
        numberOfEmployeesOnTextField.setEditable(false);
        compagnySNameTextField.setEditable(false);
        cityTextField.setEditable(false);
        streetNameTextField.setEditable(false);
        streetNumberTextField.setEditable(false);
        postalCodeTextField.setEditable(false);
        phoneNumberTextField.setEditable(false);
        emailTextField.setEditable(false);
    }

    /**
     * method allowing us to go back to initial frame, main page
     */
    private void backToBaseScreen() {
        MainPage frame = new MainPage();
        this.dispose();
        frame.setVisible(true);
    }

    /**
     * method allowing us to go back to initial frame, main page if home page is pressed
     */
    private void backToBaseScreen(ActionEvent event) {
        MainPage frame = new MainPage();
        this.dispose();
        frame.setVisible(true);
    }

    /**
     * method to get frame working. Initialize the frame.
     * Define minSize, size, place of the frame and red cross behavior
     */
    public void screenSetting() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(new Dimension(1200, 800));
        Dimension minSize = new Dimension(800, 600);
        this.setMinimumSize(minSize);
        this.setLocationRelativeTo(null);
        add(contentPane);
    }

    /**
     * @param dateInUSFormat date like '2010/10/10' we'd like to change to 10/10/2010
     * @return date in param reversed in european format
     * @throws DateTimeParseException if date in entry in incorrect, so if not like '2020/10/10'
     */
    public LocalDate StringToDate(String dateInUSFormat) throws DateTimeParseException {

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //Parsing the given String to Date object
        return LocalDate.from(dateFormatter.parse(dateInUSFormat.replace("/", "-")));

    }

}
