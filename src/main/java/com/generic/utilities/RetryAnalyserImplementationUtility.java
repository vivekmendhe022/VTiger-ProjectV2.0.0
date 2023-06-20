package com.generic.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * This class will provide implemenation for the IRetryAnalyzer
 * 
 * @author vivek
 *
 */
public class RetryAnalyserImplementationUtility implements IRetryAnalyzer {
	int count = 0;
	int retryCount = 5;

	public boolean retry(ITestResult result) {
		while (retryCount > count) {
			count++;
			return true;
		}
		return false;
	}
}
