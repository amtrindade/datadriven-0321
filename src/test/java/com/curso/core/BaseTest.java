package com.curso.core;

import static com.curso.core.DriverFactory.getDriver;

import java.io.File;
import java.io.IOException;
import java.time.ZonedDateTime;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

public abstract class BaseTest {

	
	@AfterMethod
	public void tearDown(ITestResult testResult) throws IOException {
		ZonedDateTime zdt = ZonedDateTime.now();
		
		File screenShot = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot, 
				new File("target"+ File.separator + testResult.getName() +"_"+new java.util.Date()+ ".jpg"));
		
		DriverFactory.quitDriver();
	}
}
