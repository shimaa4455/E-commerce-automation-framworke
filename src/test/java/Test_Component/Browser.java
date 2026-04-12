package Test_Component;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.FileInputStream;
import java.util.Properties;

public class Browser {

    public WebDriver driver;
    public LoginPage loginPage;
    public WebDriver intialBrowser() throws Exception
    {
        Properties prop = new Properties();
        try (FileInputStream globalFile = new FileInputStream(System.getProperty("user.dir") + "//src//main//java//resourse//global.properties")) {
            prop.load(globalFile);
        }
        String browser = prop.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
        driver = new FirefoxDriver();
        }
        else if  (browser.equalsIgnoreCase("edge"))
        {
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        return driver;

    }
    @BeforeMethod
    public LoginPage lunchApplication() throws Exception {
        driver = intialBrowser();
        LoginPage loginpage = new LoginPage(driver);
        loginpage.goTo();
        return loginpage;
    }
    @AfterMethod
    public void quitDriver()
    {
        driver.quit();
    }

    }

