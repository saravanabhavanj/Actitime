package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import base.TestBase;

public class LoginPage extends TestBase {

	@FindBy(name = "username")
	WebElement username;

	@FindBy(name = "pwd")
	WebElement password;

	@FindBy(id = "loginButton")
	WebElement loginBttn;

	@FindBy(xpath = "//div[@class='atLogoImg']")
	WebElement actitimeLogo;

	public LoginPage() {
		PageFactory.initElements(driver, this);
//		Reporter.log("Intialized all the elements in Login Page ", true);
	}

	public String validatePageTitle() {
		return driver.getTitle();
	}

	public boolean validateActiTimeLogo() {
		return actitimeLogo.isDisplayed();
	}

	public String validateLogin(String username, String password, String user) {
		String timeTrackPgTitle = null;
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
//			System.out.println("un-" + username + "pwd-" + password + "usr-" + user);
//			System.out.println("loginpage validateLogin method called");
			this.username.sendKeys(username);
			this.password.sendKeys(password);
			loginBttn.click();
			WebDriverWait wait = new WebDriverWait(driver, 10);
			if (user.equalsIgnoreCase("non admin")) {
				WebElement welcomeFrame = null;
				try {

					welcomeFrame = driver.findElement(By.tagName("iframe"));
				} catch (NoSuchElementException e) {
					System.out.println("welcome frame is not available");
				}
				if (welcomeFrame != null) {
					try {
						driver.switchTo().frame("welcomeScreenFrame");
						// JavascriptExecutor js = (JavascriptExecutor) driver;
						// wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("welcomeScreenFrame")));

						driver.switchTo().parentFrame();
						Thread.sleep(4000);
						driver.findElement(
								By.xpath("//div[@id='welcomeScreenLightbox_content']//div[@class='close-button']"))
								.click();
						Thread.sleep(10000);
						System.out.println("switched back");
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
			try {
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo_aT")));
				// Thread.sleep(4000);
				timeTrackPgTitle = driver.getTitle();
//				System.out.println("timeTrackPage Title - " + timeTrackPgTitle);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return timeTrackPgTitle;
	}

	public String validateLogout(String username, String password, String user) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			validateLogin(username, password, user);
			Thread.sleep(5000);
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//td[@class='logoutCell preventPanelsHiding']/a[@id='logoutLink']")));
			WebElement logoutLink = driver
					.findElement(By.xpath("//td[@class='logoutCell preventPanelsHiding']/a[@id='logoutLink']"));
			logoutLink.click();
			Thread.sleep(5000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='atLogoImg']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver.getTitle();
	}
}