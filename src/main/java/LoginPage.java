import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by User on 06.05.2017.
 */
public class LoginPage {
    By usernameLocator = By.id("ctl00_centreContentPlaceHolder_txtUsername");
    By passwordLocator = By.id("ctl00_centreContentPlaceHolder_txtPassword");
    By loginButtonLocator = By.id("ctl00_centreContentPlaceHolder_btnLogin");

    private final WebDriver driver;



    public LoginPage(WebDriver driver) {
        this.driver = driver;


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

    public LoginPage submitLoginExpectingFailure() {
        driver.findElement(loginButtonLocator).click();
        return new LoginPage(driver);
    }

    public HomePage loginAs(String username, String password) {
        typeUsername(username);
        typePassword(password);
        return submitLogin();
    }
}