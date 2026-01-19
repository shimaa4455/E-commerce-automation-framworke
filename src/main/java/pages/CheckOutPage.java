package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utilities.CommonMethodes;

public class CheckOutPage extends CommonMethodes {
    private WebDriver driver;
    //=============locators==============
    public By countryFiled = By.xpath("//input[@placeholder='Select Country']");
    public By dropDown = By.cssSelector(".ta-results");
    public String CountryInTheDropDown ="//input[@placeholder='Select Country']/following-sibling::section//button/span[normalize-space(text())='%s']";
    public By submitButton=By.cssSelector(".action__submit");

    //==============methodes=======================
    public CheckOutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void chooseCountry(String countryName)
    {
        Actions actions = new Actions(driver);
        actions.sendKeys(driver.findElement(countryFiled),countryName).build().perform();
        waitForTheElemnetToAppear(dropDown);
        String xpath = String.format(CountryInTheDropDown, countryName);
        driver.findElement(By.xpath(xpath)).click();

    }
    public ConfirmationPage submitOrder()
    {
        scrollDown(submitButton);
        driver.findElement(submitButton).click();
        return new ConfirmationPage(driver);
    }
}
