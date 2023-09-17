//модель страницы входа
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.constant.SitePath.LOGIN_URI;

public class LoginPage {
    private WebDriver driver;
    //ввод email
    private final By inputEmail = By.xpath(".//div/input[@type='text']");
    //ввод пароля
    private final By inputPassword = By.xpath(".//div/input[@type='password']");
    //кнопка вход
    private final By buttonEnter = By.xpath(".//form/button[text()='Войти']");
    //локатор для ссылки "Зарегистрироваться"
    private final By newUserLink = By.xpath(".//p/a[text()='Зарегистрироваться']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открываем страницу авторизации")
    public void open() {
        driver.get(LOGIN_URI);
    }

    @Step("Заполняем логин")
    public void inputEmail(String email) {
        driver.findElement(inputEmail).clear();
        driver.findElement(inputEmail).sendKeys(email);
    }
    @Step("Заполняем пароль")
    public void inputPassword(String password) {
        driver.findElement(inputPassword).clear();
        driver.findElement(inputPassword).sendKeys(password);
    }

    //заполняем данные пользователя
    @Step("Заполняем данные пользователя")
    public void fillInfo(String email, String password) {
        inputEmail(email);
        inputPassword(password);
    }

    @Step("нажимаем кнопку войти на странице авторизации после того, как введены данные пользователя")
    public void clickButtonEnter() {
        driver.findElement(buttonEnter).click();
    }

    @Step("нажимаем ссылку Зарегистироваться на странице авторизации")
    public void clickButtonRegister() {
        driver.findElement(newUserLink).click();
    }

    @Step("проверяем наличие кнопки войти после логаута")
    public String getButtonEnterText() {
        return driver.findElement(buttonEnter).getText();
    }
}
