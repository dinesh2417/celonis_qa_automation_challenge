package com.trailcloud.pagefactory;

import com.trailcloud.core.util.Element;
import io.qameta.allure.Step;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Slf4j
public class ProcessExplorer extends BasePage {

    @FindBy(xpath = "//div[contains(@class, 'pe-standalone__control--connections')]//div[@class='slider__track--flexible']")
    WebElement activitiesSectionSlider;

    @FindBy(xpath = "//div[@class='center-block color-brand-primary ce-percentage-circle ng-isolate-scope' and @curr-value = 'processPanelGlobal.getActivityValue()']")
    WebElement activitiesSectionChart;

    @FindBy(css = "div[curr-value= 'processPanelGlobal.getActivityValue()'] span[class= 'ng-binding ng-scope']")
    WebElement activitiesSectionChartPercentage;

    @FindBy(css = "button[title = 'Add next important activity to the chart']")
    WebElement activitiesSectionMoreButton;

    @FindBy(css = "button[title = 'Remove least important activity from the chart']")
    WebElement activitiesSectionLessButton;

    @FindBy(xpath = "//div[contains(@class, 'pe-standalone__control--activities')]//div[@class='slider__track--flexible']")
    WebElement connectionsSectionSlider;

    @FindBy(xpath = "//div[@class='center-block color-brand-primary ce-percentage-circle ng-isolate-scope' and @curr-value = 'processPanelGlobal.getFlowValue()']")
    WebElement connectionsSectionChart;

    @FindBy(css = "div[curr-value= 'processPanelGlobal.getFlowValue()'] span[class= 'ng-binding ng-scope']")
    WebElement connectionsSectionChartPercentage;

    @FindBy(css = "button[title = 'Add next important step to the chart']")
    WebElement connectionsSectionMoreButton;

    @FindBy(css = "button[title = 'Remove least important step from the chart']")
    WebElement connectionsSectionLessButton;

    @FindBy(css = "rect[class='click-capture']")
    WebElement canvas;

    @FindAll({@FindBy(css = "g[class='node']")})
    List<WebElement> canvasNodes;

    @FindAll({@FindBy(css = "g[class='edge']")})
    List<WebElement> canvasConnectionEdges;

    public ProcessExplorer(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Step("Check if Connections section slider is displayed")
    public boolean isConnectionsSectionSliderIsDisplayed() {
        return Element.isElementClickable(driver, connectionsSectionSlider);
    }

    @Step("Check if Connections section chart is displayed")
    public boolean isConnectionsSectionChartIsDisplayed() {
        return Element.isElementClickable(driver, connectionsSectionChart);
    }

    @Step("Check if Activities section slider is displayed")
    public boolean isActivitiesSectionSliderIsDisplayed() {
        return Element.isElementClickable(driver, activitiesSectionSlider);
    }

    @Step("Check if Activities section chart is displayed")
    public boolean isActivitiesSectionChartIsDisplayed() {
        return Element.isElementClickable(driver, activitiesSectionChart);
    }

    @Step("click canvas")
    public void clickCanvas() {
        canvas.click();
    }

    @Step("click Activities section slider")
    public void clickActivitiesSectionSlider() {
        js.executeScript("arguments[0].click();", activitiesSectionSlider);
    }

    @Step("click Connections section slider")
    public void clickConnectionsSectionSlider() {
        connectionsSectionSlider.click();
    }

    @Step("get Connections section chart percentage")
    public String getConnectionsSectionChartPercentage() throws Exception {
        return action.getText(connectionsSectionChartPercentage);
    }

    @Step("get Activities section chart percentage")
    public String getActivitiesSectionChartPercentage() throws Exception {
        return action.getText(activitiesSectionChartPercentage);
    }

    @Step("click Connections section More button")
    public void clickConnectionsSectionMoreButton(int numberOfTimes) {
        while (numberOfTimes > 0) {
            connectionsSectionMoreButton.click();
            numberOfTimes--;
        }
    }

    @Step("click Connections section Less button")
    public void clickConnectionsSectionLessButton() {
        connectionsSectionLessButton.click();
    }

    @Step("click Activities section More button")
    public void clickActivitiesSectionMoreButton(int numberOfTimes) {
        while (numberOfTimes > 0) {
            activitiesSectionMoreButton.click();
            numberOfTimes--;
        }
    }

    @Step("click Activities section Less button")
    public void clickActivitiesSectionLessButton() {
        activitiesSectionLessButton.click();
    }

    @Step("click canvas nodes count")
    public int getCanvasNodesCount() {
        return canvasNodes.size();
    }

    @Step("click canvas connection edges count")
    public int getCanvasConnectionEdgeCount() {
        return canvasConnectionEdges.size();
    }
}
