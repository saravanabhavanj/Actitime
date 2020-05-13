package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.TestBase;

public class EnterTimeTrackPage extends TestBase  {

	WebElement userLink;
	String userLinkText = null;

	public String validateUserLinkText() {
		try {
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logo_aT")));
			Thread.sleep(3000);
			userLink = driver.findElement(By.xpath("//a[@class='userProfileLink username']"));
			userLinkText = userLink.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userLinkText;
	}

	public String validateUserLink() {
		WebElement profileTitle;
		String profileTitleTxt = null;
		try {
			validateUserLinkText();
			String parentWindow = driver.getWindowHandle();
			userLink.click();
			Thread.sleep(4000);
			WebDriverWait wait = new WebDriverWait(driver,10);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userProfilePopup_content")));
			String popUpWindow = driver.getWindowHandle();
			driver.switchTo().window(popUpWindow);
			profileTitle = driver.findElement(By.xpath("//span[@class='pageTitleWithSharedSettingsImage']"));
			profileTitleTxt = profileTitle.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profileTitleTxt;
	}

	public String validateFirstName() {
		WebElement firstName;
		String firstNameVal = null;
		try {
			validateUserLink();
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			firstName = driver.findElement(By.name("firstName"));
			firstNameVal = firstName.getAttribute("value");
			System.out.println("firstNameValue - "+firstNameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return firstNameVal;
	}
	
	public String validateLastName() {
		WebElement lastName;
		String lastNameVal = null;
		try {
			validateUserLink();
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			lastName = driver.findElement(By.name("lastName"));
			lastNameVal = lastName.getAttribute("value");
			System.out.println("firstNameValue - "+lastNameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lastNameVal;
	}
	
	public String validateEmail() {
		WebElement email;
		String emailVal = null;
		try {
			validateUserLink();
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			email = driver.findElement(By.name("email"));
			emailVal = email.getAttribute("value");
			System.out.println("firstNameValue - "+emailVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailVal;
	}
	
	public String validateUserName() {
		WebElement userName;
		String userNameVal = null;
		try {
			validateUserLink();
//			JavascriptExecutor js = (JavascriptExecutor) driver;
			userName = driver.findElement(By.name("username"));
			userNameVal = userName.getAttribute("value");
			System.out.println("firstNameValue - "+userNameVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userNameVal;
	}
}
