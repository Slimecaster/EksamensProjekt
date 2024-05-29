package com.example.eksamensprojekt.Repository;

import com.example.eksamensprojekt.Model.MyUser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class DBcontrollerTest {

    // Mocking the JdbcTemplate dependency
    @Mock
    private JdbcTemplate jdbcTemplate;

    // Creating an instance of the Repository class and injecting the mocked JdbcTemplate
    @InjectMocks
    private DBcontroller dbController;


    @Test
    void createUpdateUser(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44",84,174,42,1,2,0,"User");
        dbController.createUpdateUser(user);
    }

    @Test
    void deleteUserByEmail(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44",84,174,42,1,2,0,"User");
        dbController.deleteUserByEmail("email1@gmail.com");
    }

}