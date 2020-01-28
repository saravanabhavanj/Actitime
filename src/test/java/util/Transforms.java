package util;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;

public class Transforms implements IAnnotationTransformer{
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, 
			Method testMethod){
		try{
			annotation.setRetryAnalyzer(RetryAnalyzer.class);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
