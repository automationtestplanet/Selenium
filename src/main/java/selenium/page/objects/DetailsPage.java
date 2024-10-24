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

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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

    @FindBy(xpath = "//div[contains(text(),'Start Visit')]//ancestor::a")
    private WebElement startVisitElement;

    @FindBy(id = "start-visit-with-visittype-confirm")
    private WebElement startVisitConfirmButton;

    @FindBy(xpath = "//a[contains(text(),'Visits') and contains(@href,'visits')]")
    private WebElement visitsTabElement;

    @FindBy(xpath = "//a[contains(@href,'/attachments')]")
    private WebElement attachmentsLink;

    @FindBy(xpath = "//div[text()='Click or drop a file here.']")
    private WebElement fileButton;

    @FindBy(xpath = "//button[text()='Upload file']")
    private WebElement uploadButtonElement;

    @FindBy(xpath = "//h3[contains(text(),'Caption')]//following-sibling::textarea")
    private WebElement captionElement;

    @FindBy(xpath = "//div[contains(text(),'Delete Patient')]//ancestor::a")
    private WebElement deletePatientLinkElement;

    @FindBy(id = "delete-reason")
    private WebElement deletePatientReasonElement;

    @FindBy(css = "#delete-patient-creation-dialog button.confirm.right")
    private WebElement deleteConfirmButton;

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
        Function<WebDriver, WebElement> element1 = new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver webDriver) {
                return element;
            }
        };
        Function<WebDriver, WebElement> element2 = (driver) -> element;
    }

    public void clickStartVisit() {
        getStartVisitElement().click();
        getStartVisitConfirmButton().click();
    }

    public boolean verifyVisitsTab() {
        return getVisitsTabElement().isDisplayed();
    }

    public void clickAttachments() {
        getAttachmentsLink().click();
    }

    public void clickFileButton() {
        getFileButton().click();
    }

    public void uploadFile(String filePath, String caption) {
        clickFileButton();
        try {
            Thread.sleep(10000);
            StringSelection selectStr = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selectStr, null);
            Robot robot = new Robot(); // we can perform mouse and keyboard actions
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            Thread.sleep(5000);

            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);

            Thread.sleep(10000);
            getCaptionElement().sendKeys(caption);

            if (getUploadButtonElement().isEnabled()) {
                getUploadButtonElement().click();
            }
        } catch (Exception e) {
            System.out.println("Exception Occurred while upload file");
        }
    }

    public boolean verifyFileUpload(String caption) {
        waitForVisibilityOfElement(driver.findElement(By.xpath("//p[contains(text(),'" + caption + "')]")));
        return driver.findElement(By.xpath("//p[contains(text(),'" + caption + "')]")).isDisplayed();
    }

    public void clickDeletePatient() {
        getDeletePatientLinkElement().click();
    }

    public void enterDeleteReason(String reason) {
        getDeletePatientReasonElement().sendKeys(reason);
    }

    public void clickDeletePatientConfirmButton() {
        getDeleteConfirmButton().click();
    }
}
