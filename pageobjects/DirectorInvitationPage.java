import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DirectorInvitationPage {
    @FindBy(xpath = "//button//span[@class='block']")
    public WebElement inviteDirectorButton;

    public WebDriver driver;

    public DirectorInvitationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void clickInviteDirectorButton(WebDriver driver) {
        ElementExtensions.clickOnElement(driver, inviteDirectorButton);
    }
}
