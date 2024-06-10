package com.example.eksamensprojekt.Service;

import com.example.eksamensprojekt.Model.Dish;
import com.example.eksamensprojekt.Model.Ingredient;
import com.example.eksamensprojekt.Model.Recipe;
import com.example.eksamensprojekt.Repository.DBcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import com.example.eksamensprojekt.Model.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Usecase {

    @Autowired
    private DBcontroller dbController=new DBcontroller(new JdbcTemplate());
    private MyUser myUser;
    private Recipe recipe;
    private Ingredient ingredient;
    private ArrayList<Ingredient> ingredientListForDish;

    public MyUser createUpdateUser(MyUser myUser){
        return dbController.createUpdateUser(myUser);
    }
    public void deleteUserByEmail(String email){
        dbController.deleteUserByEmail(email);
    }
    public Optional<MyUser> findUserByEmail(String email) {
        return dbController.findUserByEmail(email);
    }
    public Ingredient createUpdateIngredient(Ingredient ingredient){
        return dbController.createUpdateIngredient(ingredient);
    }
    public void deleteIngredientById(Long ingredientId){
        dbController.deleteIngredientById(ingredientId);
    }
    public Dish createUpdateDish(Dish dish){
        return dbController.createUpdateDish(dish);
    }
    public void deleteDishById(Long dishId){
        dbController.deleteDishById(dishId);
    }
    public List<Dish> findAllDishes(){
        return dbController.findAllDishes();
    }
    public List<Recipe> show21Recipes(){
        //denne her er ikke færdig i sql
        return dbController.show21Recipes();
    }
    public Optional<Recipe> findRecipeById(Long recipeId){
        return dbController.findRecipeById(recipeId);
    }
    public Recipe createUpdateRecipe(Recipe recipe){
        return dbController.createUpdateRecipe(recipe);
    }
    public void deleteRecipeById(Long recipeId){
        dbController.deleteRecipeById(recipeId);
    }
    public List<Recipe> findAllRecipes(){
        return dbController.findAllRecipes();
    }
    public List<Recipe> findFavoriteRecipes(){
        //denne her er ikke færdig i sql
        return dbController.findFavoriteRecipes();
    }
    /**
     * Calls the findUserByEmail to find a myUser by their email and calculates the myUsers BMR
     * @param email the email of the myUser object that will get their BMR calculated
     * @return the BMR of the myUser
     */
    public Double calculateBMR(String email) {
        Optional<MyUser> optionalUser = dbController.findUserByEmail(email);
        double BMR = 0;
        // Check if user is present and calculate BMR accordingly
        if (optionalUser.isPresent()) {
            myUser = optionalUser.get();
            if (myUser.getGender() == 0) {
                BMR = ((10 * myUser.getWeight()) + (6.25 * myUser.getHeight()) - (5 * myUser.getAge()) + 5);
            } else {
                BMR = ((10 * myUser.getWeight()) + (6.25 * myUser.getHeight()) - (5 * myUser.getAge()) - 161);
            }
        }
        return BMR;
    }

    /**
     * Calculates daily calorie intake for a myUser based on their BMR and activityLevel
     * @param email the email of the myUser object that will get their calories calculated
     * @return the daily calorie intake based on a myUsers BMR and activityLevel
     */
    public Double calculateActivityLevel(String email){
        if (myUser.getActivityLevel()==0){
            return calculateBMR(email)*1.2;
        }
        else if (myUser.getActivityLevel()==1) {
            return calculateBMR(email)*1.5;
        }
        else if (myUser.getActivityLevel()==2) {
            return calculateBMR(email)*1.7;

        }
        else if (myUser.getActivityLevel()==3) {
            return calculateBMR(email)*1.9;
        }
        else if (myUser.getActivityLevel()==4) {
            return calculateBMR(email)*2.4;
        } return null;
    }

    /**
     * Calculates the daily calorie intake of a myUser based on their BMR, activityLevel and goal
     * @param email the email of the myUser object that will get their calories calculated
     * @return the daily calorie intake for a myUser based on their BMR, activityLevel and goal
     */
    public Double calculateDailyCalories(String email){
        if(myUser.getGoal()==0){
            return calculateActivityLevel(email)-500;
        }
        else if (myUser.getGoal()==1) {
            return calculateActivityLevel(email)+500;
        }
        else if (myUser.getGoal()==2) {
            return calculateActivityLevel(email);
        }
        else if (myUser.getGoal()==3) {
            return calculateActivityLevel(email)+300;
        }return null;
    }

    /**
     * Calculates how many calories a meal should have depending on whether it is breakfast or not
     * @param email the email of the myUser object that will have the meal
     * @return the amount of calories a meal should have
     */
    public Double caloriesForDifferentMeals(String email){
        //Bruger .equals da vi vil sammenligne Strings
        if (recipe.getTimeOfDayMeal().equals("Breakfast")){
            return calculateDailyCalories(email)*0.4;
        }
        else{
            //Dinner og Lunch skal begge ganges med 0.3
            return calculateDailyCalories(email)*0.3;
        }
    }

    /*
    public Double caloriesInMasterRecipe(){
        //Skal måske have en liste fra recipe over ingredienser som parameter
        double sum=0;
        for(int i = 0; i<ingredientListForDish.size(); i++){
            double ingredientCalories =ingredient.getCalories()/100*recipe.getIngredientWeight();
            sum+=ingredientCalories;
        }
        return sum;
    }
    */

    /*
    public Double calculateQuantityRatio(){
        //Skal have samme liste som caloriesInMasterRecipe
        double sum=0;
        for(int i = 0; i<ingredientListForDish.size(); i++){
            caloriesPerIngredientPerMasterRecipe/caloriesInMasterRecipe()
        }
        return sum;
    }*/

    public double caloriesInMasterRecipe() {
            double sum = 0;
            for (Ingredient ingredient : ingredientListForDish) {
                double ingredientCalories = ingredient.getCalories() / 100 * ingredient.getWeight();
                sum += ingredientCalories;
            }
            return sum;
        }

        public double proteinInMasterRecipe() {
            double sum = 0;
            for (Ingredient ingredient : ingredientListForDish) {
                double ingredientProtein = ingredient.getProtein() / 100 * ingredient.getWeight();
                sum += ingredientProtein;
            }
            return sum;
        }

        public double fatInMasterRecipe() {
            double sum = 0;
            for (Ingredient ingredient : ingredientListForDish) {
                double ingredientFat = ingredient.getFat() / 100 * ingredient.getWeight();
                sum += ingredientFat;
            }
            return sum;
        }

        public double carbsInMasterRecipe() {
            double sum = 0;
            for (Ingredient ingredient : ingredientListForDish) {
                double ingredientCarbs = ingredient.getCarbs() / 100 * ingredient.getWeight();
                sum += ingredientCarbs;
            }
            return sum;
    }
}


