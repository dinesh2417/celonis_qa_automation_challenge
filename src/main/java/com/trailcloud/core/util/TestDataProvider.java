package com.trailcloud.core.util;

import org.testng.annotations.DataProvider;

/**
 * Class to pass Test data to test methods
 */
public class TestDataProvider {

    public static TestDataProvider getInstance() {
        return new TestDataProvider();
    }

    @DataProvider(name = "checkoutTestInput")
    public Object[][] getCheckoutTestInput() {
        return GetData.getData("checkoutTestInput", "validCheckoutTestInput");
    }

    @DataProvider(name = "loginTestVaildInput")
    public Object[][] getLoginTestVaildInput() {
        return GetData.getData("loginTestInput", "validLoginTestInput");
    }

    @DataProvider(name = "loginTestInvalidInput")
    public Object[][] getLoginTestInvaildInput() {
        return GetData.getData("loginTestInput", "invalidLoginTestInput");
    }

    @DataProvider(name = "signUpTestInput")
    public Object[][] getSignUpTestInput() {
        return GetData.getData("signUpTestInput", "validSignUpTestInput");
    }

}
