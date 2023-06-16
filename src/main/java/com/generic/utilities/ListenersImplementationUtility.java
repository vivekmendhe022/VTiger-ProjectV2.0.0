package com.generic.utilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class provides implementation to IListener Interface . This Class shows
 * Overriding & Abstraction.
 * 
 * @author vivek
 *
 */
public class ListenersImplementationUtility implements ITestListener {

	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "***** Test Execution Finshed *****");

		test = report.createTest(methodName);
		test.log(Status.INFO, methodName + " : Execution Started");
	}

	public void onTestSuccess(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + "***** Test Execution Successful *****");
		test.log(Status.PASS, methodName + " : Script Pass");
	}

	public void onTestFailure(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + " ***** Test Execution Failed *****");
		System.out.println(result.getThrowable());

		test.log(Status.FAIL, methodName + " : Script Fail");
		test.log(Status.WARNING, result.getThrowable());

		// Take Screen Shot
		String scr = methodName + " - " + new JavaUtility().getSystemDateInFormate();
		WebDriverUtility wutil = new WebDriverUtility();
		try {
			String path = wutil.takeScreenShot(BaseClass.SDriver, scr);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		System.out.println(methodName + " ***** Test Execution Skipped *****");
		System.out.println(result.getThrowable());
		test.log(Status.SKIP, methodName + " : Skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
	}

	public void onTestFailedWithTimeout(ITestResult result) {
	}

	public void onStart(ITestContext context) {
		System.out.println("***** Execution Stated *****");

		// Configure the extent reports.
		ExtentSparkReporter html = new ExtentSparkReporter(
				".\\ExtentReports\\Report-" + new JavaUtility().getSystemDateInFormate() + ".html");
		html.config().setDocumentTitle("VTiger Execution Report");
		html.config().setReportName("Execution Report BuildV1.0");
		html.config().setTheme(Theme.DARK);

		// Generate Reoprt from ExtentReports class
		report = new ExtentReports();
		report.attachReporter(html);
		report.setSystemInfo("Base Browser", "Chrome");
		report.setSystemInfo("Base URL", "http://TestEnvironment.com");
		report.setSystemInfo("Base Platform", "Windows - Family");
		report.setSystemInfo("Reporter Name", "Vivekanand Mendhe");
	}

	public void onFinish(ITestContext context) {
		System.out.println("***** Execution Finish *****");
		report.flush(); // Responsible for report generation.
	}

}
