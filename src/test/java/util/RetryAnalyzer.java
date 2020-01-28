package util;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer{
	int counter = 1;
	int retryLimit = 3;

	public boolean retry(ITestResult result){
		try{
		if(counter < retryLimit){
			counter++;
			return true;
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
}
