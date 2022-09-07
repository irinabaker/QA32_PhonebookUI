package com.telran.phonebook.tests;

import model.Contact;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.DataProviders;

public class AddContactTests extends TestBase {

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
        int i = (int) ((System.currentTimeMillis() / 1000) % 3600);
        //fill contact form
        app.getContact().addRandomContact(i);
        //assert contact is created
        Assert.assertTrue(app.getContact().isContactCreated("Karl" + i));
    }

    @Test(dataProvider = "newContact", dataProviderClass = DataProviders.class, enabled = false)
    public void addContactPositiveTestFromDataProvider(String name, String sName, String phone, String email, String add, String desc) {
        app.getContact().addContact(new Contact().setName(name)
                .setSurName(sName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(add)
                .setDescription(desc));
        app.getContact().removeContact();
    }

    @Test(dataProvider = "newContactWithCSV", dataProviderClass = DataProviders.class, enabled = false)
    public void addContactPositiveTestFromDataProviderWithCsvFile(Contact contact) {
        app.getContact().addContact(contact);
        app.getContact().removeContact();
        app.getUser().pause(1000);

    }

}
