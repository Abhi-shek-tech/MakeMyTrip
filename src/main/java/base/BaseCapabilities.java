package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
public class BaseCapabilities {
        public static Properties prop;
        public static WebDriver driver;

        public static ExtentHtmlReporter htmlReporter;
        public static ExtentReports extent;
        public static ExtentTest test;
        public static ExtentTest childTest;


        // Initialize the driver
        public void driverInit() {

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");

            System.setProperty("webdriver.chrome.driver", "C:/kavitha_sw/drivers/chromedriver_win32/chromedriver.exe");
            driver = new ChromeDriver(options);

            driver.get(prop.getProperty("url"));
            Duration pageLoadTimeout = Duration.ofSeconds(Integer.parseInt(prop.getProperty("pageLoadTime")));
            Duration implicitlyWaitTimeout = Duration.ofSeconds(30);

            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout);
            driver.manage().timeouts().implicitlyWait(implicitlyWaitTimeout);

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();



        }

        // get the data from property file to 'prop' object.
        public void readPropertyFile() {
            try {

                FileInputStream propFile = new FileInputStream("C:\\Users\\LENOVO\\IdeaProjects\\MakeMyTrip\\src\\main\\java\\configuration\\test.properties");
                prop = new Properties();
                prop.load(propFile);
            } catch(IOException e) {
                e.printStackTrace();
            }
        }


        //Setup extend report v4
        public void setExtentReport() {

            htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/extentReports/MMTFunctionalTestReport.html");
            htmlReporter.config().setDocumentTitle("Makemytrip Automation Report"); // Title
            htmlReporter.config().setReportName("Functional Testing"); // Report Name
            htmlReporter.config().setTheme(Theme.DARK);

            extent = new ExtentReports();
            extent.attachReporter(htmlReporter);

            extent.setSystemInfo("Host name", "localhost");
            extent.setSystemInfo("Environemnt", "QA");
            extent.setSystemInfo("user", "Kavitha");
        }
    }
