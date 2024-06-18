package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage {
    private final WebDriver driver;

    public LoginPage (WebDriver driver) {
        this.driver = driver;
    }

    //Элементы:
    /** Поле "E-mail" */
    private final By EMAIL_FIELD = By.xpath(".//input[@id='loginEmail']");
    /** Поле "Пароль" */
    private final By PASSWORD_FIELD = By.xpath(".//input[@id='loginPassword']");
    /** Кнопка "Вход" */
    private final By LOGIN_BUTTON = By.xpath(".//button[@id='authButton']");


    //Методы:
    /** Заполнить поле "E-mail" */
    public void setEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }
    /** Заполнить поле "Пароль" */
    public void setPassword (String password) {
        driver.findElement(PASSWORD_FIELD).sendKeys(password);
    }
    /** Кликнуть по кнопке "Вход" */
    public void clickLoginButton() {
        driver.findElement(LOGIN_BUTTON).click();
    }
    /** Заполнить форму авторизации и войти */
    public void login (String email, String password) {
        setEmail(email);
        setPassword(password);
        clickLoginButton();
    }



}
