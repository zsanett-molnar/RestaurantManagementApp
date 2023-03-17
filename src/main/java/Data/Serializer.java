package Data;

import BLL.DeliveryService;

import java.io.*;

public class Serializer implements Serializable {

    public static void serialize(DeliveryService deliveryService) {
        try {
            FileOutputStream file = new FileOutputStream("delivery.ser");
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(deliveryService);
            out.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static DeliveryService deserialize(String path) {
        DeliveryService deliveryService;
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(file);
            deliveryService = (DeliveryService) in.readObject();
            in.close();
            file.close();
            return deliveryService;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

}
