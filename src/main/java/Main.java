import BLL.DeliveryService;
import BLL.MenuItem;
import Data.User;
import Presentation.MainGUI;
import Data.Serializer;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        DeliveryService deliveryService = new DeliveryService();
        deliveryService.addUser("administrator", "zsanett", "zsanett");
        deliveryService.addUser("employee", "cristi", "cristi");
        deliveryService.addUser("client", "ana", "ana");
        deliveryService.addUser("client", "oli", "oli");
        deliveryService.addUser("client", "timi", "timi");
        deliveryService.addMenuItem("Salmon Sushi", 8.9, 93.0, 14.6, 11.2, 9.00, 50.00);
        deliveryService.addMenuItem("Pancake", 7.5, 250.0, 10.6, 8.2, 7.10, 23.00);
        deliveryService.addMenuItem("Chicken Noodles", 8.3, 140.0, 34.6, 9.4, 8.30, 35.00);
        deliveryService.addMenuItem("Pasta", 8.5, 80.0, 30.6, 4.4, 8.30, 28.00);
        MainGUI l = new MainGUI(deliveryService);
        Serializer.serialize(deliveryService);

//        DeliveryService deliveryService = Serializer.deserialize("delivery.ser");
//        MainGUI l = new MainGUI(deliveryService);
    }
}
