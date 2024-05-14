package com.example.eksamensprojekt.Service;

import com.example.eksamensprojekt.Model.Ingredient;
import com.example.eksamensprojekt.Model.Recipe;
import com.example.eksamensprojekt.Repository.DBcontroller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.eksamensprojekt.Model.MyUser;

import java.util.ArrayList;
import java.util.List;

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

    public Double calculateBMR(MyUser myUser){
        //I teorien skal denne metode udregne en brugers BMR ud fra deres køn. 0 er mand, 1 er kvinde
        if(myUser.getGender()==0){
            Double menBMR=((10* myUser.getWeight())+(6.25* myUser.getHeight())-(5* myUser.getAge())+5);
            return menBMR;
        }
        else{
            Double womenBMR=((10* myUser.getWeight())+(6.25* myUser.getHeight())-(5* myUser.getAge())-161);
            return womenBMR;
        }
    }
    /*public Double maybeCalculateDailyCalories(){
        return calculateBMR()* myUser.getActivityLevel();
        //I teorien skal denne metode udregne en brugers daglige kalorieindtag ved at gange deres BMR med deres aktivitetsniveau
    }*/

    public Double calculateDailyCalories(MyUser myUser){
        if (myUser.getActivityLevel()==0){
            return calculateBMR(myUser)*1.2;
        }
        else if (myUser.getActivityLevel()==1) {
            return calculateBMR(myUser)*1.375;
        }
        else if (myUser.getActivityLevel()==2) {
            return calculateBMR(myUser)*1.55;
        }
        else if (myUser.getActivityLevel()==3) {
            return calculateBMR(myUser)*1.725;
        }
        else if (myUser.getActivityLevel()==4) {
            return calculateBMR(myUser)*1.9;
        } return null;
    }



}

