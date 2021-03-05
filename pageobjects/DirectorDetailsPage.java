import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DirectorDetailsPage {
    @FindBy(xpath = "//input[@name='invited_business_name']")
    public WebElement companyNameTextField;

    @FindBy(xpath = "//input[@name='invited_full_name']")
    public WebElement directorFullNameTextField;

    @FindBy(xpath = "//input[@name='invited_email']")
    public WebElement directorEmailAddressTextField;

    @FindBy(xpath = "//span[@class='block']")
    public WebElement submitButton;

    public WebDriver driver;

    public DirectorDetailsPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void inputCompanyName(WebDriver driver, User user) {
        ElementExtensions.inputText(driver, companyNameTextField, user.companyName);
    }

    public void inputDirectorFullName(WebDriver driver, User user) {
        ElementExtensions.inputText(driver, this.directorFullNameTextField, user.directorFullName);
    }

    public void inputDirectorEmailAddress(WebDriver driver, User user) {
        ElementExtensions.inputText(driver, directorEmailAddressTextField, user.directorEmailAddress);
    }

    public void clickSubmitButton(WebDriver driver) {
        ElementExtensions.clickOnElement(driver, submitButton);
    }

    public void processDirectorDetailsPage(WebDriver driver, User user) {
        inputCompanyName(driver, user);
        inputDirectorFullName(driver, user);
        inputDirectorEmailAddress(driver, user);
        clickSubmitButton(driver);
    }
}
