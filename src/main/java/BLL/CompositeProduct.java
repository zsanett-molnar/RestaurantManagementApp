package BLL;

import java.io.Serializable;
import java.util.List;

public class  CompositeProduct extends MenuItem implements Serializable {

    private String title;
    private List<MenuItem> items;

    public CompositeProduct(String title, List<MenuItem> items) {
        this.title = title;
        this.items = items;
    }

    public void addMenuItem(MenuItem item) {
        this.items.add(item);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public Double computePrice() {
        Double price = 0.0;
        for(MenuItem i : items) {
            price = price + i.computePrice();
        }

        return price;
    }

    @Override
    public Double computeCalories() {
        Double kcal=0.0;
        for(MenuItem i : items) {
            kcal = kcal + i.computeCalories();
        }
        return kcal;
    }

    @Override
    public Double computeProtein() {
        Double protein=0.0;
        for(MenuItem i : items) {
            protein = protein + i.computeProtein();
        }
        return protein;
    }

    @Override
    public Double computeFat() {
        Double fat=0.0;
        for(MenuItem i : items) {
            fat = fat + i.computeFat();
        }
        return fat;
    }

    @Override
    public Double computeSodium() {
        Double sodium=0.0;
        for(MenuItem i : items) {
            sodium = sodium + i.computeSodium();
        }
        return sodium;
    }

    @Override
    public Double computeRating() {
        Double rating=0.0;
        for(MenuItem i : items) {
            rating = rating + i.computeRating();
        }
        return rating/items.size();
    }

    @Override
    public String toString() {
        return title + ',' + computeRating() + "," +  computeCalories() + "," +computeProtein() + "," + computeFat() + "," +
                computeSodium() + "," + computePrice();

    }
}
