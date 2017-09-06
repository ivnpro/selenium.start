package com.agency.pageObj;

import com.support.helper.Girl;
import com.support.helper.WorkerJSON;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class AddProfilePage extends BasePageAg {
    public AddProfilePage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------ Locators ------------------------------
    private final By nameBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[1]/div/input");
    private final By nameFirstBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div/input");
    private final By nameLastBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[3]/div/input");
    private final By birthdayBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[4]/div/input");
    private final By interestsBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[7]/div/textarea");
    private final By aboutBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[8]/div/textarea");
    private final By registerBtnLoc = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[10]/div/input");

    // After registration ----
    private final By idGirlLoc = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[1]/div/p");
    private final By loginGirlLoc = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div/p");
    private final By passGirlLoc = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[3]/div/p");
    private final By editGirlProfileLink = By.xpath("/html/body/div/div[2]/div[2]/div/div/a[1]");



    // ------------------------------ Actions ------------------------------
    public AddProfilePage createProfileAg(List<String> usrData) {
//        Actions move = new Actions(driver);
        Girl testGirl = new Girl();
        WebDriverWait wait = new WebDriverWait(driver, 10);

        // Вводим имя, др и интерсы, и ждем появления следующей страницы
        driver.findElement(nameBox).sendKeys(usrData.get(0));
        testGirl.setName(usrData.get(0));
        driver.findElement(nameFirstBox).sendKeys("testF");
        driver.findElement(nameLastBox).sendKeys("testL");
        driver.findElement(birthdayBox).sendKeys(usrData.get(1));
        driver.findElement(interestsBox).sendKeys(usrData.get(2));
        driver.findElement(aboutBox).sendKeys("testA");
        driver.findElement(registerBtnLoc).submit();
        wait.until(ExpectedConditions.attributeContains(
                        By.xpath("/html/body/div/div[2]/div[2]/div/form/div[1]/label/span"), "innerText", "User Id"));

        // Дополняем объект ID, логином и паролем
        testGirl.setID(driver.findElement(idGirlLoc).getAttribute("innerText"));
        testGirl.setLogin(driver.findElement(loginGirlLoc).getAttribute("innerText"));
        testGirl.setPass(driver.findElement(passGirlLoc).getAttribute("innerText"));

        // Записываем всю информацию в файл в JSON формате
        String titleAg = driver.findElement(titleAgLoc).getAttribute("innerText");
        WorkerJSON worker = new WorkerJSON();
        worker.writeGirlToJSON(titleAg, testGirl);

        return new AddProfilePage(driver);
    }

    public AddProfilePage createFewGirlsFromString(String girlName) {
//        Actions move = new Actions(driver);
        String[] nameGirls;
        WebDriverWait wait = new WebDriverWait(driver, 10);

        nameGirls = girlName.split(", ");
        for (String i: nameGirls) {
            Girl testGirl = new Girl();

            driver.findElement(nameBox).sendKeys(i);
            testGirl.setName(i);
            driver.findElement(registerBtnLoc).submit();

            wait.until(ExpectedConditions.attributeContains(
                    By.xpath("/html/body/div/div[2]/div[2]/div/form/div[1]/label/span"), "innerText", "User Id"));

            // Дополняем объект ID, логином и паролем
            testGirl.setID(driver.findElement(idGirlLoc).getAttribute("innerText"));
            testGirl.setLogin(driver.findElement(loginGirlLoc).getAttribute("innerText"));
            testGirl.setPass(driver.findElement(passGirlLoc).getAttribute("innerText"));

            // Записываем всю информацию в файл в JSON формате
            String titleAg = driver.findElement(titleAgLoc).getAttribute("innerText");
            WorkerJSON worker = new WorkerJSON();
            worker.writeGirlToJSON(titleAg, testGirl);

            ProfileListPage profileAg = new ProfileListPage(driver);
            profileAg.addGirl();
        }

        return new AddProfilePage(driver);
    }

    // ------------------------------ Assertions ------------------------------
    public AddProfilePage checkAddProfilePage() {
//        WebElement headerPage = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/h2"));
//        String value = headerPage.getAttribute("value");
//        Assert.assertTrue(value.equals("Register profile"));
//        return new AddProfilePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.attributeContains(headerPageLoc, "innerText", "Register profile"));
        return new AddProfilePage(driver);
    }

}
