package selenium.page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DetailsPage extends BaseClass {

    public DetailsPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyPatientNameInPatientDetailsPage(String name) {
        String[] nameArr = name.split(",");
        String givenName = driver.findElement(By.className("PersonName-givenName")).getText().trim();
        String familyName = driver.findElement(By.className("PersonName-familyName")).getText().trim();
        return givenName.equalsIgnoreCase(nameArr[0].trim()) && familyName.equalsIgnoreCase(nameArr[1].trim());
    }

    public String getPatientId() {
        return driver.findElement(By.xpath("//em[text()='Patient ID']//following-sibling::span")).getText().trim();
    }
}
