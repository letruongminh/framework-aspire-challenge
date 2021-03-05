import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MobileVerificationPage {
    @FindBy(xpath = "//div[contains(@class,'verify-otp')]//div[contains(@class,'digit-input__input')]")
    public WebElement otpCodeTextField;

    public WebDriver driver;

    public MobileVerificationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickOtpTextField(WebDriver driver) {
        try {
            Thread.sleep(5000);
            ElementExtensions.clickOnElement(driver, otpCodeTextField);
        }
        catch(StaleElementReferenceException | InterruptedException e){
            WebElement element = driver.findElement(By.xpath("//div[contains(@class,'verify-otp')]//div[contains(@class,'digit-input__input')]"));
            ElementExtensions.clickOnElement(driver, element);
        }
    }

    public void sendOtp(WebDriver driver) {
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.NUMPAD1)
                .sendKeys(Keys.NUMPAD2)
                .sendKeys(Keys.NUMPAD3)
                .sendKeys(Keys.NUMPAD4)
                .build()
                .perform();
    }

    public void processOtp(WebDriver driver) {
        CommonFunctions.setPageLoadTimeout(driver);
        clickOtpTextField(driver);
        sendOtp(driver);
    }
}
