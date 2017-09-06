package com.agency.pageObj;


import com.support.helper.Girl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfileListPage extends BasePageAg {
    public ProfileListPage(WebDriver driver) {
        super(driver);
    }
    // ------------------------------ Locators ------------------------------
    private final By badgeGirls = By.xpath("/html/body/div/div[2]/div[1]/div[2]/ul/li[1]/a/span");
    private final By nextPageLoc = By.xpath("html/body/div[1]/div[2]/div[2]/div[1]/ul/li[*]/a[contains(text(), 'Next')]");



    // ------------------------------ Actions ------------------------------
    public ProfileListPage addGirl() {
        Actions move=new Actions(driver);

        // Открытие страцниы создания новых пользователей
        move.click(driver.findElement(addNewGirlLoc)).build().perform();

        return new ProfileListPage(driver);
    }

    public ProfileListPage operatorPage() {
        Actions move=new Actions(driver);

        // Открытие страцниы создания новых операторов
        move.click(driver.findElement(operatorListLoc)).build().perform();

        return new ProfileListPage(driver);
    }

    public ProfileListPage editUserWithId(String idUser) {
        // По бейджу на кнопке Girl определяем общее количество девушек в агенстве
        WebElement badgeAllProfiles = driver.findElement(badgeGirls);
        String string2Count = badgeAllProfiles.getAttribute("innerHTML");
        String stringGirlsCount = string2Count.substring(string2Count.indexOf("/")+1);

        // Находим количество страниц по найденному значению
        int countOfPage = (Integer.parseInt(stringGirlsCount)/20) + 1;

        // Первый цикл будет отвечать за навигацию по страницам
        for (int i=1; i<=countOfPage; i++) {
            // Кликаем по кнопке Next для перехода на следующую страницу кроме первой
            if (i>1) {
                WebElement nextPageBtn = driver.findElement(nextPageLoc);
                Actions move = new Actions(driver);
                move.click(nextPageBtn).build().perform();
            }

            // Определяем количество девушек на странице
            int girlsOnPage = 20;
            if (i==countOfPage) {
                girlsOnPage = Integer.parseInt(stringGirlsCount)%20;
            }
            // Второй цикл сканирует девушек на странице, на соответствие указанному ID.
            for (int i0 = 2; i0 < (girlsOnPage+2); i0++) {
                WebElement userIdFromPage = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[" + i0 + "]/div/div/div/div[2]"));
                String stringWithId = userIdFromPage.getAttribute("innerHTML");
                String idFromPage = stringWithId.substring(9);
                if (idFromPage.equals(idUser)) {
                    WebElement btnEditUsr = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[" + i0 + "]/div/div/div/h4/a[2]"));
                    Actions move = new Actions(driver);
                    move.click(btnEditUsr).build().perform();
                    return new ProfileListPage(driver);
                }
            }
        }
        System.out.print("Не нашли искомого пользователя");
        return new ProfileListPage(driver);
    }


    public ProfileListPage changePassForAllGirls(String password) {
        // По бейджу на кнопке Girl определяем общее количество девушек в агенстве
        WebElement badgeAllProfiles = driver.findElement(badgeGirls);
        String string2Count = badgeAllProfiles.getAttribute("innerHTML");
        String stringGirlsCount = string2Count.substring(string2Count.indexOf("/")+1);

        // Находим количество страниц по найденному значению
        int countOfPage = (Integer.parseInt(stringGirlsCount)/20) + 1;

        // Первый цикл будет отвечать за навигацию по страницам
        for (int i=1; i<=countOfPage; i++) {
            // Кликаем по кнопке Next для перехода на следующую страницу кроме первой
            if (i>1) {
                WebElement nextPageBtn = driver.findElement(nextPageLoc);
                Actions move = new Actions(driver);
                move.click(nextPageBtn).build().perform();
            }

            // Определяем количество девушек на странице
            int girlsOnPage = 20;
            if (i==countOfPage) {
                girlsOnPage = Integer.parseInt(stringGirlsCount)%20;
            }
            // Второй цикл сканирует девушек на странице, на соответствие указанному ID.
            for (int i0 = 2; i0 < (girlsOnPage+2); i0++) {
                Girl editedGirl = new Girl();
                Actions move = new Actions(driver);

                // Если у девушки указан оператор - запомнить его в объекте
                String operName = driver.findElement(
                        By.xpath("/html/body/div/div[2]/div[2]/div["+i0+"]/div/div/div/div[*]")).getAttribute("innerText");
                if (operName.length()>10) { editedGirl.setOperName(operName.substring(operName.indexOf(":")+2)); }

                // Клик на кнопку edit
                WebElement btnEditUsr = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[" + i0 + "]/div/div/div/h4/a[2]"));
                move.click(btnEditUsr).build().perform();

                // Смена пароля, и возвращение обратно на страницу
                EditProfilePage test = new EditProfilePage(driver);
                test.changePass(password, editedGirl);


            }
        }
        return new ProfileListPage(driver);
    }

    public ProfileListPage uploadPhotoToAll() {
        // По бейджу на кнопке Girl определяем общее количество девушек в агенстве
        WebElement badgeAllProfiles = driver.findElement(badgeGirls);
        String string2Count = badgeAllProfiles.getAttribute("innerHTML");
        String stringGirlsCount = string2Count.substring(string2Count.indexOf("/")+1);

        // Находим количество страниц по найденному значению
        int countOfPage = (Integer.parseInt(stringGirlsCount)/20) + 1;

        // Первый цикл будет отвечать за навигацию по страницам
        for (int i=1; i<=countOfPage; i++) {
            // Кликаем по кнопке Next для перехода на следующую страницу кроме первой
            if (i>1) {
                WebElement nextPageBtn = driver.findElement(nextPageLoc);
                Actions move = new Actions(driver);
                move.click(nextPageBtn).build().perform();
            }

            // Определяем количество девушек на странице
            int girlsOnPage = 20;
            if (i==countOfPage) {
                girlsOnPage = Integer.parseInt(stringGirlsCount)%20;
            }
            // Второй цикл сканирует девушек на странице, на соответствие указанному ID.
            for (int i0 = 2; i0 < (girlsOnPage+2); i0++) {
                Actions move = new Actions(driver);

                // Клик на кнопку edit
                WebElement btnEditUsr = driver.findElement(By.xpath("/html/body/div/div[2]/div[2]/div[" + i0 + "]/div/div/div/h4/a[2]"));
                move.click(btnEditUsr).build().perform();

                // Смена пароля, и возвращение обратно на страницу
                EditProfilePage test = new EditProfilePage(driver);
                test.uploadPhotoSmpl();
            }
        }
        return new ProfileListPage(driver);
    }




    // ------------------------------ Assertions ------------------------------
    public ProfileListPage checkProfileListPage() {
//        WebElement headerPage = driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/h2"));
//        String value = headerPage.getAttribute("value");
//        Assert.assertTrue(value.equals("Profile list"));
//        return new ProfileListPage(driver);
        WebDriverWait wait=new WebDriverWait(driver, 10);
        wait.until(
                ExpectedConditions.attributeContains(headerPageLoc, "innerText", "Profile list"));
        return new ProfileListPage(driver);
    }

}
