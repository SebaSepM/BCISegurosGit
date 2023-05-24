package main.java.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import test.java.GG_BaseTest;

import java.util.List;
//Clasifica los localizadores y los cambia.(creo que hace eso xD)
public class GG_ElementFetch {
    public WebElement getWebElement(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "ID":
                return GG_BaseTest.driver.findElement(By.id(identifierValue));
            case "CSS":
                return GG_BaseTest.driver.findElement(By.cssSelector(identifierValue));
            case "TAGNAME":
                return GG_BaseTest.driver.findElement(By.tagName(identifierValue));
            case "XPATH":
                return GG_BaseTest.driver.findElement(By.xpath(identifierValue));
            case "LINK TEXT":
                return GG_BaseTest.driver.findElement(By.linkText(identifierValue));
            default:
                return null;
        }
    }

    public List<WebElement> getListWebElements(String identifierType, String identifierValue) {
        switch (identifierType) {
            case "ID":
                return GG_BaseTest.driver.findElements(By.id(identifierValue));
            case "CSS":
                return GG_BaseTest.driver.findElements(By.cssSelector(identifierValue));
            case "TAGNAME":
                return GG_BaseTest.driver.findElements(By.tagName(identifierValue));
            case "XPATH":
                return GG_BaseTest.driver.findElements(By.xpath(identifierValue));
            case "LINK TEXT":
                return GG_BaseTest.driver.findElements(By.linkText(identifierValue));
            default:
                return null;
        }
    }
}
