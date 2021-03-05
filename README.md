# AUTOMATION PROJECT FOR ASPIRE APP

## 1. AIM
To completely automate the registration workflow for Aspire application. So that, users are able to interact with the software more effectively. 

## 2. APPLICATION UNDER TEST 
* Link: https://feature-qa.customer-frontend.staging.aspireapp.com/sg/
* Programming Language: Java

### 2.1. Overview
There are pages in Singapore needed to go through:
1. Registration
2. Mobile Verification (OTP)
3. Personal Details
4. Email Verification (OTP)
5. Business Details
6. Identify Verification
7. Onboarding NPS

##  3. FRAMEWORK
### 3.1. GENERAL WORKFLOW
The general workflow is as below:
![Architecture](https://user-images.githubusercontent.com/53706302/110140299-87e88c00-7e06-11eb-9941-4a083b2393d5.png)

1. Test data which is stored in `data/uatdata/UserData.csv` should be prepared and saved as csv file > Framework will retrieve the data then push the data to User instance for further operations.
2. Story file containing logic of completed registration flow should be prepared.
3. Steps are created and mapped to the manual logic.
4. Execute the AppTest class under test/java folder.
5. If Test Result is **PASSED**, Test Report will be generated.
6. If Test Result is **FAILED**, we can easily debug and trace the root-cause.
7. Repeat step 1 to verify if it is fixed.

### 3.2. BEHAVIOR DRIVEN DEVELOPMENT (BDD)
In this project, as per the requirement - Cucumber & Katalon are not allowed to use, so that I decided to take advantage of JBehave. This is an another framework for BDD and it can be integrated into Serenity to generate visual report.

### 3.3. SELENIUM BASED FRAMEWORK
Selenium is a portable framework for testing web applications. In this framework, I have used this tool to emulate user interaction with browsers effectively.
#### 3.3.1. CommonFunctions class
The below table contains defined functions used to interact with UI element. This is strongly supported by Selenium test framwork

| Method | Usage | Input | Output |
| ------------- | ------------- | ------------- | ------------- |
| getUser  | Retrieve user data from csv file stored previously  | String | User |
| getTextFromElement | Extract text of displaying element | WebElement | String |
| generatePhoneNumber | Format the phone number to correct one | None | String |
| generateEmailAddress | Get randomly an email | None | String |
| pressKey | Press one specific key in the keyboard | WebDriver, WebElement, CharSequence | None | 
| selectDateOfBirth  | Select DOB | WebDriver, String, String, String | None |
| isSelectedOptionExistingInList | Verify selected option is existing in the list | List<WebElement>, String | boolean |
| waitForPageLoaded | Wait for page is fully loaded | WebDriver | None | 
| clickOutsideTextBox | Click outside the textbox to turn off the checkbox popup | WebDriver | None |
| findElements | Get list of elements appearing in the web page | WebDriver, By | List<WebElement> |
| findElement | Get the element | WebDriver, By | WebElement |
| scrollToElement | Scroll to the designated element before performing further operation | WebDriver, WebElement | None |
| getCurrentUrl | Get current url | WebDriver | String | 
| verifyTwoStrings | Verify if two inputted strings are matched | WebDriver | None | 
| setPageLoadTimeout | Set timeout | WebDriver | None |
  
#### 3.3.2. ElementExtensions class
| Method | Usage | Input | Output |
| --- | --- | --- | --- | 
| waitElementToBeClickable | wait for element to be clickable | WebDriver, WebElement | WebElement |
| waitElementToBeVisible | Wait for element to be visible | WebDriver, WebElement | WebElement |
| inputText | Support user to input text | WebDriver, WebElement, String | None |
| clickOnElement | Click on specific element | WebDriver, WebElement | None |
| actionChainClickOnElement | Create a chain of click action in the inputted element | WebDriver, WebElement | None |
| scrollToElement | Scroll to the specific element | WebDriver, WebElement | None |
| retryFindClick | Find the element again in case StaleElementReferenceException is thrown unexpectedly | WebDriver, WebElement | boolean |

### 3.3. Page Object Model (POM)
Throughout the test execution, user is navigated to various web pages. Each web page is one class containing UI element inside and the suitable methods to interact with them. ![POM](https://user-images.githubusercontent.com/53706302/110146929-b61d9a00-7e0d-11eb-8cdd-067b9b533ce9.png)

I have stored all pages under the `pageobjects` folder for easily accessing as above.
  
### 3.4. Data-driveness
In this project, I mainly want to get data via 3 objects in data object as below: 

| Objects  | Usage |
| ------------- | ------------- |
| UserData.csv  | This object must be inputted initially by user, they also must provide the **new** correct data suite before performing any test cases |
| User  | This class contains all necessary properties for the user needed to complete the testing flow |
| TestDataAccess | This class provides the method reading data from UserData.csv |

### 3.5. Ways to retrieve UI elements
All elements throughout the test case are retrieved by Xpath as per initial requirement.

## 4. STEPS NOT HAVING COMPLETED YET
### 4.1. Send Passport / Identity Card via mobile phone
* Reason: When clicking on phone, user is redirected to **Identify Onfido** to continue the process getting secure link. After clicking on the `Get secure link` button, they continue being redirected to the screen containing QR code, they must use their mobile devices to manually scan the QR to continue the process - it is a weird case for automation if QR Code is taken into consideration.

But I think the flow can be implemeted via SMS flow instead as I have found out one way to get through the issue by applying one newly created plugin - [SMS Listener Service](https://toilatester.blog/2021/02/07/huong-dan-cach-automate-otp-code-tren-mobile/). 

We can implement these below step to receive the message with the link inside: 
1. Install the app via this [link](https://github.com/toilatester/sms-listener-service/releases/tag/v1.0.0)
2. Connect Android device to PC and enable all modes supporting adb commands.
3. Execute the adb command: adb install -g sms-listener.apk 
4. Start application via this link: adb shell am start -n "com.toilatester.smslistener/com.toilatester.sms.listener.MainActivity" --ei serverPort 8185

After completing the above steps, whenever application sends message to device, the message is also fetched and send back to application. We can continue the flow by retrieving the message successfully, then extract the link inside. After this interview challenge session, I will think about performing it in person. 
### 4.2. Send Password / Identity Card via `Upload file` functionality
I think it is a possible solution as I see that after clicking on `Upload file` button, one popup is shown. I will consider this popup as normal popup as usual. 

![2021-03-06_0-36-20](https://user-images.githubusercontent.com/53706302/110152403-452db080-7e14-11eb-97df-33545a22c6b2.png)

> I will also try this case in person as well. 

Actually, this challenge is pretty intersting at all. Thank you all for helping me improve my technical knowledge through this requirements. 

## 5. VERIFICATION STEPS
### 5.1. Precondition
* Has IntelliJ previously installed
* Has all source cloned to local environment 
* Auto-download all libraries in `pom.xml` file

### 5.2. Test Execution
* Execute the `AppTest` class 

## REFERENCES
[1. SMS Listener Service](https://toilatester.blog/2021/02/07/huong-dan-cach-automate-otp-code-tren-mobile/)
[2. SMS Listener Android](https://github.com/toilatester/sms-listener-service/releases/tag/v1.0.0) 
