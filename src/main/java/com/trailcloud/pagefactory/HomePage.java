package com.trailcloud.pagefactory;

import com.trailcloud.core.util.Element;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

@Slf4j
public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("check if '{0}' workspace is available ")
    public boolean isWorkspaceAvailable(String workspaceName) {
        String workspaceXpath = "//div[@class= 'ce-item-group__label']/span[contains(text(), '" + workspaceName + "')]";
        try {
            Element.isElementAvailable(driver, By.xpath(workspaceXpath), 2);
        } catch (Exception e) {
            log.error("Cannot find the element  " + element);
            e.printStackTrace();
        }
        return true;
    }

    @Step("clicking the workspace '{0}'")
    public void clickWorkspace(String workspaceName) {
        String workspaceXpath = "//div[@class= 'ce-item-group__label']/span[contains(text(), '" + workspaceName + "')]";
        try {
            Element.isElementAvailable(driver, By.xpath(workspaceXpath), 2);
            (Element.getElement(driver, By.xpath(workspaceXpath), 2)).click();
        } catch (Exception e) {
            log.error("Cannot find the element  " + element);
            e.printStackTrace();
        }
    }
}
