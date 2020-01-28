package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
	}
	
	public String validatePageTitle(){
		return driver.getTitle();
	}
	
	public boolean validateActiTimeLogo(){
		return actitimeLogo.isDisplayed();
	}
	
	public EnterTimeTrackPage validateLogin(String username, String password){
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		loginBttn.click();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new EnterTimeTrackPage();
		
	}
}
