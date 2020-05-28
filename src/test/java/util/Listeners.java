package util;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.Status;

//import com.relevantcodes.extentreports.LogStatus;
import base.TestBase;

public class Listeners extends TestBase implements ITestListener {

	public void onStart(ITestContext result) {
		String methodName = result.getName().trim();
		Reporter.log(methodName + "- started execution", true);
		extentReport();
		
//		logger = extent.createTest(methodName+" -started execution");
//		logger.log(Status.INFO, methodName+"- started execution");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult result) {
		try {
			String methodName = result.getName().trim();
			Reporter.log(methodName + "- failed execution", true);
//			TestBase.logger.log(LogStatus.FAIL, methodName + "- failed execution");
			logger = extent.createTest(methodName);
			logger.log(Status.FAIL, methodName+"- failed execution");
			String qualifiedClassName = result.getInstanceName();
			System.out.println("qualifiedClassName - " + qualifiedClassName);
			String[] name = qualifiedClassName.split("\\.");
			int count = 0;
			for (String names : name) {
				count++;
			}
			String className = name[count - 1];
			TakeScreenshot.takeScreenshotAtEndOfTest(className, methodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getName().trim();
//		TestBase.logger.log(LogStatus.SKIP, methodName + "- skipped execution");
		logger = extent.createTest(methodName);
		logger.log(Status.SKIP, methodName+"- skipped execution");
		Reporter.log(methodName + " - skipped execution", true);
	}

	public void onTestStart(ITestResult result) {
		try {
			String methodName = result.getName().trim();
			Reporter.log(methodName + " - started execution", true);
//			TestBase.logger = TestBase.extent.startTest(methodName + " - started execution");
//			logger = extent.createTest(methodName);
//			logger.log(Status.INFO, methodName+"- started execution");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onTestSuccess(ITestResult result) {
		try {
			String methodName = result.getName().trim();
			Reporter.log(methodName + " - executed successfully", true);
//			TestBase.logger.log(LogStatus.PASS, methodName + " - executed successfully");
			logger = extent.createTest(methodName);
			logger.log(Status.PASS, methodName+"- passed execution");
			String qualifiedClassName = result.getInstanceName();
//			System.out.println("qualifiedClassName - " + qualifiedClassName);
			String[] name = qualifiedClassName.split("\\.");
			int count = 0;
			for (String names : name) {
				count++;
			}
			String className = name[count - 1];
			TakeScreenshot.takeScreenshotAtEndOfTest(className, methodName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onFinish(ITestContext result) {
		try {
			String methodName = result.getName().trim();
//			Reporter.log(methodName + "- finished execution", true);
//			TestBase.logger.log(LogStatus.PASS, methodName + " - completed execution");
//			TestBase.extent.flush();
			extent.flush();
			Thread.sleep(3000);
//			TestBase.extent.endTest(TestBase.logger);
//			TestBase.extent.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
