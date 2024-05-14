package com.example.eksamensprojekt.Service;

import com.example.eksamensprojekt.Model.MyUser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UsecaseTest {
    Usecase usecase=new Usecase();

    @Test
    public void testCalculateBMRForMale() {
        // Creating a male test user with weight 70kg, height 170cm, age 30, and gender 0 for male
        MyUser maleUser = new MyUser(1L,"test","testesen","1234","test@mail.com","1234 5678",70,170,30,0,1,2,"Admin");

        // Calculating BMR for male user
        Double expectedBMR = 10 * 70 + 6.25 * 170 - 5 * 30 + 5;
        Double actualBMR = usecase.calculateBMR(maleUser);

        // Asserting that the calculated BMR matches the expected value
        assertEquals(expectedBMR, actualBMR);
    }

    @Test
    public void testCalculateBMRForFemale() {
        // Creating a female user with weight 60kg, height 160cm, age 25, and gender 1 for female/not male
        MyUser femaleUser = new MyUser(1L,"testk","testesenk","1234","test@mail.com","1234 5678",60,160,25,1,1,2,"Admin");

        // Calculating BMR for female user
        Double expectedBMR = 10 * 60 + 6.25 * 160 - 5 * 25 - 161;
        Double actualBMR = usecase.calculateBMR(femaleUser);

        // Asserting that the calculated BMR matches the expected value
        assertEquals(expectedBMR, actualBMR);
    }

}