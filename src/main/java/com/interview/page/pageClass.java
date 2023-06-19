package com.interview.page;

public class pageClass {
	
	public String findxPathElementWithText(String text) {
		return "//*[text()='"+text+"']";
	}
	
	public String findXpathOfParentOfText(String text) {
		return "//*[text()='"+text+"']/..";
	}
}
