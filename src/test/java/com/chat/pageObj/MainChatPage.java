package com.chat.pageObj;

import com.support.helper.Girl;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainChatPage extends BasePageChat{
    public MainChatPage(WebDriver driver) {
        super(driver);
    }
    // ------------------------------ Locators ------------------------------
    private final By girlChatBadge = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[1]/div[2]/div[1]/div[1]");
    private final By inviteMngBtn = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[1]/button[1]");
    private final By closeInvitePanelBtn = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/button/span");
    private final By bombMsgInputField = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/form/div[1]/input");
    private final By radioBtnNU = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/form/div[2]/div[1]/div[1]/label/input");
    private final By radioBtnHC = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/form/div[2]/div[1]/div[2]/label/input");
    private final By selectorLocaleBomb = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/form/div[2]/div[2]/select");
    private final By addBombBtn = By.xpath("//*[@id=\"chat\"]/div/div[1]/div[3]/div[2]/fieldset/form/div[3]/button");
    private final By searchInputField = By.xpath("//*[@id=\"chat\"]/div/div[2]/div[2]/div[1]/div/div/input");
    private final By searchBtn = By.xpath("//*[@id=\"chat\"]/div/div[2]/div[2]/div[1]/div/div/span[2]/button");
    private final By searchBadge = By.xpath("//*[@id=\"chat\"]/div/div[2]/div[2]/div[2]/div/ul/li/div/div[1]");


    // ------------------------------ Actions ------------------------------
    public MainChatPage openInviteMng(){
        Actions move = new Actions(driver);
        WebElement btnInviteMng = driver.findElement(inviteMngBtn);
        move.click(btnInviteMng).build().perform();
        return new MainChatPage(driver);
    }

    public MainChatPage closeInviteMng(){
        try {
            Actions move = new Actions(driver);
            WebElement btnCloseInvitePanel = driver.findElement(closeInvitePanelBtn);
            move.click(btnCloseInvitePanel).build().perform();
        } catch (NoSuchElementException error) {
            System.out.println("Панель инвайтов не открыта");
        }
        return new MainChatPage(driver);
    }

    public MainChatPage fillFieldsBomb (String msgBomb, boolean bombForNewUsr, String locale) {
        driver.findElement(bombMsgInputField).sendKeys(msgBomb);
        Actions move = new Actions(driver);

        if (bombForNewUsr)  move.click(driver.findElement(radioBtnNU)).build().perform();
        else move.click(driver.findElement(radioBtnHC)).build().perform();

        Select select = new Select(driver.findElement(selectorLocaleBomb));
        select.selectByVisibleText(locale);

        move.click(driver.findElement(addBombBtn)).build().perform();
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new MainChatPage(driver);
    }




    // ------------------------------ Assertions ------------------------------
    public MainChatPage waitLogin(){
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        waiter.until(ExpectedConditions.visibilityOfElementLocated(girlChatBadge));
        return new MainChatPage(driver);
    }

    public MainChatPage checkGirlChat(Girl girl){
        try {
            WebElement girlBadge = driver.findElement(girlChatBadge);
            String value = girlBadge.getAttribute("outerText");
            value = value.split(",")[0];
            Assert.assertTrue(value.equals(girl.getName()));
            waitLoad(girl);
        } catch (AssertionError error){
            System.out.print("ID девушки не сходится с ожидаемым");
        }
        return new MainChatPage(driver);
    }


    private void waitLoad(Girl girl){
        WebDriverWait waiter = new WebDriverWait(driver, 10);
        Actions move = new Actions(driver);
        if (!girl.getID().equals("1")) {
            driver.findElement(searchInputField).sendKeys("1");
            move.click(driver.findElement(searchBtn)).build().perform();
            waiter.until(ExpectedConditions.attributeContains(searchBadge, "title", "User ID is 1"));
        }
        else {
            driver.findElement(searchInputField).sendKeys("2");
            move.click(driver.findElement(searchBtn)).build().perform();
            waiter.until(ExpectedConditions.attributeContains(searchBadge, "title", "User ID is 2"));
        }

        new MainChatPage(driver);
    }
}
