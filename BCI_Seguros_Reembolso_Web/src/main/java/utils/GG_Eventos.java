package main.java.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class GG_Eventos {

	//sendkeys
	public static void writeOnInput(WebElement element, String value) {
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		if (element.isDisplayed() && element.isEnabled()) {
			String nameInput = GG_Utils.inputName(element);
			int caracteres = element.getAttribute("value").toCharArray().length;
			for (int i = 0; i < caracteres; i++) {
				element.sendKeys(Keys.BACK_SPACE);
			}
			element.sendKeys(value);
			GG_Utils.outputInfo(" [evento] Se ha ingresado el texto '" + value + "' en el campo: " + nameInput);
		} else {
			String nameInput = GG_Utils.inputName(element);
			GG_Utils.eventFailed(currentEvent, "[?] El campo '" + nameInput + "' no se encuentra habilitado o desplegado");
		}
	}

	//click
	public static void clickButton(WebElement element) {
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		if (element.isEnabled()) {
			String name = GG_Utils.buttonName(element);
			element.click();
			GG_Utils.outputInfo(" [evento] Se ha hecho clic en el boton: " + name);
		} else {
			String name = element.getAttribute("text");
			GG_Utils.eventFailed(currentEvent, "[?] El boton '" + name + "' no esta desplegado o habilitado");
		}
	}

	//getText
	public static String selectByText(WebElement element, String option) {
		String currentEvent = new Throwable().getStackTrace()[0].getMethodName();
		String name = GG_Utils.inputName(element);
		Select select = new Select(element);
		select.selectByVisibleText(option);
		if (select.getFirstSelectedOption().isSelected()) {
			String selectedOption = select.getFirstSelectedOption().getText();
			GG_Utils.outputInfo(
					" [evento] Se ha seleccionado la opcion '" + selectedOption + "' en la lista desplegable " + name);
			return selectedOption;
		} else {
			GG_Utils.eventFailed(currentEvent, "[?] La opcion requerida no pudo ser seleccionada");
			return null;
		}
	}

	//executeScript
	public static void clickJavaScript(WebElement element, WebDriver driver) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		String name = GG_Utils.buttonName(element);
		jse.executeScript("arguments[0].click()", element);
		GG_Utils.outputInfo(" [evento] Se hizo clic en el boton: " + name);
	}

}
