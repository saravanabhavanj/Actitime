package util;

import java.io.File;

import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentReports;
import base.TestBase;

public class ExtentReport {
	
	public void report() {
		try{
			String currentDir = System.getProperty("user.dir");
			TestBase.extent = new ExtentReports(currentDir+"/ExtentReport/ActitimeExtentReport.html",true);
			TestBase.extent.loadConfig(new File("/workspace/actitime/src/test/java/config/extent-config.xml"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}