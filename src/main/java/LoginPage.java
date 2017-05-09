import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by User on 06.05.2017.
 */
public class LoginPage {
    By usernameLocator = By.id("ctl00_centreContentPlaceHolder_txtUsername");
    By passwordLocator = By.id("ctl00_centreContentPlaceHolder_txtPassword");
    By loginButtonLocator = By.id("ctl00_centreContentPlaceHolder_btnLogin");
    By photoLocator=By.id("ctl00_centreContentPlaceHolder_lnkEditProfilePhoto");
By resendActLocator =By.id("ctl00_centreContentPlaceHolder_btnSendEmail");
    private final WebDriver driver;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
        driver.get("https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/Login-Form/");


    }

    public LoginPage typeUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
        return this;
    }

    public HomePage submitLogin() {
        driver.findElement(loginButtonLocator).click();
        return new HomePage(driver);
    }

    public void submitLoginExpectingFailure(String username, String password) {
        typeUsername(username);
        typePassword(password);
        driver.findElement(loginButtonLocator).click();
        driver.findElement(resendActLocator).click();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(resendActLocator);
            }
        });
        Assert.assertFalse(driver.getTitle().contains("My Account - WhiteLabel Site06"), "Title not matching!");

    }

    public HomePage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}