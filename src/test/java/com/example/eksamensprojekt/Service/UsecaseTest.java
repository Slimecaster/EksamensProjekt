package com.example.eksamensprojekt.Service;

import com.example.eksamensprojekt.Model.MyUser;
import com.example.eksamensprojekt.Repository.DBcontroller;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UsecaseTest {

    // Mocking the JdbcTemplate dependency
    @Mock
    private JdbcTemplate jdbcTemplate;

    // Creating an instance of the Repository class and injecting the mocked JdbcTemplate
    @InjectMocks
    private DBcontroller dbController;

    @InjectMocks
    Usecase usecase=new Usecase();


    @Test
    void calculateBMRForFemale(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44",84,174,42,1,2,0,"User");

        Double expectedBMR=10 * 84 + 6.25 * 174 - 5 * 42 - 161;
        Double actualBMR= usecase.calculateBMR("email1@gmail.com");

        assertEquals(expectedBMR, actualBMR);
    }

    @Test
    void calculateBMRForMale(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44",84,174,42,0,2,0,"User");

        Double expectedBMR=10 * 84 + 6.25 * 174 - 5 * 42 + 5;
        Double actualBMR= usecase.calculateBMR("email1@gmail.com");

        assertEquals(expectedBMR, actualBMR);
    }









}