import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ElementExtensions {

    /**
     * This function is to wait for element to be clickable
     *
     * @param driver
     * @param element
     * @return
     */
    public static WebElement waitElementToBeClickable(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EnvSetup.TIMEOUT);
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    /**
     * This function is to wait for element to be visible
     *
     * @param driver
     * @param element
     * @return
     */
    public static WebElement waitElementToBeVisible(WebDriver driver, WebElement element) {
        WebDriverWait wait = new WebDriverWait(driver, EnvSetup.TIMEOUT);
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    /**
     * This function is to support user to input text
     *
     * @param driver
     * @param element
     * @param value
     */
    public static void inputText(WebDriver driver, WebElement element, String value) {
        element = waitElementToBeVisible(driver, element);
        element.sendKeys(value);
    }

    /**
     * This function is to click on element
     *
     * @param driver
     * @param element
     */
    public static void clickOnElement(WebDriver driver, WebElement element) {
        try {
            element = waitElementToBeClickable(driver, element);
            element.click();
        } catch (StaleElementReferenceException e) {
            e.printStackTrace();
        }
    }

    public static void actionChainClickOnElement(WebDriver driver, WebElement element) {
        ElementExtensions.waitElementToBeVisible(driver, element);
        Actions action = new Actions(driver);
        action.click(element).build().perform();
    }

    /**
     * This function is to scroll to the specific element
     *
     * @param driver
     * @param element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * This function is to retrieve element again
     *
     * @param driver
     * @param element
     * @return
     */
    public static boolean retryFindClick(WebDriver driver, WebElement element) {
        boolean result = false;
        int attempts = 0;
        while (attempts < 2) {
            try {
                ElementExtensions.clickOnElement(driver, element);
                result = true;
                break;
            } catch (StaleElementReferenceException e) {
                e.printStackTrace();
            }
            attempts++;
        }
        return result;
    }
}
