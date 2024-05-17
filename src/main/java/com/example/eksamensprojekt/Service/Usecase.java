package com.example.eksamensprojekt.Service;

import com.example.eksamensprojekt.Model.Ingredient;
import com.example.eksamensprojekt.Model.Recipe;
import com.example.eksamensprojekt.Repository.DBcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eksamensprojekt.Model.MyUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Usecase {

    @Autowired
    private DBcontroller dbController;
    private MyUser myUser;
    private Recipe recipe;
    private Ingredient ingredient;
    private ArrayList<Ingredient> ingredientListForDish;

    public void createUpdateUser(MyUser myUser){
        dbController.createUpdateUser(myUser);
    }

    public List<Recipe> findAllRecipes(){
        return dbController.findAllRecipes();
    }

    public Double calculateBMR(String email) {
        //I teorien skal denne metode udregne en brugers BMR ud fra deres køn. 0 er mand, 1 er kvinde
        Optional<MyUser> optionalUser = dbController.findUserByEmail(email);
        double BMR = 0;

        // Check if user is present and calculate BMR accordingly
        if (optionalUser.isPresent()) {
            MyUser myUser = optionalUser.get();

            if (myUser.getGender() == 0) {
                BMR = ((10 * myUser.getWeight()) + (6.25 * myUser.getHeight()) - (5 * myUser.getAge()) + 5);
            } else {
                BMR = ((10 * myUser.getWeight()) + (6.25 * myUser.getHeight()) - (5 * myUser.getAge()) - 161);

            }
        }
        System.out.println(BMR);
        return BMR;
    }

    public Double calculateActivityLevel(String email){
        if (myUser.getActivityLevel()==0){
            return calculateBMR(email)*1.2;
        }
        else if (myUser.getActivityLevel()==1) {
            return calculateBMR(email)*1.375;
        }
        else if (myUser.getActivityLevel()==2) {
            return calculateBMR(email)*1.55;
        }
        else if (myUser.getActivityLevel()==3) {
            return calculateBMR(email)*1.725;
        }
        else if (myUser.getActivityLevel()==4) {
            return calculateBMR(email)*1.9;
        } return null;
    }
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

    /*public Double caloriesForDifferentMeals(String email){
        if (timeOfDayMeal="Breakfast"){
            return calculateDailyCalories(email)*0.4;
        }
        else{
            return calculateDailyCalories(email)*0.3;
        }
    }*/

    /*public Double caloriesInMasterRecipe(){
        double sum=0;
        for(int i = 0; i<ingredientListForDish.size(); i++){
            double ingredientCalories =ingredient.getCalories()/100*ingredient.getIngredientWeight();
            sum+=ingredientCalories;
        }
        return sum;
    }*/


}

