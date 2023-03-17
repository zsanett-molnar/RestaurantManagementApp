package Presentation;

import BLL.DeliveryService;
import Data.Serializer;
import Data.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminController implements ActionListener {

    private AdministratorGUI administratorGUI;
    private DeliveryService deliveryService;
    private User administrator;

    public AdminController(AdministratorGUI administratorGUI, DeliveryService deliveryService, User user) {
        this.administratorGUI = administratorGUI;
        this.deliveryService=deliveryService;
        this.administrator=user;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==administratorGUI.getImportProducts()) {
            deliveryService.importProducts();
            Serializer.serialize(deliveryService);
//
        }

        if(e.getSource()==administratorGUI.getReturnToMainGUI()) {
            administratorGUI.closeWindow();
            MainGUI main = new MainGUI(deliveryService);
            main.createMainWindow();
        }

        if(e.getSource()==administratorGUI.getAddNewProduct()) {
            administratorGUI.addNewProductWindow();
        }

        if(e.getSource()==administratorGUI.getADD()) {
            String title = administratorGUI.getTitle();
            Double rating = administratorGUI.getRating();
            Double calories = administratorGUI.getCalories();
            Double protein = administratorGUI.getProtein();
            Double fat = administratorGUI.getFat();
            Double sodium = administratorGUI.getSodium();
            Double price = administratorGUI.getPrice();
            deliveryService.addMenuItem(title, rating, calories, protein, fat, sodium, price);
            Serializer.serialize(deliveryService);
        }

        if(e.getSource()==administratorGUI.getReturnToMainGUI2()) {
            administratorGUI.closeWindow2();
        }
        //DELETE
        if(e.getSource()==administratorGUI.getDeleteProduct()) {
            administratorGUI.createDeleteProductWindow();
        }

        if(e.getSource()==administratorGUI.getDELETE()) {
            String name = administratorGUI.getDeleteValue();
            deliveryService.deleteMenuItem(name);
            Serializer.serialize(deliveryService);
        }
        //MODIFY
        if(e.getSource()==administratorGUI.getModifyProduct()) {
            administratorGUI.createModifyProductWindow();
        }

        if(e.getSource()==administratorGUI.getMODIFY()) {
            String conditionField = administratorGUI.getCriteriaField();
            String toModifyField = administratorGUI.getToModifyField();
            String conditionValue = administratorGUI.getCriteriaValue();
            String toSetValue = administratorGUI.getToSetValue();
            deliveryService.modifyMenuItem(conditionField,conditionValue,toModifyField,toSetValue);
            Serializer.serialize(deliveryService);
        }

        //COMPUTE
        if(e.getSource()==administratorGUI.getCreateCompositeProduct()) {
            administratorGUI.createComputeProductWindow();
            //deliveryService.setCompositeItem(new ArrayList<>());
        }

        if(e.getSource()==administratorGUI.getAddToCompositeProduct()) {
            String item = administratorGUI.getMenuComboBoxSelected();
            deliveryService.addToCompositeList(item);
            //deliveryService.setCompositeItem(new ArrayList<>());
//            for(MenuItem i : deliveryService.getCompositeItem()) {
//                System.out.println(i.toString());
//            }
            Serializer.serialize(deliveryService);
        }

        if(e.getSource()==administratorGUI.getCOMPUTE()) {
            String name = administratorGUI.getComputedName();
            deliveryService.createMenuItem(name);
            //deliveryService.setCompositeItem(new ArrayList<>());
            Serializer.serialize(deliveryService);
        }

        if(e.getSource()==administratorGUI.getRaport1()) {
            Integer startHour = Integer.parseInt(administratorGUI.getStartH());
            Integer endHour = Integer.parseInt(administratorGUI.getEndH());
            deliveryService.report1(startHour,endHour);
        }

        if(e.getSource()==administratorGUI.getRaport2()) {
            Integer amount = administratorGUI.getAmount();
            deliveryService.report2(amount);
        }

        if(e.getSource()==administratorGUI.getRaport4()) {
            Integer day = administratorGUI.getDay();
            Integer month = administratorGUI.getMonth();
            Integer year = administratorGUI.getYear();
            deliveryService.report4(day,month,year);
        }

        if(e.getSource()==administratorGUI.getRaport3())
        {
            Integer amount2 = administratorGUI.getAmount2();
            Integer price = administratorGUI.getPrice2();
            deliveryService.report3(price,amount2);
        }

        if(e.getSource()==administratorGUI.getViewOrders()) {
            deliveryService.displayOrders();
        }
    }
}
