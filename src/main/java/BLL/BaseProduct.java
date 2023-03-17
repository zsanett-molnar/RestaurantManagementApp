package BLL;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable
{
    private String title;
    private Double rating;
    private Double calories;
    private Double protein;
    private Double fat;
    private Double sodium;
    private Double price;

    public BaseProduct(String title, Double rating, Double calories, Double protein, Double fat, Double sodium, Double price) {
        this.title = title;
        this.rating = rating;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.sodium = sodium;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Double getCalories() {
        return calories;
    }

    public void setCalories(Double calories) {
        this.calories = calories;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getFat() {
        return fat;
    }

    public void setFat(Double fat) {
        this.fat = fat;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public Double computePrice() {
        return this.price;
    }

    @Override
    public Double computeCalories() {
        return this.calories;
    }

    @Override
    public Double computeProtein() {
        return this.protein;
    }

    @Override
    public Double computeFat() {
        return this.fat;
    }

    @Override
    public Double computeSodium() {
        return this.sodium;
    }

    @Override
    public Double computeRating() {
        return this.rating;
    }

    @Override
    public String toString() {
        return title + "," +
               rating +
                "," + calories +
                "," + protein +
                "," + fat +
                "," + sodium +
                "," + price;
    }

    public String getValue(String field) {
        String value="";
        if(field.equals("title")) {
            value = title;
        }
        if(field.equals("rating")) {
            value = String.valueOf(rating);
        }
        if(field.equals("calories")) {
            value = String.valueOf(calories);
        }
        if(field.equals("protein")) {
            value = String.valueOf(protein);
        }
        if(field.equals("fat")) {
            value = String.valueOf(fat);
        }
        if(field.equals("sodium")) {
            value = String.valueOf(sodium);
        }
        if(field.equals("price")) {
            value = String.valueOf(price);
        }

        return value;
    }

    public String setValue(String field, String value) {
        if(field.equals("title")) {
            setTitle(value);
        }
        if(field.equals("rating")) {
           setRating(Double.parseDouble(value));
        }
        if(field.equals("calories")) {
            setCalories(Double.parseDouble(value));
        }
        if(field.equals("protein")) {
            setProtein(Double.parseDouble(value));
        }
        if(field.equals("fat")) {
            setFat(Double.parseDouble(value));
        }
        if(field.equals("sodium")) {
            setSodium(Double.parseDouble(value));
        }
        if(field.equals("price")) {
            setPrice(Double.parseDouble(value));
        }

        return value;
    }



}
