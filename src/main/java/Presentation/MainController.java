package Presentation;
import BLL.DeliveryService;
import Data.Serializer;
import Data.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainController implements ActionListener {

    private MainGUI mainGUI;
    private DeliveryService deliveryService;

    public MainController(MainGUI mainGUI, DeliveryService deliveryService) {
        this.mainGUI=mainGUI;
        this.deliveryService=deliveryService;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if(source == mainGUI.getRegister() && mainGUI.getComboBox().getSelectedItem().equals("administrator")) {
            if(mainGUI.verifyTextFields()) {
                mainGUI.displayRegistrationMessage1();
            }
            else mainGUI.displayMessage();

        }

        if(source == mainGUI.getRegister() && mainGUI.getComboBox().getSelectedItem().equals("employee")) {
            if(mainGUI.verifyTextFields()) {
                mainGUI.displayRegistrationMessage2();
            }
            else mainGUI.displayMessage();
        }

        if(source == mainGUI.getRegister() && mainGUI.getComboBox().getSelectedItem().equals("client")) {

            if(mainGUI.verifyTextFields()) {
                String username = mainGUI.getUsername();
                String password = mainGUI.getPassword();

                boolean x = deliveryService.checkUsernameExistence(username);
                if(x==true) {
                    System.out.println("REGISTRATION SUCCESSFUL");
                }
                else System.out.println("USERNAME TAKEN");
                deliveryService.addUser("client", username, password);
            }
            else mainGUI.displayMessage();

        }

        if(source == mainGUI.getLogin() && mainGUI.getComboBox().getSelectedItem().equals("administrator")) {
            if(mainGUI.verifyTextFields()) {
                String username = mainGUI.getUsername();
                String password = mainGUI.getPassword();
                int state = deliveryService.checkCredentials(username,password,"administrator");
                if(state == 1) {
                    //System.out.println("ALL OK");
                    mainGUI.closeGUI();
                    User user = deliveryService.findUser(username);
                    AdministratorGUI gui = new AdministratorGUI(deliveryService, user);
                    gui.createAdminWindow();
                }
                if(state == 2) {
                    System.out.println("WRONG USERNAME");
                }
                if(state == 3) {
                    System.out.println("WRONG PASSWORD");
                }
                if(state == 4) {
                    System.out.println("WRONG ROLE");
                }
                if(state == 5) {
                    System.out.println("COULDN'T FIND USER");
                }

            }
            else mainGUI.displayMessage();

        }

        if(source == mainGUI.getLogin() && mainGUI.getComboBox().getSelectedItem().equals("client")) {
            String username = mainGUI.getUsername();
            String password = mainGUI.getPassword();
            int state = deliveryService.checkCredentials(username,password,"client");
            if(state == 1) {
                mainGUI.closeGUI();
                User user = deliveryService.findUser(username);
                ClientGUI gui = new ClientGUI(deliveryService,user);
                gui.createClientWindow();
            }
            if(state == 2) {
                System.out.println("WRONG USERNAME");
            }
            if(state == 3) {
                System.out.println("WRONG PASSWORD");
            }
            if(state == 4) {
                System.out.println("WRONG ROLE");
            }

            if(state == 5) {
                System.out.println("COULDN'T FIND USER");
            }

            Serializer.serialize(deliveryService);
        }

        if(source == mainGUI.getLogin() && mainGUI.getComboBox().getSelectedItem().equals("employee")) {
            String username = mainGUI.getUsername();
            String password = mainGUI.getPassword();
            int state = deliveryService.checkCredentials(username,password,"employee");
            if(state == 1) {
                mainGUI.closeGUI();
                EmployeeGUI employeeGUI = new EmployeeGUI(deliveryService);
                employeeGUI.createEmployeeWindow();
            }
            if(state == 2) {
                System.out.println("WRONG USERNAME");
            }
            if(state == 3) {
                System.out.println("WRONG PASSWORD");
            }
            if(state == 4) {
                System.out.println("WRONG ROLE");
            }
            if(state == 5) {
                System.out.println("COULDN'T FIND USER");
            }

        }



    }
}
