package com.APITestingExamination.Utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtility {
	private FileInputStream istream;
	private Properties propfile = null ; 
	
	/* public void PropertyUtility() {
		try {
			String filepath= Constants.PROPERTIES_FILE ;
			this.istream = new FileInputStream(filepath) ;
			this.propfile = new Properties();
			this.propfile.load(istream) ; 
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}  */
	
	public void loadPropertyFile()  {
		this.propfile = new Properties();
		try {
		istream = new FileInputStream(Constants.PROPERTIES_FILE) ;
		propfile.load(istream) ; 
		
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
	}
	
	public String readPropertyValue(String  key) {
		loadPropertyFile();
		String value = propfile.getProperty(key) ; 
		return value ; 
	}
	

}
