package util;

import java.io.IOException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import base.TestBase;

public class Listeners extends TestBase implements ITestListener{

	public void onFinish(ITestContext arg0) {

	}

	public void onStart(ITestContext arg0) {
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		
	}

	public void onTestFailure(ITestResult result) {
		try{
			Reporter.log("onTestFailure called");
			String methodName = result.getName().trim();
			String qualifiedClassName = result.getInstanceName();
			System.out.println("qualifiedClassName - "+qualifiedClassName);
			String[] name = qualifiedClassName.split("\\.");
			int count = 0;
			for(String names:name){
				count++; 
			}
			String className = name[count-1];
			TakeScreenshot.takeScreenshotAtEndOfTest(className, methodName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult arg0) {

	}

	public void onTestStart(ITestResult arg0) {
		
	}

	public void onTestSuccess(ITestResult arg0) {
		
	}

}
