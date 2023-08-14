package com.utilites;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	Properties property;

	//Constructor to Load and Read the config property file
	public ReadConfig() {

		File source = new File("./Configuration/config.properties");
		try{
			FileInputStream fis = new FileInputStream(source);
			property = new Properties();
			property.load(fis);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//To Retrive the Application URL from Config Property File
	public String getApplicationURL() {
		String url = property.getProperty("ApplicationURL");
		return url;
	}
	
	//To Retrive the Browser Name from Config Property File
	public String getbrowser() {
		String browser = property.getProperty("Browser");
		return browser;
	}
	
	//To Retrive the Browser Path from Config Property File
	public String getChromeBrowserPath() {
		String chromeBrowserPath = property.getProperty("chromepath");
		return chromeBrowserPath;
	}
	
	public String getedgeBrowserPath() {
		String edgeBrowserPath = property.getProperty("edgepath");
		return edgeBrowserPath;
	}
		
	public String userName() {
		String userNameValue = property.getProperty("UserName");
		return userNameValue;
	}
	
	public String phoneNumber() {
		String phoneNumberValue = property.getProperty("PhoneNumber");
		return phoneNumberValue;
	}
	
	public String getOTP() {
		String OTPValue = property.getProperty("OTP");
		return OTPValue;
	}
	
	public String getGenderList() {
		String getGenderListValue = property.getProperty("ICICIGenderList");
		return getGenderListValue;
	}
	
	public String getPinCode() {
		String getGenderListValue = property.getProperty("Pincode");
		return getGenderListValue;
	}
	
	public String getICICIPaymentFreq() {
		String getICICIPaymentFreqValue = property.getProperty("ICICIPaymentFrequency");
		return getICICIPaymentFreqValue;
	}
}
