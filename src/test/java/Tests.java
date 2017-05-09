import com.google.common.base.Verify;
import org.hamcrest.core.Is;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.internal.seleniumemulation.IsElementPresent;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import javax.mail.*;
import javax.mail.search.SubjectTerm;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static com.sun.deploy.cache.Cache.exists;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.Assert;

/**
 * Created by User on 06.05.2017.
 */
public class Tests {
    private static final String FILENAME = "E:\\test\\filename.txt";
    @DataProvider(name = "testReg")

        public Object[][] createData2() throws Exception {
            HashMap<String,String[]> dataSet= new Text2TestData(System.getProperty("user.dir")+"\\config.properties").getData();
            //Let us assume username and password dataset
            int size1 =2;
        int size2 =8;
            Object[][] creds = new Object[size1][size2];
            String emailStrings[]=dataSet.get("email");
            String firstnameStrings[]=dataSet.get("firstname");
            String surnameStrings[]=dataSet.get("surname");
            String companyStrings[]=dataSet.get("company");
            String cityStrings[]=dataSet.get("city");
            String stateStrings[]=dataSet.get("state");
            String countryStrings[]=dataSet.get("country");
            String passwordStrings[]=dataSet.get("password");
            for(int i=0;i<size1;i++)
            {
                creds[i][0]=emailStrings[i];
                creds[i][1]=firstnameStrings[i];
                creds[i][2]=surnameStrings[i];
                creds[i][3]=companyStrings[i];
                creds[i][4]=cityStrings[i];
                creds[i][5]=stateStrings[i];
                creds[i][6]=countryStrings[i];
                creds[i][7]=passwordStrings[i];
            }
            return creds;
        }
        /*return new Object[][]{
                {"a123444410@mailinator.com", "black", "bruce", "allied", "chisinau", "All", "Angola", "Passw0rd"},
        };*/


//Test verifies end-to end registration
    @org.testng.annotations.Test(dataProvider = "testReg")
    public void testRegistration(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();

        Step1RegPage st1regpage = new Step1RegPage(driver);//First registration page
        RegistrationPage regPage = st1regpage.step1finish(email);//Second registration page
        regPage.registrAs(fname, sname, company, city, state, country, pas);//Fill all the registration fields

        //If the link is clickable
        MailHelper mailhelper= new MailHelper(driver);
        LoginPage loginPage= mailhelper.confirmReg(email);
        loginPage.loginAs(email,pas);

        //If link is not clickable
        /*wd.get("https://site06.qaw03.rxweb-dev.com/en/Website-Sign-Up/Login-Form/");
        LoginPage logPage = new LoginPage(wd);
        logPage.loginAs(email,pas);*/

        wd.killDriver(driver);


    }
//Tests the registration process until the activation
    @org.testng.annotations.Test(dataProvider = "testReg")
    public void testReg(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        By locator=By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_submitButton");
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();

        Step1RegPage st1regpage = new Step1RegPage(driver);//First registration page
        RegistrationPage regPage = st1regpage.step1finish(email);//Second registration page
        regPage.registrAs(fname, sname, company, city, state, country, pas);//Fill all the registration fields


        Thread.sleep(2000);
        //Verifying if Sign up button is present on the page
        boolean a;
            try {
                driver.findElement(locator);
                a= true;
            } catch (NoSuchElementException e) {
                a=false;
            }

        Assert.assertFalse(a,"Registration failed");

        wd.killDriver(driver);

    }


    //Test verifies if email is not activated
    @Test (dataProvider = "testReg")
    public void testRegNotActiv(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();
        Step1RegPage st1regpage = new Step1RegPage(driver);//First registration page
        st1regpage.submitStep1notActiv(email);//Second registration page


        wd.killDriver(driver);


    }

    //Test verifies if email is used
    @Test (dataProvider = "testReg")
    public void testEmailIsUsed(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();

        Step1RegPage st1regpage = new Step1RegPage(driver);//Начальная страница регистрации
        st1regpage.submitStep1isUsed(email);//Вторая страница регистрации

        wd.killDriver(driver);


    }

    //Login test
    @Test (dataProvider = "testReg")
    public void Login(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();

       LoginPage loginPage = new LoginPage(driver);
        loginPage.loginAs(email,pas);

        wd.killDriver(driver);


    }
    @Test (dataProvider = "testReg")
    public void Confirm(String email, String fname, String sname, String company, String city, String state, String country, String pas) throws Exception {
        Driver wd = new Driver();
        WebDriver driver = wd.setDriver();

        MailHelper mailHelper=new MailHelper(driver);
        mailHelper.confirmReg(email);

        wd.killDriver(driver);


    }
}