package com.functionalLibrary;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.testcases.BaseClass;
import io.netty.util.internal.ThreadLocalRandom;

public class FunctionalLibrary extends BaseClass{

	public void sendKeysMethod(WebDriver driver, By element, String value) {
		driver.findElement(element).sendKeys(value);
	}

	public void implicitWait(WebDriver driver) {

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	
	}

	public void explicitWait(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));

		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

	//To Scroll the Web Page
	public void scrollPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver; 
		js.executeScript("window.scrollBy(0,100)"); 
	}

	public String getValue(WebDriver driver, By element, String attValue) {
		String value = driver.findElement(element).getAttribute(attValue);
		return value;
	}

	//To Get Text from UI
	public String getText(WebDriver driver, By element) {
		String textValue = driver.findElement(element).getText();
		return textValue;
	}

	//To Click on the Element
	public  void clickonElement(WebDriver driver, By element) {
		driver.findElement(element).click();

	}

	//Verify Element is present on the Web Page
	public boolean veriyElementIsDisplayed(WebDriver driver, By webElement){
		boolean mandatoryField=false;
		try {
			mandatoryField= driver.findElement(webElement).isDisplayed();
		}
		catch(Exception e) {
			mandatoryField = false;
		}
		return mandatoryField;
	}

	//Check Element is present or not;
	public void isFieldMandatory(WebDriver driver, By elements, String fieldName) {
		boolean requiredText = veriyElementIsDisplayed(driver,elements);
		if(requiredText) {
			System.out.println("The "+fieldName+" is Required Field");
		}
		else {
			System.out.println("The "+fieldName+" is Not Required Field");
		}
	}

	//To Send Values to the Text Box
	public void sendValues(WebDriver driver, By element, String textValue) {
		driver.findElement(element).sendKeys(textValue);
	}

	//To Scroll the Slider Bar one time
	public void scrollHorizantal(WebDriver driver, By element) {
		WebElement scroll = driver.findElement(element);
		scroll.sendKeys(Keys.ARROW_RIGHT);
	}

	//To Generate Random Dates with in specified date
	public LocalDate datebetween(LocalDate startInclusive, LocalDate endExclusive) {
		long startEpochDay = startInclusive.toEpochDay();
		long endEpochDay = endExclusive.toEpochDay();
		long randomDay = ThreadLocalRandom
				.current()
				.nextLong(startEpochDay, endEpochDay);

		return LocalDate.ofEpochDay(randomDay);
	}

	public String randomDateGenerator() {
		//Generate Random Dates
		LocalDate start = LocalDate.of(1965, Month.JANUARY, 01);
		LocalDate end = LocalDate.of(2005, Month.JANUARY, 01);
		LocalDate randomDate = datebetween(start, end);
		String dateFormat = randomDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		System.out.println("The Date Format is :: "+dateFormat);

		return dateFormat;
	}

	//To convert String to List
	public List<String> convertStringToList(String value){
		String[] strSplit = value.split(",");
		List<String> listValue = new ArrayList<String>(Arrays.asList(strSplit));
		return listValue;
	}

	//To Click element using Javascript Executor
	public void clickElementusingJavascript(WebDriver driver,By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	//To click element using Action Class
	public void clickonElementUsingAction(WebDriver driver, By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		Actions at = new Actions(driver);
		at.moveToElement(driver.findElement(element)).click().perform();
	}

	//To Generate random text
	public String generateRandomText(String sReturnType, int length){
		final String rangeAlphaNumeric = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String rangeAlpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		final String rangeNumeric = "123456789";
		int rangeLength = 0;
		String temp="";
		StringBuilder randomText = new StringBuilder();
		SecureRandom secRnd = new SecureRandom ();             
		if (sReturnType.equalsIgnoreCase("Numeric")){
			temp = rangeNumeric;
		}
		else if(sReturnType.equalsIgnoreCase("Characters")){
			temp = rangeAlpha;
		}
		else if(sReturnType.equalsIgnoreCase("AlphaNumeric")){
			temp = rangeAlphaNumeric;
		}
		else{
			System.out.println("Enter a vaild Return Type to generate random text");
		}
		rangeLength = temp.length();
		for(int i=0;i<length;i++){
			randomText.append(temp.charAt(secRnd.nextInt(rangeLength)));         
		}
		return randomText.toString();
	}

	//To get Current Date
	public String generateCurrentDate() {
		LocalDate currentDate = LocalDate.now();
		String todayDate = currentDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		return todayDate;
	}

	//To Clear the field
	public void clearField(WebDriver driver, By element) {
		driver.findElement(element).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
	}

	//To Click on random options
	public String randomNumberClick(List<WebElement> listValue)
	{
		Random random = new Random();
		int randomNumber =random.nextInt(listValue.size());
		WebElement randomElement = listValue.get(randomNumber);
		randomElement.click();

		String selectedValue = randomElement.getText();
		return selectedValue;
	}


	public void scrollSlider(WebDriver driver, By element,int min, int max)
	{
		WebElement slider = driver.findElement(element);
		for (int i = min; i <= max; i++) {
			slider.sendKeys(Keys.ARROW_RIGHT);
		}

	}
	public int stringTOInt(String stringValue) {
		
		int value =Integer.parseInt(stringValue); 
		
		return value;
	}
	
	public String intToString(int value) {
		String strValue=String.valueOf(value);
		return strValue;
	}
	
	public int getRandomNumberWithinRange(int min, int max) {
	    return (int) ((Math.random() * (max - min)) + min);
	}
	
	//To Get elements from UI and Store in List
	public List<String> getElementInList(List<WebElement> elements) {
		
		// Iterate through the elements and retrieve the attribute value
		List<String> listValues = new ArrayList<String>();
		for (WebElement element : elements) {
			String attributeValue = element.getText();
			listValues.add(attributeValue);
		}
		
		return listValues;
	}
	
	
}

