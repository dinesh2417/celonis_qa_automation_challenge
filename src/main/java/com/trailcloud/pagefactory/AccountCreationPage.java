package com.trailcloud.pagefactory;

import com.trailcloud.core.util.Wait;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.UUID;

public class AccountCreationPage extends BasePage {

    @FindBy(id = "id_gender2")
    WebElement mrsRadioButton;
    @FindBy(id = "customer_firstname")
    WebElement firstNameField;
    @FindBy(id = "customer_lastname")
    WebElement lastNameField;
    @FindBy(id = "passwd")
    WebElement passwordField;
    @FindBy(id = "days")
    WebElement dayOfBirthSelector;
    @FindBy(id = "months")
    WebElement monthOfBirthSelector;
    @FindBy(id = "years")
    WebElement yearOfBirthSelector;

    @FindBy(id = "company")
    WebElement companyField;
    @FindBy(id = "address1")
    WebElement address1Field;
    @FindBy(id = "address2")
    WebElement address2Field;
    @FindBy(id = "city")
    WebElement cityField;
    @FindBy(id = "id_state")
    WebElement stateField;
    @FindBy(id = "postcode")
    WebElement postCodeField;
    @FindBy(id = "other")
    WebElement additionalInfoField;
    @FindBy(id = "phone")
    WebElement homePhoneField;
    @FindBy(id = "phone_mobile")
    WebElement mobilePhoneField;
    @FindBy(id = "alias")
    WebElement aliasField;

    @FindBy(id = "submitAccount")
    WebElement registerButton;

    public AccountCreationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Fill personal information")
    public void fillPersonalInformation(String firstName, String lastName, String password, String day,
                                        String month, String year) throws Exception {
        selectTitle();
        enterFirstName(firstName);
        enterLastName(lastName);
        enterPassword(password);
        selectDayOfBirth(day);
        selectMonthOfBirth(month);
        selectYearOfBirth(year);
    }

    @Step("Fill address information")
    public void fillAddressInformation() throws Exception {
        enterCompanyName();
        enterAddress1();
        enterAddress2();
        enterCity();
        selectState();
        enterPostCode();
        enterAdditionalInformation();
        enterHomePhone();
        enterMobilePhone();
        enterAlias();
    }

    @Step("Submit account creation")
    public void submitAccountCreation() {
        Wait.elementToBeClickable(driver, registerButton).click();
    }

    @Step("Select Title")
    private void selectTitle() {
        Wait.elementToBeClickable(driver, mrsRadioButton).click();
    }

    @Step("Enter first name")
    private void enterFirstName(String firstName) throws Exception {
        action.enterValue(firstNameField, firstName);
    }

    @Step("Enter last name")
    private void enterLastName(String lastName) throws Exception {
        action.enterValue(lastNameField, lastName);
    }

    @Step("Enter password")
    private void enterPassword(String password) throws Exception {
        action.enterValue(passwordField, password);
    }

    @Step("Select day of birth")
    private void selectDayOfBirth(String dayOfBirth) throws Exception {
        action.selectByValue(dayOfBirthSelector, dayOfBirth);
    }

    @Step("Select month of birth")
    private void selectMonthOfBirth(String monthOfBirth) throws Exception {
        action.selectByValue(monthOfBirthSelector, monthOfBirth);
    }

    @Step("Select year of birth")
    private void selectYearOfBirth(String yearOfBirth) throws Exception {
        action.selectByValue(yearOfBirthSelector, yearOfBirth);
    }

    @Step("Enter company name")
    private void enterCompanyName() throws Exception {
        action.enterValue(companyField, UUID.randomUUID().toString());
    }

    @Step("Enter address")
    private void enterAddress1() throws Exception {
        action.enterValue(address1Field, "Address");
    }

    @Step("Enter address2")
    private void enterAddress2() throws Exception {
        action.enterValue(address2Field, UUID.randomUUID().toString());
    }

    @Step("Enter city")
    private void enterCity() throws Exception {
        action.enterValue(cityField, UUID.randomUUID().toString());
    }

    @Step("Enter state")
    private void selectState() throws Exception {
        action.selectByValue(stateField, "2");
    }

    @Step("Enter postCode")
    private void enterPostCode() throws Exception {
        action.enterValue(postCodeField, "11111");
    }

    @Step("Enter additionalInfo")
    private void enterAdditionalInformation() throws Exception {
        action.enterValue(additionalInfoField, "Additional information");
    }

    @Step("Enter homePhone")
    private void enterHomePhone() throws Exception {
        action.enterValue(homePhoneField, "777777777");
    }

    @Step("Enter mobilePhone")
    private void enterMobilePhone() throws Exception {
        action.enterValue(mobilePhoneField, "777777777");
    }

    @Step("Enter alias")
    private void enterAlias() throws Exception {
        action.enterValue(aliasField, "alias");
    }
}
