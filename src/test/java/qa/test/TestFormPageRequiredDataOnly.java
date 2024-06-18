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
public class TestFormPageRequiredDataOnly extends BaseTest {

    private final String EMAIL;
    private final String NAME;
    private final String GENDER;

    public TestFormPageRequiredDataOnly(String email, String name, String gender) {
        this.EMAIL = email;
        this.NAME = name;
        this.GENDER = gender;
    }




   @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][] {
                {"test@protei.ru", "Test", "Женский"},
                {"st@protei.ru", "Тест", "Мужской"},

        };
    }



    @Test
    public void fillFormWithRequiredDataOnly() {

        //Входим в систему
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        //Заполняем обязательные поля формы
        FormPage formPage = new FormPage(driver);
        formPage.fillRequiredFields(EMAIL, NAME);
        formPage.choiceGender(GENDER);
        formPage.clickAddButton();
        ErrorsAndModalWindow modalWindow = new ErrorsAndModalWindow(driver);

        //Ждем, что появляется модальное окно с текстом "Данные отправлены"
        modalWindow.waitSuccessWindow();
        Assert.assertTrue("Окно с текстом \"Данные отправлены\" отсутствует",modalWindow.checkIsSuccessWindow());

    }

    @Test
    public void fillDataTableWithDataFromForm() {
        //Входим в систему
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("test@protei.ru", "test");

        //Заполняем обязательные поля формы
        FormPage formPage = new FormPage(driver);
        formPage.fillRequiredFields(EMAIL, NAME);
        formPage.choiceGender(GENDER);
        formPage.clickAddButton();

        //Ждем, что появляется модальное окно с текстом "Данные отправлены" и кликаем по кнопке "Ок"
        ErrorsAndModalWindow modalWindow = new ErrorsAndModalWindow(driver);
        modalWindow.waitSuccessWindow();
        modalWindow.clickOkButton();

        //Ждем, пока появится таблица и сверяем значения обязательных полей с выведенными в таблице
        formPage.waitVisibleDataTable();

        Assert.assertEquals(EMAIL,driver.findElement(By.xpath(".//tr[last()]/td[1]")).getText());
        Assert.assertEquals(NAME,driver.findElement(By.xpath(".//tr[last()]/td[2]")).getText());
        Assert.assertEquals(GENDER,driver.findElement(By.xpath(".//tr[last()]/td[3]")).getText());
    }


}
