package qa.test;

import org.junit.Assert;
import org.junit.Test;
import qa.pages.FormPage;
import qa.pages.LoginPage;

public class TestLoginPage extends BaseTest {

    @Test
    public void loginWithRightData() {
        LoginPage loginPage = new LoginPage(driver);
        FormPage formPage = new FormPage(driver);

        //Заполняем форму входа и нажимаем на кнопку "Вход"
        loginPage.login("test@protei.ru", "test");
        //После ввода корректных данных проверяем, что вход осуществился и произошел переход на следующую страницу
        formPage.waitVisibleDataTable();
        Assert.assertTrue("После заполнения формы авторизации не произошел переход на страницу с таблицей", formPage.checkIsDataTable());
        System.out.println("Вход в систему осуществляется успешно");
    }
}
