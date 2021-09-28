package TestCases;

import TestBase.TestBase;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void loginAsUser() throws InterruptedException {

        log.debug("Login to URL");
        driver.findElement(By.xpath(OR.getProperty("Login_button"))).click();

        Assert.assertTrue(isElementPresent(By.xpath(OR.getProperty("login_button"))),"Login is not successful");

        Thread.sleep(3000);
    }

}
