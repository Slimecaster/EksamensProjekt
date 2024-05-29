package com.example.eksamensprojekt.Model;

import java.util.ArrayList;

public class Recipe {
    private Long recipeId;
    private double calories, protein, fat, carbs;
    private String description;
    private ArrayList<Ingredient> ingredientListForDish=new ArrayList<>();
    private String timeOfDayMeal;
    //En privat medlemsvariabel til at kunne bestemme om en ret er til morgenmad, frokost eller aftensmad.
    private double ingredientWeight;
    //En private medlemsvariabel, der skal bruges i omskrivning af opskrifter


    public Recipe() {
    }

    public Recipe(String timeOfDayMeal, double ingredientWeight, String description, double carbs, double fat, double protein, double calories, Long recipeId) {
        this.timeOfDayMeal = timeOfDayMeal;
        this.ingredientWeight = ingredientWeight;
        this.description = description;
        this.carbs = carbs;
        this.fat = fat;
        this.protein = protein;
        this.calories = calories;
        this.recipeId = recipeId;
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

    public double getIngredientWeight() {
        return ingredientWeight;
    }

    public void setIngredientWeight(double ingredientWeight) {
        this.ingredientWeight = ingredientWeight;
    }

    public String getTimeOfDayMeal() {
        return timeOfDayMeal;
    }

    public void setTimeOfDayMeal(String timeOfDayMeal) {
        this.timeOfDayMeal = timeOfDayMeal;
    }
}

