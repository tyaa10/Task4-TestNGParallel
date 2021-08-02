package util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverSingletone {

    private static WebDriverSingletone instance;

    private final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

    private WebDriverSingletone() {}

    public static WebDriverSingletone getInstance() {
        if (instance == null) {
            instance = new WebDriverSingletone();
        }
        return instance;
    }

    public WebDriver getDriver() {
        if (webDriverThreadLocal.get() != null) {
            return webDriverThreadLocal.get();
        }

        WebDriver driver;
        String driverName = new PropertiesReader().getDriverName();
        String driverLocation = new PropertiesReader().getDriverLocation();
        System.setProperty(driverName, driverLocation);
        driver = new ChromeDriver() {
            {
                manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
            }
        };
        webDriverThreadLocal.set(driver);
        return webDriverThreadLocal.get();
    }

    public void closeDriver() {
        try {
            webDriverThreadLocal.get().quit();
        }
        catch (Exception e) {
            System.err.println("ERROR:Can not close Webdriver!");
        } finally {
            webDriverThreadLocal.remove();
        }
    }

}