package AFPA.CDA06.demo.Swing;

import AFPA.CDA06.demo.DAO.DAOClient;
import AFPA.CDA06.demo.DAO.DAOContract;
import AFPA.CDA06.demo.Entities.Client;
import AFPA.CDA06.demo.Entities.Contract;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static AFPA.CDA06.demo.Entities.ArrayListClientDAO.clientArrayList;
import static AFPA.CDA06.demo.Entities.Client.contractList;

public class ContractListFrame extends JFrame{
    private JButton buttonHome;
    private JTable tableContract;
    private JPanel contentPane;
    private JButton buttonExit;

    String[] headerContract = {"ID client", "ID contract", "Contract's name", "Contract's Amount"};
    private final Client client;
    private static final Logger LOGGER = LogManager.getLogger(ContractListFrame.class.getName());

    /**
     *      Constructor of the frame to display the contract list of a client
     *      call screenSetting()'s function
     *      to display the window properly
     *      Affect function to button Home page
     */
    public ContractListFrame(Client client) {
        this.client = client;
        setTitle("Show contracts");
        screenSetting();
        buttonHome.setText("Home page");
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ContractListFrame.this.exit(event);
            }
        });
        buttonHome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ContractListFrame.this.backToBaseScreen(event);
            }
        });
        try {
            ContractDisplayTable();
        } catch (Exception exception) {
            exception.printStackTrace();
            LOGGER.error("Error ContractListFrame, in the display " + exception);
        }


    }

    /**
     * Method use to close properly application.
     */
    private void exit(ActionEvent event) {
        this.dispose();
        System.exit(0);
    }

    /**
     *      Method called in constructor
     *      display a JTable using headerContract for columns name and Contract's attribute for the rows.
     */
    private void ContractDisplayTable() throws Exception {
        DefaultTableModel tab = new DefaultTableModel(new Object[][]{}, headerContract);
        contractList.clear();
        DAOContract.findByIDClient(client.getID());
        for (Contract contract : contractList) {
            tab.addRow(new Object[] {
                    contract.getID(),
                    contract.getIDContract(),
                    contract.getContractName(),
                    contract.getContractAmount()
            });
        }
        LOGGER.info("ContractDisplayTable worked properly.");
        tableContract.setModel(tab);
    }

    /**
     *      Method use on first place, in the constructor.
     *      set the window's dimension and basic setting, such as
     *      red cross behavior, close the apps
     *      minimal size, and location of the window.
     *      set the panel contentPane, who has all our visual element into.
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
     *      Method affected to Home page button.
     *      close that frame and open the MainPage frame, which is the menu's frame.
     */
    private void backToBaseScreen(ActionEvent event) {
        MainPage frame = new MainPage();
        this.dispose();
        frame.setVisible(true);
    }
}
