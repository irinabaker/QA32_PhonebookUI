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
            driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
        } else {
            //click on LOGIN link
            driver.findElement(By.xpath("//a[contains(.,'LOGIN')]")).click();
        }
    }

    //test
    @Test
    public void createAccountPositiveTest() throws InterruptedException {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("karl+2@gmail.co");
        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("Aa12345~");
        //click on Registration button
        driver.findElement(By.xpath("//button[contains(.,'Registration')]")).click();
        //verify Sign Out button displayed
        Thread.sleep(1000);
        Assert.assertTrue(isElementPresent(By.xpath("//button[contains(.,'Sign Out')]")));
    }

    @Test
    public void createAccountNegativeTest() {
        //assert is registration form displayed
        Assert.assertTrue(isElementPresent2(By.cssSelector(".login_login__3EHKB")));
        //fill registration form
        driver.findElement(By.cssSelector("[placeholder='Email']")).click();
        driver.findElement(By.cssSelector("[placeholder='Email']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Email']")).sendKeys("karl+2@gmail.co");
        driver.findElement(By.cssSelector("[placeholder='Password']")).click();
        driver.findElement(By.cssSelector("[placeholder='Password']")).clear();
        driver.findElement(By.cssSelector("[placeholder='Password']")).sendKeys("Aa12345");
        //click on Registration button
        driver.findElement(By.xpath("//button[contains(.,'Registration')]")).click();
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
