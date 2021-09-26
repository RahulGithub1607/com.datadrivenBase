package TestBase;

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

    //WebDriver, Properties,Logs,ExtentReports,DB ,Excel,Mail

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static FileInputStream fis;
    //before suite
    @BeforeSuite
    public void setup() throws IOException {
        if (driver == null) {           // Read Config Property
            try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//Config.properties")) {
                config.load(fis);
            }
            //  Read the OR  property file
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "//src//test//resources//OR.properties");
                {
                    OR.load(fis);
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
        } else if (config.getProperty("browser").equals("IE")) {
            System.setProperty("webdriver.IE.driver", System.getProperty("user.dir") + "\\src\\test\\src\\IEDriverServer.exe");

            driver = new InternetExplorerDriver();
        }
        //login to URL
        driver.get(config.getProperty("LoginUrl"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Long.parseLong(config.getProperty("implicit.wait")), TimeUnit.SECONDS);


    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}