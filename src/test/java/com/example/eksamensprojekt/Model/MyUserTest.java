package com.example.eksamensprojekt.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MyUserTest {

    @Test
    void setEmail() {
        MyUser myUser=new MyUser();
        myUser.setEmail("test@gmail.com");
        assertEquals("test@gmail.com",myUser.getEmail());
    }

    @Test
    void setPassword() {
        MyUser myUser = new MyUser();
        myUser.setPassword("password");
        assertEquals("password", myUser.getPassword());
    }

}