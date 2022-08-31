package com.telran.phonebook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class TestBase {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://contacts-app.tobbymarshall815.vercel.app");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public boolean isElementPresent(By locator) {
        return driver.findElements(locator).size() > 0;
    }

    public boolean isElementPresent2(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public void type(By locator, String text) {
        click(locator);
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickWithAction(By locator) {

        Actions actions = new Actions(driver);
        WebElement element = driver.findElement(locator);
        actions.moveToElement(element).perform();
        element.click();
    }

    @AfterMethod(enabled = false)
    public void tearDown() {
        driver.quit();
    }

    public void addContact() {
        type(By.cssSelector("input:nth-child(1)"), "Karl");
        type(By.cssSelector("input:nth-child(2)"), "Adam");
        type(By.cssSelector("input:nth-child(3)"), "123456");
        type(By.cssSelector("input:nth-child(4)"), "adam@gmail.com");
        type(By.cssSelector("input:nth-child(5)"), "Koblenz");
        type(By.cssSelector("input:nth-child(6)"), "torwart");
        //click on the button Save
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
    }

    public void clickOnAddLink() {
        click(By.cssSelector("a:nth-child(5)"));
    }

    public void login() {
        fillLoginRegistrationForm("karl+2@gmail.co", "Aa12345~");
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public void fillLoginRegistrationForm(String email, String password) {
        type(By.cssSelector("[placeholder='Email']"), email);
        type(By.cssSelector("[placeholder='Password']"), password);
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void removeContact() {
        if (!isContactListEmpty()) {
            //click on the contact cart
            click(By.cssSelector(".contact-item_card__2SOIM"));
            //click on the Remove button
            click(By.xpath("//button[text()='Remove']"));
        }
    }

    public int sizeOfContacts() {
        if (driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size() > 0) {
            return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).size();
        } else
            return 0;
    }

    public boolean isContactListEmpty() {
        return driver.findElements(By.cssSelector(".contact-item_card__2SOIM")).isEmpty();
    }
}
