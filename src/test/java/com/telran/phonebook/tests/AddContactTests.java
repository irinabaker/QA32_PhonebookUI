package com.telran.phonebook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        //ensure Sign Out button isn't displayed
        if (!app.getHeader().isSignOutButtonPresent()) {
            //click on Login link
            app.getHeader().clickOnLoginLink();
            //login
            app.getUser().login();
            //click on the link Add
            app.getHeader().clickOnAddLink();
        }
    }

    @Test
    public void addContactPositiveTest() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        //fill contact form
        app.getContact().addRandomContact(i);
        //assert contact is created
        Assert.assertTrue(app.getContact().isContactCreated("Karl" + i));
    }

}
