package testcase;

import org.testng.annotations.Test;
import base.TestBase;
import pages.LoginPage;
import util.readExcel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

public class LoginPageTest extends TestBase {
	LoginPage loginPage;
	readExcel rdExcel;
	String userName;
	String password;

	@BeforeMethod(groups = { "Functional", "Sanity", "admin", "non admin" }, enabled = true)
	@Parameters({ "browser", "user" })
	public void setUp(String browser, String user) {
		try {
			initializePropertyFile();
			rdExcel = new readExcel("Credentials");
			rdExcel.getHeaderText();
			initializeBrowser(browser);
			loginPage = new LoginPage();
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	public int getRowId(String user) {
		int rowId = 0;
		try {
			for (int i = 1; i < rdExcel.rowCount(); i++) {
				rowId = i;
				rdExcel.getRowData(rowId);
				System.out.println("entered into loop and user is - " + rdExcel.lhm.get("User"));
				if (rdExcel.lhm.get("User").equalsIgnoreCase(user)) {
					userName = rdExcel.lhm.get("UserName");
					password = rdExcel.lhm.get("Password");
					System.out.println("user name - " + rdExcel.lhm.get("UserName"));
					System.out.println("password - " + rdExcel.lhm.get("Password"));
					// break;
				} else {
					System.out.println("user record didnt match");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("returned rowid -" + rowId);
		return rowId;
	}

	@Test(priority = 1, groups = { "Functional", "Sanity", "admin", "non admin" }, enabled = true)
	public void validatePageTitleTest() {
		try {
			String loginPageTitle = loginPage.validatePageTitle();
			Assert.assertEquals(loginPageTitle, "actiTIME - Login");
			System.out.println("loginPageTitle - " + loginPageTitle);
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Test(priority = 2, groups = { "Functional", "Sanity", "admin", "non admin" }, 
			enabled = true, dependsOnMethods = { "validatePageTitleTest" })
	public void validateActiTimeLogoTest() {
		try {
			boolean flag = loginPage.validateActiTimeLogo();
			Assert.assertTrue(flag);
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 3, groups = { "Functional", "Sanity", "admin", "non admin" }, 
			enabled = true, dependsOnMethods = { "validatePageTitleTest" }) //
	public void validateLoginTest(String user) {
		try {
			getRowId(user);
			// String timeTrackPgTitle =
			// loginPage.validateLogin(property.getProperty("adminUser"),
			// property.getProperty("adminPassword"));
			String timeTrackPgTitle = loginPage.validateLogin(userName, password, user);
			Assert.assertEquals("actiTIME - Enter Time-Track", timeTrackPgTitle);
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@Parameters("user")
	@Test(priority = 4, groups = { "Functional", "Sanity", "admin", "non admin" }, 
			enabled = true, dependsOnMethods = { "validatePageTitleTest" })
	public void validateLogout(String user) {
		try {
			// String pgTitle =
			// loginPage.validateLogout(property.getProperty("adminUser"),
			// property.getProperty("adminPassword"));
			String pgTitle = loginPage.validateLogout(userName, password, user);
			System.out.println("page title - " + pgTitle);
			Assert.assertEquals(pgTitle, "actiTIME - Login");
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}

	@AfterMethod(groups = { "Functional", "Sanity", "admin", "non admin" })
	public void quit() {
		try {
			System.out.println("calling quit method");
			driver.quit();
		} catch (Exception e) {
			Assert.assertTrue(false);
			e.printStackTrace();
		}
	}
}
