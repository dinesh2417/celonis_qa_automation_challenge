package com.trailcloud.pagefactory;

import com.trailcloud.core.util.Element;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class MyAccountPage extends BasePage {

    @FindBy(xpath = "//a[@data-testing-uid= 'userMenu-myAccount-link']")
    WebElement editProfileButton;
    @FindBy(xpath = "//button[@data-testing-uid= 'userMenu-logout-button']")
    WebElement signOutButton;
    @FindBy(xpath = "//button[@data-testing-uid= 'userMenu-avatar-button']")
    WebElement myAccountButton;

    public MyAccountPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Check if MyAccount button displayed")
    public boolean isMyAccountButtonDisplayed() {
        return Element.isDisplayed(myAccountButton);
    }

    @Step("Click MyAccount button")
    public void clickMyAccount() {
        myAccountButton.click();
    }

    @Step("Check if MyAccount button isClickable")
    public boolean isMyAccountButtonIsClickable() {
        return Element.isElementClickable(driver, myAccountButton);
    }

    @Step("Check if EditProfile button displayed")
    public boolean isEditProfileButtonDisplayed() {
        return Element.isElementClickable(driver, editProfileButton);
    }

    @Step("Check if Signout button displayed")
    public boolean isSignOutButtonDisplayed() {

        return Element.isElementClickable(driver, signOutButton);
    }

    @Step("Click signout button")
    public void clickSignOut() {
        signOutButton.click();
    }

    @Step("Click my account and signout")
    public void clickMyAccountAndSignOut() {
        if (isMyAccountButtonDisplayed()) {
            myAccountButton.click();
            signOutButton.click();
        }
    }
}
