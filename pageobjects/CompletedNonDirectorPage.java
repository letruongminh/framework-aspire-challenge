import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CompletedNonDirectorPage {
    @FindBy(xpath = "//span[@class='q-btn__wrapper col row q-anchor--skip']")
    public WebElement returnToWebsiteButton;

    public WebDriver driver;

    public CompletedNonDirectorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickReturnWebsite(WebDriver driver) {
        ElementExtensions.clickOnElement(driver, returnToWebsiteButton);
    }
}
