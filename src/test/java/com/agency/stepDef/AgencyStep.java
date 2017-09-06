package com.agency.stepDef;


import com.agency.pageObj.*;
import com.support.hooks.Hooks;
import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class AgencyStep {
    WebDriver driver = Hooks.getDriver();
    LoginPageAg loginAg;
    ProfileListPage profileAg;
    AddProfilePage addProfileAg;
    EditProfilePage editProfileAg;
    OperatorListPage operatorList;


    // ---------------- Given step ----------------
    @Given("^the agency main page$")
    public void the_agency_login_page() throws Throwable {
        loginAg = new LoginPageAg(driver);
        loginAg.navigateToAgency();
        loginAg.loginAsLadyBugs();
        profileAg = new ProfileListPage(driver);
        profileAg.checkProfileListPage();
    }

    @Given("^the agency \"([^\"]*)\" main page$")
    public void theAgencyMainPage(String title) throws Throwable {
        loginAg = new LoginPageAg(driver);
        loginAg.navigateToAgency();
        loginAg.loginAsAgencyFromJSON(title);
        profileAg = new ProfileListPage(driver);
        profileAg.checkProfileListPage();
    }

    @Given("^the agency with email \"([^\"]*)\" and pass \"([^\"]*)\" main page$")
    public void the_agency_login_page(String email, String pass) throws Throwable {
        loginAg = new LoginPageAg(driver);
        loginAg.navigateToAgency();
        loginAg.loginAsAgency(email, pass);
        profileAg = new ProfileListPage(driver);
        profileAg.checkProfileListPage();
    }

    @Given("^the prod agency with email \"([^\"]*)\" and pass \"([^\"]*)\" main page$")
    public void prod_agency_login_page(String email, String pass) throws Throwable {
        loginAg = new LoginPageAg(driver);
        loginAg.navigateToProdAgency();
        loginAg.loginAsAgency(email, pass);
        profileAg = new ProfileListPage(driver);
        profileAg.checkProfileListPage();
    }

//    @Given("^the Bloomy admin main page$")
//    public void theBloomyAdminMainPage() throws Throwable {
//        login = new LoginPage(driver);
//        login.navigateToAdm();
//        login.loginAsAdmin();
//        nav = new MainPage(driver);
//        nav.checkForMainPage();
//    }
//
//
//    // ---------------- When step ----------------
    @When("^go to page add profile$")
    public void logging_in_as_an_admin() throws Throwable {
        profileAg = new ProfileListPage(driver);
        profileAg.addGirl();
        addProfileAg = new AddProfilePage(driver);
        addProfileAg.checkAddProfilePage();
    }

    @And("^create new profiles in agency from table:$")
    public void createNewProfiles (DataTable newProfiles) throws Throwable {
        List<List<String>> dataProfiles = newProfiles.raw();
        addProfileAg = new AddProfilePage(driver);
        for (int i = 1; i < dataProfiles.size(); i++) {
            List list = dataProfiles.get(i);
            addProfileAg.createProfileAg(list);
            profileAg = new ProfileListPage(driver);
            profileAg.addGirl();
        }
    }

    @When("^edit profile (\\w+)$")
    public void editProfile(String idUser) throws Throwable {
        profileAg = new ProfileListPage(driver);
        profileAg.editUserWithId(idUser);
    }

    @And("^change password for profile (\\w+)$")
    public void changePasswordForProfile(String idUser) throws Throwable {
        editProfileAg = new EditProfilePage(driver);
//        editProfileAg.changePass();
    }

    @When("^go to page operator list$")
    public void goToPageOperatorList() throws Throwable {
        profileAg = new ProfileListPage(driver);
        profileAg.operatorPage();
        operatorList = new OperatorListPage(driver);
        operatorList.checkOperatorListPage();
    }

    @And("^create operator from table with ID girls:$")
    public void createOperatorFromTable(DataTable newOperator) throws Throwable {
        List<List<String>> dataOperator = newOperator.raw();
        operatorList = new OperatorListPage(driver);
        for (int i = 1; i < dataOperator.size(); i++) {
            List list = dataOperator.get(i);
            operatorList.createOperator(list);
            profileAg = new ProfileListPage(driver);
            profileAg.operatorPage();
        }
    }

    @And("^create operator from table with name girls:$")
    public void createOperatorWithNameFromTable(DataTable newOperator) throws Throwable {
        List<List<String>> dataOperator = newOperator.raw();
        operatorList = new OperatorListPage(driver);
        for (int i = 1; i < dataOperator.size(); i++) {
            List list = dataOperator.get(i);
            operatorList.createOperatorWithNameGirl(list);
            profileAg = new ProfileListPage(driver);
            profileAg.operatorPage();
        }
    }

    @And("^create operator from table with new girls:$")
    public void createOperatorWithNewFromTable(DataTable newOperator) throws Throwable {
        List<List<String>> dataOperator = newOperator.raw();
        operatorList = new OperatorListPage(driver);
        for (int i = 1; i < dataOperator.size(); i++) {
            List<String> list = dataOperator.get(i);
            profileAg = new ProfileListPage(driver);
            profileAg.addGirl();
            addProfileAg = new AddProfilePage(driver);
            addProfileAg.createFewGirlsFromString(list.get(2));
            profileAg.operatorPage();
            operatorList = new OperatorListPage(driver);
            operatorList.createOperatorWithNameGirl(list);
        }
    }

    @When("^edit and change pass to \"([^\"]*)\" to all girls$")
    public void editAndChangePassToToAllGirls(String newPass) throws Throwable {
        profileAg = new ProfileListPage(driver);
        profileAg.changePassForAllGirls(newPass);
    }

    @When("^upload any photo to all profiles in agency")
    public void uploadAnyPhotoToAllProfiles() throws Throwable {
        profileAg = new ProfileListPage(driver);
        profileAg.uploadPhotoToAll();
    }


//
//    @When("^from side menu search only men account$")
//    public void fromSideMenuSearchOnlyMenAccount() throws Throwable {
//        nav = new MainPage(driver);
//        nav.searchMen();
//    }
//
//    @When("^from side menu create new user$")
//    public void fromSideMenuCreateNewUser() throws Throwable {
//        nav = new MainPage(driver);
//        nav.openCreateNewUser();
//    }
//
//    @And("^create user from table:$")
//    public void createUserFromTable(DataTable newUserCreditnails) throws Throwable {
//        List<List<String>> data = newUserCreditnails.raw();
//        newUsr = new NewUserPage(driver);
//        newUsr.createUsers(data);
//
//    }
//
//
//    // ---------------- Then steep ----------------
//    @Then("^the home page admin is available$")
//    public void the_home_page_admin_is_available() throws Throwable {
//        nav = new MainPage(driver);
//        nav.checkForMainPage();
//    }
//
//    @Then("^check search men account$")
//    public void checkSearchMenAccount() throws Throwable {
//        nav = new MainPage(driver);
//        nav.checkMenSearch();
//    }


//    // ---------------- Hooks ---------------------
//    @Before
//    public void startUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\\\auto_bloomy\\chromedriver\\chromedriver.exe");
//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--window-size=1000,1000");
//        driver = new ChromeDriver(option);
//    }
//
//    @After
//    public void tearDown() {
//        try {
//            // thread to sleep for 2000 milliseconds
//            Thread.sleep(5000);
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        driver.quit();
//    }
}
