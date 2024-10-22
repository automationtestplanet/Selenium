package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenMrsTest {
    static WebDriver driver;

    static void launchOpenMrsApplication(String url) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
    }

    static void loginToOpenMrs(String userName, String password, String moduleName) {
        driver.findElement(By.id("username")).sendKeys(userName);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.id(moduleName)).click();
        driver.findElement(By.cssSelector("input[value='Log In']")).click();
    }

    static boolean verifyPageTitle(String title) {
        return driver.getTitle().trim().equalsIgnoreCase(title);
    }

    static boolean verifyTileTile(String tileName){
        return driver.findElement(By.partialLinkText(tileName)).isDisplayed();
    }

    static void clickTile(String tileName){
        driver.findElement(By.partialLinkText(tileName)).click();
    }

    static boolean verifyPageName(String pageName){
        return driver.findElement(By.xpath("//h2[contains(text(),'"+pageName+"')]")).isDisplayed();
    }

    static void enterName(String name) {
        String[] nameArr = name.split(",");
        driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
        driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
    }

    static void selectGender(String gender) {
        Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
        genderDropdown.selectByVisibleText(gender);
    }

    static void enterDateOfBirth(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
        Select birthMonthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
        birthMonthDropdown.selectByVisibleText(dateOfBirthArr[1].trim());
        driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
    }

    static void enterAddress(String address) {
        String[] addressArr = address.split(",");
        driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
        driver.findElement(By.id("cityVillage")).sendKeys(addressArr[1].trim());
        driver.findElement(By.id("stateProvince")).sendKeys(addressArr[2].trim());
        driver.findElement(By.id("country")).sendKeys(addressArr[3].trim());
        driver.findElement(By.id("postalCode")).sendKeys(addressArr[4].trim());
    }

    static void enterPhoneNumber(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    static void clickNext() {
        driver.findElement(By.id("next-button")).click();
    }

    static boolean verifyDetailsToConfirm(String name, String gender, String birthDate, String phoneNumber) {
        String actualName = driver.findElement(By.xpath("//span[text()='Name: ']//parent::p")).getText().trim();
        String actualGender = driver.findElement(By.xpath("//span[text()='Gender: ']//parent::p")).getText().trim();
        String actualBirthDate = driver.findElement(By.xpath("//span[text()='Birthdate: ']//parent::p")).getText().trim();
        String actualPhoneNumber = driver.findElement(By.xpath("//span[text()='Phone Number: ']//parent::p")).getText().trim();

        return actualName.contains(name) && actualGender.contains(gender) && actualBirthDate.contains(birthDate) && actualPhoneNumber.contains(phoneNumber);
    }

    static void clickConfirm() {
        driver.findElement(By.cssSelector("input[value='Confirm']")).click();
    }

    static void clickCancel() {
        driver.findElement(By.id("cancelSubmission")).click();
    }

    static boolean verifyPatientNameInPatientDetailsPage(String name) {
        String[] nameArr = name.split(",");
        String givenName = driver.findElement(By.className("PersonName-givenName")).getText().trim();
        String familyName = driver.findElement(By.className("PersonName-familyName")).getText().trim();
        return givenName.equalsIgnoreCase(nameArr[0].trim()) && familyName.equalsIgnoreCase(nameArr[1].trim());
    }

    static String getPatientId(){
        return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
    }

    static WebElement logoutElement(){
        return driver.findElement(By.partialLinkText("Logout"));
    }

    static boolean verifyElementDisplayed(WebElement element){
        return element.isDisplayed();
    }

    public static void main(String[] args) {
        launchOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");
        if (verifyPageTitle("Login")) {
            loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (verifyElementDisplayed(logoutElement()) && verifyPageTitle("Home")) {
                System.out.println("Login Successful");
                // Register a Patient
                if (verifyTileTile("Register a patient")) {
                    clickTile("Register a patient");
                    if (verifyPageName("Register a patient")) {
                        enterName("ATP, Test1");
                        clickNext();
                        selectGender("Male");
                        clickNext();
                        enterDateOfBirth("01, January, 1990");
                        clickNext();
                        enterAddress("SRNagar, Hyderabad, Telangana, India, 500038");
                        clickNext();
                        enterPhoneNumber("9876543211");
                        clickNext();
                        clickNext();
                        if (verifyDetailsToConfirm("ATP, Test1", "Male", "01, January, 1990", "9876543211")) {
                            System.out.println("Details are properly displaying, clicking on confirm");
                            clickConfirm();
                            if (verifyPatientNameInPatientDetailsPage("ATP, Test1")) {
                                System.out.println("Patient Name is displaying correctly in Patient details page");
                                System.out.println(getPatientId());
                            } else {
                                System.out.println("Patient Name is not displaying correctly in Patient details page");
                            }
                        } else {
                            System.out.println("Details are showing incorrect, clicking on Cancel");
                            clickCancel();
                        }
                    } else {
                        System.out.println("Register patient page is not displayed");
                    }
                } else {
                    System.out.println("Register a Patient tile is not available");
                }
            } else {
                System.out.println("Login Failed");
            }
        } else {
            System.out.println("Login Page is not available");
        }
    }
}
