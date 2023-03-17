package Presentation;

import BLL.DeliveryService;

import javax.swing.*;

public class MainGUI extends JFrame {

    private JFrame loginWindow = new JFrame("Login Window");
    private JTextField usernameTF = new JTextField("", 20);
    private JPasswordField passTF = new JPasswordField(20);
    private String[] users = {"administrator", "client", "employee"};
    private JComboBox<String> roles = new JComboBox<String>(users);
    private JButton login = new JButton("Log in");
    private JButton register = new JButton("Register");

    private DeliveryService deliveryService;
    private MainController controller;


    public MainGUI(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
        this.controller = new MainController(this, deliveryService);
        createMainWindow();
    }

    public void createMainWindow(){
        login.addActionListener(controller);
        register.addActionListener(controller);


        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(600, 400);

        JLabel nameLabel = new JLabel("Username:");
        JLabel passLabel = new JLabel("Password:");


        JPanel p = new JPanel();
        JPanel p0 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        JLabel msg1 = new JLabel("Introduce username and password");
        p0.add(msg1);
        p1.add(roles);
        p2.add(nameLabel);
        p2.add(usernameTF);
        p3.add(passLabel);
        p3.add(passTF);
        p4.add(login);
        p4.add(register);

        p.add(p0);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        loginWindow.setVisible(true);
        loginWindow.setContentPane(p);
    }

    public void displayMessage() {
        JFrame loginWindow = new JFrame("Login Window");
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(200, 200);
        JPanel p = new JPanel();

        JLabel msg = new JLabel("You didn't introduce some data");
        p.add(msg);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        loginWindow.setVisible(true);
        loginWindow.setContentPane(p);
    }

    public void displayRegistrationMessage1() {
        JFrame loginWindow = new JFrame("Error");
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(400, 200);
        JPanel p = new JPanel();

        JLabel msg = new JLabel("You can't register as an administrator!");
        p.add(msg);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        loginWindow.setVisible(true);
        loginWindow.setContentPane(p);
    }

    public void displayRegistrationMessage2() {
        JFrame loginWindow = new JFrame("Error");
        loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginWindow.setSize(400, 200);
        JPanel p = new JPanel();

        JLabel msg = new JLabel("You can't register as an employee!");
        p.add(msg);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        loginWindow.setVisible(true);
        loginWindow.setContentPane(p);
    }


    public JButton getLogin() {
        return login;
    }

    public JButton getRegister() {
        return register;
    }

    public String getUsername() {
        return usernameTF.getText();
    }

    public String getPassword() {
        return passTF.getText();
    }

    public boolean verifyTextFields() {
        if (getUsername().equals("") || getPassword().equals("")) {
            return false;
        }

        return true;
    }

    public JComboBox getComboBox() {
        return roles;
    }

    public void closeGUI() {
        loginWindow.dispose();
    }


}
