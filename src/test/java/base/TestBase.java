package base;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import util.ExtentReport;

 public class TestBase {
	public static WebDriver driver;
	public static Properties property;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
//	public TestBase(){
//		try{
//			Reporter.log("TestBase constructor invoked",true);
//			extentReport();
//			initializePropertyFile();
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	@BeforeSuite
	public void initializePropertyFile(){
		try{
			property = new Properties();
			FileInputStream fileInput = new FileInputStream(
					"D:/workspace/actitime/src/test/java/config/config.properties");
			property.load(fileInput);
			Reporter.log("Property file loaded successfully",true);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	@BeforeSuite
	public void extentReport(){
//		ExtentReport extReport = new ExtentReport();
//		extReport.report();
		String currentDir = System.getProperty("user.dir");
		TestBase.extent = new ExtentReports(currentDir+"/ExtentReport/ActitimeExtentReport.html",true);
		TestBase.extent.loadConfig(new File("/workspace/actitime/src/test/java/config/extent-config.xml"));
		Reporter.log("Extent report configured successfully",true);
	}
	
	public void initializeBrowser(String browserName){
		try{
			if(browserName.equals("chrome")){
				Reporter.log("Initializing chrome browser",true);
				System.setProperty("webdriver.chrome.driver", 
						"D:/workspace/actitime/src/test/java/resource/chromedriver.exe");
				driver = new ChromeDriver();
				Reporter.log("Chrome browser launched successfully",true);
			}
			else if(browserName.equals("firefox")){
				Reporter.log("Initializing firefox browser",true);
				System.setProperty("webdriver.gecko.driver", 
						"D:/workspace/actitime/src/test/java/resource/geckodriver.exe");
				DesiredCapabilities capabilities = DesiredCapabilities.firefox();
				capabilities.setCapability("marionette", true);
				driver = new FirefoxDriver();
				Reporter.log("Firefox browser launched successfully",true);
			}else{
				Reporter.log("Unable to launch browser",true);
				Assert.assertTrue(false);
			}
		}catch(Exception e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(property.getProperty("url"));
	}
}
