import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonEditPage {

    @FindBy(xpath = "//div[@label='Date of Birth']//input")
    public WebElement dateOfBirthTextField;

    @FindBy(xpath = "//div[contains(@class,'q-virtual-scroll__content')]/div//div[@class='q-item__label']")
    public List<WebElement> listNationality;

    @FindBy(xpath = "//div[@class='q-virtual-scroll__content']//div[@class='q-item__label']")
    public List<WebElement> listPurposesOfUsing;

    @FindBy(xpath = "//div[@class='q-virtual-scroll__content']//div[@class='q-item__label']")
    public List<WebElement> listGender;

    @FindBy(xpath = "//input[@data-cy='kyc-gender']")
    public WebElement genderTextField;

    @FindBy(xpath = "//div[@url='options']")
    public WebElement interestedProduct;

    @FindBy(xpath = "//div[@url='countries/all']")
    public WebElement nationalityTextField;

    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    public WebElement submitButton;

    public WebDriver driver;

    public PersonEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillPersonalDetails(WebDriver driver, User user) throws InterruptedException {
        ElementExtensions.clickOnElement(driver, this.dateOfBirthTextField);
        CommonFunctions.selectDateOfBirth(driver, user.date, user.month, user.year);

        ElementExtensions.clickOnElement(driver, this.nationalityTextField);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listNationality = CommonFunctions.findElements(driver, By.xpath("//div[contains(@class,'q-virtual-scroll__content')]/div//div[@class='q-item__label']"));
        for (WebElement element : listNationality) {
            String strElement = element.getText();
            if (strElement.equals(user.nationality)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }

        ElementExtensions.clickOnElement(driver, this.genderTextField);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listGender = this.listGender;
        for (WebElement element : listGender) {
            String strElement = element.getText();
            if (strElement.equals(user.gender)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }

        ElementExtensions.clickOnElement(driver, this.interestedProduct);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listPurposesOfUsing = this.listPurposesOfUsing;
        for (WebElement element : listPurposesOfUsing) {
            String strElement = element.getText();
            if (strElement.equals(user.interestedProduct)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }
        CommonFunctions.clickOutsideTextBox(driver);
        ElementExtensions.clickOnElement(driver, this.submitButton);
    }
}
