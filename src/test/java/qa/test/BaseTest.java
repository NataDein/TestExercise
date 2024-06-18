package qa.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import qa.pages.LoginPage;

public class BaseTest {
    public static WebDriver driver;

    protected final String SITE_URL = "D:/Nata/NataProjects/projects/TestProtei/qa-test.html"; //При запуске заменить на подходящий url

    @Before
    public void openBrowser() {
        driver = new ChromeDriver();
        driver.get(SITE_URL);
    }



    @After
    public void tearDown() {
        driver.quit();
    }
}
