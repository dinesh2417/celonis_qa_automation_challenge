package com.trailcloud.pagefactory;

import com.trailcloud.core.util.Element;
import com.trailcloud.core.util.Wait;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class AuthenticationPage extends BasePage {
    @FindBy(name = "username")
    WebElement usernameField;
    @FindBy(name = "password")
    WebElement passwordField;
    @FindBy(xpath = "//button[@data-testing-uid = 'login-form-submit-button']")
    WebElement signInButton;
    @FindBy(xpath = "//span[@class = 'ce-validation__hint']")
    WebElement authFailedMessage;
    @FindBy(xpath = "//h1[@class='ce-login-layout__title']")
    WebElement signedOutMessage;

    public AuthenticationPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("logging in")
    public void loginAs(String username, String password) throws Exception {
        enterUsername(username);
        enterPassword(password);
        clickSignIn();
    }

    @Step("Enter username to login")
    public void enterUsername(String email) throws Exception {
        action.enterValue(usernameField, email);
    }

    @Step("Enter password to login")
    public void enterPassword(String password) throws Exception {
        action.enterValue(passwordField, password);
    }

    @Step("Submit login form")
    public void clickSignIn() {
        Wait.elementToBeClickable(driver, signInButton).click();
    }

    @Step("Check if SignIn button displayed")
    public boolean isSignInButtonDisplayed() {
        return Element.isElementClickable(driver, signInButton);
    }

    @Step("Check if failed message displayed")
    public boolean isAuthFailedMessageDisplayed() {
        return Element.isElementClickable(driver, authFailedMessage);
    }

    @Step("Check if signed out message displayed")
    public boolean isSignedOutMessageDisplayed() {
        return Element.isElementClickable(driver, signedOutMessage);
    }

    @Step("get displayed failed message")
    public String getdisplayedAuthFailedMessage() throws Exception {
        return action.getText(authFailedMessage);
    }

}
