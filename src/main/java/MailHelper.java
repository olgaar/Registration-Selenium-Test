

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import javax.mail.Authenticator;
import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;
import javax.mail.search.FromTerm;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class MailHelper {
    By mailLocator = By.id("inboxfield");
    By mailSubmitLoc=(By.className("input-group-btn"));
   By letterLoc=By.xpath("//div[normalize-space(text())='Activate Your WhiteLabel Site06 Profile']");
    By visibLocator=By.id("publiccontenttypeselect");
    By btnLoginLocator=By.id("ctl00_centreContentPlaceHolder_btnLogin");

        private final WebDriver driver;


    public MailHelper(WebDriver driver) {
            this.driver = driver;

        }
     public MailHelper enterMail(String email){
        driver.get("https://www.mailinator.com/");
         WebElement element = driver.findElement(mailLocator);
         element.click();
         element.sendKeys(email);
         element = driver.findElement(mailSubmitLoc);
         element.click();
         return this;
    }
    public MailHelper findLetter(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement element = driver.findElement(letterLoc);
        element.click();

        return this;
    }
    public MailHelper makeLinkVisible() {
        WebElement selectElem = driver.findElement(visibLocator);
        Select select = new Select(selectElem);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        select.selectByVisibleText("text/plain");
        selectElem.click();
        return this;
    }
//В этом методе я пробовала разные варианты, драйвер не видит ссылку
    public MailHelper clickLink() {
        // WebDriverWait wait = new WebDriverWait(wd, 5);
        //wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/Activation-Page/?")));
        //element=wd.findElement(By.xpath("//a[contains(text(), 'pageculture')]/@href"));
        //element=wd.findElement(By.cssSelector("a[href*=pageculture]"));//(By.xpath((".//*[@id='publicshowmaildivcontent']/a")));
        WebElement element= driver.findElement(By.xpath("//div[normalize-space(text())='https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/Activation-Page/?']"));
        element.click();
        return this;
    }
    public LoginPage confirmReg(String email){
        enterMail(email);
        findLetter();
        makeLinkVisible();
        clickLink();
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(btnLoginLocator);
            }
        });
        Assert.assertTrue(driver.getTitle().contains("Login Form - WhiteLabel Site06"), "Title is not about login form");
        return new LoginPage(driver);
    }
    public MailHelper confirmRegFailed(String email){
        enterMail(email);
        findLetter();
        makeLinkVisible();
        clickLink();
        return new MailHelper(driver);
    }
}        