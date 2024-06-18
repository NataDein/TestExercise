package qa.test;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import qa.pages.ErrorsAndModalWindow;
import qa.pages.LoginPage;

@RunWith(Parameterized.class)
public class TestLoginPageWithIncorrectData extends BaseTest {
    private final String EMAIL;
    private final String PASSWORD;

    public TestLoginPageWithIncorrectData(String email, String password) {
        this.EMAIL = email;
        this.PASSWORD = password;
    }

    @Parameterized.Parameters
    public static Object[][] getLoginData() {
        return new Object[][] {
                {"test@protei.ru", "password"}, //корректная почта, некорректный пароль
                {"st@protei.ru", "test"},       //некорректная почта, корректный пароль
                {"qa@protei.ru", "password"},   //некорректная почта, некорректный пароль
                {"TEST@PROTEI.RU", "test"},     //неверный регистр почты, корректный пароль
                {"test@protei.ru", "TEST"},     //корректная почта, некорректный регистр пароля
                {"TEST@PROTEI.RU", "TEST"},     //некорректный регистр почты и пароля
        };
    }

    @Test
    public void loginWithIncorrectDataCausesError() {
        LoginPage loginPage = new LoginPage(driver);
        //Вводим некорректные данные в форму авторизации
        loginPage.login(EMAIL,PASSWORD);
        ErrorsAndModalWindow errors = new ErrorsAndModalWindow(driver);
        //Ждем, что появится ошибка
        errors.waitVisibleErrorInvalidEmailOrPassword();
        Assert.assertTrue("Ошибка с текстом \"Неверный E-Mail или пароль\" отсутствует", errors.checkIsErrorInvalidEmailOrPassword());
        System.out.println("При вводе в поля \"Email\" и \"Пароль\" некорректного значения успешно появилась ошибка \"Неверный E-Mail или пароль\"");

    }
}
