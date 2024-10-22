package selenium.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage extends BaseClass{

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        String[] nameArr = name.split(",");
        driver.findElement(By.name("givenName")).sendKeys(nameArr[0].trim());
        driver.findElement(By.name("familyName")).sendKeys(nameArr[1].trim());
    }

    public void selectGender(String gender) {
        Select genderDropdown = new Select(driver.findElement(By.id("gender-field")));
        genderDropdown.selectByVisibleText(gender);
    }

    public void enterDateOfBirth(String dateOfBirth) {
        String[] dateOfBirthArr = dateOfBirth.split(",");
        driver.findElement(By.id("birthdateDay-field")).sendKeys(dateOfBirthArr[0].trim());
        Select birthMonthDropdown = new Select(driver.findElement(By.id("birthdateMonth-field")));
        birthMonthDropdown.selectByVisibleText(dateOfBirthArr[1].trim());
        driver.findElement(By.id("birthdateYear-field")).sendKeys(dateOfBirthArr[2].trim());
    }

    public void enterAddress(String address) {
        String[] addressArr = address.split(",");
        driver.findElement(By.id("address1")).sendKeys(addressArr[0].trim());
        driver.findElement(By.id("cityVillage")).sendKeys(addressArr[1].trim());
        driver.findElement(By.id("stateProvince")).sendKeys(addressArr[2].trim());
        driver.findElement(By.id("country")).sendKeys(addressArr[3].trim());
        driver.findElement(By.id("postalCode")).sendKeys(addressArr[4].trim());
    }

    public void enterPhoneNumber(String phoneNumber) {
        driver.findElement(By.name("phoneNumber")).sendKeys(phoneNumber);
    }

    public void clickNext() {
        driver.findElement(By.id("next-button")).click();
    }

    public boolean verifyDetailsToConfirm(String name, String gender, String birthDate, String phoneNumber) {
        String actualName = driver.findElement(By.xpath("//span[text()='Name: ']//parent::p")).getText().trim();
        String actualGender = driver.findElement(By.xpath("//span[text()='Gender: ']//parent::p")).getText().trim();
        String actualBirthDate = driver.findElement(By.xpath("//span[text()='Birthdate: ']//parent::p")).getText().trim();
        String actualPhoneNumber = driver.findElement(By.xpath("//span[text()='Phone Number: ']//parent::p")).getText().trim();

        return actualName.contains(name) && actualGender.contains(gender) && actualBirthDate.contains(birthDate) && actualPhoneNumber.contains(phoneNumber);
    }

    public void clickConfirm() {
        driver.findElement(By.cssSelector("input[value='Confirm']")).click();
    }

    public void clickCancel() {
        driver.findElement(By.id("cancelSubmission")).click();
    }
}
