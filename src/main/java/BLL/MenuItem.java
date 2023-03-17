package BLL;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public MenuItem() {

    }

    public abstract String getTitle();

    public abstract Double computePrice();

    public abstract Double computeCalories();

    public abstract Double computeProtein();

    public abstract Double computeFat();

    public abstract Double computeSodium();

    public abstract Double computeRating();

}
