package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FootballPage extends AbstractPage{

    @FindBy(xpath = "(//*[contains(text(),'Футбол')])[2]")
    private WebElement footballId;

    public WebElement waitForOpeningPageFootball() {
        return waitElement(footballId);
    }

    public AbstractPage openPage() {
        return null;
    }
}
