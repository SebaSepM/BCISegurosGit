package main.java.utils;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import test.java.GG_BaseTest;

public class GG_Utils {

	public static void infoTestCase(String funcionality, String description) {
		String logText = "Funcionalidad: " + funcionality;
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.TEAL);
		GG_BaseTest.logger.log(Status.INFO, m);
		logText = "Descripcion: " + description;
		m = MarkupHelper.createLabel(logText, ExtentColor.TEAL);
		GG_BaseTest.logger.log(Status.INFO, m);
	}

	public static void eventFailed(String currentEvent, String errorMessage) {
		GG_BaseTest.logger.warning("[ERROR] en " + currentEvent + ", No se pudo terminar a causa de: " + errorMessage);
		System.out.println("[ERROR] en " + currentEvent + ", No se pudo terminar a causa de: " + errorMessage);

		String logText = "FAILED: " + currentEvent;
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		GG_BaseTest.logger.log(Status.FAIL, m);
		String path = takeScreenshot(currentEvent);
		try {
			GG_BaseTest.logger.addScreenCaptureFromPath(path, currentEvent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Assert.fail();
	}
	
	public static String takeScreenshot(String nameMethod) {
    	//Obtener Fecha y Hora para la Evidencia del Pasó Fallido.
	    LocalTime hhora = LocalTime.now();
	    DateTimeFormatter f_t = DateTimeFormatter.ofPattern("HHmmss");
	    
	    LocalDate ffecha = LocalDate.now();
	    DateTimeFormatter f_d = DateTimeFormatter.ofPattern("yyyyMMdd");
		
	    String xHora = hhora.format(f_t).toString();
	    String xFecha = ffecha.format(f_d).toString();       
        
        String xSufijo = xFecha + "_" + xHora;
        //Fin		

		String fileName = CC_Parametros.gloDir + File.separator + "screenshots" + File.separator + 
			   "failed" + File.separator + nameMethod + "_" + xSufijo;
		File f = ((TakesScreenshot) GG_BaseTest.driver).getScreenshotAs(OutputType.FILE);

		try {
			File newFile = new File(fileName + ".png");
			FileUtils.copyFile(f, newFile);
			
			return newFile.getAbsolutePath();

		} catch (IOException e) {
			e.printStackTrace();
			
			return "";
		}
	}

	public static void outputInfo(String output) {
		GG_BaseTest.logger.info(output);
		System.out.println(output);
	}

	public static void testCasePassed(String currentTestCase, String message) {
		outputInfo("PASSED: " + currentTestCase + "\n" + message + "\n");
	}

	public static void testCaseFailed(String currentTestCase, String caseTestDesc, Exception e) {
		outputInfo("No se ha terminado el caso de prueba '" + currentTestCase + "' por la siguiente excepcion: " + "\n"
				+ e.getMessage());
		outputInfo("FAILED: " + currentTestCase + " - " + caseTestDesc + "\n");
		takeScreenshot(currentTestCase);
		Assert.fail();
	}

	public static String inputName(WebElement element) {
		String nameCamelCase = element.getAttribute("id").replace("input", "").replace("IR", "").replace("Lbl", "")
				.replace("bto", "").replace("Login", "").replace("SS03_lgn", "").replace("Log", "").replace("In_", "")
				.replace("Interno_", "").replace("SSO3_lgn", "").trim();
		if (nameCamelCase.equals("")) {
			nameCamelCase = element.getAttribute("formcontrolname").replace("input", "").replace("IR", "")
					.replace("Lbl", "").replace("bto", "").trim();
		}
		String[] nameSplit = nameCamelCase.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
		String name = "";
		for (int i = 0; i < nameSplit.length; i++) {
			name = name + nameSplit[i] + " ";
		}
		return name.trim();
	}

	public static String buttonName(WebElement element) {
		String nameCamelCase = element.getText();
		if (nameCamelCase.equalsIgnoreCase("")) {
			nameCamelCase = element.getAttribute("text");
		}
		if (nameCamelCase == null || nameCamelCase.equalsIgnoreCase("null")) {
			nameCamelCase = element.getAttribute("data-original-title");
		}
		if (nameCamelCase == null || nameCamelCase.equalsIgnoreCase("null")) {
			nameCamelCase = element.getAttribute("defaultValue");
		}
		if (nameCamelCase.equalsIgnoreCase("") || nameCamelCase == null || nameCamelCase.equalsIgnoreCase("null")) {
			nameCamelCase = element.getAttribute("class").split("-")[2];
		}
		String[] nameSplit = nameCamelCase.split("(?<!(^|[A-Z]))(?=[A-Z])|(?<!^)(?=[A-Z][a-z])");
		String name = "";
		for (int i = 0; i < nameSplit.length; i++) {
			name = name + nameSplit[i] + " ";
		}
		return name.trim();
	}
}
