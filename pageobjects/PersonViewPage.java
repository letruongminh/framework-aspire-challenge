import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PersonViewPage {
    @FindBy(xpath = "//button//span[text()='Get Started']")
    public WebElement getStartedButton;

    public WebDriver driver;

    public PersonViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickGetStartedButton(WebDriver driver) {
        CommonFunctions.waitForPageLoaded(driver);
        ElementExtensions.clickOnElement(driver, this.getStartedButton);
    }
}
