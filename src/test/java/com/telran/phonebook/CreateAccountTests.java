package com.telran.phonebook;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTests extends TestBase{

    @BeforeMethod
    public void ensurePreconditions() {
        //precondition: user should be logged out
        if (!isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"))){
            click(By.xpath("//button[contains(.,'Sign Out')]"));
        } else {
            //click on LOGIN link
            clickOnLoginLink();
        }
    }

    //test
    @Test
    public void createAccountPositiveTest() {
        int i = (int) ((System.currentTimeMillis()/1000)%3600);
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        fillLoginRegistrationForm("email" + i + "@gmail.com","Aa12345~");
        //click on Registration button
        click(By.xpath("//button[contains(.,'Registration')]"));
        //verify Sign Out button displayed
       pause(1000);
        Assert.assertTrue(isSignOutButtonPresent());
    }

    @Test
    public void createAccountNegativeTest() {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        fillLoginRegistrationForm("karl+2@gmail.co", "Aa12345");
        //click on Registration button
        click(By.xpath("//button[contains(.,'Registration')]"));
        //verify Sign Out button displayed
        Assert.assertTrue(isAlertPresent());
        Assert.assertTrue(isElementPresent(By.xpath("//div[contains(.,'Registration failed with code 400')]")));
    }

    public boolean isAlertPresent() {
        Alert alert = new WebDriverWait(driver,20).until(ExpectedConditions.alertIsPresent());
        if (alert == null) {
            return false;
        } else {
            driver.switchTo().alert();
            alert.accept();
            return true;
        }
    }

}
