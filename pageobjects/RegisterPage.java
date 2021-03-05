import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegisterPage {
    @FindBy(xpath = "//input[@name='full_name']")
    public WebElement fullNameTextField;

    @FindBy(xpath = "//input[@name='email']")
    public WebElement emailAddressTextField;

    @FindBy(xpath = "//input[@name='phone']")
    public WebElement phoneTextField;

    @FindBy(xpath = "//div[text()='Appointed director']")
    public WebElement appointedDirectorButton;

    @FindBy(xpath = "//div[text()='Non-director']")
    public WebElement nonDirectorButton;

    @FindBy(xpath = "//input[contains(@icon-url, 'hear-about')]")
    public WebElement whereHearAboutOption;

    @FindBy(xpath = "//div[@class='q-checkbox__bg absolute']")
    public WebElement confirmCheckbox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement continueButton;

    @FindBy(xpath = "//div[@class='q-item__label']")
    public List<WebElement> whereToHearAboutList;

    @FindBy(xpath = "//input[@data-cy='register-person-referral-code']")
    public WebElement promoCodeTextField;

    @FindBy(xpath = "//div[@class='q-img__content absolute-full']")
    public WebElement nationalityFlag;

    public WebDriver driver;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillRegisterFormAsAppointedDirector(WebDriver driver, User user) throws InterruptedException {
        ElementExtensions.inputText(driver, this.fullNameTextField, user.fullName);
        ElementExtensions.inputText(driver, this.emailAddressTextField, user.emailAddress);
        try {
            ElementExtensions.waitElementToBeVisible(driver, nationalityFlag);
            ElementExtensions.inputText(driver, phoneTextField, user.mobileNumber);
        } catch (StaleElementReferenceException e) {
            WebElement phoneTextField = CommonFunctions.findElement(driver, By.xpath("//input[@name='phone']"));
            ElementExtensions.inputText(driver, phoneTextField, user.mobileNumber);
        }
        ElementExtensions.clickOnElement(driver, this.appointedDirectorButton);
        ElementExtensions.clickOnElement(driver, this.whereHearAboutOption);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listWhereToHear = this.whereToHearAboutList;
        for (WebElement element : listWhereToHear) {
            String strElement = element.getText();
            if (strElement.equals(user.whereHearAbout)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }
        ElementExtensions.inputText(driver, this.promoCodeTextField, user.promoCode);
        ElementExtensions.scrollToElement(driver, this.confirmCheckbox);
        ElementExtensions.clickOnElement(driver, this.confirmCheckbox);
        ElementExtensions.waitElementToBeClickable(driver, this.continueButton);
        ElementExtensions.clickOnElement(driver, this.continueButton);
    }

    public void fillRegisterFormAsNonDirector(WebDriver driver, User user) {
        ElementExtensions.inputText(driver, this.fullNameTextField, user.fullName);
        ElementExtensions.inputText(driver, this.emailAddressTextField, user.emailAddress);
        WebElement phoneTextField = CommonFunctions.findElement(driver, By.xpath("//input[@name='phone']"));
        ElementExtensions.inputText(driver, phoneTextField, user.mobileNumber);
        ElementExtensions.clickOnElement(driver, this.nonDirectorButton);
        ElementExtensions.clickOnElement(driver, this.whereHearAboutOption);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listWhereToHear = this.whereToHearAboutList;
        for (WebElement element : listWhereToHear) {
            String strElement = element.getText();
            if (strElement.equals(user.whereHearAbout)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }
        ElementExtensions.inputText(driver, this.promoCodeTextField, user.promoCode);
        ElementExtensions.scrollToElement(driver, this.confirmCheckbox);
        ElementExtensions.clickOnElement(driver, this.confirmCheckbox);
        ElementExtensions.waitElementToBeClickable(driver, this.continueButton);
        ElementExtensions.clickOnElement(driver, this.continueButton);
    }
}
