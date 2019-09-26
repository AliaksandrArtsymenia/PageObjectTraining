package pages;

import driver.WebDriverSingleton;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class AbstractPage {
    protected WebDriver driver;
    private final static int WAIT_SECONDS = 10;
    protected Logger log;

    public AbstractPage() {
        driver = WebDriverSingleton.getDriver();
        log = LogManager.getRootLogger();
        PageFactory.initElements(driver, this);
    }

    public abstract AbstractPage openPage();

    public WebElement waitElement(WebElement element) {
        return new WebDriverWait(driver, WAIT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }
}
