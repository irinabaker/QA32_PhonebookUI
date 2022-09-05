package fw;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HeaderHelper extends HelperBase{

    public HeaderHelper(WebDriver driver) {
        super(driver);
    }

    public void clickOnAddLink() {
        click(By.cssSelector("a:nth-child(5)"));
    }

    public void clickOnLoginLink() {
        click(By.xpath("//a[contains(.,'LOGIN')]"));
    }

    public boolean isSignOutButtonPresent() {
        return isElementPresent(By.xpath("//button[contains(.,'Sign Out')]"));
    }

    public void clickOnSignOutButton() {
        driver.findElement(By.xpath("//button[contains(.,'Sign Out')]")).click();
    }

    public boolean isLoginLinkPresent() {
        return isElementPresent(By.xpath("//a[contains(.,'LOGIN')]"));
    }
}
