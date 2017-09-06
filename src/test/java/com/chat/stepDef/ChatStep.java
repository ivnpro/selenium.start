package com.chat.stepDef;

import com.chat.pageObj.LoginPageChat;
import com.chat.pageObj.MainChatPage;
import com.support.hooks.Hooks;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.support.helper.WorkerJSON.getGirlByNameFromJSON;

public class ChatStep {
    WebDriver driver = Hooks.getDriver();
    LoginPageChat loginChat;
    MainChatPage chatPage;

    // ---------------- Given step ----------------
    @Given("^the chat from girl with name \"([^\"]*)\" from agency \"([^\"]*)\"$")
    public void login_chat_with_name(String nameGirl, String titleAg) throws Throwable {
        loginChat = new LoginPageChat(driver);
        loginChat.navigateToChat();
        loginChat.loginAsGirlObj(nameGirl, titleAg);
        chatPage = new MainChatPage(driver);
        chatPage.waitLogin();
        chatPage.checkGirlChat(getGirlByNameFromJSON(titleAg, nameGirl));
    }

    @When("^create all bombs$")
    public void createAllBombs() throws Throwable {
        chatPage = new MainChatPage(driver);
        chatPage.openInviteMng();
        chatPage.fillFieldsBomb("hello {name}", true, "English");
        chatPage.fillFieldsBomb("hi {name}", false, "English");
        chatPage.fillFieldsBomb("Здравствуй {name}", true, "Russian");
        chatPage.fillFieldsBomb("Привет {name}", false, "Russian");
        chatPage.closeInviteMng();

    }
}
