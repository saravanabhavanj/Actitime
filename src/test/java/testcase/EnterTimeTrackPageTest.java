package testcase;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import base.TestBase;
import pages.EnterTimeTrackPage;
import pages.LoginPage;
import util.readExcel;

public class EnterTimeTrackPageTest extends TestBase {

	EnterTimeTrackPage timeTrackPage = new EnterTimeTrackPage();
	LoginPage loginPage;
	readExcel rdProfileExcel;
	readExcel rdCredentialExcel;
	String userName;
	String password;
	String userNameLink;
	String firstName;
	String lastName;
	String email;
	String credentialSheet = "Credentials";
	String profileSheet = "ProfileData";

	@BeforeMethod(groups = { "Functional", "Sanity", "non admin", "admin" })
	@Parameters({ "browser", "user" })
	public void setUp(String browser, String user) {
		try {
			initializePropertyFile();
			initializeBrowser(browser);
			// loginPage.validateLogin(property.getProperty("adminUser"),
			// property.getProperty("adminPassword"), user);
			rdProfileExcel = new readExcel("ProfileData");
			rdProfileExcel.getHeaderText();
			rdCredentialExcel = new readExcel("Credentials");
			rdCredentialExcel.getHeaderText();
			loginPage = new LoginPage();
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	public int getRowId(String user) {
		int rowId = 0;
		try {
			for (int i = 1; i < rdProfileExcel.rowCount(); i++) {
				rowId = i;
				rdCredentialExcel.getRowData(rowId);
				System.out.println("entered into loop and user is - " + rdProfileExcel.lhm.get("User"));
				if (rdCredentialExcel.lhm.get("User").equalsIgnoreCase(user)) {
					userName = rdCredentialExcel.lhm.get("UserName");
					password = rdCredentialExcel.lhm.get("Password");
					System.out.println("user name - " + rdCredentialExcel.lhm.get("UserName"));
					System.out.println("password - " + rdCredentialExcel.lhm.get("Password"));
				} else {
					System.out.println("Error while reading credentials excel");
				}
				rdProfileExcel.getRowData(rowId);
				if (rdProfileExcel.lhm.get("User").equalsIgnoreCase(user)) {
					// userName = rdProfileExcel.lhm.get("UserName"); -- not
					// conf bcoz both names r same
					firstName = rdProfileExcel.lhm.get("FirstName");
					lastName = rdProfileExcel.lhm.get("LastName");
					email = rdProfileExcel.lhm.get("Email");
					userNameLink = firstName + " "+lastName;
					System.out.println("fn - "+firstName+" ln- "+lastName+" email- "+email+" un - "+userName);
				} else {
					System.out.println("Error while reading profiledata excel");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("returned rowid -" + rowId);
		return rowId;
	}

	@Parameters("user")
	@Test(priority = 1, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" })
	public void validateUserLinkTextTest(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("returned string - "+timeTrackPage.validateUserLinkText());
			Assert.assertEquals(timeTrackPage.validateUserLinkText(), userNameLink);
		} catch (Exception e) {
			System.out.println("validateUserLinkTextTest catch block");
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 2, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" } )
//			,dependsOnMethods = { "validateUserLinkTextTest" })
	public void validateUserLinkTest(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("no issue in executing login method");
			Assert.assertEquals(timeTrackPage.validateUserLink(), "My Profile");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 3, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" }) 
//			,dependsOnMethods = { "validateUserLinkTest" })
	public void validateFirstNameText(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("returned first name - " + firstName);
			Assert.assertEquals(timeTrackPage.validateFirstName(), firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 4, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" }) 
//			,dependsOnMethods = { "validateUserLinkTest" })
	public void validateLastNameText(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("returned last name - " + readExcel.lhm.get("LastName"));
			Assert.assertEquals(timeTrackPage.validateLastName(), lastName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 5, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" }) 
//			,dependsOnMethods = { "validateUserLinkTest" })
	public void validateEmailText(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("returned email name - " + email);
			Assert.assertEquals(timeTrackPage.validateEmail(), email);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 6, enabled = true, groups = { "Functional", "Sanity", "non admin", "admin" }) 
//			,dependsOnMethods = { "validateUserLinkTest" })
	public void validateUserNameText(String user) {
		try {
			getRowId(user);
			loginPage.validateLogin(userName, password, user);
			System.out.println("returned user name - " + userName);
			Assert.assertEquals(timeTrackPage.validateUserName(), userName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@AfterMethod(groups = { "Functional", "Sanity", "non admin", "admin" })
	public void quit() {
		try {
			driver.quit();
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
