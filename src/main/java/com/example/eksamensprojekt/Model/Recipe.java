package com.example.eksamensprojekt.Model;

import java.util.ArrayList;

public class Recipe {
    private Long recipeId;
    private double calories, protein, fat, carbs;
    private String description;
    private ArrayList<Ingredient> ingredientListForDish=new ArrayList<>();
    //private String timeOfDayMeal;
    //En privat medlemsvariabel til at kunne bestemme om en ret er til morgenmad, frokost eller aftensmad.
    //private double ingredientWeight
    //En private medlemsvariabel, der skal bruges i omskrivning af opskrifter


    public Recipe() {
    }

    public Recipe(Long recipeId, double calories, double protein, double fat, double carbs, String description) {
        this.recipeId = recipeId;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.description = description;
    }



    public double calculateCalories(ArrayList<Ingredient> ingredientList){
        double totalCalories=0.0;
        Ingredient ingredient=new Ingredient();
        for(int i = 0; i<ingredientListForDish.size(); i++){
            this.protein+=ingredient.getProtein()*(ingredient.getWeight()/100);
            this.fat+=ingredient.getFat()*(ingredient.getWeight()/100);
            this.carbs+=ingredient.getCarbs()*(ingredient.getWeight()/100);

        }
        totalCalories=protein+fat+carbs;
        return totalCalories;
    }


    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

