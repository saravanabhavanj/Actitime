package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

import base.TestBase;

public class LoginPage extends TestBase{
	
	@FindBy(name = "username")
	WebElement username;
	
	@FindBy(name = "pwd")
	WebElement password;
	
	@FindBy(id = "loginButton")
	WebElement loginBttn;
	
	@FindBy(xpath = "//div[@class='atLogoImg']")
	WebElement actitimeLogo;
	
	public LoginPage(){
		PageFactory.initElements(driver, this);
		Reporter.log("Intialized all the elements in Login Page ",true);
	}
	
	public String validatePageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateActiTimeLogo(){
		return actitimeLogo.isDisplayed();
	}
	
	public String validateLogin(String username, String password){
		String timeTrackPgTitle = null;
		try{
			Thread.sleep(3000);
			this.username.sendKeys(username);
			this.password.sendKeys(password);
			Thread.sleep(3000);
			loginBttn.click();
			Thread.sleep(3000);
			timeTrackPgTitle = driver.getTitle();
			System.out.println("timeTrackPgTitle"+timeTrackPgTitle);
		}catch(Exception e){
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return timeTrackPgTitle;
	}
}
