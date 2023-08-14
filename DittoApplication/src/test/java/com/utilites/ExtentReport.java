package com.utilites;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.ExtentHtmlReporter; //deprecated in 4.1.x
//https://stackoverflow.com/questions/66422304/extenthtmlreporter-class-cant-be-imported-with-extentreports-5-0-6-version
import com.aventstack.extentreports.reporter.configuration.Theme;
public class ExtentReport {
	
	public WebDriver driver;
	public ExtentSparkReporter  sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	@BeforeTest
	public void startReport() {
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/"+repName);
		
		// Create an object of Extent Reports
		extentReport = new ExtentReports();  
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Host Name", "Functional Test Admin");
		extentReport.setSystemInfo("Environment", "Stag QA");
		extentReport.setSystemInfo("User Name", "Test Admin");
		sparkReporter.config().setDocumentTitle("Ditto Web Application");
		// Name of the report
		sparkReporter.config().setReportName("Test Automation Report for DWA");
		// Dark Theme
		sparkReporter.config().setTheme(Theme.STANDARD);
	}
	//This method is to capture the screenshot and return the path of the screenshot.
	public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		// after execution, you could see a folder "FailedTestsScreenshots" under src folder
		String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver","C://SDETADDA//chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.twitter.com/");
	}
	@Test
	public void verifyTitle() {
		extentTest = extentReport.createTest("To verify Twitter Title");
		Assert.assertEquals(driver.getTitle(),"Twitter");
	}
	@Test
	public void verifyLogo() {
		extentTest = extentReport.createTest("To verify Twitter Logo");
		boolean image = driver.findElement(By.xpath("//img[@id='facebooklogo']")).isDisplayed();
		extentTest.createNode("Image is Present");
		Assert.assertTrue(image);
		extentTest.createNode("Image is not Present");
		Assert.assertFalse(image);
	}
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){
			//MarkupHelper is used to display the output in different colors
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - Test Case Failed", ExtentColor.RED));
			String screenshotPath = getScreenShot(driver, result.getName());
			//To add it in the extent report
			extentTest.fail("Test Case Failed Snapshot is below " + extentTest.addScreenCaptureFromPath(screenshotPath));
		}
		else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " - Test Case Skipped", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" Test Case PASSED", ExtentColor.GREEN));
		}
		driver.quit();
	}

	@AfterTest
	public void endReport() {
		extentReport.flush();
	}
}