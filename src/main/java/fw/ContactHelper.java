package fw;

import model.Contact;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ContactHelper extends HelperBase{

    public ContactHelper(WebDriver driver) {
        super(driver);
    }

    public void addContact(Contact contact) {
        type(By.cssSelector("input:nth-child(1)"), contact.getName());
        type(By.cssSelector("input:nth-child(2)"), contact.getSurName());
        type(By.cssSelector("input:nth-child(3)"), contact.getPhone());
        type(By.cssSelector("input:nth-child(4)"), contact.getEmail());
        type(By.cssSelector("input:nth-child(5)"), contact.getAddress());
        type(By.cssSelector("input:nth-child(6)"), contact.getDescription());
        //click on the button Save
        clickWithAction(By.cssSelector(".add_form__2rsm2 button"));
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

    public void addRandomContact(int i) {
        addContact(new Contact()
                .setName("Karl" + i)
                .setSurName("Adam")
                .setPhone("123456" + i)
                .setEmail("adam" + i + "@gmail.com")
                .setAddress("Koblenz")
                .setDescription("torwart"));
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
