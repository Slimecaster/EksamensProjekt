package com.example.eksamensprojekt.Repository;

import com.example.eksamensprojekt.Model.MyUser;
import com.example.eksamensprojekt.Model.Subscription;
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

    Subscription subscription=new Subscription();


    @Test
    void createUpdateUser(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44","User",84,174,42,1,2,0,subscription);
        dbController.createUpdateUser(user);
    }

    @Test
    void deleteUserByEmail(){
        MyUser user = new MyUser(1L,"Fname1","Sname1","Password1","email1@gmail.com","11 22 33 44","User",84,174,42,1,2,0,subscription);
        dbController.deleteUserByEmail("email1@gmail.com");
    }

}