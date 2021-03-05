import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BusinessEditPage {
    @FindBy(xpath = "//input[@icon-url='img/kyc/business-name.svg']")
    public WebElement businessNameTextBox;

    @FindBy(xpath = "//input[@data-cy='register-business-registration-type']")
    public WebElement registrationTypeTextBox;

    @FindBy(xpath = "//input[@data-cy='register-business-registration-numer']")
    public WebElement businessRegistrationNumberTextBox;

    @FindBy(xpath = "//input[@data-cy='register-business-industry']")
    public WebElement industryNameTextBox;

    @FindBy(xpath = "//input[@data-cy='register-business-sub-industry']")
    public WebElement subIndustryNameTextBox;

    @FindBy(xpath = "//div[@class='q-virtual-scroll__content']//div[@class='q-item__label']")
    public List<WebElement> listRegistrationType;

    @FindBy(xpath = "//div[@class='q-virtual-scroll__content']//div[@class='q-item__label']")
    public List<WebElement> listIndustries;

    @FindBy(xpath = "//div[@class='q-virtual-scroll__content']//div[@class='q-item__label']")
    public List<WebElement> listSubIndustries;

    @FindBy(xpath = "//span[contains(text(), 'Submit')]")
    public WebElement submitButton;

    public WebDriver driver;

    public BusinessEditPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public void fillBusinessDetails(WebDriver driver, User user) {
        ElementExtensions.inputText(driver, this.businessNameTextBox, user.businessName);
        try {
            CommonFunctions.waitForPageLoaded(driver);
            ElementExtensions.actionChainClickOnElement(driver, this.registrationTypeTextBox);
        } catch (StaleElementReferenceException e) {
            WebElement element = driver.findElement(By.xpath("//input[@data-cy='register-business-registration-type']"));
            ElementExtensions.clickOnElement(driver, element);
        }

        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listRegistrationType = this.listRegistrationType;
        for (WebElement element : listRegistrationType) {
            String strElement = element.getText();
            if (strElement.equals(user.registrationType)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }

        ElementExtensions.inputText(driver, this.businessRegistrationNumberTextBox,
                user.businessRegistrationNumber);
        ElementExtensions.clickOnElement(driver, this.industryNameTextBox);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listIndustries = this.listIndustries;
        for (WebElement element : listIndustries) {
            String strElement = element.getText();
            if (strElement.equals(user.industry)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }
        ElementExtensions.clickOnElement(driver, this.subIndustryNameTextBox);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listSubIndustries = this.listSubIndustries;
        for (WebElement element : listSubIndustries) {
            String strElement = element.getText();
            if (strElement.equals(user.subIndustry)) {
                ElementExtensions.clickOnElement(driver, element);
                break;
            }
        }

        ElementExtensions.clickOnElement(driver, this.submitButton);
    }
}
