package com.agency.pageObj;

import com.support.helper.Girl;
import com.support.helper.WorkerJSON;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.support.helper.Linker.agencyLink;

public class EditProfilePage extends BasePageAg {
    public EditProfilePage(WebDriver driver) {
        super(driver);
    }

    // ------------------------------ Locators ------------------------------
    private final By userIdLoc = By.xpath("//*[@id=\"edit-form\"]/div[1]/div/p");
    private final By userLoginLoc = By.xpath("//*[@id=\"edit-form\"]/div[2]/div/p");
    private final By btnSaveLoc = By.xpath("//*[@id='edit-form']/div[15]/div/input");
    private final By changePassBtn = By.xpath("//*[@id=\"edit-form\"]/div[3]/div[1]/a");
    private final By changePassForm = By.xpath("//*[@id=\"edit-form\"]/div[3]/div[2]/input");
    private final By uploadVerPhotoBtn = By.xpath("//*[@id=\"edit-form\"]/div[6]/div[4]/div/div/div[3]/div[2]/a");
    private final By uploadPhotoBtn = By.xpath("//*[@id=\"edit-form\"]/div[13]/div/div/div[3]/div[2]/a");
    private final By progressBarLoc = By.xpath("//*[@id=\"edit-form\"]/div[13]/div/div/div[2]/div/div");
    private final By progressVerBarText = By.xpath("//*[@id=\"edit-form\"]/div[6]/div[4]/div/div/div[3]/div[1]");

    private final By nameBox = By.xpath("//*[@id=\"edit-form\"]/div[4]/div/input");
    private final By birthdayBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[2]/div/input");
    private final By interestsBox = By.xpath("/html/body/div/div[2]/div[2]/div/form/div[5]/div/textarea");


    // ------------------------------ Actions ------------------------------
    public ProfileListPage changePass(String pass, Girl editedGirl) {
//        WebElement idUsr = driver.findElement(userIdLoc);
//        String value = idUsr.getAttribute("value");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions move = new Actions(driver);

        // Копируем id, имя, логин и пароль в объект
        editedGirl.setID(driver.findElement(userIdLoc).getAttribute("innerText"));
        editedGirl.setLogin(driver.findElement(userLoginLoc).getAttribute("innerText"));
        editedGirl.setName(driver.findElement(nameBox).getAttribute("value"));
        editedGirl.setPass(pass);

        // Меняем пароль
        move.click(driver.findElement(changePassBtn)).build().perform();
        wait.until(
                ExpectedConditions.elementToBeClickable(changePassForm));
        driver.findElement(changePassForm).sendKeys(pass);
        move.click(driver.findElement(btnSaveLoc)).build().perform();

        // Записываем всю информацию в файл в JSON формате
        WorkerJSON worker = new WorkerJSON();
        worker.writeGirlToJSON(driver.findElement(titleAgLoc).getAttribute("innerText"),editedGirl);

//        move.click(driver.findElement(By.xpath("/html/body/div/div[2]/div[1]/div[2]/ul/li[1]/a"))).build().perform();
        driver.navigate().back();
        driver.navigate().back();

        return new ProfileListPage(driver);
    }

    public ProfileListPage uploadPhotoSmpl() {
//        WebElement idUsr = driver.findElement(userIdLoc);
//        String value = idUsr.getAttribute("value");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Actions move = new Actions(driver);

        WebElement upload0 = driver.findElement(By.xpath("//*[@id=\"file-uploader0\"]"));
        upload0.sendKeys(WorkerJSON.BASE_PATH + "1.jpg");
        try {
            Thread.sleep(1000);
            move.click(driver.findElement(uploadVerPhotoBtn)).build().perform();
            wait.until(
                    ExpectedConditions.attributeContains(progressVerBarText, "innerText", "1 file selected"));
        } catch (Exception e) {
            System.out.println(e);
        }


        WebElement upload = driver.findElement(By.xpath("//*[@id=\"file-uploader1\"]"));
        upload.sendKeys(WorkerJSON.BASE_PATH+"1.jpg");
//        driver.findElement(By.id("file-submit")).click();
        try {
            Thread.sleep(1000);
            move.click(driver.findElement(uploadPhotoBtn)).build().perform();
            wait.until(
                    ExpectedConditions.attributeContains(progressBarLoc, "innerText", "100%"));
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }

        move.click(driver.findElement(btnSaveLoc)).build().perform();
        try {
            // thread to sleep for 5000 milliseconds
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }
        driver.navigate().to(agencyLink+"/profiles/");
        return new ProfileListPage(driver);
    }

    // ------------------------------ Assertions ------------------------------
    public EditProfilePage checkEditProfilePage(String id) {
//        WebElement headerPage = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/h2"));
//        String value = headerPage.getAttribute("value");
//        Assert.assertTrue(value.equals("Register profile"));
//        return new EditProfilePage(driver);

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(
                ExpectedConditions.attributeContains(headerPageLoc, "innerText", "Edit profile"));

        WebElement idUsrOnPage = driver.findElement(userIdLoc);
        String idUserString = idUsrOnPage.getAttribute("innerText");
        if (!idUserString.equals(id)) { Assert.fail("Id is't correct"); }

        return new EditProfilePage(driver);
    }

}
