import PageObject.ScooterMainPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScooterMainPageTests {
    private WebDriver driver;

    @BeforeEach
    void setUp(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.education-services.ru/");
    }

    @Test
    public void testCountAccordionItems(){
        ScooterMainPage page = new ScooterMainPage(driver);
        page.scrollToFaqBlock();
        assertEquals(8, page.getFaqItems().size());
    }


    @ParameterizedTest
    @CsvSource(delimiter = ';', value = {
            "Сколько это стоит? И как оплатить?               ; Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Хочу сразу несколько самокатов! Так можно?       ; Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Как рассчитывается время аренды?                 ; Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Можно ли заказать самокат прямо на сегодня?      ; Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Можно ли продлить заказ или вернуть самокат рано? ; Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Вы привозите зарядку вместе с самокатом?         ; Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Можно ли отменить заказ?                         ; Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Я жизу за МКАДом, привезёте?                     ; Да, обязательно. Всем самокатов! И Москве, и Московской области."
    })
    void testAccordionPanels(String question, String answer) {
        ScooterMainPage page = new ScooterMainPage(driver);
        page.scrollToFaqBlock();

        List<WebElement> items = page.getFaqItems();

        for (WebElement item : items) {

            WebElement button = item.findElement(By.className("accordion__button"));
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);

            String actualQuestion = button.getText().trim();

            if (actualQuestion.equals(question)) {
                button.click();
                WebElement panel = item.findElement(By.className("accordion__panel"));

                String actualAnswer = panel.getText().trim();
                assertEquals(answer, actualAnswer);
                break;
            }
        }
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
