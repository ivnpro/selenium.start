package com.support.helper;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExtraShit {
    public static String getAllAttribute(By locator, WebDriver driver){
        WebElement element = driver.findElement(locator); // Your element
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        Object aa=executor.executeScript("var items = {}; for (index = 0; index < arguments[0].attributes.length; ++index) { items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value }; return items;", element);
        return (aa.toString());
    }

}
