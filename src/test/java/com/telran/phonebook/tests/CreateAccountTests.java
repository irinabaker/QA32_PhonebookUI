package com.telran.phonebook.tests;

import model.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        //precondition: user should be logged out
        if (!app.getHeader().isLoginLinkPresent()){
            app.getHeader().clickOnSignOutButton();
        } else {
            //click on LOGIN link
            app.getHeader().clickOnLoginLink();
        }
    }

    //test
    @Test
    public void createAccountPositiveTest() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        //assert is registration form displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        //fill registration form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("email" + i + "@gmail.com").setPassword("Aa12345~"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //verify Sign Out button displayed
       app.getUser().pause(1000);
        Assert.assertTrue(app.getHeader().isSignOutButtonPresent());
    }

    @Test
    public void createAccountNegativeTestWithInvalidPassword() {
        //assert is registration form displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        //fill registration form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+2@gmail.co").setPassword("Aa12345"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //verify Sign Out button displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().isErrorMessagePresent());
    }

    @Test
    public void createAccountNegativeTestWithoutPassword() {
        //assert is registration form displayed
        Assert.assertTrue(app.getUser().isLoginRegFormPresent());
        //fill registration form
        app.getUser().fillLoginRegistrationForm(new User().setEmail("karl+2@gmail.co"));
        //click on Registration button
        app.getUser().clickOnRegistrationButton();
        //verify Sign Out button displayed
        Assert.assertTrue(app.getUser().isAlertPresent());
        Assert.assertTrue(app.getUser().isErrorMessagePresent());
    }



}
