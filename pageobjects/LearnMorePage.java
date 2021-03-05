import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LearnMorePage {
    @FindBy(xpath = "//span[text()='Learn More']")
    @CacheLookup
    public WebElement learnMoreButton;

    @FindBy(xpath = "//div[@class='aspire-cta-screen__content']//span[contains(text(), 'Singapore')]")
    @CacheLookup
    public WebElement alreadyHaveRegisterBusinessLink;

    public WebDriver driver;

    public LearnMorePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickAlreadyHaveRegisterBusinessLink(WebDriver driver) {
        ElementExtensions.clickOnElement(driver, this.alreadyHaveRegisterBusinessLink);
    }
}
