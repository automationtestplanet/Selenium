package selenium.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Getter
public class DetailsPage extends BaseClass {

    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(className = "PersonName-givenName")
    private WebElement personGivenNameElement;

    @FindBy(className = "PersonName-familyName")
    private WebElement personFamilyNameElement;

    @FindBy(xpath = "//em[text()='Patient ID']//following-sibling::span")
    private WebElement patientIdElement;

    public boolean verifyPatientNameInPatientDetailsPage(String name) {
        String[] nameArr = name.split(",");
        waitForVisibilityOfElement(getPersonGivenNameElement());
        String givenName = getPersonGivenNameElement().getText().trim();
        String familyName = getPersonFamilyNameElement().getText().trim();
        return givenName.equalsIgnoreCase(nameArr[0].trim()) && familyName.equalsIgnoreCase(nameArr[1].trim());
    }

    public String getPatientId() {
        return getPatientIdElement().getText().trim();
    }

    public void waitForVisibilityOfElement(WebElement element) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver);
        wait.withTimeout(Duration.ofSeconds(20)).pollingEvery(Duration.ofSeconds(5)).ignoring(NoSuchElementException.class);
        Function<WebDriver, WebElement> element1 = new Function<WebDriver, WebElement>(){
            @Override
            public WebElement apply(WebDriver webDriver) {
                return element;
            }
        };
        Function<WebDriver, WebElement> element2 = (driver)-> element;
    }
}
