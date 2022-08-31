package com.telran.phonebook;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RemoveContactTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (!isSignOutButtonPresent()) {
            clickOnLoginLink();
            login();
            clickOnAddLink();
            addContact();
        }
    }

    @Test
    public void removeContactPositiveTest() {
        //check size of contact list
        int sizeBefore = sizeOfContacts();
        removeContact();
        pause(1000);
        //check size of contact list
        int sizeAfter = sizeOfContacts();
        //compare list before - list after
        Assert.assertEquals(sizeAfter + 1, sizeBefore);
    }

}
