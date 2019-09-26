package tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FootballPageIsOpenedTest extends CommonCondition {
    @Test(dataProvider = "data")
    public void verifyIfFootballPageIsOpened(String content, String title) {
        WebElement element = mainPage.openMenu().goToFootballPage().waitForOpeningPageFootball();
        Assert.assertEquals(element.getClass().getName(), content + title);
    }

    @DataProvider(name = "data")
    private static Object[][] getDate() {
        return new Object[][]{
                {"WebElement", "title"}
        };
    }
}
