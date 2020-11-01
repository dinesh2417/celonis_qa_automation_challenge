# Celonis - Trailcloud application QA Automation Framework
Test automation framework for testing Celonis-TrailCloud app along with testcase codes and TestNG test suites

## Dependencies
Intellij IDEA 2020.1 (or later)<br/>
JDK 11 <br/>
Maven 3.6.3 (or later)<br/>
Allure 2.13.5 (or later)<br/>
Firefox 80.0 (or later)

## Framework
Hybrid framework with Page Object Model design  pattern

## Prerequisites
* JDK and Maven should be installed and path should be available in the environment variable
* Allure should be installed and path should be available in the environment variable
    * Allure binary is available under 'https://github.com/allure-framework/allure2/releases'
* Firefox should be installed and path should be updated in selenium-framework.properties
* Chrome driver will be handled by BrowserBinaryManager and no need of chromedriver.exe
 
## How to run
There are several ways to run the test:<br/>

**Through Command Promt:** <br/>
Open the folder in cmd where the POM file located and run `mvn clean install` or `mvn clean test` <br/>

For running the healthcheck suite: `mvn clean test -DsuiteXmlFile=src/test/resources/healthcheck.xml`<br/>
For running the smoketest suite: `mvn clean test -DsuiteXmlFile=src/test/resources/smoketest.xml`<br/>
For running the componenttest suite: `mvn clean test -DsuiteXmlFile=src/test/resources/componenttest.xml`<br/>
For running all the testcases in chrome browser : `mvn clean test -DsuiteXmlFile=src/test/resources/all-suite-chrome.xml`<br/>
For running all the testcases in firefox browser : `mvn clean test -DsuiteXmlFile=src/test/resources/all-suite-firefox.xml`<br/>
For running all the testcases in both the browser : `mvn clean test -DsuiteXmlFile=src/test/resources/all-suite.xml`<br/>

For running test along with surefire report: `mvn surefire-report:report`<br/>

**Through TestNG suite in IDE:** <br/>
Open as Maven project in Intellij and run the TestNG suite file under the directory <br/>
`\src\test\resources`<br/>

**Through direct test in IDE:** <br/>
Open as Maven project in Intellij and run the tests directly under the directory <br/>
`\src\test\java\com\trailcloud\testcode`

## Maven commands (should run under the project directory)
##### Build the project without running tests
`mvn clean install -DskipTests`

##### Run the tests
`mvn clean test`

##### Run the tests using TestNG.xml suite
`mvn clean test -DsuiteXmlFile=src/test/resources/all-suite-chrome.xml`

##### Generate the Allure report
`allure serve allure-results`

##### Generate the Surefire Html report
`mvn surefire-report:report`

## Reports
There are several reports available with different purposes:<br/>
##### allure & its location
* It contains complete details of the test including with parameter passed<br/>
* It also contains the test step along with failed screenshots<br/>
* Available only when running tests via testng.xml file, not for the direct test run <br/>
* result json files available under : `target\allure-results`
* for Html report, should run the cmd in target folder : `allure serve allure-results`
##### emailable-report.html & its location
* It contains complete details of the test including with parameter passed<br/>
* available under : `\target\surefire-reports`
##### ExtentReport.html & its location
* It contains necessary details of the test with nice gui and pie charts<br/>
* available under (if run via Command Line Prompt): `\target\surefire-reports`<br/>
                  (if run via TestNG.xml and direct test): `\test-output`
##### Testng.Html report & location
* It contains necessary details of the test<br/>
* available under : `\test-output`
##### Surefire Html report & location
* It contains basic info of the test<br/>
* available under : `\target\site`

## Screenshots
Failed test screenshots are available under `\screenshot` folder

## Maintainers
| Dinesh | dinesh2417@gamil.com |
| --- | --- |

