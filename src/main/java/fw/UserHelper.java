package fw;

import model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserHelper extends HelperBase{

    public UserHelper(WebDriver driver) {
        super(driver);
    }

    public void login() {
        fillLoginRegistrationForm(new User().setEmail("karl+2@gmail.co").setPassword("Aa12345~"));
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public void fillLoginRegistrationForm(User user) {
        type(By.cssSelector("[placeholder='Email']"), user.getEmail());
        type(By.cssSelector("[placeholder='Password']"), user.getPassword());
    }

    public void clickOnLoginButton() {
        click(By.xpath("//button[contains(.,'Login')]"));
    }

    public boolean isLoginRegFormPresent() {
        return isElementPresent2(By.cssSelector(".login_login__3EHKB"));
    }

    public void clickOnRegistrationButton() {
        click(By.xpath("//button[contains(.,'Registration')]"));
    }

    public boolean isErrorMessagePresent() {
        return isElementPresent(By.xpath("//div[contains(.,'Registration failed with code 400')]"));
    }
}
