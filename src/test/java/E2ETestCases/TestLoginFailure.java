package E2ETestCases;

import Test_Component.Browser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ProductsPages;

public class TestLoginFailure extends Browser {
    @Test
    public void testLoginFailure()
    {
        loginPage.login("shimaa@gmail.com","swaNy4455@");
        Assert.assertEquals(loginPage.getErrorMessage(),"Incorrect email or password.");

    }
}
