package com.interview.hitachiSolutions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class BaseClass {

	Properties property;
	
	public BaseClass() throws Exception {
		readProperties();
	}
	
	private void readProperties() throws Exception {
		
		BufferedReader br=new BufferedReader(new FileReader(new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\interview\\config\\config.properties")));
		property=new Properties();
		this.property.load(br);
		
	}
	
	public String getPropertyValue(String key) {
		String value=this.property.getProperty(key);
		if(value!=null)
			return value;
		else
			throw new RuntimeException("Property value is not defined for the Key"+key);
	}
	
}
