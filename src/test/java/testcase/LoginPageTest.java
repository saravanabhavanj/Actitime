package testcase;

import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;
import pages.LoginPage;
import util.ExtentReport;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
//	public LoginPageTest(){
//		super();
//		Reporter.log("Super class constructor called ",true);
//	}
	
	@BeforeMethod(groups={"Functional","Sanity"})
	@Parameters("browser")
	public void setUp(String browser) throws InterruptedException{
		try{
			initializeBrowser(browser);
			loginPage = new LoginPage();
		}catch(Exception e){
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
		
	@Test(priority = 1,groups = {"Sanity"})
	public void validatePageTitleTest(){
		try{
			TestBase.logger = TestBase.extent.startTest("Start validatePageTitleTest");
			Reporter.log("validatePageTitleTest method execution started",true);
			String loginPageTitle = loginPage.validatePageTitle();
			Assert.assertEquals(loginPageTitle, "actiTIME - Login");
			System.out.println("loginPageTitle - "+loginPageTitle);
			TestBase.logger.log(LogStatus.PASS,"validatePageTitleTest passed");
			Reporter.log("validatePageTitleTest method executed successfully",true);
		}catch(Exception e){
			Assert.assertTrue(false);
			Reporter.log("validatePageTitleTest method execution failed",true);
			TestBase.logger.log(LogStatus.FAIL,"validatePageTitleTest failed");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2,groups = {"Sanity"})
	public void validateActiTimeLogoTest(){
		try{
			TestBase.logger = TestBase.extent.startTest("Start validateActiTimeLogoTest");
			Reporter.log("validateActiTimeLogoTest method - ",true);
			boolean flag = loginPage.validateActiTimeLogo();
			Assert.assertTrue(flag);
			TestBase.logger.log(LogStatus.PASS,"validateActiTimeLogoTest passed");
		}catch(Exception e){
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL,"validateActiTimeLogoTest failed");
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3,groups = {"Functional"}, enabled = true)
	public void validateLoginTest(){
		try{
			TestBase.logger = TestBase.extent.startTest("Start validateLoginTest");
			Reporter.log("validateLoginTest method - ",true);
			String timeTrackPgTitle = loginPage.validateLogin(property.getProperty("adminUser"), 
					property.getProperty("adminPassword"));
			Assert.assertEquals("actiTIME - Enter Time-Track", timeTrackPgTitle);
			TestBase.logger.log(LogStatus.PASS,"validateLoginTest passed");
		}catch(Exception e){
			Assert.assertTrue(false);
			TestBase.logger.log(LogStatus.FAIL,"validateLoginTest failed");
			e.printStackTrace();
		}
	}
	
	@AfterMethod(groups = {"Functional","Sanity"})
	public void quit() throws InterruptedException, IOException{
		try{
			TestBase.extent.endTest(TestBase.logger);
			TestBase.extent.flush();
			driver.quit();
			//TestBase.extent.close();
		}catch(Exception e){
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
