//модель формы для заполнения данных для страницы регистрации
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserInfo {
    private WebDriver driver;
    //локаторы для ввода данных для регистрации
    //для заполнения имени и логина пользователя использую один локалор, получаю все элементы и выбираю нужные а методах
    private final By nameInput = By.xpath(".//div/input[@class='text input__textfield text_type_main-default']");
    //email
    private final By passwordInput = By.xpath(".//div/input[@type='password']");

    public UserInfo(WebDriver driver) {
        this.driver = driver;
    }

    @Step("заполянем имя")
    public void inputName(String name) {
        driver.findElements(nameInput).get(0).clear();
        driver.findElements(nameInput).get(0).sendKeys(name);
    }

    @Step("заполняем email")
    public void inputEmail(String email) {
        driver.findElements(nameInput).get(1).clear();
        driver.findElements(nameInput).get(1).sendKeys(email);
    }

    @Step("заполняем пароль")
    public void inputPassword(String password) {
        driver.findElement(passwordInput).clear();
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("заполняем всю информацию о пользователе")
    public void fillUserInfo(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
    }
}
