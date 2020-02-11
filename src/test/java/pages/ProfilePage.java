package pages;

public class ProfilePage {
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
	
	
}
