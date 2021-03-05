import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BusinessViewPage {
    @FindBy(xpath = "//span[contains(text(), 'Get Started')]")
    public WebElement getStartedButton;

    public WebDriver driver;

    public BusinessViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickGetStartedButton(WebDriver driver) {
        CommonFunctions.waitForPageLoaded(driver);
        ElementExtensions.clickOnElement(driver, this.getStartedButton);
    }
}
