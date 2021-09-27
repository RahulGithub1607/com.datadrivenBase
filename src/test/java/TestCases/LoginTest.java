package TestCases;

import TestBase.TestBase;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class LoginTest extends TestBase {
    @Test
    public void loginAsUser() throws InterruptedException {
        log.debug("Login to URL");
     driver.findElement(By.xpath(OR.getProperty("Login_button"))).click();
     Thread.sleep(3000);
    }

}
