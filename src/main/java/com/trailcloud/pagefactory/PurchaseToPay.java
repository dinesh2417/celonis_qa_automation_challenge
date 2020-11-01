package com.trailcloud.pagefactory;

import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class PurchaseToPay extends BasePage {

    @FindBy(css = "a[class = 'ce-tile__link ng-star-inserted']")
    WebElement analysisLink;

    public PurchaseToPay(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Click Analysis link")
    public void clickAnalysisLink() {
        analysisLink.click();
    }


}
