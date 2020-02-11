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
	
	public String validateLogin(String username, String password){
	String timeTrackPgTitle = null;
	try{
		Thread.sleep(3000);
		this.username.sendKeys(username);
		this.password.sendKeys(password);
		Thread.sleep(3000);
			
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
