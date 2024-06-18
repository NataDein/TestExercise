package qa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.pages.ErrorsAndModalWindow;
import qa.pages.FormPage;
import qa.pages.LoginPage;

@RunWith(Parameterized.class)
public class TestFormPageEmptyName extends BaseTest {

    private final String EMAIL;
    private final String NAME;


    public TestFormPageEmptyName (String email, String name) {
        this.EMAIL = email;
        this.NAME = name;
    }

    //На мой взгляд в системе не хватает валидации значений для данного поля и ошибки типа "Некорректное имя". Т.к. сейчас в поле принимается любое значение состоящее из пробелов, спец.символов, цифр. На мой взгляд это некорректно.

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"test@protei.ru", ""},
                {"test@protei.ru", "   "}, //Тест с данными значениями упадет, т.к. не вызовет нужную ошибку, но я считаю, что данное значение не является корректным
        };
    }
    @Test
    public void fillFormWithEmptyName() {

        //Входим в систему
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        //Заполняем поле "Email", а поле "Имя" оставляем пустым
        FormPage formPage = new FormPage(driver);
        formPage.fillRequiredFields(EMAIL, NAME);

        //Кликаем по кнопке "Добавить"
        formPage.clickAddButton();

        //Ждем, что появится ошибка "Поле имя не может быть пустым"
        ErrorsAndModalWindow errors = new ErrorsAndModalWindow(driver);
        errors.waitVisibleErrorNameCannotBeEmpty();
        Assert.assertTrue("Ошибка с текстом \"Поле имя не может быть пустым\" отсутствует",errors.checkIsErrorNameCannotBeEmpty());

    }

}
