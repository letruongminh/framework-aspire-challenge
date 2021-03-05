import org.jbehave.core.annotations.*;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BaseSteps {
    WebDriver driver = BrowserManager.getDriver();
    BusinessViewPage businessViewPage = new BusinessViewPage(driver);
    LoginPage loginPage = new LoginPage(driver);
    RegisterPage registerPage = new RegisterPage(driver);
    MobileVerificationPage otpPage = new MobileVerificationPage(driver);
    RegisterCompletionPage registerCompletionPage = new RegisterCompletionPage(driver);
    IncorporateSelectorPage incorporateSelectorPage = new IncorporateSelectorPage(driver);
    LearnMorePage learnMorePage = new LearnMorePage(driver);
    RegisterSelectMethodPage registerSelectMethodPage = new RegisterSelectMethodPage(driver);
    PersonViewPage personViewPage = new PersonViewPage(driver);
    PersonEditPage personEditPage = new PersonEditPage(driver);
    BusinessEditPage businessEditPage = new BusinessEditPage(driver);
    IdentifyViewPage identifyViewPage = new IdentifyViewPage(driver);
    IdentifyOnfidoPage identifyOnfidoPage = new IdentifyOnfidoPage(driver);
    DirectorInvitationPage directorInvitationPage = new DirectorInvitationPage(driver);
    DirectorDetailsPage directorDetailsPage = new DirectorDetailsPage(driver);
    CompletedNonDirectorPage completedNonDirectorPage = new CompletedNonDirectorPage(driver);
    public User user;

    @BeforeScenario
    public void setUp() {
        BrowserManager.loadApplication(driver, EnvSetup.UAT_SITE);
    }

    @Given("a user with detailed data")
    public void givenDetailedData() {
        this.user = CommonFunctions.getUser("UserData.csv");
    }

    @When("I complete registration flow as appointed director")
    public void completeRegistrationFlow() throws InterruptedException {
        loginPage.clickOnRegisterLink(driver);
        registerPage.fillRegisterFormAsAppointedDirector(driver, this.user);
        otpPage.processOtp(driver);
        registerCompletionPage.clickContinueButton(driver);
        incorporateSelectorPage.clickNotHaveBusinessContinueButton(driver);
        learnMorePage.clickAlreadyHaveRegisterBusinessLink(driver);
        registerSelectMethodPage.clickStandardRegistrationButton(driver);

        personViewPage.clickGetStartedButton(driver);
        personEditPage.fillPersonalDetails(driver, this.user);

        otpPage.processOtp(driver);
        businessViewPage.clickGetStartedButton(driver);
        businessEditPage.fillBusinessDetails(driver, this.user);
        Thread.sleep(5000);
        identifyViewPage.clickGetStartedButton(driver);
    }

    @When("I complete registration flow as non-director")
    public void completeRegistrationFlowAsNonDirector() throws InterruptedException {
        loginPage.clickOnRegisterLink(driver);
        CommonFunctions.waitForPageLoaded(driver);
        loginPage.clickOnRegisterLink(driver);
        registerPage.fillRegisterFormAsNonDirector(driver, this.user);
        directorInvitationPage.clickInviteDirectorButton(driver);
        directorDetailsPage.processDirectorDetailsPage(driver, user);
        completedNonDirectorPage.clickReturnWebsite(driver);
    }

    @Then("I can meet the step for waiting the approval")
    public void waitForApproval() {
        identifyOnfidoPage.clickBeginVerificationButton(driver);
    }

    @Then("I go back to main page")
    public void goBackToMainPage() {
        completedNonDirectorPage.clickReturnWebsite(driver);
        String actualMainPage = CommonFunctions.getCurrentUrl(driver);
        CommonFunctions.verifyTwoStrings(EnvSetup.MAIN_PAGE, actualMainPage);
    }

    @AfterScenario
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
