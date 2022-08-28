package com.pixbrand.base;

import java.lang.reflect.Method;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.pixbrand.report.Extent;
import com.pixbrand.report.ExtentManager;
import com.pixbrand.utils.PropertyReader;

public class TranquilleBase {

	@BeforeMethod
	public void setUpReport(Method method) {

		ExtentReports reports = ExtentManager.getExtentReportsInstance();
		ExtentTest extentTest = reports.createTest(method.getName());
		Extent.setTest(extentTest);
	}

	@AfterMethod
	protected void afterMethod(ITestResult result, Method method) {
		// this.result=result;

		if (result.getStatus() == ITestResult.FAILURE) {
			try {

				// To add it in the extent report

				Extent.getTest().log(Status.FAIL, "Test Case Failed is " + result.getName());
				// Extent.getTest()
				// .fail("Captured Screenshot is below:" +
				// Extent.getTest().addScreenCaptureFromPath(baseUrl));
				Extent.getTest().log(Status.FAIL, "Throw Exception" + result.getThrowable());
				Extent.getTest().log(Status.FAIL, result.getStatus() + "-> Status Code Is");

			} catch (Exception e) {

				e.printStackTrace();
			}

		}

		else if (result.getStatus() == ITestResult.SKIP) {
			Extent.getTest().log(Status.SKIP, "Test Skipped " + result.getThrowable());
			Extent.getTest().log(Status.SKIP, "Test Case Skipped is " + result.getName());

		} else {

			Extent.getTest().log(Status.INFO, " Class Name Is--->    TranquilleTestcases");
			Extent.getTest().log(Status.PASS, "Status Code = " + 200);
			Extent.getTest().log(Status.PASS, result.getName() + " ->Test Case Passed ");

		}
	}

	@AfterSuite
	public void setUpFlushed() {
		ExtentManager.getExtentReportsInstance().flush();

	}

}
