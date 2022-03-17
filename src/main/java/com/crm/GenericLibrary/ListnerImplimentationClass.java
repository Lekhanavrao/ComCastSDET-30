package com.crm.GenericLibrary;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplimentationClass implements ITestListener {
	
	ExtentReports reports;
	ExtentTest test;
	
		public void onTestStart(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			test = reports.createTest(MethodName);
			//Reporter.log(MethodName + "--- testscript execution started");	
		}

		public void onTestSuccess(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			test.log(Status.PASS, MethodName+"--------->passes");
			//Reporter.log(MethodName + "--- testscript execution sucessfull - PASS");
			
			
		}

		public void onTestFailure(ITestResult result) {
			
			String path = null;
			String MethodName = result.getMethod().getMethodName()+"-";
			
			//Step 1: Configure screenshot name
			String screenshotName = result.getMethod().getMethodName()+new JavaUtility().getSystemDateInFormat()+".html";
			System.out.println(screenshotName);
			
			//Step 2: using screenshot method from webDriver Utility
			try {
				
				path = new WebDriverUtility().getScreenShot(BaseClass.sDriver, screenshotName);
				
		
				//EventFiringWebDriver eDriver = new EventFiringWebDriver(BaseClass.sDriver);
				//File src = eDriver.getScreenshotAs(OutputType.FILE);
				//String pa = System.getProperty("user.dir")+"/ScreenShots/"+screenshotName+".PNG";
				//String path = "./Screenshots/"+screenshotName+".png";
				//File dst = new File(pa);
				//Files.copy(src, dst);
				
			} catch (Throwable e) {
				
				e.printStackTrace();
			}
			
			test.log(Status.FAIL, MethodName+"--------->failed");
		   //it will capture the exception and log it in the report
			test.log(Status.FAIL, result.getThrowable());
			test.addScreenCaptureFromPath(path);
		}

		public void onTestSkipped(ITestResult result) {
			
			String MethodName = result.getMethod().getMethodName();
			test.log(Status.SKIP, MethodName+"--------->skipped");
		//it will capture the exception and log it in the report
			test.log(Status.SKIP, result.getThrowable());
			
			
			
			//Reporter.log(MethodName + "--- TestScript Skipped");
			
			
		}

		public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
			
			
		}

		public void onTestFailedWithTimeout(ITestResult result) {
			
			
		}

		public void onStart(ITestContext context) {
			
			//Execution will start here
			/* Configure the report*/
			
			ExtentSparkReporter htmlReport = new ExtentSparkReporter("./ExtentReports/"+new JavaUtility().getSystemDateInFormat());
			htmlReport.config().setDocumentTitle("SDET-30 Execution Report");
			htmlReport.config().setTheme(Theme.DARK);
			htmlReport.config().setReportName("Selenium Excetuion Report");
			
			reports = new ExtentReports();
			reports.attachReporter(htmlReport);
			reports.setSystemInfo("Base-Browser", "Chrome");
			reports.setSystemInfo("OS", "Windows");
			reports.setSystemInfo("Base-url", "http://localhost:8885");
			reports.setSystemInfo("Reporter Name", "Lekhana");
			
		}

		public void onFinish(ITestContext context) {
			
			//consolidate all the parameters and generate the report
			reports.flush();
			
		}
	  
}
