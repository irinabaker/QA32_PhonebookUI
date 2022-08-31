package com.telran.phonebook;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod
    public void ensurePreconditions() {
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))){
            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
        } else {
            driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
        }
    }

    @Test
    public void loginPositiveTest() {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill login form
        type(By.cssSelector("[placeholder='Email']"), "karl+2@gmail.co");
        type(By.cssSelector("[placeholder='Password']"), "Aa12345~");
        click(By.xpath("//button[contains(.,'Login')]"));
        //verify Sign Out button displayed
        pause(1000);
        Assert.assertTrue(isSignOutButtonPresent());
    }

}
