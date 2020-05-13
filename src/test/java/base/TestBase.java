package base;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import util.ExtentReport;

 public class TestBase {
	public static WebDriver driver;
	public static Properties property;
	public static ExtentReports extent;
	public static ExtentTest logger;
	
//	@BeforeSuite
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
	
//	@BeforeSuite
	public void extentReport(){
		DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh-mm-ss");
		Date date = new Date();
		String currentDir = System.getProperty("user.dir");
		TestBase.extent = new ExtentReports(currentDir+"/ExtentReport/ActitimeExtentReport_"+dateFormat.format(date)+".html",true);
		TestBase.extent.loadConfig(new File("/workspace/actitime/src/test/java/config/extent-config.xml"));
		Reporter.log("Extent report configured successfully",true);
	}
	
//	@BeforeMethod
	public void initializeBrowser(String browser){
		try{
			if(browser.equals("chrome")){
				Reporter.log("Initializing chrome browser",true);
				System.setProperty("webdriver.chrome.driver", 
						"D:/workspace/actitime/src/test/java/resource/chromedriver.exe");
				driver = new ChromeDriver();
				Reporter.log("Chrome browser launched successfully",true);
			}
			else if(browser.equals("firefox")){
				try{
				Reporter.log("Initializing firefox browser",true);
				System.setProperty("webdriver.gecko.driver", 
						"D:/workspace/actitime/src/test/java/resource/geckodriver.exe");
				FirefoxOptions options = new FirefoxOptions();
				options.setBinary(new FirefoxBinary(new File("C:/Program Files/Mozilla Firefox/firefox.exe")));
				options.setAcceptInsecureCerts(true);
				options.setCapability("acceptSslCerts", true);
				options.setCapability("specificationLevel", 1);
				options.isJavascriptEnabled();
				options.setUnhandledPromptBehaviour(UnexpectedAlertBehaviour.DISMISS_AND_NOTIFY);
				options.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
				options.setCapability(CapabilityType.UNHANDLED_PROMPT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);				
				driver = new FirefoxDriver(options);
				Reporter.log("Firefox browser launched successfully",true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
//			else if(browser.equals("ie")){
//				Reporter.log("Initializing edge browser",true);
//				System.setProperty("webdriver.edge.driver", 
//						"D:/workspace/actitime/src/test/java/resource/MicrosoftWebDriver.exe");
//				driver = new EdgeDriver();
//				System.out.println("edge launched");
//				Reporter.log("Edge browser launched successfully",true);
//			}
			else{
				Reporter.log("Unable to launch browser",true);
				Assert.assertTrue(false);
			}
		}catch(Exception e){
			e.printStackTrace();
			Assert.assertTrue(false);
		}
//		System.out.println("out of the loop");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(property.getProperty("url"));
	}
//	@AfterSuite
//	public void quit(){
//		TestBase.extent.endTest(TestBase.logger);
//		TestBase.extent.flush();
//	}
}
