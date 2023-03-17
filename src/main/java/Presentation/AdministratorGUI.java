package Presentation;

import BLL.DeliveryService;
import Data.User;

import javax.swing.*;

import static java.lang.String.valueOf;

public class AdministratorGUI extends JFrame {
    private JFrame adminWindow = new JFrame("Login Window");
    private JFrame addProductWindow = new JFrame("Add new Product");
    private JFrame deleteProductWindow = new JFrame("Delete product");
    private JFrame modifyProductWindow = new JFrame("Modify product");
    private JFrame composedProductWindow = new JFrame("Create composed product");

    private JButton importProducts  = new JButton("Import");
    private JButton addNewProduct = new JButton("Add product");
    private JButton createCompositeProduct = new JButton("Create composed product");
    private JButton deleteProduct = new JButton("Delete");
    private JButton modifyProduct = new JButton("Modify product");
    private JButton raport1 = new JButton("Time interval report");
    private JButton raport2 = new JButton("Products ordered more");
    private JButton raport3 = new JButton("Report3");
    private JButton raport4 = new JButton("Report4");
    private JButton returnToMainGUI = new JButton("Sign out");
    private JButton returnToMainGUI2 = new JButton("Sign out");
    private JButton ADD = new JButton("ADD");
    private JButton DELETE = new JButton("DELETE");
    private JButton MODIFY = new JButton("MODIFY");
    private JButton viewOrders = new JButton("View orders");

    //ADD PRODUCT
    private JTextField titleTF = new JTextField("", 10);
    private JTextField ratingTF = new JTextField("", 10);
    private JTextField caloriesTF = new JTextField("", 10);
    private JTextField proteinTF = new JTextField("", 10);
    private JTextField fatTF = new JTextField("", 10);
    private JTextField sodiumTF = new JTextField("", 10);
    private JTextField priceTF = new JTextField("", 10);

    //DELETE PRODUCT
    private JTextField deleteCriteria = new JTextField("",15);
    //MODIFY
    private JTextField modifyCriteria = new JTextField("", 15);
    private JTextField toSetValue = new JTextField("", 15);
    private String[] fields = {"title", "rating", "calories", "protein", "fat", "sodium", "price"};
    private JComboBox<String> criteriaFields = new JComboBox<String>(fields);
    private JComboBox<String> toModifyFields = new JComboBox<String>(fields);

    //COMPUTE PRODUCT
    private JButton addToCompositeProduct = new JButton("Add");
    private JButton COMPUTE = new JButton("COMPUTE");
    private JTextField compositeName = new JTextField("", 20);
    private JComboBox<String> menuItems;

    //RAPORT1
    private JTextField startHour = new JTextField("",5);
    private JTextField endHour = new JTextField("",5);

    //RAPORT2
    private JTextField amountTF = new JTextField("",5);

    //RAPORT3
    private JTextField amount2 = new JTextField("", 5);
    private JTextField price = new JTextField("",5);

    //RAPORT4
    private JTextField day = new JTextField("",5);
    private JTextField month = new JTextField("",5);
    private JTextField year = new JTextField("",5);

    private DeliveryService deliveryService;
    private AdminController controller;

    public AdministratorGUI(DeliveryService deliveryService, User user) {
        this.deliveryService=deliveryService;
        this.controller=new AdminController(this, deliveryService, user);

    }

    public void createAdminWindow() {
        addNewProduct.addActionListener(controller);
        deleteProduct.addActionListener(controller);
        importProducts.addActionListener(controller);
        modifyProduct.addActionListener(controller);
        createCompositeProduct.addActionListener(controller);
        raport1.addActionListener(controller);
        raport2.addActionListener(controller);
        raport3.addActionListener(controller);
        raport4.addActionListener(controller);
        returnToMainGUI.addActionListener(controller);
        createCompositeProduct.addActionListener(controller);
        viewOrders.addActionListener(controller);

        adminWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminWindow.setSize(600, 400);

        JLabel start = new JLabel("StartH:");
        JLabel end = new JLabel("EndH");
        JLabel amount = new JLabel("Amount");

        JPanel p = new JPanel();
        JPanel p0 = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();

        p0.add(importProducts);
        p1.add(addNewProduct);
        p2.add(deleteProduct);
        p2.add(modifyProduct);
        p2.add(createCompositeProduct);
        p3.add(start);
        p3.add(startHour);
        p3.add(end);
        p3.add(endHour);
        p3.add(raport1);
        p4.add(amount);
        p4.add(amountTF);
        p4.add(raport2);

        p5.add(new JLabel("Amount"));
        p5.add(amount2);
        p5.add(new JLabel("Price"));
        p5.add(price);
        p5.add(raport3);
        p6.add(new JLabel("D:"));
        p6.add(day);
        p6.add(new JLabel("M:"));
        p6.add(month);
        p6.add(new JLabel("Y:"));
        p6.add(year);
        p6.add(raport4);
        p7.add(returnToMainGUI);
        p7.add(viewOrders);


        p.add(p0);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.add(p6);
        p.add(p7);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        adminWindow.setVisible(true);
        adminWindow.setContentPane(p);
    }

    public void addNewProductWindow() {
        ADD.addActionListener(controller);
        returnToMainGUI2.addActionListener(controller);
        addProductWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addProductWindow.setSize(600, 400);
        JPanel p = new JPanel();

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();

        JLabel label1 = new JLabel("Title:");
        JLabel label2 = new JLabel("Rating:");
        JLabel label3 = new JLabel("Calories:");
        JLabel label4 = new JLabel("Protein:");
        JLabel label5 = new JLabel("Fat:");
        JLabel label6 = new JLabel("Sodium:");
        JLabel label7 = new JLabel("Price:");

        p1.add(label1);
        p1.add(titleTF);
        p1.add(label2);
        p1.add(ratingTF);
        p2.add(label3);
        p2.add(caloriesTF);
        p2.add(label4);
        p2.add(proteinTF);
        p3.add(label5);
        p3.add(fatTF);
        p3.add(label6);
        p3.add(sodiumTF);
        p4.add(label7);
        p4.add(priceTF);
        p5.add(ADD);
        p5.add(returnToMainGUI2);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p5);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        addProductWindow.setVisible(true);
        addProductWindow.setContentPane(p);

    }

    public void createDeleteProductWindow() {
        DELETE.addActionListener(controller);
        deleteProductWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        deleteProductWindow.setSize(600, 400);

        JLabel label = new JLabel("Delete criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();

        p1.add(DELETE);
        p2.add(label);
        p2.add(deleteCriteria);

        p.add(p2);
        p.add(p1);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        deleteProductWindow.setVisible(true);
        deleteProductWindow.setContentPane(p);
    }

    public void createModifyProductWindow() {
        MODIFY.addActionListener(controller);
        modifyProductWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        modifyProductWindow.setSize(600, 400);

        JLabel label = new JLabel("Modify criteria:");
        JLabel label2 = new JLabel("Set value:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();

        p2.add(criteriaFields);
        p2.add(label);
        p2.add(modifyCriteria);
        p4.add(toModifyFields);
        p4.add(label2);
        p4.add(toSetValue);
        p1.add(MODIFY);

        p.add(p3);
        p.add(p2);
        p.add(p4);
        p.add(p1);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        modifyProductWindow.setVisible(true);
        modifyProductWindow.setContentPane(p);

    }

    public void createComputeProductWindow() {
        addToCompositeProduct.addActionListener(controller);
        COMPUTE.addActionListener(controller);
        composedProductWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        composedProductWindow.setSize(600, 400);

        String result = deliveryService.getMenuItems();
        String[] data = result.split("\n");
        setComboBoxValues(data);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(menuItems);
        p2.add(addToCompositeProduct);
        p3.add(compositeName);
        p3.add(COMPUTE);
        p.add(p1);
        p.add(p2);
        p.add(p3);
        composedProductWindow.setVisible(true);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        composedProductWindow.setContentPane(p);

    }

    public JButton getImportProducts() {
        return importProducts;
    }

    public JButton getAddNewProduct() {
        return addNewProduct;
    }

    public JButton getDeleteProduct() {
        return deleteProduct;
    }

    public JButton getRaport1() {
        return raport1;
    }

    public JButton getRaport2() {
        return raport2;
    }

    public JButton getRaport3() {
        return raport3;
    }

    public JButton getRaport4() {
        return raport4;
    }

    public JButton getReturnToMainGUI() {
        return returnToMainGUI;
    }

    public JButton getCreateCompositeProduct() {
        return createCompositeProduct;
    }

    public JButton getADD() {
        return ADD;
    }

    public JButton getReturnToMainGUI2() {
        return returnToMainGUI2;
    }

    public String getTitle() {
        return titleTF.getText();
    }

    public Double getRating() {
        return Double.parseDouble(ratingTF.getText());
    }

    public Double getCalories() {
        return Double.parseDouble(caloriesTF.getText());
    }

    public Double getProtein() {
        return Double.parseDouble(proteinTF.getText());
    }

    public Double getFat() {
        return Double.parseDouble(fatTF.getText());
    }

    public Double getSodium() {
        return Double.parseDouble(sodiumTF.getText());
    }

    public Double getPrice() {
        return Double.parseDouble(priceTF.getText());
    }

    public JButton getDELETE() {
        return DELETE;
    }

    public String getDeleteValue() {
        return deleteCriteria.getText();
    }

    public JButton getModifyProduct() {
        return modifyProduct;
    }

    public JButton getMODIFY() {
        return MODIFY;
    }

    public String getCriteriaField() {
        return valueOf(criteriaFields.getSelectedItem());
    }

    public String getCriteriaValue() {
        return modifyCriteria.getText();
    }

    public String getToModifyField() {
        return valueOf(toModifyFields.getSelectedItem());
    }

    public String getToSetValue() {
        return toSetValue.getText();
    }

    public void closeWindow() {
        adminWindow.dispose();
    }

    public void closeWindow2() {
        addProductWindow.dispose();
    }

    public void setComboBoxValues(String[] values) {
        JComboBox<String> aux = new JComboBox<>(values);
        menuItems = aux;
    }

    public JButton getAddToCompositeProduct() {
        return addToCompositeProduct;
    }

    public String getMenuComboBoxSelected() {
        return valueOf(menuItems.getSelectedItem());
    }

    public JButton getCOMPUTE() {
        return COMPUTE;
    }

    public String getComputedName() {
        return compositeName.getText();
    }

    public String getStartH() {
        return startHour.getText();
    }
    public String getEndH() {
        return endHour.getText();
    }

    public JButton getViewOrders() {
        return viewOrders;
    }

    public Integer getAmount() {
        return Integer.parseInt(amountTF.getText());
    }

    public Integer getDay() {
        return Integer.parseInt(day.getText());
    }

    public Integer getMonth() {
        return Integer.parseInt(month.getText());
    }

    public Integer getYear() {
        return Integer.parseInt(year.getText());
    }

    public Integer getAmount2() {
        return Integer.parseInt(amount2.getText());
    }

    public Integer getPrice2() {
        return Integer.parseInt(price.getText());
    }
}
