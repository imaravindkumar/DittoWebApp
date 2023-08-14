package com.testcases;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.functionalLibrary.FunctionalLibrary;
import com.pageObject.DittoWebApp;
import com.utilites.ReadConfig;

public class ICICITermFlow extends BaseClass {

	private FunctionalLibrary functionlibrary = new FunctionalLibrary();
	public String appNumber = "123456789012";
	private DittoWebApp dittoweb = new DittoWebApp();
	ReadConfig readconfig = new ReadConfig();
	public String userName = readconfig.userName();
	public String phoneNumber = readconfig.phoneNumber();
	public String OTPNumber = readconfig.getOTP();
	
	
	@Test(priority=0)
	public void DWATitleVerification() {
		
		driver.get(baseURL);
		log.info("The Title of the Application "+driver.getTitle());
		extentTest = extentReport.createTest("Verification of DWA Title");
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Ditto | Insurance made simple";
		
		if(actualTitle.equals(expectedTitle))
		{
			System.out.println("Title :: "+actualTitle);
			extentTest.createNode("DWA Title").pass("Title is matched :: "+actualTitle);  
			
		}else {
			System.out.println("Title :: "+expectedTitle);
			extentTest.createNode("DWA Title").pass("Title is matched :: "+actualTitle);  
		}
		
		functionlibrary.implicitWait(driver);
		functionlibrary.clickonElement(driver, dittoweb.termSelectICICI);

	}


	@Test(priority=1)
	public void tellUsAboutPage() throws InterruptedException {

		log.info("Tell Us About Page Validation.");
		extentTest = extentReport.createTest("Verification of Tell Us About Page");
		
		functionlibrary.implicitWait(driver);
		System.out.println("In Tell us about page");
		functionlibrary.clickonElement(driver,dittoweb.nextButton);
		functionlibrary.implicitWait(driver);
		
		//Mandatory field Validation	
		isFieldMandatory(driver,dittoweb.genderFieldMandatory, "Gender");
		isFieldMandatory(driver,dittoweb.dobFieldMandatory, "Date of Birth");
		isFieldMandatory(driver,dittoweb.smokeFieldMandatory, "Smoke Question Yes or NO");


		List<String> genderListFromUI = new ArrayList<String>();
		List<String> actualGenderList = new ArrayList<String>();
		List<WebElement> genderList = driver.findElements(dittoweb.genderList);
//		List<WebElement> smokeList = driver.findElements(dittoweb.smokeFieldButton);
		
		// Iterate through the elements and retrieve the attribute value
		for (WebElement element : genderList) {
			String attributeValue = element.getText();
			genderListFromUI.add(attributeValue);
		}

		actualGenderList= functionlibrary.convertStringToList(readconfig.getGenderList());
	
		boolean valuelist =  Arrays.equals(genderListFromUI.toArray(),actualGenderList.toArray());
		if(valuelist) {
			log.info("The Gender List Value are : "+genderListFromUI);
			extentTest.createNode("Verification of Gender List ").pass("Gender values are Matched :: "+genderListFromUI);
			System.out.println("The Gender List are :: "+genderListFromUI);
			Assert.assertTrue(true);
		}
		else {
			extentTest.createNode("Verification of Gender List ").fail("Gender values are Matched :: "+genderListFromUI);
			Assert.assertTrue(false);
			System.out.println("The Gender List are not equal :: "+genderListFromUI);
		}
		
		
		String selectedGenderValue = functionlibrary.randomNumberClick(genderList);
		System.out.println("The Gender selected is :: "+selectedGenderValue);
	
		//Check 18 years note message
		driver.findElement(dittoweb.dobField).sendKeys(functionlibrary.generateCurrentDate());
		functionlibrary.isFieldMandatory(driver,dittoweb.dob18yearNoteMessage,"DOB 18 years old note message");

		Thread.sleep(500);

		functionlibrary.clearField(driver,dittoweb.dobField);
			driver.findElement(dittoweb.dobField).sendKeys("29/07/1823");
		functionlibrary.isFieldMandatory(driver,dittoweb.dob18yearNoteMessage,"DOB :: You can't be older than 60 years");

		Thread.sleep(500);
		functionlibrary.clearField(driver,dittoweb.dobField);
		driver.findElement(dittoweb.dobField).sendKeys(functionlibrary.randomDateGenerator());

	//	String selectedSmokeValue = functionlibrary.randomNumberClick(smokeList);
	//	System.out.println("The Smoke Field selected is :: "+selectedSmokeValue);
		functionlibrary.clickonElement(driver,dittoweb.smokeFieldNo);
		
		driver.findElement(dittoweb.nextButton).click();

		//Wait to Load Page
		functionlibrary.implicitWait(driver);

	}

	@Test(priority=2)
	public void aBitAboutYourLifestylePage() throws InterruptedException {

		log.info("A bit about your lifestyle page");
		extentTest = extentReport.createTest("Verification of A bit about your lifestyle page");

		driver.findElement(dittoweb.nextButton).click();
		//Wait to Load Page
		functionlibrary.implicitWait(driver);

		isFieldMandatory(driver,dittoweb.pincodeMandatory,"Pincode");
		isFieldMandatory(driver,dittoweb.annualIncomeMandatory,"Annual Income");
		isFieldMandatory(driver,dittoweb.monthlyExpensesMandatory,"Monthly Income");
		isFieldMandatory(driver,dittoweb.diabetesMandatory,"Diabetes");

	
		functionlibrary.sendValues(driver,dittoweb.pincodeField,readconfig.getPinCode());
		functionlibrary.sendValues(driver,dittoweb.annualIncomeField,functionlibrary.generateRandomText("Numeric",7));
		functionlibrary.sendValues(driver,dittoweb.monthlyExpensesField,functionlibrary.generateRandomText("Numeric",5));
		Thread.sleep(500);

		functionlibrary.clickonElementUsingAction(driver,dittoweb.nextButton);
		
		List<WebElement> diabeticsList = driver.findElements(dittoweb.diabetesField);
		String diabeticsValue = functionlibrary.randomNumberClick(diabeticsList);
		System.out.println("The diabetics Field value selected is :: "+diabeticsValue);
		
		functionlibrary.clickonElementUsingAction(driver,dittoweb.nextButton);
	}


	@Test(priority=3)
	public void recommendedCover() throws InterruptedException {

		log.info("Recommended cover");
		extentTest = extentReport.createTest("Verification of Recommended cover page");

		//Wait to Load Page
		functionlibrary.implicitWait(driver);

		String minValue = functionlibrary.getValue(driver,dittoweb.scrollbar,"aria-valuemin");
		String maxValue = functionlibrary.getValue(driver,dittoweb.scrollbar,"aria-valuemax");
		int min = functionlibrary.stringTOInt(minValue);
		int max = functionlibrary.stringTOInt(maxValue);
		
		System.out.println("The Minimum Scroll Value is :: "+minValue);
		System.out.println("The Maximum Scroll Value is :: "+maxValue);

		functionlibrary.clearField(driver,dittoweb.coverTillField);
		functionlibrary.implicitWait(driver);
		functionlibrary.sendValues(driver,dittoweb.coverTillField,minValue);
		//Wait to Load Page
		functionlibrary.implicitWait(driver);
		
		//Scroll the slider by one Value
		functionlibrary.scrollSlider(driver, dittoweb.scrollbar, min, max);
		functionlibrary.implicitWait(driver);
		functionlibrary.clearField(driver,dittoweb.coverTillField);
	
		functionlibrary.sendValues(driver,dittoweb.coverTillField,"70");

		//Wait to Load Page
		Thread.sleep(2000);

		String inputAgeValue = functionlibrary.getText(driver, dittoweb.scrollBarValue);
		String scrollBarValue = functionlibrary.getText(driver, dittoweb.scrollBarValue);
		
		System.out.println("The Scroll Bar Value :: "+scrollBarValue);
		System.out.println("The Input Bar Value :: "+inputAgeValue);

		
		if(inputAgeValue.equals(scrollBarValue)) {
			System.out.println("The Scroll Bar Value is Matched with the Input Age Value :: "+scrollBarValue);
			extentTest.pass("Verification of Scroll Bar Value with the Input Age Value :: "+scrollBarValue+ " is Matched.");
		}
		else {
			System.out.println("The Scroll Bar Value is not Matched with the Input Age Value :: "+scrollBarValue);
			extentTest.fail("Verification of Scroll Bar Value with the Input Age Value :: "+scrollBarValue+ " is not Matched.");
		}
		
		Thread.sleep(1000);
		
		String premiumAmount = functionlibrary.getText(driver, dittoweb.premiumAmount);
		String premiumPeriod = functionlibrary.getText(driver, dittoweb.premiumPeriod);
		String coverAmount = functionlibrary.getText(driver, dittoweb.coverAmount);
		String coverTill =  functionlibrary.getValue(driver, dittoweb.coverTill,"value");
		String totalPerYear = functionlibrary.getText(driver, dittoweb.totalPerYear);
		
		System.out.println("The Premium Amount for the Cover "+coverAmount+" Amount and Cover Till "+coverTill+"  :: "+premiumAmount+premiumPeriod);

		System.out.println("The Premium Amount showed in the pricing card :: "+totalPerYear);

		if(premiumAmount.equals(premiumAmount))
		{
			System.out.println("The Premium in Recommended Cover and The Premium in Pricing card is same :: "+premiumAmount);
			extentTest.createNode("The Premium Amount for the Cover "+coverAmount+" Amount and Cover Till "+coverTill+"  :: "+premiumAmount+premiumPeriod)
			.pass("The Premium in Recommended Cover and The Premium in Pricing card is same :: "+totalPerYear);
		}
		else {
			System.out.println("The Premium in Recommended Cover and The Premium in Pricing card is not Same :: "+premiumAmount);
			extentTest.createNode("The Premium Amount for the Cover "+coverAmount+" Amount and Cover Till "+coverTill+"  :: "+premiumAmount+premiumPeriod)
			.fail("\"The Premium in Recommended Cover and The Premium in Pricing card is not same :: "+totalPerYear);
		}

		functionlibrary.clickonElement(driver, dittoweb.nextButton);
		functionlibrary.implicitWait(driver);


	}


	@Test(priority=4)
	public void paymentTermOptions() throws InterruptedException {

		log.info("Payment Term options");
		extentTest = extentReport.createTest("Verification of Payment Term options");

		//Wait to Load Page
		functionlibrary.implicitWait(driver);
	
		//Payment Term Options
		List<String> payTermOptionsFromUI = new ArrayList<String>();

		List<WebElement> payTermOptionsList = driver.findElements(dittoweb.termOptions);

		// Iterate through the elements and retrieve the attribute value
		for (WebElement element : payTermOptionsList) {
			String attributeValue = element.getText();
			payTermOptionsFromUI.add(attributeValue);	
		}
				
		System.out.println("The Avaliable option to select premium are :: "+payTermOptionsFromUI);
		extentTest.createNode("The Avaliable option to select premium are :: "+payTermOptionsFromUI);

		for (WebElement element : payTermOptionsList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Regular Pay")) {
				element.click();
			}
		}
		
		//Wait to Load Page
		functionlibrary.implicitWait(driver);
		functionlibrary.clickonElement(driver, dittoweb.nextButton);		
		//Wait to Load Page
		functionlibrary.implicitWait(driver);
	}

	@Test(priority=5)
	public void addeExtraProtection() throws InterruptedException {

		log.info("Add extra protection");
		extentTest = extentReport.createTest("Verification of Add extra protection");

		//Wait to Load Page
		functionlibrary.implicitWait(driver);

		String lifeStageBenefit = functionlibrary.getText(driver, dittoweb.lifeStageBenefit);
		System.out.println("The Add Extra Benefit Protection is :: "+lifeStageBenefit);
		extentTest.createNode("The Add Extra Benefit Protection is :: "+lifeStageBenefit);

		driver.findElement(dittoweb.nextButton).click();
		functionlibrary.implicitWait(driver);

		String terminalIllness = functionlibrary.getText(driver, dittoweb.lifeStageBenefit);
		System.out.println("The Add Extra Benefit Protection is :: "+terminalIllness);
		extentTest.createNode("The Add Extra Benefit Protection is :: "+terminalIllness);

		driver.findElement(dittoweb.nextButton).click();
		functionlibrary.implicitWait(driver);

		String waiverOfPremium = functionlibrary.getText(driver, dittoweb.lifeStageBenefit);
		System.out.println("The Add Extra Benefit Protection is :: "+waiverOfPremium);
		extentTest.createNode("The Add Extra Benefit Protection is :: "+waiverOfPremium);

		driver.findElement(dittoweb.nextButton).click();
		functionlibrary.implicitWait(driver);

		String freeAddOn = functionlibrary.getText(driver, dittoweb.freeAddOn);
		System.out.println("The Free Add-Ons Benefits in the Pricing Card are :: "+freeAddOn);
		extentTest.createNode("The Free Add-Ons Benefits in the Pricing Card are :: "+freeAddOn);

		List<String> freeAddonList= functionlibrary.convertStringToList(freeAddOn);
		if(freeAddonList.contains(lifeStageBenefit) || freeAddonList.contains(terminalIllness) || freeAddonList.contains(waiverOfPremium)) {
			System.out.println("The Free Add-Ons displayed in the Pricing card is matched with the Add Extra Protection Plan :: "+freeAddOn);
			extentTest.createNode("The Free Add-Ons displayed in the Pricing card is matched with the Add Extra Protection Plan :: "+freeAddOn);
		}
		else {
			System.out.println("The Free Add-Ons displayed in the Pricing card is not matched with the Add Extra Protection Plan :: "+freeAddOn);
			extentTest.createNode("The Free Add-Ons displayed in the Pricing card is not matched with the Add Extra Protection Plan :: "+freeAddOn);
		}

		String accidentalCover = functionlibrary.getText(driver, dittoweb.lifeStageBenefit);
		System.out.println("The Add Extra Benefit Protection is :: "+accidentalCover);
		extentTest.createNode("The Add Extra Benefit Protection is :: "+accidentalCover);
		driver.findElement(dittoweb.nextButton).click();
		functionlibrary.implicitWait(driver);


		String criticalIllness = functionlibrary.getText(driver, dittoweb.lifeStageBenefit);
		System.out.println("The Add Extra Benefit Protection is :: "+criticalIllness);
		extentTest.createNode("The Add Extra Benefit Protection is :: "+criticalIllness);
		driver.findElement(dittoweb.nextButton).click();
		functionlibrary.implicitWait(driver);	

		//Policy Summary
		String policyPeriod = functionlibrary.getText(driver, dittoweb.policyPeriod);
		System.out.println("The Policy Period is :: "+policyPeriod);
		extentTest.createNode("The Policy Period is :: "+policyPeriod);

		String paymentTerm = functionlibrary.getText(driver, dittoweb.policyPeriodTerm);
		System.out.println("The Policy Period Term is :: "+paymentTerm);
		extentTest.createNode("The Policy Period Term is :: "+paymentTerm);

		String totalPerYearPremium = functionlibrary.getText(driver, dittoweb.totalPerYearPremium);
		System.out.println("The Total per year Premium is :: "+totalPerYearPremium);
		extentTest.createNode("The Total per year Premium is :: "+totalPerYearPremium);

		functionlibrary.clickonElement(driver, dittoweb.buyThis);
		functionlibrary.implicitWait(driver);

		functionlibrary.clickonElement(driver, dittoweb.confirmAndBuy);
		functionlibrary.implicitWait(driver);

//		functionlibrary.sendKeysMethod(driver, dittoweb.fullName,userName);

//		functionlibrary.sendKeysMethod(driver, dittoweb.mobileNumber,phoneNumber);
		
//		functionlibrary.clickonElement(driver, dittoweb.sendOTPButton);
		
//		functionlibrary.sendKeysMethod(driver, dittoweb.sendOTP,OTPNumber);
		
//		functionlibrary.clickonElement(driver, dittoweb.submitOTP);
		
	}
	
	@Test(priority=6, enabled=false)
	public void proposalform() throws InterruptedException {

		log.info("Payment Term options");
		extentTest = extentReport.createTest("Verification of Proposal form");

		//Wait to Load Page
		functionlibrary.implicitWait(driver);
	
		functionlibrary.clickonElement(driver, dittoweb.startButton);
		functionlibrary.implicitWait(driver);
		
		functionlibrary.clickonElement(driver, dittoweb.nextButton);
		
		functionlibrary.sendValues(driver, dittoweb.lastName, "TESTPolicy");
		
		functionlibrary.sendValues(driver, dittoweb.email, "testpolicycheck@yahoo.com");

		functionlibrary.clickonElement(driver, dittoweb.education);
		
		List<String> educationList = new ArrayList<String>();

		List<WebElement> educationOptionsList = driver.findElements(dittoweb.educationList);

		// Iterate through the elements and retrieve the attribute value
		for (WebElement element : educationOptionsList) {
			String attributeValue = element.getText();
			educationList.add(attributeValue);	
		}
				
		System.out.println("The Avaliable option to Education premium are :: "+educationList);
		extentTest.createNode("The Avaliable option to select Education are :: "+educationList);

		for (WebElement element : educationOptionsList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Graduate")) {
				element.click();
			}
		}
		
		functionlibrary.clickonElement(driver, dittoweb.martialstatus);
		List<WebElement> martialOptionsList = driver.findElements(dittoweb.educationList);

		for (WebElement element : martialOptionsList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Single")) {
				element.click();
			}
		}
		
		functionlibrary.implicitWait(driver);
		String errorMsg = functionlibrary.getText(driver, dittoweb.nationalResiding);
		System.out.println("I’m an Indian national residing in India error message is :: "+errorMsg);
		
		functionlibrary.clickonElement(driver, dittoweb.residingToogleButton);
		
		functionlibrary.clickonElement(driver, dittoweb.nextButton);
		
		functionlibrary.implicitWait(driver);
		
		functionlibrary.sendValues(driver, dittoweb.addressLine1, "House Number Number");
		functionlibrary.sendValues(driver, dittoweb.addressLine2, "House Number Number");
		functionlibrary.sendValues(driver, dittoweb.city, "Bangalore");

		functionlibrary.clickonElement(driver, dittoweb.sameasPermanent);
		
		functionlibrary.clickonElement(driver, dittoweb.nextButton);		
		functionlibrary.implicitWait(driver);
		
		functionlibrary.sendValues(driver, dittoweb.panNumber, "BWPPA6961A");
		
		functionlibrary.clickonElement(driver, dittoweb.yourOccuption);
		
		List<WebElement> occuptionsList = driver.findElements(dittoweb.educationList);

		for (WebElement element : occuptionsList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Salaried")) {
				element.click();
			}
		}
			
		
		functionlibrary.sendValues(driver, dittoweb.orgName, "Accenture");
		driver.findElement(dittoweb.orgName).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.ENTER));

		functionlibrary.clickonElement(driver, dittoweb.orgType);
		List<WebElement> orgsList = driver.findElements(dittoweb.educationList);

		for (WebElement element : orgsList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Pvt Ltd")) {
				element.click();
			}
		}
		
		functionlibrary.clickonElement(driver, dittoweb.indType);
		List<WebElement> indList = driver.findElements(dittoweb.educationList);

		for (WebElement element : indList) {
			String attributeValue = element.getText();
			if(attributeValue.equals("Stock")) {
				element.click();
			}
		}
		

		functionlibrary.clickonElement(driver, dittoweb.nextButton);		
		functionlibrary.implicitWait(driver);
		
		//Nominee Details
		functionlibrary.sendValues(driver, dittoweb.nomineFname, functionlibrary.generateRandomText("Characters", 10));
		functionlibrary.sendValues(driver, dittoweb.nomineLname, functionlibrary.generateRandomText("Characters", 10));
		getListAndClick(driver,dittoweb.nomineeGender, dittoweb.educationList, "Male");
		getListAndClick(driver,dittoweb.nomineeRelationship, dittoweb.educationList, "Brother");
		functionlibrary.sendValues(driver, dittoweb.nomineeDOB, functionlibrary.generateCurrentDate());
		
		functionlibrary.implicitWait(driver);
		
		//Appointee Details
	//	functionlibrary.sendValues(driver, dittoweb.appointeeFname, functionlibrary.generateRandomText("Characters", 10));
	//	functionlibrary.sendValues(driver, dittoweb.appointeeLname, functionlibrary.generateRandomText("Characters", 10));
		
		functionlibrary.sendValues(driver, dittoweb.appointeeFname, "TestPolicy");
		functionlibrary.sendValues(driver, dittoweb.appointeeLname, "PolicyTest");
		getListAndClick(driver,dittoweb.appointeeGender, dittoweb.educationList, "Male");
		getListAndClick(driver,dittoweb.appointeeRelationship, dittoweb.educationList, "Brother");
		functionlibrary.sendValues(driver, dittoweb.appointeeDOB, functionlibrary.generateCurrentDate());
		
		functionlibrary.clickonElementUsingAction(driver, dittoweb.nextButton);
		
		String errormMsg = functionlibrary.getText(driver,dittoweb.appointeeDOBErrormsg);
		System.out.println("The Appointee DOB Error Msg :: "+errormMsg);
		
		functionlibrary.clearField(driver,dittoweb.appointeeDOB );
		functionlibrary.sendValues(driver, dittoweb.appointeeDOB, "01/01/2000");
		
		functionlibrary.clickonElementUsingAction(driver, dittoweb.nextButton);
		functionlibrary.implicitWait(driver);
		
		//LifeStyle Details
		scrollAndClick(dittoweb.lifestyleHeight, "5 feet 8 inch");
		functionlibrary.sendValues(driver, dittoweb.lifestyleWeight, "70");
		
		Thread.sleep(500);
		
		clickonList(dittoweb.lifestyleFaqs);
		
		functionlibrary.clickonElement(driver, dittoweb.nextButton);		
		functionlibrary.implicitWait(driver);
		
		functionlibrary.clickonElement(driver, dittoweb.medicalHistoryQue);
		functionlibrary.clickonElement(driver, dittoweb.medicalHistoryQue19);
		functionlibrary.sendValues(driver,dittoweb.remarkTextarea ,functionlibrary.generateRandomText("Characters", 100));
		functionlibrary.clickonElementUsingAction(driver, dittoweb.nextButton);
		
		//Covid Questionnaire
		clickonList(dittoweb.lifestyleFaqs);
		functionlibrary.clickonElementUsingAction(driver, dittoweb.nextButton);

		//NSDL Database Management Limited
		functionlibrary.sendValues(driver, dittoweb.insuranceAccount, "NSDL Database Management Limited");
		driver.findElement(dittoweb.insuranceAccount).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.ENTER));
		
		functionlibrary.clickonElement(driver, dittoweb.gotItButton);
		functionlibrary.implicitWait(driver);
		
		functionlibrary.clickonElementUsingAction(driver, dittoweb.declarationOne);
		functionlibrary.clickonElementUsingAction(driver, dittoweb.declarationTwo);
		
		functionlibrary.clickonElementUsingAction(driver, dittoweb.confirmButton);
		functionlibrary.implicitWait(driver);


	}


	//Check Element is present or not;
	public void isFieldMandatory(WebDriver driver, By elements, String fieldName) {
		boolean requiredText = functionlibrary.veriyElementIsDisplayed(driver,elements);
		if(requiredText) {
			System.out.println("The "+fieldName+" is Required Field");
			extentTest.pass("Mandatory Field Verification :: "+fieldName+ " is Mandated.");
		}
		else {
			System.out.println("The "+fieldName+" is Not Required Field");
			extentTest.fail("Mandatory Field Verification :: "+fieldName+ " is not Mandated.");

		}

	}

	public void getListAndClick(WebDriver driver, By element, By elements, String selectValue ) {
		functionlibrary.clickonElement(driver, element);
		List<WebElement> list = driver.findElements(dittoweb.educationList);

		for (WebElement getelement : list) {
			String attributeValue = getelement.getText();
			if(attributeValue.equals(selectValue)) {
				
				getelement.click();
			}
		}
		
	}
	
	public void scrollAndClick(By element , String Value) {

		driver.findElement(element).click();
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(30));
		Actions action = new Actions(driver);
		WebElement clickElement =  driver.findElement(By.xpath("//ul[@role='listbox']/li[contains(text(),'"+Value+"')]"));
		wait.until(ExpectedConditions.elementToBeClickable(clickElement));
		action.moveToElement(clickElement).click().perform();
	}
	
	public void clickonList(By element) {
	
		List<WebElement> list = driver.findElements(element);
		for (WebElement elements : list) {
			elements.click();
			elements.click();
		}
	}
}
