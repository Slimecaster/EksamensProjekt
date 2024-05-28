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
}