package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FootballLinkTest extends CommonCondition{
    @Test
    public void verifyIfFootballIsInFavoriteList() {
        Assert.assertFalse(mainPage.openMenu().waitElement(mainPage.getFootballLink()).isDisplayed());
    }
}
