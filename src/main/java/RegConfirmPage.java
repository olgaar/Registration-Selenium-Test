import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

/**
 * Created by User on 07.05.2017.
 */
public class RegConfirmPage {
    By btnLoginLocator=By.id("ctl00_centreContentPlaceHolder_btnLogin");

    private final WebDriver driver;



    public RegConfirmPage(WebDriver driver) {
        this.driver = driver;
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(btnLoginLocator);
            }
        });
        Assert.assertTrue(driver.getTitle().contains("Login Form - WhiteLabel Site06"), "Title not matching Login Form!");
    }

}
