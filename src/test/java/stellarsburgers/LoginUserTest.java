//тестируем авторизацию пользователя
package stellarsburgers;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import stellarsburgers.User.UserClient;
import stellarsburgers.User.UserModel;

import java.util.concurrent.TimeUnit;

import static stellarsburgers.Driver.WebDriverCreator.createChromeDriver;
import static stellarsburgers.Utils.Utils.*;
import static stellarsburgers.constant.SitePath.PROFILE_URI;

public class LoginUserTest {
    private WebDriver driver;
    private String accessToken = "";
    private UserClient userClient;
    private UserModel user;

    @Before
    public void setUp() {
        driver = createChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        //созадаем пользователя
        userClient = new UserClient();
        user = new UserModel(randomName(), randomEmail(), randomPassword());
        Response response = userClient.create(user);
        accessToken = response.path("accessToken");
    }

    //проверяем вход в аккаунт через кнопку "Войти в аккаунт" на главной странице
    @Test
    @DisplayName("enterInAccountMainPageTest")
    public void enterInAccountMainPageTest() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        //нажимаем кнопку войти в аккаунт
        mainPage.buttonEnterClick();
        //нужно заполнить поля и нажать кнопку войти
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();
        //проверяем, что вход выполнился, для этого заходим в личный кабинет
        mainPage.toUserAccountClick();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(PROFILE_URI, actualResult);
    }


    //проверяем вход в аккаунт через кнопку Личный кабинет
    @Test
    @DisplayName("enterViaCabinet")
    public void enterViaCabinet() {
        MainPage mainPage = new MainPage(driver);
        mainPage.open();
        //нажимаем кнопку Личный кабинет
        mainPage.toUserAccountClick();
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();
        //переходим в личный кабинет и проверяем, что авторизация прошла
        mainPage.toUserAccountClick();

        AccountPage accountPage = new AccountPage(driver);
        //проверяем наличие кнопки выйти
        String actualResult = accountPage.getLogoutButtonText();
        Assert.assertEquals("Выход", actualResult);
    }

    //проверяем вход через кнопку в форме регистрации
    @Test
    @DisplayName("enterViaRegisterButtonTest")
    public void enterViaRegisterButtonTest() {
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage.open();
        registerPage.enterLinkClick();

        //заполняем данные
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();
        //переходим в личный кабинет и проверяем, что авторизация прошла
        MainPage mainPage = new MainPage(driver);
        mainPage.toUserAccountClick();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(PROFILE_URI, actualResult);
    }

    //проверяем вход через кнопку в форме восстановления пароля
    @Test
    @DisplayName("enterViaForgotPasswordTest")
    public void enterViaForgotPasswordTest() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.open();
        forgotPasswordPage.enterLinkClick();

        //заполняем данные и нажимаем кнопку войти
        LoginPage loginPage = new LoginPage(driver);
        loginPage.fillInfo(user.getEmail(), user.getPassword());
        loginPage.clickButtonEnter();
        //переходим в личный кабинет и проверяем, что авторизация прошла
        MainPage mainPage = new MainPage(driver);
        mainPage.toUserAccountClick();
        String actualResult = driver.getCurrentUrl();
        Assert.assertEquals(PROFILE_URI, actualResult);
    }

    @After
    public void tearsDown() {
        driver.quit();
        userClient.delete(accessToken);
    }

}
