package com.agency.pageObj;

import com.alibaba.fastjson.JSON;
import com.support.helper.Girl;
import com.support.helper.Operator;
import com.support.helper.WorkerJSON;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.support.helper.WorkerJSON.getGirlByNameFromJSON;


public class OperatorListPage extends BasePageAg{
    public OperatorListPage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------ Locators ------------------------------
    private final By newOperNameLoc = By.xpath("/html/body/div/div[2]/div[2]/form/div/div[1]/input");
    private final By headerPageLoc = By.xpath("/html/body/div/div[1]/div[2]/h2");
    private final By newOperRateLoc = By.xpath("/html/body/div/div[2]/div[2]/form/div/div[2]/input");
    private final By newOperGirlLoc = By.xpath("/html/body/div/div[2]/div[2]/form/div/div[3]/span/span[1]/span/ul/li/input");
    private final By newOperCreateBtnLoc = By.xpath("/html/body/div/div[2]/div[2]/form/div/div[4]/input");
    private final By newOperLoginLoc = By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[1]/div/p");
    private final By newOperPassLoc = By.xpath("/html/body/div/div[2]/div[2]/div[2]/div[2]/div/p");



    // ------------------------------ Actions ------------------------------

    // Старый метод использует ID девушек которыми оперировать грешно
    public OperatorListPage createOperator(List<String> dataOper) {
        Operator testOper = new Operator();
        String[] idGirl;

        // Заполнение полей Name и Rate
        driver.findElement(newOperNameLoc).sendKeys(dataOper.get(0));
        testOper.setName(dataOper.get(0));
        driver.findElement(newOperRateLoc).sendKeys(dataOper.get(1));
        testOper.setRate(dataOper.get(1));

        // Добавление девушек в поле OperGirl и завершение создания оператора
        idGirl = dataOper.get(2).split(", ");
        testOper.setListGirls(new ArrayList<String>(Arrays.asList(idGirl)));
        for (String i: idGirl) {
            driver.findElement(newOperGirlLoc).sendKeys(i);
            driver.findElement(newOperGirlLoc).sendKeys(Keys.RETURN);
        }
        driver.findElement(newOperCreateBtnLoc).submit();

        // Копирования логина и пароля
        testOper.setLogin(driver.findElement(newOperLoginLoc).getAttribute("innerHTML"));
        testOper.setPass(driver.findElement(newOperPassLoc).getAttribute("innerHTML"));

        // Запись объекта Operator в JSON формате в файл
        String titleAg = driver.findElement(titleAgLoc).getAttribute("innerHTML");
        try(FileWriter logFileAg = new FileWriter(WorkerJSON.BASE_PATH + "test\\java\\com\\support\\data\\agency-"+titleAg+".txt", true))
        {
            String jsonString = JSON.toJSONString(testOper);
            logFileAg.write(" \n"+"operator: "+jsonString);
        }
        catch(IOException ex){System.out.println(ex.getMessage());}

        return new OperatorListPage(driver);
    }

    public OperatorListPage createOperatorWithNameGirl(List<String> dataOper) {
        Operator testOper = new Operator();
        Girl addedGirl;
        String[] nameOperatorGirl;
        String titleAg = driver.findElement(titleAgLoc).getAttribute("innerHTML");

        // Заполнение полей Name и Rate
        driver.findElement(newOperNameLoc).sendKeys(dataOper.get(0));
        testOper.setName(dataOper.get(0));
        driver.findElement(newOperRateLoc).sendKeys(dataOper.get(1));
        testOper.setRate(dataOper.get(1));

        // Добавление девушек в поле OperGirl и завершение создания оператора
        nameOperatorGirl = dataOper.get(2).split(", ");
        testOper.setListGirls(new ArrayList<String>(Arrays.asList(nameOperatorGirl)));
        for (String i: nameOperatorGirl) {
            // Тащим девушку из JSON по ее имени
            addedGirl = getGirlByNameFromJSON(titleAg, i);
            String idAddGirl = addedGirl.getID();

            // Добавляем девушку по ID в поле добавления девушек при создании оператора
            driver.findElement(newOperGirlLoc).sendKeys(idAddGirl);
            driver.findElement(newOperGirlLoc).sendKeys(Keys.RETURN);

            // записываем девушке нового оператора
            addedGirl.setOperName(dataOper.get(0));
            WorkerJSON worker = new WorkerJSON();
            worker.writeGirlToJSON(titleAg, addedGirl);
        }
        driver.findElement(newOperCreateBtnLoc).submit();

        // Копирования логина и пароля
        testOper.setLogin(driver.findElement(newOperLoginLoc).getAttribute("innerHTML"));
        testOper.setPass(driver.findElement(newOperPassLoc).getAttribute("innerHTML"));

        // Запись объекта Operator в JSON формате в файл
        WorkerJSON worker = new WorkerJSON();
        worker.writeOperatorToJSON(titleAg, testOper);




        return new OperatorListPage(driver);
    }


    // ------------------------------ Assertions ------------------------------

    public AddProfilePage checkOperatorListPage() {
//        WebElement headerPage = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/h2"));
//        String value = headerPage.getAttribute("value");
//        Assert.assertTrue(value.equals("Register profile"));
//        return new AddProfilePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.attributeContains(headerPageLoc, "innerText", "Operators list"));
        return new AddProfilePage(driver);
    }
}
