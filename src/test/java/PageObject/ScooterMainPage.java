package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScooterMainPage {
    private final WebDriver driver;
    private final By faqBlock = By.cssSelector("[class^='Home_FAQ']");
    private final By faqAccordion = By.className("accordion");
    private final By accordionItems = By.className("accordion__item");

    public static final By topOrderButton = By.cssSelector("button[class^='Button_Button']:not([class*='Button_Middle'])");
    public static final By middleOrderButton = By.cssSelector("button[class*='Button_Middle']");

    public ScooterMainPage(WebDriver driver){
        this.driver = driver;
    }

    public void scrollToFaqBlock() {
        WebElement element = driver.findElement(faqBlock);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);

        assertTrue(driver.findElement(faqAccordion).isDisplayed(), "FAQ не видим");
    }

    public List<WebElement> getFaqItems() {
        return driver.findElements(accordionItems);
    }

    public void clickCreateOrder(WebDriver driver, By button){
      //  ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", button);
        driver.findElement(button).click();
    }
}