package TestBase;

import Utilities.ExcelReader;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

//    WebDriver, DONE
//     Properties, DONE
//     Logs : Log4J jar, .log file(Application.log & Selenium.log), Log4.properties, Logger
//     ExtentReports,
//     isElementPresent Done
//     DB ,
//     Excel,
//     Mail,
//     ReportNG,DONE
//     Jenkins

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    public static Logger log= Logger.getLogger("devpinoyLogger");
    public static ExcelReader excel =new ExcelReader(System.getProperty("user.dir") + "//src//test//resources//excel//TestData.xlsx");


    //before suite
    @BeforeSuite
    public void setup() throws IOException {
        if (driver == null) {           // Read Config Property
            try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//Config.properties")) {
                config.load(fis);
                log.debug("Config file is loaded");
            }
            //  Read the OR  property file
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//OR.properties");
                {
                    OR.load(fis);
                    log.debug("OR file is loaded");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (config.getProperty("browser").equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\src\\test\\src\\geckodriver.exe");
            driver = new FirefoxDriver();

        } else if (config.getProperty("browser").equals("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\test\\src\\chromedriver.exe");
            driver = new ChromeDriver();
            log.debug("Chrome is launched");
        } else if (config.getProperty("browser").equals("IE")) {
            System.setProperty("webdriver.IE.driver", System.getProperty("user.dir") + "\\src\\test\\src\\IEDriverServer.exe");

            driver = new InternetExplorerDriver();
        }
        //login to URL
        driver.get(config.getProperty("LoginUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait")), TimeUnit.SECONDS);


    }
    // Check isElementPresent
    public boolean isElementPresent(By by)
    {
      try
      {
          driver.findElement(by);
          return true;
      }
      catch(NoSuchElementException e)
      {
       return  false;
      }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        log.debug("Test Execution is completed");
    }
}