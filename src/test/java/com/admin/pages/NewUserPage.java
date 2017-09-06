package com.admin.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class NewUserPage extends BasePage {

    // ------------------------------ Locators ------------------------------

    private final By boxName = By.xpath("/html/body/div/div[1]/section[2]/div/div/form/div[1]/div/input");
    private final By boxEmail = By.xpath("/html/body/div/div[1]/section[2]/div/div/form/div[2]/div/input");
    private final By boxBirthday = By.xpath("/html/body/div/div[1]/section[2]/div/div/form/div[4]/div/input");


    // ------------------------------ Actions ------------------------------
    // Конструктор главной страницы
    public NewUserPage (WebDriver driver) {
        super(driver);
        JavascriptExecutor js = (JavascriptExecutor)driver;
    }

    public NewUserPage createUser(List<String> usrData) {
//        Actions move = new Actions(driver);

        WebElement name = driver.findElement(boxName);
        WebElement email = driver.findElement(boxEmail);
        WebElement birthday = driver.findElement(boxBirthday);


        for (int i1 = 0; i1 < usrData.size(); i1++) {
            Object valueList = usrData.get(i1);
            String value = (String) valueList;
            switch (i1){
                case 0:
                    name.sendKeys(value);
                    break;
                case 1:
                    email.sendKeys(value);
                    break;
                case 2:
                    birthday.sendKeys(value);
                    break;
            }


            try {
                // thread to sleep for 5000 milliseconds
                Thread.sleep(3000);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
//        move.click(loginBtn).build().perform();
        return new NewUserPage(driver);
    }

    // ------------------------------ Assertions ------------------------------

}
