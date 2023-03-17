package BLL;

import java.awt.*;
import java.util.List;

public interface IDeliveryServiceProcessing {

    List<MenuItem> getFromCsv(String fileName);

    void addMenuItem(String title, Double rating, Double calories, Double protein, Double fat, Double sodium, Double price);

    void createMenuItem(String name);

    void deleteMenuItem(String name);

    void modifyMenuItem(String field1, String value, String field2, String toSetValue);

    String report1(int start, int end); //generateTimeIntervalReport

    String report2(int amount); //generateOrderedMoreReport

    String report3(int amount, int times); //generateClientsOrderedMoreReport

    String report4(Integer day, Integer month, Integer year); //OrderedInDayReport

    String searchByTitle(String title);

    String searchByRating(Double rating);

    String searchByCalories(Double calories);

    String searchByProtein(Double protein);

    String searchByFat(Double fat);

    String searchBySodium(Double sodium);

    String  searchByPrice(Double price);

    void createOrder(String clientName, List<MenuItem>list);
}


