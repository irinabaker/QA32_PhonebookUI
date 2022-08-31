package com.telran.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AddContactTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        //ensure Sign Out button isn't displayed
        if (!isSignOutButtonPresent()) {
            //click on Login link
            clickOnLoginLink();
            //login
            type(By.cssSelector("[placeholder='Email']"), "karl+2@gmail.co");
            type(By.cssSelector("[placeholder='Password']"), "Aa12345~");
            click(By.xpath("//button[contains(.,'Login')]"));
            //click on the link Add
            clickOnAddLink();
        }
    }

    @Test
    public void addContactPositiveTest() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        //fill contact form
        type(By.cssSelector("input:nth-child(1)"),"Karl" + i);
        type(By.cssSelector("input:nth-child(2)"),"Adam");
        type(By.cssSelector("input:nth-child(3)"),"123456" + i);
        type(By.cssSelector("input:nth-child(4)"),"adam" + i + "@gmail.com");
        type(By.cssSelector("input:nth-child(5)"),"Koblenz");
        type(By.cssSelector("input:nth-child(6)"),"torwart");
        //click on the button Save
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
        //assert contact is created
        Assert.assertTrue(isContactCreated("Karl" + i));
    }

    public boolean isContactCreated(String text) {
        List<WebElement> contacts = driver.findElements(By.xpath("//h2"));
        for (WebElement el: contacts) {
            if (el.getText().contains(text))
                return true;
        }
        return false;
    }

}
