import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class IncorporateSelectorPage {
    @FindBy(xpath = "//span[contains(text(), 'have a business yet')]/parent::div/following-sibling::button")
    public WebElement notHaveBusinessContinueButton;

    public WebDriver driver;

    public IncorporateSelectorPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickNotHaveBusinessContinueButton(WebDriver driver) {
        try{
            Thread.sleep(3000);
            ElementExtensions.clickOnElement(driver, notHaveBusinessContinueButton);
        }
        catch (StaleElementReferenceException | InterruptedException e){
            WebElement element = driver.findElement(By.xpath("//span[contains(text(),'have a business yet')]/parent::div/following-sibling::button"));
            ElementExtensions.clickOnElement(driver, element);
        }
    }
}
