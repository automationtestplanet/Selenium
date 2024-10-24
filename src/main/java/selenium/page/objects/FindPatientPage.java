package selenium.page.objects;

import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class FindPatientPage extends BaseClass {
    public FindPatientPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "patient-search")
    private WebElement findPatientSearchElement;

    @FindBy(xpath = "//table[@ID='patient-search-results-table']/thead/tr/th/div")
    private List<WebElement> findPatientTableHeadersElement;

    @FindBy(xpath = "//table[@ID='patient-search-results-table']/tbody/tr[1]")
    private WebElement findPatientTableFirstRecord;

    @FindBy(xpath = "//td[text()='No matching records found']")
    private WebElement noMatchRecordsFoundElement;

    public void enterValueInPatientSearch(String value) {
        getFindPatientSearchElement().sendKeys(value);
    }

    public int getFindPatientTableColumnIndex(String columnName) {
        Map<String, Integer> tableHeadersMap = new HashMap<>();
        int index = 1;
        for (WebElement eachColumnElement : getFindPatientTableHeadersElement()) {
            tableHeadersMap.put(eachColumnElement.getText().trim(), index);
            index++;
        }
        return tableHeadersMap.get(columnName);
    }

    public String getFindPatientTableColumnValue(String columnName) {
        return driver.findElement(By.xpath("//table[@ID='patient-search-results-table']/tbody/tr/td[" + getFindPatientTableColumnIndex(columnName) + "]")).getText().trim();
    }

    public void clickFindPatientTableFirstRecord() {
        getFindPatientTableFirstRecord().click();
    }

    public boolean verifyNoMatchRecordsFoundMessage() {
        return getNoMatchRecordsFoundElement().isDisplayed();
    }
}
