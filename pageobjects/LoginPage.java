import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(xpath = "//input[@name='phone']")
    public WebElement mobileNumber;

    @FindBy(xpath = "//a[@href='/sg/register']")
    public WebElement registerLink;

    public WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickOnRegisterLink(WebDriver driver) {
        CommonFunctions.waitForPageLoaded(driver);
        ElementExtensions.clickOnElement(driver, this.registerLink);
    }
}
