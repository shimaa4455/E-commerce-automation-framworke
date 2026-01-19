package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.CommonMethodes;

public class LoginPage extends CommonMethodes {

    WebDriver driver;
    public LoginPage(WebDriver driver) {
        //super is key word inhert the driver from child to parent
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    //=========Locators============
    @FindBy(id="userEmail")
    WebElement userEmail;
    @FindBy(id="userPassword")
    WebElement userPassword;
    @FindBy(id="login")
    WebElement login;
    //========interactionOfPage==============
    public ProductsPages login(String email, String password) {
        userEmail.sendKeys(email);
        userPassword.sendKeys(password);
        login.click();
        return new ProductsPages(driver);
    }
    public void goTo()
    {
        driver.get("https://rahulshettyacademy.com/client");

    }


}
