package driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverSingleton {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private WebDriverSingleton() {
    }

    public static void setDriver() {
        WebDriverManager.chromedriver().setup();
        driver.set(new ChromeDriver());
    }

    public static WebDriver getDriver() {
        driver.get().manage().window().maximize();
        return driver.get();
    }

    public static void closeDriver() {
        driver.get().quit();
        driver.remove();
    }
}
