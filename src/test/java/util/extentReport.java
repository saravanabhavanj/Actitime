package util;

import java.io.File;
import com.relevantcodes.extentreports.ExtentReports;
import base.TestBase;

class ExtentReport {
	
	public void report() {
		String currentDir = System.getProperty("user.dir");
		TestBase.extent = new ExtentReports(currentDir+"ExtentReport/MyExtentReport.html",true);
		TestBase.extent.loadConfig(new File("/actitime/src/test/java/config/extent-config.xml"));
	}
	
	public void flushReport() {
		TestBase.extent.flush();
		TestBase.extent.endTest(TestBase.test);
		TestBase.extent.close();
    }
}