import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.Assert;

/**
 * Created by User on 06.05.2017.
 */
public class HomePage {
    By photoLocator=By.id("ctl00_centreContentPlaceHolder_lnkEditProfilePhoto");
    private final WebDriver driver;
    public HomePage(WebDriver driver) {
        this.driver = driver;


            (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver d) {
                    return d.findElement(photoLocator);
                }
            });
        Assert.assertTrue(driver.getTitle().contains("My Account - WhiteLabel Site06"), "Title not matching!");

        }
    }

