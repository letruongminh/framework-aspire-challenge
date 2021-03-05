import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class DateControlPage {
    @FindBy(xpath = "(//div[contains(@class, 'q-date__calendar')]//button[contains(@class, 'rectangle')])[1]")
    public WebElement monthSelect;

    @FindBy(xpath = "(//div[contains(@class, 'q-date__calendar')]//button[contains(@class, 'rectangle')])[2]")
    public WebElement yearSelect;

    @FindBy(xpath = "//span[contains(@class, 'q-btn__wrapper')]//span[@class='block']")
    public List<WebElement> listMonth;

    @FindBy(xpath = "//div[contains(@class, 'q-date__years')]//span[@class='block']")
    public List<WebElement> listYear;

    @FindBy(xpath = "//div[@class='q-date__calendar-days fit']//span[@class='block']")
    public List<WebElement> listDate;

    @FindBy(xpath = "//i[@class='fas fa-chevron-left q-icon notranslate']")
    public WebElement backYearButton;

    public WebDriver driver;

    public DateControlPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
