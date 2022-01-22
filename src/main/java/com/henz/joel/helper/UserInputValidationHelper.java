package com.henz.joel.helper;

import javax.servlet.http.HttpServletRequest;

public class UserInputValidationHelper {
	
	public static boolean checkIfResultPageIsAccessedDirectlyWithoutQueryParam(HttpServletRequest request, String[] arr) {
		
		boolean isPageAccessedDirectlyWithoutQueryParam = false;
		
		for(String str: arr) {
			if(request.getParameter(str) == null) {
				isPageAccessedDirectlyWithoutQueryParam = true;
				break;
			}
		}
		return isPageAccessedDirectlyWithoutQueryParam;
	}
	
	public static boolean notAllFieldsAreFilledIn(HttpServletRequest request, String[] arr) {
		
		boolean notAllFieldsAreFilledIn = false;
		
		for(String str: arr) {
			if(request.getParameter(str).equals("")) {
				notAllFieldsAreFilledIn = true;
				break;
			}
		}
		
		return notAllFieldsAreFilledIn;
	}
	
	public static boolean checkIfInputIsNumber(String... numbers) {
		boolean isNumber = true;
		
		for(String str: numbers) {
			try {
				Integer.parseInt(str);
			} catch (NumberFormatException e) {
				isNumber = false;
			}
		}
		
		return isNumber;
	}

}
