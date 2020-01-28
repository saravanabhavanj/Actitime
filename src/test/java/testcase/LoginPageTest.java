package testcase;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import java.io.IOException;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import base.TestBase;
import pages.EnterTimeTrackPage;
import pages.LoginPage;

public class LoginPageTest extends TestBase{
	LoginPage loginPage;
	EnterTimeTrackPage enterTimeTrackPage;
	
	public LoginPageTest(){
		super();
	}

	@BeforeMethod
	public void setUp() throws InterruptedException{
		try{
			Reporter.log("@BeforeTest method - ",true);
			initialization();
			loginPage = new LoginPage();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
	@Test(priority = 1)
	public void validatePageTitleTest(){
		try{
			Reporter.log("@validatePageTitleTest method - ",true);
			String loginPageTitle = loginPage.validatePageTitle();
			Assert.assertEquals(loginPageTitle, "actiTIME - Login1");
			System.out.println("loginPageTitle - "+loginPageTitle);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(priority = 2)
	public void validateActiTimeLogoTest(){
		try{
			Reporter.log("validateActiTimeLogoTest method - ",true);
			boolean flag = loginPage.validateActiTimeLogo();
			Assert.assertTrue(flag);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(priority = 3)
	public void validateLoginTest(){
		try{
			Reporter.log("validateLoginTest method - ",true);
			enterTimeTrackPage = loginPage.validateLogin(property.getProperty("adminUser"), 
					property.getProperty("adminPassword"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterMethod
	public void quit(ITestResult result) throws InterruptedException, IOException{
		try{
			driver.quit();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
