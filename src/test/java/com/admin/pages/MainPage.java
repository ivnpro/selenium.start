package com.admin.pages;

import com.support.helper.Agency;
import com.support.helper.WorkerJSON;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class MainPage extends BasePage {
    // ------------------------------ Locators ------------------------------
    // ------- Navigation -------
    private final By sideBarToggleSize = By.className("sr-only");

    // ------- Side Menu -------
    private final By sideMenuProfiles = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[2]/a/span");
    private final By sideMenuAgencies = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[6]/a/span");
//    private final By sideMenuPayments = By.className("fa fa-bomb");

    // ------- Profile menu -------
    private final By treeSearch = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[2]/ul/li[1]/a/span");
    private final By searchMan = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[2]/ul/li[1]/ul/li[2]/a/span");
    private final By createProfile = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[2]/ul/li[2]/a/span");
    private final By createAgency = By.xpath("/html/body/div[1]/aside[1]/section/ul/li[6]/ul/li/a/span");

    // ------- Create Agency -------
    private final By titleLoc = By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/form/div[1]/div/input");
    private final By emailLoc = By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/form/div[2]/div/input");
    private final By passLoc = By.xpath("/html/body/div[1]/div[1]/section[2]/div/div/form/div[3]/div/input");




    // ------------------------------ Actions ------------------------------
    // Создание объекта для действий и объекта для ожиданий
    private Actions move=new Actions(driver);
    private WebDriverWait wait=new WebDriverWait(driver, 10);

    // Конструктор главной страницы
    public MainPage(WebDriver driver) {
        super(driver);
    }

    // Открытие полноразмерного меню навигации
    private MainPage fullNavigateMenu() {
        WebElement toggleSizeBtn=driver.findElement(sideBarToggleSize);
        move.click(toggleSizeBtn).build().perform();

        return new MainPage(driver);
    }

    // Нажатие кнопок бокового меню по Xpath
    private MainPage clickBtnMenu (By locator) {
        WebElement btnToClick=wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator));
        move.click(btnToClick).build().perform();

        return new MainPage(driver);
    }


    public MainPage searchMen() {
        fullNavigateMenu();

        // Открытие подменю профилей
        WebElement menuProfiles=wait.until(
                ExpectedConditions.visibilityOfElementLocated(sideMenuProfiles));
        move.click(menuProfiles).build().perform();

        // Открытие подменю поиска
        WebElement treeSearchBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(treeSearch));
        move.click(treeSearchBtn).build().perform();

        // Поиск мужчины
        WebElement searchManBtn = wait.until(
                ExpectedConditions.visibilityOfElementLocated(searchMan));
        move.click(searchManBtn).build().perform();


        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new MainPage(driver);
    }

    public MainPage openCreateNewUser() {
        fullNavigateMenu();

        clickBtnMenu(sideMenuProfiles);
        clickBtnMenu(createProfile);

        return new MainPage(driver);
    }

    public MainPage openCreateAgency() {

        fullNavigateMenu();
        clickBtnMenu(sideMenuAgencies);
        clickBtnMenu(createAgency);

        return new MainPage(driver);
    }


    // Создание агенства по строке из таблицы внутри фичи
    public MainPage createAgency(List<String> dataAg) {
        Agency creatingAgency = new Agency(dataAg.get(0), dataAg.get(1), dataAg.get(2));
        WorkerJSON worker = new WorkerJSON();
        worker.writeAgencyToJSON(creatingAgency);

        driver.findElement(titleLoc).sendKeys(creatingAgency.getTitle());
        driver.findElement(emailLoc).sendKeys(creatingAgency.getEmail());
        driver.findElement(passLoc).sendKeys(creatingAgency.getPass());
        driver.findElement(passLoc).submit();

        move.click(driver.findElement(By.xpath("//*[@id=\"success-dialog\"]/div/div/form/div[3]/button"))).build().perform();

        return new MainPage(driver);
    }


    // ------------------------------ Assertions ------------------------------
    public MainPage checkForMainPage() {
        try {
            WebElement errorBtn = driver.findElement(By.linkText("Go to main page"));
            move.click(errorBtn).build().perform();

        } catch (NoSuchElementException ignored) {

        } finally {
            WebDriverWait waiter = new WebDriverWait(driver, 3);
            waiter.until(ExpectedConditions.visibilityOfElementLocated(By.className("logo-mini")));
        }
        return new MainPage(driver);
    }

    public MainPage checkMenSearch() {
        WebElement navList = driver.findElement(By.xpath("/html/body/div[1]/header/nav/form/div[1]/input"));
        String value = navList.getAttribute("value");
        Assert.assertTrue(value.equals("sex:1"));
//        if (value == "sex:1") {
//            System.out.println(value);
//            return new MainPage(driver);
//        }
//        else {
//
//        }
        return new MainPage(driver);
    }

}
