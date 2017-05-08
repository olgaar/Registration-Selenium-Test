import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 07.05.2017.
 */
public class Step1RegPage {
    By emailLocator=By.id("ctl00_centreContentPlaceHolder_ctlSignUp_txtEmail");
    By step1Locator=By.id("ctl00_centreContentPlaceHolder_ctlSignUp_submitButton");


    private final WebDriver driver;



    public Step1RegPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/");

    }
    public Step1RegPage step1email(String email) {
        driver.findElement(emailLocator).sendKeys(email);
              return this;
    }
    public RegistrationPage step1confirm() {
        driver.findElement(step1Locator).click();
        return new RegistrationPage(driver);
    }

    public LoginPage submitStep1ExpectingFailure() {
        driver.findElement(step1Locator).click();
        return new LoginPage(driver);
    }
    public RegistrationPage step1finish(String email) {
        step1email(email);
        return step1confirm();
    }
}
