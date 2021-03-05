import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterCompletionPage {
    @FindBy(xpath = "//span[contains(text(), 'Continue')]")
    public WebElement continueButton;

    public WebDriver driver;

    public RegisterCompletionPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickContinueButton(WebDriver driver) {
        CommonFunctions.waitForPageLoaded(driver);
        ElementExtensions.clickOnElement(driver, this.continueButton);
    }
}
