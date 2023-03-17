package Presentation;

import BLL.DeliveryService;
import Data.Notificator;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class EmployeeGUI implements Observer {
    private JFrame employeeWindow = new JFrame("Employee Window");
    private JButton getBack = new JButton("Log out");
    private JTextArea ordersTextArea = new JTextArea();

    private DeliveryService deliveryService;
    private EmployeeController employeeController;

    public EmployeeGUI(DeliveryService deliveryService) {
        this.deliveryService=deliveryService;
        employeeController=new EmployeeController(this,deliveryService);
    }

    public void createEmployeeWindow() {
        getBack.addActionListener(employeeController);

        employeeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        employeeWindow.setSize(600, 400);
        ordersTextArea.setSize(350,200);

        ordersTextArea.setText(Notificator.readDetails());

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.add(ordersTextArea);
        p2.add(getBack);

        p.add(p1);
        p.add(p2);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        employeeWindow.setVisible(true);
        employeeWindow.setContentPane(p);
    }

    @Override
    public void update(Observable o, Object arg) {
        Notificator.writeDetails(arg.toString() );
    }
}
