package com.telran.phonebook.tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getHeader().isLoginLinkPresent()){
            app.getHeader().clickOnSignOutButton();
        } else {
            app.getHeader().clickOnLoginLink();
        }
    }

    @Test
    public void loginPositiveTest() {
        //assert is registration form displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        //fill login form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+2@gmail.co").setPassword("Aa12345~"));
        app.getUser().clickOnLoginButton();
        //verify Sign Out button displayed
        app.getUser().pause(1000);
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());
    }

}
