package Test_Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.LoginPage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
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

//dataReader
    public List<HashMap< String,String >> getJsonDataToMap(String filePath) throws IOException {
        //loginData.JSON to string
        String jsonContent= FileUtils.readFileToString(new File(filePath), "UTF-8");

        //string to hashmap
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap< String,String >> data= mapper.readValue(jsonContent, new TypeReference< List <HashMap< String,String>>>() {
        });
        return data;
    }
    //take screenshot
    public String getScreenShot(String testCaseName) throws IOException
    {
        TakesScreenshot ts=(TakesScreenshot) getDriver();
        File source=ts.getScreenshotAs(OutputType.FILE);
        File file = new File (System.getProperty("user.dir")+"//Screenshots/"+testCaseName+".png");
        FileUtils.copyFile(source, file);
        return System.getProperty("user.dir")+"//Screenshots/"+testCaseName+".png";

    }

    @BeforeMethod(alwaysRun = true)
    public void lunchApplication() throws Exception {
        WebDriver localDriver = intialBrowser();
        // Page objects should be created inside each test, not stored here
        new LoginPage(localDriver).goTo();
    }

   /*@AfterMethod
    public void quitDriver() {
      getDriver().quit();
        driver.remove();
   }*/
}
