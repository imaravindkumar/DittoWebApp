package com.pageObject;

import org.openqa.selenium.By;

public class DittoWebApp {

	public By termSelectICICI = By.xpath("//span[contains(text(),'ICICI iProtect Smart')]");
	public By nextButton = By.xpath("//span[contains(text(),'Next')]");
	public By updateButton =By.xpath("//span[contains(text(),'Update')]");
	public By genderFieldMandatory = By.xpath("(//p[@class='MuiFormHelperText-root Mui-error'])[1]");
	public By dobFieldMandatory = By.xpath("//p[@id='date-picker-dialog-dob-helper-text']");
	public By smokeFieldMandatory = By.xpath("(//p[@class='MuiFormHelperText-root Mui-error'])[2]");

	public By genderList = By.xpath("(//span[@class='MuiButton-startIcon MuiButton-iconSizeLarge']/..)");
	public By dobField = By.xpath("//input[@name='dob']");
	public By smokeFieldButton = By.xpath("//label[contains(text(),'Have you smoked in the last')]/following::span[starts-with(@class,'MuiButton-label jss')]");
	public By smokeFieldNo = By.xpath("//span[normalize-space()='No']");
	public By dob18yearNoteMessage = By.xpath("//p[@id='date-picker-dialog-dob-helper-text']");

	//A Bit about yourself page
	public By pincodeField = By.xpath("//input[@name='pincode']");
	public By pincodeMandatory = By.xpath("//p[@id='text-input-pincode-helper-text']");
	public By annualIncomeField = By.xpath("//input[@name='annInc']");
	public By annualIncomeMandatory = By.xpath("//p[@id='text-input-annInc-helper-text']");
	public By monthlyExpensesField = By.xpath("//input[@name='mExpenses']");
	public By monthlyExpensesMandatory = By.xpath("//p[@id='text-input-mExpenses-helper-text']");
	public By diabetesFieldNo = By.xpath("//label[contains(text(),'Do you')]/following::span[1]");
	public By diabetesFieldYes= By.xpath("//label[contains(text(),'Do you')]/following::span[2]");
	public By diabetesField = By.xpath("//label[contains(text(),'Do you have diabetes?')]/following::span[starts-with(@class,'MuiButton-label jss')]");
	public By diabetesMandatory = By.xpath("//p[@class='MuiFormHelperText-root Mui-error']");

	//Recommended cover
	public By scrollbar = By.xpath("//span[@role='slider']");
	public By coverTillField = By.xpath("//input[@id='text-input-input-age']");
	public By scrollBarValue= By.xpath("(//span[@aria-orientation='horizontal']//span)[3]");
	public By premiumAmount = By.xpath("//h6[@class='MuiTypography-root MuiTypography-h6']/span[1]");
	public By premiumPeriod = By.xpath("//h6[@class='MuiTypography-root MuiTypography-h6']/span[2]");
	public By coverAmount = By.xpath("//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorTextPrimary']/span");
	public By coverTill = By.xpath("//input[@name='input-age']");
	public By totalPerYear = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-alignRight'])[1]");
	public By clickonPaymentFreq = By.xpath("//div[@id='select-term-frequency']");
	public By paymentFreq = By.xpath("//li[@role='option']");

	//Payment Term Options
	public By termOptions= By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1 MuiTypography-gutterBottom']/div");

	//Add Extra Premium
	public By lifeStageBenefit = By.xpath("//h6[@class='MuiTypography-root MuiTypography-subtitle1']/span");
	public By freeAddOn = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-colorTextSecondary MuiTypography-gutterBottom']//span)[2]");
	public By policyPeriod = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-colorTextPrimary']//span)[2]");
	public By policyPeriodTerm = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body1 MuiTypography-colorTextPrimary']//span)[3]");
	public By totalPerYearPremium = By.xpath("(//p[@class='MuiTypography-root MuiTypography-body2 MuiTypography-alignRight'])[1]");
	public By buyThis = By.xpath("//span[text()='Buy this']");
	public By confirmAndBuy = By.xpath("//span[text()='Confirm and buy']");
	public By fullName = By.xpath("//input[@name='fullName']");
	public By mobileNumber = By.xpath("//input[@name='mobile']");
	public By sendOTPButton = By.xpath("//span[text()='Send OTP']");
	public By sendOTP = By.xpath("//input[@name='otp']");
	public By submitOTP = By.xpath("//span[text()='Submit']");
	
	//Proposal Form
	public By startButton = By.xpath("//span[contains(text(),'Start')]");
	public By lastName = By.xpath("//input[@name='lastName']");
	public By email = By.xpath("//input[@name='email']");
	public By education = By.xpath("//div[@id='select-education']");
	public By educationList = By.xpath("//ul[@role='listbox']/li");
	public By martialstatus = By.xpath("//div[text()='Marital Status']");
	public By nationalResiding = By.xpath("//p[@class='MuiFormHelperText-root Mui-error']");
	public By residingToogleButton = By.xpath("//input[@name='insuranceswitch']");
	
	public By addressLine1 = By.xpath("//input[@placeholder='#DO-No, Street']");
	public By addressLine2 = By.xpath("//input[@placeholder='Area, locality']");
	public By city = By.xpath("//input[@name='commAddr.city']");
	public By sameasPermanent = By.xpath("//input[@name='samePermAddr']");
	public By panNumber = By.xpath("//input[@placeholder='PAN Number']");
	public By yourOccuption = By.xpath("//div[text()='Select your occupation']");
	public By orgType = By.xpath("//div[@id='select-occupationDetails.orgType']");
	public By indType = By.xpath("//div[@id='select-occupationDetails.industry']");

	public By orgName = By.xpath("//input[@placeholder='Organization name']");
	
	//Nominee Details
	public By nomineFname = By.xpath("//input[@name='nomineeDetails.firstName']");
	public By nomineLname = By.xpath("//input[@name='nomineeDetails.lastName']");
	public By nomineeGender = By.xpath("//div[@id='select-nomineeDetails.gender']");
	public By nomineeRelationship = By.xpath("//div[@id='select-nomineeDetails.relationship']");
	public By nomineeDOB = By.xpath("//input[@name='nomineeDetails.dob']");
	
	//Appointee Details
	public By appointeeFname = By.xpath("//input[@name='appointeeDetails.firstName']");
	public By appointeeLname = By.xpath("//input[@name='appointeeDetails.lastName']");
	public By appointeeGender = By.xpath("//div[@id='select-appointeeDetails.gender']");
	public By appointeeRelationship = By.xpath("//div[@id='select-appointeeDetails.relationship']");
	public By appointeeDOB = By.xpath("//input[@id='date-picker-dialog-appointeeDetails.dob']");
	public By appointeeDOBErrormsg = By.xpath("//p[@id='date-picker-dialog-appointeeDetails.dob-helper-text']");
	
	//Lifestyle Details
	public By lifestyleHeight = By.xpath("//div[@id='select-height_ft-height_inch']");
	public By lifestyleWeight = By.xpath("//input[@name='weight']");
	public By lifestyleFaqs = By.xpath("//input[@name='insuranceswitch']");
	
	public By medicalHistoryQue = By.xpath("(//input[@name='insuranceswitch'])[6]");
	public By medicalHistoryQue19 = By.xpath("(//input[@name='insuranceswitch'])[19]");
	public By remarkTextarea = By.xpath("//textarea[@name='remarks']");
	public By insuranceAccount = By.xpath("//input[@id='auto-complete-eiaRepository']");
	public By gotItButton = By.xpath("//span[text()='Got it']");
	
	public By declarationOne = By.xpath("//input[@name='proposerIsPayee']");
	public By declarationTwo = By.xpath("//input[@name='cofirm']");
	public By confirmButton = By.xpath("//span[text()='Confirm']");
	

	
}
