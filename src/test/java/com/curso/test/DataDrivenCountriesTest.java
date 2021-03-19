package com.curso.test;

import static com.curso.core.DriverFactory.getDriver;
import static org.testng.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.curso.core.BaseTest;
import com.curso.core.SpreadsheetData;

public class DataDrivenCountriesTest extends BaseTest {
	

	@BeforeMethod
	public void beforeMethod() {
		getDriver().get("https://en.wikipedia.org/");
	}
	
	@Test(dataProvider = "countries")
	public void testSearchCountries(String searchCountry, String expectedCountry) {
		WebElement textSearch = getDriver().findElement(By.name("search"));
		textSearch.sendKeys(searchCountry);
		
		WebElement btnSearch = getDriver().findElement(By.name("go"));
		btnSearch.click();
		
		WebElement title = getDriver().findElement(By.id("firstHeading"));
		
		assertEquals(title.getText(), expectedCountry, "País não confere!");
	}

	@DataProvider(name = "countries")
	public Object[][] dataProviderCountries() {
		return new Object[][] {
			{"India", "India" },
			{"Brasil", "Brasil" },
			{"Argentina", "Argentina"},
			{"Italy", "Italy"},
			{"Venezuela", "Venezuela"},
			{"United States", "United States"}
		};
	}
	
	@Test(dataProvider = "countriesExcel")
	public void testSearchCountriesExcel(String pesquisa, String esperado) {
		WebElement textSearch = getDriver().findElement(By.name("search"));
		textSearch.sendKeys(pesquisa);
		
		WebElement btnSearch = getDriver().findElement(By.name("go"));
		btnSearch.click();
		
		WebElement title = getDriver().findElement(By.id("firstHeading"));
		
		assertEquals(title.getText(), esperado, "País não confere!");
	}
	
	@DataProvider(name= "countriesExcel")
	public Object[][] dataProviderExcel() {
		Object[][] testData = SpreadsheetData
				.readExcelData("Paises", "src/test/resources/paises.xls", "Dados");
		return testData;
	}
}
