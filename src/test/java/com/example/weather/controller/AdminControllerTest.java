package com.example.weather.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminControllerTest {


    @Test
    @DisplayName("get user List")
    @RepeatedTest(value = 5, name = "{displayName} :: repetition {currentRepetition} of {totalRepetitions}")
    void getUsersList() {
    }

    @Test
    void getUserById() {
    }

    @Test
    void updateUser() {
    }
}