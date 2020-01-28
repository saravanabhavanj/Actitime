package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class TestBase {
	public static WebDriver driver;
	public static Properties property;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	public TestBase(){
		try{
			property = new Properties();
			FileInputStream fileInput = new FileInputStream(
					"D:/workspace/actitime/src/test/java/config/config.properties");
			property.load(fileInput);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void initialization() throws InterruptedException{
		String browserName = property.getProperty("browser");
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", 
					"D:/workspace/actitime/src/test/java/resource/chromedriver.exe");
			driver = new ChromeDriver();
		}
		else if(browserName.equals("firefox")){
				System.setProperty("webdriver.gecko.driver", 
						"D:/workspace/actitime/src/test/java/resource/geckodriver.exe");
				driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(property.getProperty("url"));
	}
}
