package selenium.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class OpenMrsTest {

    public static void main(String[] args) {

        // Find Patient
        // Active Visits and Add Attachments
        // Delete Patient

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://demo.openmrs.org/openmrs/login.htm");
//        driver.navigate().to("https://demo.openmrs.org/openmrs/login.htm");
//        driver.navigate().back();

//        System.out.println(driver.getTitle());
//        System.out.println(driver.getCurrentUrl());
//        System.out.println(driver.getPageSource());

        if (driver.getTitle().trim().equalsIgnoreCase("Login")) {
            driver.findElement(By.id("username")).sendKeys("Admin");
            driver.findElement(By.name("password")).sendKeys("Admin123");
            driver.findElement(By.id("Registration Desk")).click();
            driver.findElement(By.cssSelector("input[value='Log In']")).click();

            if (driver.findElement(By.partialLinkText("Logout")).isDisplayed() && driver.getTitle().trim().equalsIgnoreCase("Home")) {
                System.out.println("Login Successful");
                // Register a Patient

                if (driver.findElement(By.partialLinkText("Register a patient")).isDisplayed()) {
                    driver.findElement(By.partialLinkText("Register a patient")).click();

                    if (driver.findElement(By.xpath("//h2[contains(text(),'Register a patient')]")).isDisplayed()) {
                        driver.findElement(By.name("givenName")).sendKeys("ATP");
                        driver.findElement(By.name("familyName")).sendKeys("Test1");
                        driver.findElement(By.id("next-button")).click();

                        Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
                        genderDropdown.selectByIndex(0);
                        genderDropdown.selectByValue("M");
                        genderDropdown.selectByVisibleText("Male");

                        List<WebElement> allOptions = genderDropdown.getOptions();
                        System.out.println(allOptions.size());
                        System.out.println(genderDropdown.getFirstSelectedOption().getAttribute("value"));
                        System.out.println(genderDropdown.getFirstSelectedOption().getText());
                        driver.findElement(By.id("next-button")).click();

                        driver.findElement(By.id("birthdateDay-field")).sendKeys("01");
                        Select birthMonthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
                        birthMonthDropdown.selectByVisibleText("January");
                        driver.findElement(By.id("birthdateYear-field")).sendKeys("1990");
                        driver.findElement(By.id("next-button")).click();

                        driver.findElement(By.id("address1")).sendKeys("S R Nagar");
                        driver.findElement(By.id("cityVillage")).sendKeys("Hyderabad");
                        driver.findElement(By.id("stateProvince")).sendKeys("Telangana");
                        driver.findElement(By.id("country")).sendKeys("India");
                        driver.findElement(By.id("postalCode")).sendKeys("500038");
                        driver.findElement(By.id("next-button")).click();

                        driver.findElement(By.name("phoneNumber")).sendKeys("9876543210");
                        driver.findElement(By.id("next-button")).click();
                        driver.findElement(By.id("next-button")).click();

                        String name = driver.findElement(By.xpath("//span[text()='Name: ']//parent::p")).getText().trim();
                        String gender = driver.findElement(By.xpath("//span[text()='Gender: ']//parent::p")).getText().trim();
                        String birthDate = driver.findElement(By.xpath("//span[text()='Birthdate: ']//parent::p")).getText().trim();
                        String phoneNumber = driver.findElement(By.xpath("//span[text()='Phone Number: ']//parent::p")).getText().trim();

                        if (name.contains("ATP, Test1") && gender.contains("Male") && birthDate.contains("01, January, 1990") && phoneNumber.contains("9876543210")) {
                            System.out.println("Details are properly displaying, clicking on confirm");
                            driver.findElement(By.cssSelector("input[value='Confirm']")).click();

                            String givenName = driver.findElement(By.className("PersonName-givenName")).getText().trim();
                            String familyName = driver.findElement(By.className("PersonName-familyName")).getText().trim();

                            if (givenName.equalsIgnoreCase("ATP") && familyName.equalsIgnoreCase("Test1")) {
                                System.out.println("Patient Name is displaying correctly in Patient details page");
                                String patientId = driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
                                System.out.println(patientId);
                            } else {
                                System.out.println("Patient Name is not displaying correctly in Patient details page");
                            }
                        } else {
                            System.out.println("Details are showing incorrect, clicking on Cancel");
                            driver.findElement(By.id("cancelSubmission")).click();
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
