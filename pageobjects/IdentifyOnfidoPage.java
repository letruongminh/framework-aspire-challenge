import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IdentifyOnfidoPage {
    @FindBy(xpath = "//span[@class='block']")
    public WebElement beginVerificationButton;

    public WebDriver driver;

    public IdentifyOnfidoPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickBeginVerificationButton(WebDriver driver) {
        ElementExtensions.clickOnElement(driver, this.beginVerificationButton);
    }
}
