//модель страницы восстановления пароля
package stellarsburgers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static stellarsburgers.constant.SitePath.FORGOT_URL;
public class ForgotPasswordPage {
    private WebDriver driver;
    //Локатор для ссылки "Войти"
    private final By enterLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }
    @Step("Открывам страницу восстановления пароля")
    public void open() {
        driver.get(FORGOT_URL);
    }
    @Step("Нажимаем ссылку Войти")
    public void enterLinkClick() {
        driver.findElement(enterLink).click();
    }
}
