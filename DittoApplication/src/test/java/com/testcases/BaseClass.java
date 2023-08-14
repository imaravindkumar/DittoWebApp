package com.testcases;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.utilites.ReadConfig;


public class BaseClass {

	ReadConfig readconfig = new ReadConfig();
	public String baseURL = readconfig.getApplicationURL();
	
	public static WebDriver driver;
	
	//Logger Object
	public static Logger log;
	
	//Extent Report Manager Objects
	public ExtentSparkReporter  sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;
	

	@Parameters("browser")
	@BeforeClass
	public void setup(String browser)
	{
		log = LogManager.getLogger(BaseClass.class);

		if(browser.equalsIgnoreCase("chrome")) {
			driver =  new ChromeDriver();
			
		}
		else if(browser.equalsIgnoreCase("edge")) {			
			driver =  new EdgeDriver();
		}

		else {
			System.out.println("No Driver Selected. Please specify the driver in the TestNG XML File.");
		}

		log.info(browser+" Driver Launch Success");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}

	
	@BeforeTest
	public void startReport() {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-"+timeStamp+".html";
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/"+repName);

		// Create an object of Extent Reports
		extentReport = new ExtentReports();  
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Environment", "Stag QA");
		extentReport.setSystemInfo("Testing URL ",baseURL );

		sparkReporter.config().setDocumentTitle("Ditto Web Application");
		// Name of the report
		sparkReporter.config().setReportName("Test Automation Report for DWA");
		// Dark Theme
		sparkReporter.config().setTheme(Theme.STANDARD);
	}
	
	
	@AfterTest
	public void endReport() {		
		extentReport.flush();
	}
	
	@AfterMethod
	public void getResult(ITestResult result) throws Exception{
		if(result.getStatus() == ITestResult.FAILURE){

			//MarkupHelper is used to display the output in different colors
			extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " :- TEST CASE FAILED", ExtentColor.RED)); //to add name in extent report
			
			//extentTest.log(Status.FAIL, MarkupHelper.createLabel(result.getThrowable() + " - TEST CASE FAILED", ExtentColor.RED)); //to add error/exception in extent report
			String screenshotPath = getScreenShot(driver, result.getName());
			
			//To add it in the extent report
			extentTest.fail("Test Case Failed Snapshot " + extentTest.addScreenCaptureFromPath(screenshotPath));

		}
		else if(result.getStatus() == ITestResult.SKIP){
			extentTest.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " :- Test Case Skipped", ExtentColor.ORANGE));
		}
		else if(result.getStatus() == ITestResult.SUCCESS)
		{
			extentTest.log(Status.PASS, MarkupHelper.createLabel(result.getName()+" :- Test Case PASSED", ExtentColor.GREEN));
		}

		//driver.quit();

	}
	
	
	/****************** Capture Screen Shot ***********************/
	public String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		String dateName = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/ScreenShots/" + screenshotName +" "+ dateName+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(sourceFile, finalDestination);
		
		return destination;
	}
	
	
}
