package qa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.pages.ErrorsAndModalWindow;
import qa.pages.FormPage;
import qa.pages.LoginPage;

@RunWith(Parameterized.class)
public class TestFormPageIncorrectEmail extends BaseTest {

    private final String EMAIL;
    private final String NAME;


    public TestFormPageIncorrectEmail (String email, String name) {
        this.EMAIL = email;
        this.NAME = name;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"test", "Test"},
                {"", "test"},
                {"    ", "test"},
                {"@", "test"},
                {"t@", "test"},
                {" @ ", "test"}, //Тест с данными значениями упадет, т.к. не вызовет нужную ошибку, но я считаю, что данное значение не является корректным
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "test"}, //Тест с данными значениями упадет, т.к. не вызовет нужную ошибку, но я считаю, что данное значение не является корректным
        };
    }



    @Test
    public void fillFormWithIncorrectEmail() {

        //Входим в систему
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        //Заполняем обязательные поля. Для поля "Email" вводится неформатное значение
        FormPage formPage = new FormPage(driver);
        formPage.fillRequiredFields(EMAIL, NAME);
        //Кликаем по кнопке "Добавить"
        formPage.clickAddButton();

        //Ждем, что появится ошибка "Неверный формат E-Mail"
        ErrorsAndModalWindow errors = new ErrorsAndModalWindow(driver);
        errors.waitVisibleErrorInvalidEmailFormat();
        Assert.assertTrue("Ошибка с текстом \"Неверый формат E-Mail\" отсутствует",errors.checkIsErrorInvalidEmailFormat());

    }
}
