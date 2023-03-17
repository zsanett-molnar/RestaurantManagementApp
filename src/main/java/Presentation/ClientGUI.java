package Presentation;

import BLL.DeliveryService;
import Data.User;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import javax.swing.*;

import static java.lang.String.valueOf;

public class ClientGUI extends JFrame {
    private JFrame clientWindow = new JFrame("Client Window");
    private JFrame searchByTitleWindow = new JFrame("Search by title");
    private JFrame searchByRatingWindow = new JFrame("Search by rating");
    private JFrame searchByCaloriesWindow = new JFrame("Search by calories");
    private JFrame searchByProteinWindow = new JFrame("Search by protein");
    private JFrame searchByFatWindow = new JFrame("Search by fat");
    private JFrame searchBySodiumWindow = new JFrame("Search by sodium");
    private JFrame searchByPriceWindow = new JFrame("Search by price");
    private JFrame searchResultWindow = new JFrame("Search result window");
    private JFrame createOrderWindow = new JFrame("Create Order");


    private JButton viewProducts = new JButton("View Products");
    private JButton returnToMainGUI = new JButton("Sign out");
    private JButton searchByTitle = new JButton("Search by title");
    private JButton searchByRating = new JButton("Search by rating");
    private JButton searchByCalories = new JButton("Search by calories");
    private JButton searchByProtein = new JButton("Search by protein");
    private JButton searchByFat = new JButton("Search by fat");
    private JButton searchBySodium = new JButton("Search by sodium");
    private JButton searchByPrice = new JButton("Search by price");
    private JButton createOrder = new JButton("Create order");
    private JButton addToShoppingBasket = new JButton("Add to basket");
    private JButton placeOrder = new JButton("Place order");


    //private JButton SEARCH = new JButton("Search");

    private JTextField searchCriteria = new JTextField("",20);
    private JButton searchByTitleButton = new JButton("Search");
    private JButton searchByRatingButton = new JButton("Search");
    private JButton searchByCaloriesButton = new JButton("Search");
    private JButton searchByProteinButton = new JButton("Search");
    private JButton searchByFatButton = new JButton("Search");
    private JButton searchBySodiumButton = new JButton("Search");
    private JButton searchByPriceButton = new JButton("Search");

    private JButton back = new JButton("Back");
    private JButton signOut = new JButton("Sign out");

    //ORDER
    private JComboBox<String> menuItems;


    private DeliveryService deliveryService;
    private ClientController controller;

    public ClientGUI(DeliveryService deliveryService, User user) {
        this.deliveryService=deliveryService;
        this.controller=new ClientController(this, deliveryService, user);

    }

    public void createClientWindow() {
        viewProducts.addActionListener(controller);
        returnToMainGUI.addActionListener(controller);
        searchByTitle.addActionListener(controller);
        searchByRating.addActionListener(controller);
        searchByCalories.addActionListener(controller);
        searchByProtein.addActionListener(controller);
        searchByFat.addActionListener(controller);
        searchBySodium.addActionListener(controller);
        searchByPrice.addActionListener(controller);
        createOrder.addActionListener(controller);
        signOut.addActionListener(controller);

        clientWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        clientWindow.setSize(600, 400);

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();

        p1.add(viewProducts);
        p2.add(searchByTitle);
        p2.add(searchByRating);
        p2.add(searchByCalories);
        p3.add(searchByProtein);
        p3.add(searchByFat);
        p3.add(searchBySodium);
        p4.add(searchByPrice);
        p6.add(createOrder);
        p5.add(returnToMainGUI);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.add(p4);
        p.add(p6);
        p.add(p5);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        clientWindow.setVisible(true);
        clientWindow.setContentPane(p);
    }

    public void searchResultWindow(String result) {
        searchResultWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchResultWindow.setSize(600, 400);
        JPanel p = new JPanel();

        String[] data = result.split("\n");


        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Product(title,rating,calories,protein,fat,sodium,price)",data);
        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        JDialog d = new JDialog();
        d.add(sp);
        d.setSize(600,400);
        d.setVisible(true);

        //p.add(d);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        clientWindow.setVisible(true);
        clientWindow.setContentPane(p);
    }


    public JButton getViewProducts() {
        return viewProducts;
    }

    public JButton getReturnToMainGUI() {
        return returnToMainGUI;
    }

    public JButton getSearchByTitle() {
        return searchByTitle;
    }

    public JButton getSearchByRating() {
        return searchByRating;
    }

    public JButton getSearchByCalories() {
        return searchByCalories;
    }

    public JButton getSearchByProtein() {
        return searchByProtein;
    }

    public JButton getSearchByFat() {
        return searchByFat;
    }

    public JButton getSearchBySodium() {
        return searchBySodium;
    }

    public JButton getSearchByPrice() {
        return searchByPrice;
    }

    //
    public JButton getSearchByTitleButton() {
        return searchByTitleButton;
    }

    public JButton getSearchByRatingButton() {
        return searchByRatingButton;
    }

    public String getValue() {
        return searchCriteria.getText();
    }

    public JButton getSearchByCaloriesButton() {
        return searchByCaloriesButton;
    }

    public JButton getSearchByProteinButton() {
        return searchByProteinButton;
    }

    public JButton getSearchByFatButton() {
        return searchByFatButton;
    }

    public JButton getSearchBySodiumButton() {
        return searchBySodiumButton;
    }

    public JButton getSearchByPriceButton() {
        return searchByPriceButton;
    }

    public JButton getBack() {
        return back;
    }

    public void closeWindow() {
        clientWindow.dispose();
    }

    //order


    public JButton getCreateOrder() {
        return createOrder;
    }

    public JButton getAddToShoppingBasket() {
        return addToShoppingBasket;
    }

    public JButton getPlaceOrder() {
        return placeOrder;
    }

    public static <T> void getRows(List<T> list) {

        DefaultTableModel model = new DefaultTableModel();

        int i=0;
        Boolean firstIteration = true;

        for(Object object:list) {

            String data[]=new String[100];

            for(Field field:object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                Object value;
                try {
                    value=field.get(object);
                    if(firstIteration==true)
                        model.addColumn(field.getName());
                    data[i]=value.toString();
                    i++;


                }
                catch(IllegalArgumentException e) {
                    e.printStackTrace();
                }
                catch(IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
            model.addRow(data);
            i=0;
            firstIteration=false;
        }

        JTable j = new JTable(model);
        JScrollPane sp = new JScrollPane(j);

        JDialog d = new JDialog();
        d.add(sp);
        d.setSize(600,400);
        d.setVisible(true);
    }

    public void createSearchByTitleWindow() {
        searchByTitleButton.addActionListener(controller);
        back.addActionListener(controller);
        searchByTitleWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByTitleWindow.setSize(600, 400);

        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByTitleButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByTitleWindow.setVisible(true);
        searchByTitleWindow.setContentPane(p);

    }

    public void createSearchByRatingWindow() {
        back.addActionListener(controller);
        searchByRatingButton.addActionListener(controller);
        searchByRatingWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByRatingWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByRatingButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByRatingWindow.setVisible(true);
        searchByRatingWindow.setContentPane(p);

    }

    public void createSearchByProteinWindow() {
        back.addActionListener(controller);
        searchByProteinButton.addActionListener(controller);
        searchByProteinWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByProteinWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByProteinButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByProteinWindow.setVisible(true);
        searchByProteinWindow.setContentPane(p);

    }

    public void createSearchByCaloriesWindow() {
        searchByCaloriesButton.addActionListener(controller);
        back.addActionListener(controller);
        searchByCaloriesWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByCaloriesWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByCaloriesButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByCaloriesWindow.setVisible(true);
        searchByCaloriesWindow.setContentPane(p);

    }

    public void createSearchByFatWindow() {
        back.addActionListener(controller);
        searchByFatButton.addActionListener(controller);
        searchByFatWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByFatWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByFatButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByFatWindow.setVisible(true);
        searchByFatWindow.setContentPane(p);

    }

    public void createSearchBySodiumWindow() {
        back.addActionListener(controller);
        searchBySodiumButton.addActionListener(controller);
        searchBySodiumWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchBySodiumWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchBySodiumButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchBySodiumWindow.setVisible(true);
        searchBySodiumWindow.setContentPane(p);

    }

    public void createSearchByPriceWindow() {
        back.addActionListener(controller);
        searchByPriceButton.addActionListener(controller);
        searchByPriceWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        searchByPriceWindow.setSize(600, 400);
        JLabel label = new JLabel("Search criteria:");

        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.add(label);
        p1.add(searchCriteria);
        p2.add(searchByPriceButton);
        p3.add(back);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        searchByPriceWindow.setVisible(true);
        searchByPriceWindow.setContentPane(p);

    }

    public void addOrderWindow() {
        deliveryService.setShoppingBasket(new ArrayList<>());
        placeOrder.addActionListener(controller);
        addToShoppingBasket.addActionListener(controller);
        createOrderWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createOrderWindow.setSize(600, 400);
        JPanel p = new JPanel();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        String result = deliveryService.getMenuItems();
        String[] data = result.split("\n");
        setComboBoxValues(data);
        p1.add(menuItems);
        p2.add(addToShoppingBasket);
        p3.add(placeOrder);
        p3.add(signOut);

        p.add(p1);
        p.add(p2);
        p.add(p3);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        createOrderWindow.setVisible(true);
        createOrderWindow.setContentPane(p);
    }

    public void closeCreateOrderWindow() {
        createOrderWindow.dispose();
        clientWindow.dispose();
        MainGUI mainGUI = new MainGUI(deliveryService);
        mainGUI.createMainWindow();
    }

    public void backButton() {

        searchByTitleWindow.dispose();
        searchByRatingWindow.dispose();
        searchByCaloriesWindow.dispose();
        searchByProteinWindow.dispose();
        searchByFatWindow.dispose();
        searchBySodiumWindow.dispose();
        searchByPriceWindow.dispose();
        searchResultWindow.dispose();
        //createClientWindow();

    }

    public void setComboBoxValues(String[] values) {
        JComboBox<String> aux = new JComboBox<>(values);
        menuItems = aux;
    }
    public String getMenuComboBoxSelected() {
        return valueOf(menuItems.getSelectedItem());
    }


    public void closeClientWindow() {
        clientWindow.dispose();
    }

    public JButton getSignOut() {
        return signOut;
    }
}
