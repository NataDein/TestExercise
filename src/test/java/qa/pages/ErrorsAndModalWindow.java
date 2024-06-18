package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ErrorsAndModalWindow {
    private final WebDriver driver;

    public ErrorsAndModalWindow (WebDriver driver) {
        this.driver = driver;
    }
    //Ошибки:

    //Элементы
    /** Ошибка с текстом "Неверный E-Mail или пароль" */
    private final By INVALID_EMAIL_OR_PASSWORD = By.xpath(".//div[@id='invalidEmailPassword']");
    /** Ошибка с текстом "Неверный формат E-Mail" */
    private final By INVALID_EMAIL_FORMAT = By.xpath(".//div[@id='emailFormatError']");
    /** Ошибка с текстом "Поле "Имя" не может быть пустым" */
    private final By NAME_CANNOT_BE_EMPTY = By.xpath(".//div[@id='blankNameError']");

    //Ожидания ошибок:
    /** Ожидание появления ошибки "Неверный формат E-Mail" */
    public void waitVisibleErrorInvalidEmailFormat() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(INVALID_EMAIL_FORMAT));
    }

    /** Ожидание появления ошибки "Неверный E-Mail или пароль" */
    public void waitVisibleErrorInvalidEmailOrPassword() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(INVALID_EMAIL_OR_PASSWORD));
    }

    /** Ожидание появления ошибки "Поле "Имя" не может быть пустым" */
    public void waitVisibleErrorNameCannotBeEmpty() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(NAME_CANNOT_BE_EMPTY));
    }

    //Проверки наличия ошибок
    /** Возвращает результат поиска ошибки "Неверный формат E-Mail" */
    public boolean checkIsErrorInvalidEmailFormat() {
        return !driver.findElements(INVALID_EMAIL_FORMAT).isEmpty();
    }

    /** Возвращает результат поиска ошибки "Неверный E-Mail или пароль" */
    public boolean checkIsErrorInvalidEmailOrPassword() {
        return !driver.findElements(INVALID_EMAIL_OR_PASSWORD).isEmpty();
    }

    /** Возвращает результат поиска ошибки "Поле Имя не может быть пустым" */
    public boolean checkIsErrorNameCannotBeEmpty() {
        return !driver.findElements(NAME_CANNOT_BE_EMPTY).isEmpty();
    }



    //Модальное окно "Данные отправлены"

    //Элементы
    /** Текст в модальном окне "Данные добавлены" */
    private final By SUCCESS_WINDOW = By.xpath(".//div[contains(text(), 'Данные добавлены')]");
    /** Кнопка "Ок" в модальном окне "Данные добавлены" */
    private final By OK_BUTTON = By.xpath(".//button[text()='Ok']");

    //Методы
    /** Нажатие на кнопку "Ок" */
    public void clickOkButton() {
        driver.findElement(OK_BUTTON).click();
    }


    //Ожидания

    /** Ожидание появления окна "Данные отправлены" */
    public void waitSuccessWindow() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_WINDOW));
    }


    //Проверки наличия элементов
    /** Возвращает результат поиска текста "Данные отправлены" */
    public boolean checkIsSuccessWindow() {
        return !driver.findElements(SUCCESS_WINDOW).isEmpty();
    }

}
