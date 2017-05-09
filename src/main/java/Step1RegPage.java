import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by User on 07.05.2017.
 */
public class Step1RegPage {
    By emailLocator=By.id("ctl00_centreContentPlaceHolder_ctlSignUp_txtEmail");
    By step1Locator=By.id("ctl00_centreContentPlaceHolder_ctlSignUp_submitButton");
    By btnLoginLocator=By.id("ctl00_centreContentPlaceHolder_btnLogin");


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

    public LoginPage submitStep1isUsed(String email) {
        step1email(email);
        driver.findElement(step1Locator).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(btnLoginLocator);
            }
        });
        Assert.assertTrue(driver.getTitle().contains("Login Form - WhiteLabel Site06"), "Title is not about login form");
        return new LoginPage(driver);
    }
    public LoginPage submitStep1notActiv(String email) {
        step1email(email);
        driver.findElement(step1Locator).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("pageName"));
            }
        });
        Assert.assertTrue(driver.getTitle().contains("Activation Page - WhiteLabel Site06"), "Title is not about non activation reg");
        return new LoginPage(driver);
    }
    public RegistrationPage step1finish(String email) {
        step1email(email);
        return step1confirm();
    }
}
