//модель страницы регистрации
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.constant.SitePath.REGISTER_URI;

public class RegisterPage {
    private WebDriver driver;
    //локатор для кнопки Зарегистрироваться
    private final By buttonRegister = By.xpath(".//button[text()='Зарегистрироваться']");
    //локатор для сообщения о том, что введет неверный пароль при регистрации
    private final By wrongPasswordMsg = By.xpath(".//div/p[@class='input__error text_type_main-default']");
    //локатор для ссылки Войти (для тестирования входа со страницы регистрации)
    private final By enterLink = By.xpath(".//div/p/a[text()='Войти']");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(REGISTER_URI);
    }

    @Step("проверяем, прошла ли регистрация")
    public String isRegister() {
        String result = driver.findElement(enterLink).getText();
        return result;
    }

    @Step("проверяем надпись Некорректный пароль, если ввели пароль, меньше 6 символов")
    public String passwordMsg() {
        String result = driver.findElement(wrongPasswordMsg).getText();
        return result;
    }

    @Step("нажимаем кнопку Зарегистрироватья")
    public void clickButtonRegister() {
        driver.findElement(buttonRegister).click();
    }

    @Step("нажимаем ссылку Войти на странице регистрации")
    public void enterLinkClick() {
        driver.findElement(enterLink).click();
    }
}
