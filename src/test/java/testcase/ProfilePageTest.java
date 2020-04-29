package testcase;

import org.testng.annotations.Test;

import base.TestBase;

public class ProfilePageTest extends TestBase{
	@Test(groups={"Functional","Sanity"})
	public void display(){
		System.out.println("profile page displayed");
	}
}
