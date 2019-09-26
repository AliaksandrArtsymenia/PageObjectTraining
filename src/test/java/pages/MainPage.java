package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage {
    private static final String BASE_URL = "https://www.eurosport.ru/";

    @FindBy(xpath = "//button[@class='hamburger']")
    private WebElement menuButton;

    @FindBy(xpath = "//*[contains(text(),'Популярные виды спорта')]/following::*[starts-with(text(),'Футбол')][1]/parent::*")
    private WebElement footballLink;

    public MainPage openMenu() {
        waitElement(menuButton).click();
        log.info("menu button clicked");
        return this;
    }

    public WebElement getFootballLink() {
        return footballLink;
    }

    public FootballPage goToFootballPage() {
        waitElement(footballLink).click();
        log.info(String.format("Button %s clicked",footballLink.getTagName()));
        return new FootballPage();
    }

    @Override
    public MainPage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }
}
