package Presentation;

import BLL.DeliveryService;
import BLL.MenuItem;
import Data.Client;
import Data.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ClientController implements ActionListener {

    private ClientGUI clientGUI;
    private DeliveryService deliveryService;
    private User client;

    public ClientController(ClientGUI gui, DeliveryService deliveryService, User user) {
        this.clientGUI=gui;
        this.deliveryService=deliveryService;
        this.client=user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==clientGUI.getViewProducts()) {
            clientGUI.getRows(deliveryService.getitems());

        }

        if(e.getSource()==clientGUI.getReturnToMainGUI()) {
            clientGUI.closeWindow();
            MainGUI main = new MainGUI(deliveryService);
            main.createMainWindow();
        }

        //SEARCH BY TITLE
        if(e.getSource()==clientGUI.getSearchByTitle()) {
            clientGUI.createSearchByTitleWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchByTitleButton()) {

            String title = clientGUI.getValue();
            String result = deliveryService.searchByTitle(title);
            clientGUI.searchResultWindow(result);

        }

        //SEARCH BY RATING
        if(e.getSource()==clientGUI.getSearchByRating()) {
            clientGUI.createSearchByRatingWindow();
            clientGUI.closeClientWindow();
        }
        if(e.getSource()==clientGUI.getSearchByRatingButton()) {
            Double rating = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchByRating(rating);
            clientGUI.searchResultWindow(result);
        }

        //SEARCH BY CALORIES
        if(e.getSource()==clientGUI.getSearchByCalories()) {
            clientGUI.createSearchByCaloriesWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchByCaloriesButton()) {
            Double calories = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchByCalories(calories);
            clientGUI.searchResultWindow(result);
        }

        //SEARCH BY PROTEIN
        if(e.getSource()==clientGUI.getSearchByProtein()) {
            clientGUI.createSearchByProteinWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchByProteinButton()) {
            Double protein = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchByProtein(protein);
            clientGUI.searchResultWindow(result);
        }

        //SEARCH BY FAT
        if(e.getSource()==clientGUI.getSearchByFat()) {
            clientGUI.createSearchByFatWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchByFatButton()) {
            Double fat = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchByFat(fat);
            clientGUI.searchResultWindow(result);
        }

        //SEARCH BY SODIUM
        if(e.getSource()==clientGUI.getSearchBySodium()) {
            clientGUI.createSearchBySodiumWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchBySodiumButton()) {
            Double sodium = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchBySodium(sodium);
            clientGUI.searchResultWindow(result);
        }

        //SEARCH BY PRICE
        if(e.getSource()==clientGUI.getSearchByPrice()) {
            clientGUI.createSearchByPriceWindow();
            clientGUI.closeClientWindow();
        }

        if(e.getSource()==clientGUI.getSearchByPriceButton()) {
            Double price = Double.parseDouble(clientGUI.getValue());
            String result = deliveryService.searchByPrice(price);
            clientGUI.searchResultWindow(result);
        }

        if(e.getSource()==clientGUI.getBack()) {
            clientGUI.backButton();
        }

        //CREATE ORDER
        if(e.getSource()==clientGUI.getCreateOrder()) {
            clientGUI.addOrderWindow();
        }

        if(e.getSource()==clientGUI.getAddToShoppingBasket()) {
            String item = clientGUI.getMenuComboBoxSelected();
            deliveryService.addToShoppingBasket(item);
        }

        if(e.getSource()==clientGUI.getPlaceOrder()) {
            String clientName = client.getName();
            deliveryService.createOrder(clientName, deliveryService.getShoppingBasket());
            deliveryService.setShoppingBasket(new ArrayList<>());
        }

        if(e.getSource()==clientGUI.getSignOut()) {
            clientGUI.closeCreateOrderWindow();
        }

    }
}
