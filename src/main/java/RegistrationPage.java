import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 * Created by User on 07.05.2017.
 */
public class RegistrationPage {
    By firstnameLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtFirstname");
    By surnameLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtSurname");
    By companyLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtCompany");
    By cityLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtCity");
    By stateLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtCounty");
    By passLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtPassword");
    By passConfLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_txtConfirmPassword");
    By countryLocator = By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_ddlCountries");
    By agreeLocator=By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_chkTAndC");
    By signupLocator=By.id("ctl00_centreContentPlaceHolder_ctlCreateProfile_submitButton");
    private final WebDriver driver;



    public RegistrationPage(WebDriver driver) {
        this.driver = driver;

    }




    public RegistrationPage typeFName(String firstname) {
        driver.findElement(firstnameLocator).sendKeys(firstname);
        return this;
    }
    public RegistrationPage typeSName(String surname) {
        driver.findElement(surnameLocator).sendKeys(surname);
        return this;
    }
    public RegistrationPage typeCompany(String company) {
        driver.findElement(companyLocator).sendKeys(company);
        return this;
    }
    public RegistrationPage typeCity(String city) {
        driver.findElement(cityLocator).sendKeys(city);
        return this;
    }
    public RegistrationPage typeState(String state) {
        driver.findElement(stateLocator).sendKeys(state);
        return this;
    }

    public RegistrationPage chooseCountry(String country) {
        //driver.findElement(countryLocator).click();
        //By countryName = By.xpath("\"//div[normalize-space(text())='"+country+"']\"");
       // Select select = new Select(driver.findElement(countryLocator));
        //select.selectByVisibleText(country);
        WebElement selectElem = driver.findElement(countryLocator);
        Select select = new Select(selectElem);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        select.selectByVisibleText(country);
        selectElem.click();
        return this;
    }


    public RegistrationPage typePassword1(String password) {
        driver.findElement(passLocator).sendKeys(password);
        return this;
    }
    public RegistrationPage typePassword2(String password) {
        driver.findElement(passConfLocator).sendKeys(password);
        return this;
    }

    public RegistrationPage agreeCT() {
        driver.findElement(agreeLocator).click();
        return this;
    }


    public MailHelper submitRegistration() {
        driver.findElement(signupLocator).click();
        return new MailHelper(driver);
    }

    public RegistrationPage submitRegistrationExpectingFailure() {
        driver.findElement(signupLocator).click();
        return new RegistrationPage(driver);
    }

    public MailHelper registrAs(String firstname, String surname, String company, String city, String state, String country, String password) {
        typeFName(firstname);
        typeSName(surname);
        typeCompany(company);
        typeCity(city);
        chooseCountry(country);
        typePassword1(password);
        typePassword2(password);
        typeState(state);
        agreeCT();
        return submitRegistration();
    }
}



