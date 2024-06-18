package qa.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.pages.*;

@RunWith(Parameterized.class)
public class TestLoginPageWithIncorrectEmailFormat extends BaseTest {

    private final String EMAIL;
    private final String PASSWORD;

    public TestLoginPageWithIncorrectEmailFormat(String email, String password) {
        this.EMAIL = email;
        this.PASSWORD = password;
    }

    @Parameterized.Parameters
    public static Object[][] getLoginData() {
        return new Object[][] {
                //Так как требований к тестовому файлу не было приложено.
                //Из тестовых проверок предполагается, что у почты должен быть формат "x@x".
                {"test", "test"},
                {"", "test"},
                {"    ", "test"},
                {"@", "test"},
                {"t@", "test"},
                {" @ ", "test"}, //Тест с данными значениями упадет, т.к. не вызовет нужную ошибку, но я считаю, что данное значение не является корректным
                {"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa@aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", "test"}, //Тест с данными значениями упадет, т.к. не вызовет нужную ошибку, но я считаю, что данное значение не является корректным
        };
    }

    @Test
    public void loginWithIncorrectEmailFormatCausesError() {
        LoginPage loginPage = new LoginPage(driver);
        //Вводим неформатное значение в поле "Email", заполняем пароль и нажимаем на вход
        loginPage.login(EMAIL,PASSWORD);
        //Ждем, что появится ошибка
        ErrorsAndModalWindow errors = new ErrorsAndModalWindow(driver);
        errors.waitVisibleErrorInvalidEmailFormat();
        Assert.assertTrue("Ошибка с текстом \"Неверный формат E-Mail\" отсутствует", errors.checkIsErrorInvalidEmailFormat());
        System.out.println("При вводе в поле \"Email\" значения несоответствующего формату \"x@x\" успешно появилась ошибка \"Неверный формат E-Mail\"");

    }
}
