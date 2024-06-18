package qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FormPage {
    private final WebDriver driver;

    public FormPage (WebDriver driver) {
        this.driver = driver;
    }

    //Элементы
    /** Поле "E-mail" */
    private final By EMAIL_FIELD = By.xpath(".//input[@id='dataEmail']");
    /** Поле "Имя" */
    private final By NAME_FIELD = By.xpath(".//input[@id='dataName']");
    /** Выпадающий список "Пол" */
    private final By GENDER_FIELD = By.xpath(".//select[@id='dataGender']");
    /** Женский пол */
    private final By FEMALE = By.xpath(".//select[@id='dataGender']/option[text()='Женский']");
    /** Мужской пол */
    private final By MALE = By.xpath(".//select[@id='dataGender']/option[text()='Мужской']");

    /** Все чек-боксы страницы */
    private final By CHECKBOXES = By.xpath(".//input[@type='checkbox']");
    /** Чек-бокс "Вариант 1.1" */
    private final By CHECKBOX1 = By.xpath(".//input[@id='dataCheck11']");
    /** Чек-бокс "Вариант 1.2" */
    private final By CHECKBOX2 = By.xpath(".//input[@id='dataCheck12']");
    /** Радиокнопка "Вариант 2.1" */
    private final By RADIOBUTTON1 = By.xpath(".//input[@id='dataSelect21']");
    /** Радиокнопка "Вариант 2.2" */
    private final By RADIOBUTTON2 = By.xpath(".//input[@id='dataSelect22']");
    /** Радиокнопка "Вариант 2.3" */
    private final By RADIOBUTTON3 = By.xpath(".//input[@id='dataSelect23']");
    /** Кнопка "Добавить" */
    private final By ADD_BUTTON = By.xpath(".//button[@id='dataSend']");
    /** Таблица с данными */
    private final By DATA_TABLE = By.xpath(".//table[@id='dataTable']");

    //Методы:
    /** Заполнение поля "Email" */
    public void setDataEmail(String email) {
        driver.findElement(EMAIL_FIELD).sendKeys(email);
    }
    /** Заполнение поля "Имя" */
    public void setName(String name) {
        driver.findElement(NAME_FIELD).sendKeys(name);
    }

    /** Нажатие на чекбокс */
    public void clickCheckbox(String idPart) {
        driver.findElement(By.xpath(".//input[@id='dataCheck" + idPart + "']")).click();
    }

    /** Прокликивание нескольких чек-боксов в зависимости от указанных значений */
    public void clickCheckboxes(String checkboxesValue) {
        List<String> checkboxesValues = List.of(checkboxesValue.split(", "));

        for (String value: checkboxesValues) {
            String idPart = getElementIdPart(value);

            if (idPart.equals("Нет")) continue;

            clickCheckbox(idPart);
        }
    }

    /** Нажатие на радиокнопку */
    public void clickRadiobutton(String idPart) {
        driver.findElement(By.xpath(".//input[@id='dataSelect" + idPart + "']")).click();
    }

    /** Прокликивание радиокнопок в зависимости от указанных значений */
    public void clickRadiobuttons(String radiobuttonValue) {
        String idPartRadiobutton = getElementIdPart(radiobuttonValue);

        if (idPartRadiobutton.equals("Нет")) return;

        clickRadiobutton(idPartRadiobutton);
    }

    /** Нажатие на кнопку "Добавить" */
    public void clickAddButton() {
        driver.findElement(ADD_BUTTON).click();
    }

    /** Заполнение и отправка обязательных полей формы */
    public void fillRequiredFields(String email, String name) {
        setDataEmail(email);
        setName(name);
    }

    /** Вычисляем набор чек-боксов */
    public List<WebElement> getCheckboxes() {
        return driver.findElements(CHECKBOXES);
    }

    /** Выбор пола */
    public void choiceGender (String gender) {
        driver.findElement(GENDER_FIELD).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(FEMALE));
        if (gender.equals("Мужской")) {
            driver.findElement(MALE).click();
        } else {
            driver.findElement(FEMALE).click();
        }

    }

    /** Вернуть idPart для вычисления id элемента чек-боксов или радио-кнопок */
    public String getElementIdPart(String value) {
        return String.join("", value.split("\\."));
    }





    //Ожидания
    /** Ожидание появления таблицы с данными на странице */
    public void waitVisibleDataTable() {
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(DATA_TABLE));
    }

    //Проверки
    /** Возвращает результат поиска таблицы с данными */
    public boolean checkIsDataTable() {
        return !driver.findElements(DATA_TABLE).isEmpty();
    }







}
