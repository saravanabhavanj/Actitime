package util;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import base.TestBase;

public class TakeScreenshot extends TestBase {
	public static void takeScreenshotAtEndOfTest(String className, String methodName) throws IOException {
		try{
			DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy hh-mm-ss");
			Date date = new Date();
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String currentDir = System.getProperty("user.dir");
			System.out.println("current dir - "+currentDir);
			FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/"+className+"/"+methodName+"_"+ 
					dateFormat.format(date) + ".png"));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
