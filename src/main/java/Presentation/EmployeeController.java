package Presentation;

import BLL.DeliveryService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeeController implements ActionListener {

    private EmployeeGUI employeeGUI;
    private DeliveryService deliveryService;

    public EmployeeController(EmployeeGUI gui, DeliveryService deliveryService) {
        this.employeeGUI=gui;
        this.deliveryService=deliveryService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
