package qa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import qa.pages.ErrorsAndModalWindow;
import qa.pages.FormPage;
import qa.pages.LoginPage;

@RunWith(Parameterized.class)
public class TestFormPageChoiceCheckboxAndRadiobutton extends BaseTest {

    private final String EMAIL;
    private final String NAME;
    private final String GENDER;
    private final String CHECKBOXES_VALUE;
    private final String RADIOBUTTON;

    public TestFormPageChoiceCheckboxAndRadiobutton
            (String email,
            String name,
            String gender,
            String checkboxes_value,
            String radiobutton) {
        this.EMAIL = email;
        this.NAME = name;
        this.GENDER = gender;
        this.CHECKBOXES_VALUE = checkboxes_value;
        this.RADIOBUTTON = radiobutton;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"test@protei.ru", "Test", "Женский", "Нет", "2.1"},
                {"test@protei.ru", "Test", "Женский", "1.1", "2.2"},
                {"test@protei.ru", "Test", "Женский", "1.2", "2.3"},
                {"test@protei.ru", "Test", "Женский", "1.1, 1.2", "Нет"}, //Тест с данными значениями упадет, т.к. если не выбрана ни одна радиокнопка в таблице отображается пустота. Я считаю, что данное значение не является корректным, т.к. заполнение таблицы для чек-боксов и радио-кнопок логично сделать единообразным
        };
    }

    @Test
    public void fillFormWithCheckboxAndRadiobutton() {

        //Входим в систему
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        //Заполняем обязательные поля
        FormPage formPage = new FormPage(driver);
        formPage.fillRequiredFields(EMAIL, NAME);
        formPage.choiceGender(GENDER);

        //Прокликиваем нужные чек-боксы и радиокнопки
        formPage.clickCheckboxes(CHECKBOXES_VALUE);
        formPage.clickRadiobuttons(RADIOBUTTON);

        //Нажимаем на кнопку "Добавить"
        formPage.clickAddButton();

        //Ждем, что появится модальное окно с текстом "Данные отправлены"
        ErrorsAndModalWindow modalWindow = new ErrorsAndModalWindow(driver);
        modalWindow.waitSuccessWindow();
        //Нажимаем "ок" в модальном окне
        modalWindow.clickOkButton();

        //Сверяем, значения в таблице с введенными значениями
        formPage.waitVisibleDataTable();
        Assert.assertEquals(CHECKBOXES_VALUE,driver.findElement(By.xpath(".//tr[last()]/td[4]")).getText());
        Assert.assertEquals(RADIOBUTTON,driver.findElement(By.xpath(".//tr[last()]/td[5]")).getText());
    }

}


