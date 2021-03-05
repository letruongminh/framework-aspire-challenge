import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IdentifyViewPage {
    @FindBy(xpath = "//span[@class='block']")
    public WebElement getStartedButton;

    public WebDriver driver;

    public IdentifyViewPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickGetStartedButton(WebDriver driver) {
        try {
            CommonFunctions.waitForPageLoaded(driver);
            ElementExtensions.clickOnElement(driver, this.getStartedButton);
        } catch (StaleElementReferenceException e) {
            WebElement element = driver.findElement(By.xpath("//span[@class='block']"));
            element.click();
        }
    }
}
