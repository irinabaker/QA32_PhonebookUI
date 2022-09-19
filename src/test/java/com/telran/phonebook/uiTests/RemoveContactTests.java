package com.telran.phonebook.uiTests;

import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!app.getHeader().isSignOutButtonPresent()) {
            app.getHeader().clickOnLoginLink();
            app.getUser().login();
            app.getHeader().clickOnAddLink();
            app.getContact().addContact(new Contact().setName("Karl")
                    .setSurName("Adam")
                    .setPhone("123456")
                    .setEmail("adam@gmail.com")
                    .setAddress("Koblenz")
                    .setDescription("torwart"));
        }
    }

    @Test
    public void removeContactPositiveTest() {
        //check size of contact list
        int sizeBefore = app.getContact().sizeOfContacts();
        app.getContact().removeContact();
        app.getContact().pause(1000);
        //check size of contact list
        int sizeAfter = app.getContact().sizeOfContacts();
        //compare list before - list after
        Assert.assertEquals(sizeAfter + 1, sizeBefore);
    }

}
