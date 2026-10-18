package PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ScooterOrderPage {
    private WebDriver driver;

    public ScooterOrderPage(WebDriver driver){
        this.driver = driver;
    }

    //first dialog
    private final By firstNameInput = By.xpath("//input[@placeholder='* Имя']");
    private final By lastNameInput = By.xpath("//input[@placeholder='* Фамилия']");
    private final By addressInput = By.xpath("//input[contains(@placeholder, 'Адрес')]");
    private final By phoneInput = By.xpath("//input[contains(@placeholder, 'Телефон')]");
    private final By metroInput = By.className("select-search__input");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    public void enterFirstName(String firstName) {driver.findElement(firstNameInput).sendKeys(firstName);}
    public void enterLastName(String lastName) {driver.findElement(lastNameInput).sendKeys(lastName);}
    public void enterAddress(String address) {driver.findElement(addressInput).sendKeys(address);}
    public void enterPhone(String phone) {driver.findElement(phoneInput).sendKeys(phone);}
    public void selectFirstMetroStation() {
        driver.findElement(metroInput).click();
        By firstStationOption = By.className("select-search__select");
        driver.findElement(firstStationOption).click();
    }
    public void clickNext() {
        driver.findElement(nextButton).click();
    }

    //second dialog
    private final By deliveryDateInput = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    private final By rentalDurationDropdown = By.className("Dropdown-control");
    private final By orderButton =
            By.xpath("//button[text()='Заказать' and contains(@class, 'Button_Button') and contains(@class, 'Button_Middle')]");


    public void enterDeliveryDateInput(String date) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(deliveryDateInput)).click();
        driver.findElement(deliveryDateInput).sendKeys(date);
    }

    public void selectFirstOption() {
        driver.findElement(rentalDurationDropdown).click();
        By firstOption = By.className("Dropdown-menu");
        driver.findElement(firstOption).click();
    }

    public void clickOrder() {
        driver.findElement(orderButton).click();
    }
}
