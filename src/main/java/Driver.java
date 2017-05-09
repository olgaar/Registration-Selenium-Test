import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by User on 08.05.2017.
 */
public class Driver {

    public WebDriver setDriver() {
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        return driver;
    }

    public void killDriver(WebDriver driver) {
        driver.close();
        driver.quit();
    }

}
