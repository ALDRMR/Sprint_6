import PageObject.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static PageObject.ScooterMainPage.topOrderButton;

public class ScooterOrderPageTests {
    private WebDriver driver;

    @BeforeEach
    void setUpChrome(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.education-services.ru/");
    }

//    @BeforeEach
//    void setUpFirefox() {
//        FirefoxOptions options = new FirefoxOptions();
//        // options.addArguments("--headless");
//        driver = new FirefoxDriver(options);
//        driver.get("https://qa-scooter.education-services.ru/");
//    }


    @Test
    public void testOrder() throws InterruptedException {
        ScooterMainPage mainPage = new ScooterMainPage(driver);
        mainPage.clickCreateOrder(driver, topOrderButton);
        ScooterOrderPage orderPage = new ScooterOrderPage(driver);

        //first order controls
        orderPage.enterFirstName("Тесто");
        orderPage.enterLastName("Тесто");
        orderPage.enterPhone("+7900000000");
        orderPage.enterAddress("Тесто");
        orderPage.selectFirstMetroStation();
        orderPage.clickNext();

        //second order controls
        orderPage.selectFirstOption();
        orderPage.enterDeliveryDateInput("01.01.2026");
        orderPage.clickOrder();

        //order question dialog
        OrderQuestionDialog orderQuestionDialog = new OrderQuestionDialog(driver);
        orderQuestionDialog.clickYesButton();

        //order final dialog
        OrderFinalDialog orderFinalDialog = new OrderFinalDialog(driver);
        orderFinalDialog.clickButton();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
