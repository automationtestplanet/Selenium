package selenium.demo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.page.objects.*;

public class OpenMrsTest2 {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/src/main/resources/Drivers/chrome/chromedriver129v.exe");
        WebDriver driver = new ChromeDriver();
        LoginPage loginPage = new LoginPage(driver);
        HomePage homePage = new HomePage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        DetailsPage detailsPage = new DetailsPage(driver);
        FindPatientPage findPatientPage = new FindPatientPage(driver);
        loginPage.launchOpenMrsApplication("https://demo.openmrs.org/openmrs/login.htm");
        if (loginPage.verifyPageTitle("Login")) {
            loginPage.loginToOpenMrs("Admin", "Admin123", "Registration Desk");
            if (homePage.verifyElementDisplayed(homePage.getLogoutElement()) && homePage.verifyPageTitle("Home")) {
                System.out.println("Login Successful");
                // Register a Patient
                if (homePage.verifyTileTile("Register a patient")) {
                    homePage.clickTile("Register a patient");
                    if (registerPage.verifyPageName("Register a patient")) {
                        registerPage.enterRegistrationDetails("ATP, Test1", "Male", "01, January, 1990", "SRNagar, Hyderabad, Telangana, India, 500038", "9876543211");
                        if (registerPage.verifyDetailsToConfirm("ATP, Test1", "Male", "01, January, 1990", "9876543211")) {
                            System.out.println("Details are properly displaying, clicking on confirm");
                            registerPage.clickConfirm();
                            if (detailsPage.verifyPatientNameInPatientDetailsPage("ATP, Test1")) {
                                System.out.println("Patient Name is displaying correctly in Patient details page");
                                System.out.println(detailsPage.getPatientId());

                                //Find Patient
                                detailsPage.clickHomeIcon();
                                homePage.clickTile("Find Patient Record");
                                findPatientPage.verifyPageName("Find Patient Record");
                                findPatientPage.enterValueInPatientSearch("ATP Test1");
                                if (findPatientPage.getFindPatientTableColumnValue("Name").equalsIgnoreCase("ATP Test1")) {
                                    System.out.println("Filtered Patient record showing correctly");
                                    findPatientPage.clickFindPatientTableFirstRecord();
                                    if (detailsPage.verifyPatientNameInPatientDetailsPage("ATP, Test1")) {
                                        System.out.println("Find Patient is working as expected");

                                        //Start Visits and Add Attachments
                                        detailsPage.clickStartVisit();
                                        if (detailsPage.verifyVisitsTab()) {
                                            System.out.println("Start Visits available");
                                            detailsPage.clickAttachments();
                                            String filePath = System.getProperty("user.dir") + "/src/main/resources/Files/UploadFile.pdf";
                                            detailsPage.uploadFile(filePath, "Test1");
                                            if (detailsPage.verifyFileUpload("Test1")) {
                                                System.out.println("File Upload Successful");

                                                // Delete Patient
                                                detailsPage.clickHomeIcon();
                                                homePage.clickTile("Find Patient Record");
                                                findPatientPage.verifyPageName("Find Patient Record");
                                                findPatientPage.enterValueInPatientSearch("ATP Test1");
                                                findPatientPage.clickFindPatientTableFirstRecord();
                                                detailsPage.clickDeletePatient();
                                                detailsPage.enterDeleteReason("Other");
                                                detailsPage.clickDeletePatientConfirmButton();
                                                findPatientPage.enterValueInPatientSearch("ATP Test1");
                                                if(findPatientPage.verifyNoMatchRecordsFoundMessage()){
                                                    System.out.println("Patient record is deleted");
                                                }else{
                                                    System.out.println("Patient record is not deleted");
                                                }
                                            } else {
                                                System.out.println("File Upload failed");
                                            }
                                        } else {
                                            System.out.println("Start Visits not available");
                                        }

                                    } else {
                                        System.out.println("Find Patient is not working as expected");
                                    }
                                } else {
                                    System.out.println("Filtered Patient record showing incorrect");
                                }
                            } else {
                                System.out.println("Patient Name is not displaying correctly in Patient details page");
                            }
                        } else {
                            System.out.println("Details are showing incorrect, clicking on Cancel");
                            registerPage.clickCancel();
                        }
                    } else {
                        System.out.println("Register patient page is not displayed");
                    }
                } else {
                    System.out.println("Register a patient tile is not available");
                }
            } else {
                System.out.println("Login Failed");
            }
        } else {
            System.out.println("Login Page is not available");
        }
    }
}
