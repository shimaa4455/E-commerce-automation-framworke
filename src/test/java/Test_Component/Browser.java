package Test_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

public class Browser {

    // ThreadLocal driver for parallel tests
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public WebDriver getDriver() {
        return driver.get();
    }

    public WebDriver intialBrowser() throws Exception {
        Properties prop = new Properties();
        try (FileInputStream globalFile = new FileInputStream(
                System.getProperty("user.dir") + "//src//main//java//resourse//global.properties")) {
            prop.load(globalFile);
        }
        String browser = prop.getProperty("browser");

        WebDriver localDriver;
        if (browser.equalsIgnoreCase("chrome")) {
            localDriver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            localDriver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            localDriver = new EdgeDriver();
        } else {
            throw new RuntimeException("Unsupported browser: " + browser);
        }

        localDriver.manage().window().maximize();
        localDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//hena b7ot kol driver fe thread lw7do
        driver.set(localDriver);
        return getDriver();
    }

    @BeforeMethod
    public void lunchApplication() throws Exception {
        WebDriver localDriver = intialBrowser();
        // Page objects should be created inside each test, not stored here
        new LoginPage(localDriver).goTo();
    }

   @AfterMethod
    public void quitDriver() {
      getDriver().quit();
        driver.remove();
   }
}
