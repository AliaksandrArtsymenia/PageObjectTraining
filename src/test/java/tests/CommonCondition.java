package tests;

import driver.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import pages.MainPage;
import utils.ListenerUI;

@Listeners(ListenerUI.class)
public class CommonCondition {
    protected MainPage mainPage;

    @BeforeMethod
    public void setUpDriver() {
        WebDriverSingleton.setDriver();
        mainPage = new MainPage().openPage();
    }

    @AfterMethod
    public void closeDriver() {
        WebDriverSingleton.closeDriver();
    }
}
