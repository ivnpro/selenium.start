    package com.admin.stepdef;

    import com.admin.api.FeatureSet;
    import com.admin.pages.LoginPage;
    import com.admin.pages.MainPage;
    import com.admin.pages.NewUserPage;
    import com.support.hooks.Hooks;
    import cucumber.api.DataTable;
    import cucumber.api.java.en.And;
    import cucumber.api.java.en.Given;
    import cucumber.api.java.en.Then;
    import cucumber.api.java.en.When;
    import org.openqa.selenium.WebDriver;

    import java.util.List;

    public class AdminStep {
    WebDriver driver = Hooks.getDriver();
    LoginPage login;
    MainPage nav;
    NewUserPage newUsr;


        // ---------------- Given step ----------------
    @Given("^the Bloomy admin login page$")
    public void the_Bloomy_admin_login_page() throws Throwable {
        login = new LoginPage(driver);
        login.navigateToAdm();
    }

    @Given("^the Bloomy admin main page$")
    public void theBloomyAdminMainPage() throws Throwable {
        login = new LoginPage(driver);
        login.navigateToAdm();
        login.loginAsAdmin();
        nav = new MainPage(driver);
        nav.checkForMainPage();
    }

    @Given("^set some feature$")
    public void setSomeFeature() throws Throwable {
        FeatureSet.main();
    }


    // ---------------- When step ----------------
    @When("^logging in as an admin$")
    public void logging_in_as_an_admin() throws Throwable {
        login.loginAsAdmin();
    }

    @When("^from side menu search only men account$")
    public void fromSideMenuSearchOnlyMenAccount() throws Throwable {
        nav = new MainPage(driver);
        nav.searchMen();
    }

    @When("^from side menu create new user$")
    public void fromSideMenuCreateNewUser() throws Throwable {
        nav = new MainPage(driver);
        nav.openCreateNewUser();
    }

    @And("^create user from table:$")
    public void createUserFromTable(DataTable newUserCreditnails) throws Throwable {
        List<List<String>> data = newUserCreditnails.raw();
        newUsr = new NewUserPage(driver);
//        newUsr.createUser(data);
        for (int i = 1; i < data.size(); i++) {
            List list = data.get(i);
            newUsr.createUser(list);
        }
    }

    @When("^create agency from table:$")
    public void createAgencyFromTable(DataTable newAgency) throws Throwable {
        List<List<String>> dataAgency = newAgency.raw();
        nav = new MainPage(driver);
        for (int i = 1; i < dataAgency.size(); i++) {
            List list = dataAgency.get(i);
            nav.openCreateAgency();
            nav.createAgency(list);
        }

    }


    // ---------------- Then steep ----------------
    @Then("^the home page admin is available$")
    public void the_home_page_admin_is_available() throws Throwable {
            nav = new MainPage(driver);
            nav.checkForMainPage();
        }

    @Then("^check search men account$")
    public void checkSearchMenAccount() throws Throwable {
        nav = new MainPage(driver);
        nav.checkMenSearch();
    }



        // ---------------- Hooks ---------------------
//    @Before
//    public void startUp() {
//        System.setProperty("webdriver.chrome.driver", "C:\\\\auto_bloomy\\chromedriver\\chromedriver.exe");
//        ChromeOptions option = new ChromeOptions();
//        option.addArguments("--window-size=1500,1000");
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
    }
