import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.person.Person;
import junit.framework.Assert;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CommonFunctions {
    static Fairy fairy = Fairy.create();
    static Person person = fairy.person();
    private WebDriver driver;

    /**
     * @param file
     * @return a user with data in csv file
     */
    public static User getUser(String file) {
        User user = null;
        try {
            user = TestDataAccess.readAllData(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public static String getTextFromElement(WebElement element) {
        String text = element.getText();
        return text;
    }

    /**
     * Initial we have phone number format as follows: 123-456-789
     *
     * @return a standard phone number: 123456789
     */
    public static String generatePhoneNumber() {
        String initialPhoneNumber = person.getTelephoneNumber();
        String correctPhoneNumber = "";
        String[] phonePartArray = initialPhoneNumber.split("-");
        for (String phonePart : phonePartArray) {
            correctPhoneNumber += phonePart;
        }
        return correctPhoneNumber;
    }

    /**
     * @return a standard email
     */
    public static String generateEmailAddress() {
        return person.getEmail();
    }

    /**
     * Simulate press one specific key in the keyboard
     *
     * @param driver
     * @param sequence
     */
    public static void pressKey(WebDriver driver, WebElement element, CharSequence sequence) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click()", element);
        element.sendKeys(sequence);
    }

    /**
     * Support selecting date of birth for user
     *
     * @param date  - from 1 to 31
     * @param month - 3 first letters of month - Mar
     * @param year  - must be larger than 18
     */
    public static void selectDateOfBirth(WebDriver driver, String date, String month, String year) throws InterruptedException {
        DateControlPage dateControlPage = new DateControlPage(driver);

        // Select month
        ElementExtensions.clickOnElement(driver, dateControlPage.monthSelect);
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listMonth = dateControlPage.listMonth;
        for (WebElement selectedMonth : listMonth) {
            if (selectedMonth.getText().equals(month)) {
                ElementExtensions.clickOnElement(driver, selectedMonth);
                break;
            }
        }

        // Select year
        ElementExtensions.clickOnElement(driver, dateControlPage.yearSelect);
        CommonFunctions.waitForPageLoaded(driver);
        while (!isSelectedOptionExistingInList(dateControlPage.listYear, year)) {
            CommonFunctions.waitForPageLoaded(driver);
            ElementExtensions.clickOnElement(driver, dateControlPage.backYearButton);
        }

        List<WebElement> listYear = dateControlPage.listYear;
        for (WebElement selectedYear : listYear) {
            if (selectedYear.getText().equals(year)) {
                ElementExtensions.clickOnElement(driver, selectedYear);
                break;
            }
        }

        // Select date
        CommonFunctions.waitForPageLoaded(driver);
        List<WebElement> listDate = driver.findElements(By.xpath("//div[@class='q-date__calendar-days fit']//span[@class='block']"));
        for (WebElement selectedDate : listDate) {
            String selectedDateText = selectedDate.getText();
            if (selectedDateText.equals(date)) {
                JavascriptExecutor executor = (JavascriptExecutor) driver;
                executor.executeScript("arguments[0].click();", selectedDate);
                break;
            }
        }
    }

    /**
     * Verify is selected option is existing in list
     *
     * @param listOption
     * @param inputtedOption
     * @return
     */
    public static boolean isSelectedOptionExistingInList(List<WebElement> listOption, String inputtedOption) {
        boolean isExisting = false;
        for (WebElement element : listOption) {
            if (element.getText().equals(inputtedOption)) {
                isExisting = true;
                break;
            }
        }
        return isExisting;
    }

    /**
     * Wait for page is fully loaded
     *
     * @param driver
     */
    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            @NullableDecl
            @Override
            public Boolean apply(@NullableDecl WebDriver webDriver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
            }
        };
        try {
            Thread.sleep(2000);
            WebDriverWait wait = new WebDriverWait(driver, EnvSetup.TIMEOUT);
            wait.until(expectation);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Click outside text box to turn off the checkbox popup
     *
     * @param driver
     */
    public static void clickOutsideTextBox(WebDriver driver) {
        Actions action = new Actions(driver);
        action.sendKeys(Keys.TAB).build().perform();
    }

    /**
     * Get list of elements appearing in the web page
     *
     * @param driver
     * @param by
     * @return
     */
    public static List<WebElement> findElements(WebDriver driver, By by) {
        List<WebElement> listElement = driver.findElements(by);
        return listElement;
    }

    /**
     * Retrieve the web element again to avoid stale element reference exception
     *
     * @param driver
     * @param by
     * @return
     */
    public static WebElement findElement(WebDriver driver, By by) {
        WebElement element = driver.findElement(by);
        return element;
    }

    /**
     * Scroll to the designated element before performing further operation
     *
     * @param driver
     * @param element
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    /**
     * Get current url
     *
     * @param driver
     * @return
     */
    public static String getCurrentUrl(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    /**
     * Verify if two inputted strings are matched
     *
     * @param expectedString
     * @param actualString
     */
    public static void verifyTwoStrings(String expectedString, String actualString) {
        Assert.assertEquals(expectedString, actualString);
    }

    public static void setPageLoadTimeout(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(EnvSetup.TIMEOUT, TimeUnit.SECONDS);
    }
}
