package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.CommonMethodes;

public class ConfirmationPage extends CommonMethodes {
    private WebDriver driver;

    //==============methodes=======================
    public ConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    //===========locators=========
    public By thnaksmesaage = By.cssSelector(".hero-primary");

    //=============methodes==================
    public String getConfirmMessage()
    {
        waitForTheElemnetToAppear(thnaksmesaage);
        return driver.findElement(thnaksmesaage).getText();
    }

}
