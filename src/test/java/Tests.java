import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.DataProvider;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.sun.deploy.cache.Cache.exists;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by User on 06.05.2017.
 */
public class Tests {

    @DataProvider(name = "testReg")
    private Object[][] createData2() {
        return new Object[][]{
                {"a123444410@mailinator.com", "black", "bruce", "allied", "chisinau", "All", "Angola", "Passw0rd"},
        };
    }


    @org.testng.annotations.Test(dataProvider = "testReg")
    public void test(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\Download\\chromedriver_win32\\chromedriver.exe");
        WebDriver wd = new ChromeDriver();

        Step1RegPage st1regpage = new Step1RegPage(wd);//Начальная страница регистрации
        RegistrationPage regPage = st1regpage.step1finish(email);//Вторая страница регистрации
        regPage.registrAs(fname, sname, company, city, state, country, pas);//Заполняются все поля регистрации

        /* Эта часть должна работать, если кликается ссылка
        MailHelper mailhelper= new MailHelper(wd);
        LoginPage loginPage= mailhelper.confirmReg(email);
        loginPage.loginAs(email,pas);
*/
        //Т.к. ссылка не кликается
        wd.get("https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/Login-Form/");
        LoginPage logPage = new LoginPage(wd);
        logPage.loginAs(email,pas);

        wd.close();
        wd.quit();


    }
}