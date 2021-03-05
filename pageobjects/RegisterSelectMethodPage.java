import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterSelectMethodPage {
    @FindBy(xpath = "//span[contains(text(),'Standard Registration')]/parent::div/following-sibling::button")
    public WebElement standardRegistrationButton;

    @FindBy(xpath = "//span[contains(text(),'MyInfo Registration')]/parent::div/following-sibling::button")
    public WebElement myInfoRegistrationButton;

    public WebDriver driver;

    public RegisterSelectMethodPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickStandardRegistrationButton(WebDriver driver) throws InterruptedException {
        try {
            standardRegistrationButton.click();
        } catch (StaleElementReferenceException e) {
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'Standard Registration')]/parent::div/following-sibling::button"));
            element.click();
        }
    }
}
