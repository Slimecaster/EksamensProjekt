package com.example.eksamensprojekt.Model;

import java.util.ArrayList;

public class Recipe {
    private Long recipeId;
    private double calories, protein, fat, carbs;
    private String description;
    private ArrayList<Ingredient> ingredientListForDish=new ArrayList<>();
    private String timeOfDayMeal;
    //En privat medlemsvariabel til at kunne bestemme om en ret er til morgenmad, frokost eller aftensmad.



    public Recipe() {
    }

    public Recipe(Long recipeId, double calories, double protein, double fat, double carbs, String description, ArrayList<Ingredient> ingredientListForDish, String timeOfDayMeal) {
        this.recipeId = recipeId;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.description = description;
        this.ingredientListForDish = ingredientListForDish;
        this.timeOfDayMeal = timeOfDayMeal;
    }

    public ArrayList<Ingredient> getIngredientListForDish() {
        return ingredientListForDish;
    }

    public void setIngredientListForDish(ArrayList<Ingredient> ingredientListForDish) {
        this.ingredientListForDish = ingredientListForDish;
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

    public String getTimeOfDayMeal() {
        return timeOfDayMeal;
    }

    public void setTimeOfDayMeal(String timeOfDayMeal) {
        this.timeOfDayMeal = timeOfDayMeal;
    }
}

