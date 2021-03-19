package com.curso.core;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.curso.inter.BrowserInterface;

public class DriverFactory implements BrowserInterface{
	
	public static WebDriver driver = null;
	
	public static WebDriver getDriver() {
		
		if (driver == null) {
		
			String path = GlobalProperties.getProperty("webdriver.path");
			String browser = GlobalProperties.getProperty("webdriver.browser");

			if (browser.equals(CHROME)) {
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");
				driver = new ChromeDriver();				
			}
			
			if (browser.equals(CHROME_HEADLESS)) {
				System.setProperty("webdriver.chrome.driver", path + "chromedriver");
				
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1200x600");
				
				driver = new ChromeDriver(options);				
			}
			
			if (browser.equals(FIREFOX)) {
				System.setProperty("webdriver.gecko.driver", path + "geckodriver");
				driver = new FirefoxDriver();
			}
			
			if (browser.equals(FIREFOX_HEADLESS)) {
				System.setProperty("webdriver.gecko.driver", path + "geckodriver");
				
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--headless");
				options.addArguments("--window-size=1200x600");
				
				driver = new FirefoxDriver(options);
			}
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}
		
		return driver;		
	}
	
	public static void quitDriver() {
		if (driver != null) {
			driver.quit();
			driver = null;
		}
	}

}
