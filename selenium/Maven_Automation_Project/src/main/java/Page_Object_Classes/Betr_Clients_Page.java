package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.XMLFormatter;

public class Betr_Clients_Page extends Abstract_Class {

    ExtentTest logger;
    public Betr_Clients_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    @FindBy(xpath = "//span[@class='slds-truncate' and text()='Betr Clients']")
    WebElement betrCientTab;
    @FindBy(xpath = "//*[@title='New']")
    WebElement newButton;
    @FindBy(xpath = "//*[text()='Salutation']/following::a[@class='select']")
    WebElement salutation;
    @FindBy(xpath = "//*[@placeholder='Last Name']")
    WebElement lastName;
    @FindBy(xpath = "//*[text()='Birthdate']/following::div[@class='form-element']/input")
    WebElement birthDateField;
    @FindBy(xpath = "//*[text()='SSN']/following::input")
    WebElement ssn;
    @FindBy(xpath = "//*[text()='Phone']/following::input[@type='tel']")
    WebElement phone;
    @FindBy(xpath = "//*[text()='Email']/following::input")
    WebElement email;
    @FindBy(xpath = "//*[@placeholder='Mailing Street']")
    WebElement mailingStreet;
    @FindBy(xpath = "//*[@placeholder='Mailing City']")
    WebElement mailingCity;
    @FindBy(xpath = "//*[@placeholder='Mailing State/Province']")
    WebElement mailingState;
    @FindBy(xpath = "//*[@placeholder='Mailing Zip/Postal Code']")
    WebElement mailingZip;
    @FindBy(xpath = "//*[text()='Validated']/following::input[@type='checkbox']")
    WebElement validatedCheckbox;
    @FindBy(xpath = "//*[text()='Drafts Authorized']/following::input[@type='checkbox']")
    WebElement draftsAutthorized;
    @FindBy(xpath = "//*[text()='Betr Policy Group']/following::div[@class='autocompleteWrapper slds-grow']/input")
    WebElement betrPolicyGrp;
    @FindBy(xpath = "//*[@title='NDR Default Policy Group']")
    WebElement polciyGroupName;
    @FindBy(xpath = "//*[text()='Bank Routing Number']/following::input[@type='text']")
    WebElement bankRoutingNumber;
    @FindBy(xpath = "//*[text()='Bank Account Number']/following::input[@type='text']")
    WebElement bankAccountNumber;
    @FindBy(xpath = "//*[text()='Co-Client First Name']/following::input[@type='text']")
    WebElement coClientFirstName;
    @FindBy(xpath = "//*[text()='Co-Client Last Name']/following::input[@type='text']")
    WebElement coClientLastName;
    @FindBy(xpath = "//*[text()='Co-Client Birthdate']/following::div[@class='form-element']/input")
    WebElement coClientBirthDate;
    @FindBy(xpath = "//*[text()='Co-Client SSN']/following::input[@type='text']")
    WebElement coClientSsn;
    @FindBy(xpath = "//button[@title='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//a[contains(@class,'textUnderline outputLookupLink')]")
    WebElement accountNumber;
    @FindBy(xpath = "//*[contains(@placeholder,'Search')]")
    WebElement searchSalesforce;
    @FindBy(xpath = "//*[@class='data-match']")
    WebElement searchValueLink;
    @FindBy(xpath = "//button[@title='Edit Client']")
    WebElement editClient;
    @FindBy(xpath = "//button[@type='button' and @title='Clear Selection']")
    WebElement closeIcon;
    @FindBy(xpath = "//a[@class='forceActionLink' and @title='Delete']")
    WebElement clientDeleteButton;
    @FindBy(xpath = "//button[@type='button' and @name='Delete']")
    WebElement accountDeleteButton;
    @FindBy(xpath = "//button[@type='button' and @title='Delete']")
    WebElement popUpDeleteButton;

    public Betr_Clients_Page newBetrClient(String birthDate,String lastNameInput, String ssNum, String phoneNum, String emailAdd, String streetNum, String city, String state, String zip, String policyGroup,String bankRoute, String bankAccount,String coClientFirst,
           String coClientLast, String coClientBirthInfo, String coClientSsNum) throws InterruptedException {
        logger.log(LogStatus.INFO,"Creating a new Betr Client");
        driver.navigate().to("https://cs24.lightning.force.com/lightning/o/Contact/list?");
        //click on new
        Reusable_Methods_With_Loggers.clickByJs(driver,newButton,"New",logger);
        //enter birth date
        Reusable_Methods_With_Loggers.userKeys(driver,birthDateField,birthDate,"Birth Date",logger);
        //entering last name
        Reusable_Methods_With_Loggers.userKeys(driver,lastName,lastNameInput,"Last Name",logger);
        //entering SSN
        Reusable_Methods_With_Loggers.userKeys(driver,ssn,ssNum,"SSN",logger);
        //entering phone number
        Reusable_Methods_With_Loggers.userKeys(driver,phone,phoneNum,"Phone Number",logger);
        //entering email
        Reusable_Methods_With_Loggers.userKeys(driver,email,emailAdd,"Email",logger);
        //entering street address
        Reusable_Methods_With_Loggers.userKeys(driver,mailingStreet,streetNum,"Street Address",logger);
        //entering city
        Reusable_Methods_With_Loggers.userKeys(driver,mailingCity,city,"City",logger);
        //entering state
        Reusable_Methods_With_Loggers.userKeys(driver,mailingState,state,"State",logger);
        //entering zip code
        Reusable_Methods_With_Loggers.userKeys(driver,mailingZip,zip,"Zip Code",logger);
        //click on validated checkbox
        Reusable_Methods_With_Loggers.clickByJs(driver,validatedCheckbox,"Validated Checkbox",logger);
        //click on Drafts Authorized checkbox
        Reusable_Methods_With_Loggers.clickByJs(driver,draftsAutthorized,"Drafts Authorized Checkbox",logger);
        //click on policy group
        Reusable_Methods_With_Loggers.click(driver,betrPolicyGrp,"Betr Policy Group",logger);
        Thread.sleep(2100);
        //enter policy group
        Reusable_Methods_With_Loggers.userKeys(driver,betrPolicyGrp,policyGroup,"Betr Policy Group",logger);
        //click on policy group name
        Reusable_Methods_With_Loggers.clickByJs(driver,polciyGroupName,"Policy Group Name Link",logger);
        //enter bank routing
        Reusable_Methods_With_Loggers.userKeys(driver,bankRoutingNumber,bankRoute,"Bank Routing Number",logger);
        //enter bank account
        Reusable_Methods_With_Loggers.userKeys(driver,bankAccountNumber,bankAccount,"Bank Account Number",logger);
        //enter Co-Client First Name
        Reusable_Methods_With_Loggers.userKeys(driver,coClientFirstName,coClientFirst,"Co-Client First Name",logger);
        //enter Co-Client Last Name
        Reusable_Methods_With_Loggers.userKeys(driver,coClientLastName,coClientLast,"Co-Client Last Name",logger);
        //enter Co-Client DOB
        Reusable_Methods_With_Loggers.userKeys(driver,coClientBirthDate,coClientBirthInfo,"Co-Client DOB",logger);
        //enter Co-Client SSN
        Reusable_Methods_With_Loggers.userKeys(driver,coClientSsn,coClientSsNum,"Co-Client SSN",logger);
        //clicking on 'Save' button
        Reusable_Methods_With_Loggers.clickByJs(driver,saveButton,"Save Button",logger);
        return new Betr_Clients_Page(driver);
    }//end of newBetrClient method

    public String getAccountNumber() throws InterruptedException {
        String acc = Reusable_Methods_With_Loggers.captureText(driver,accountNumber,"Account Number",logger);
        return acc;
    }//end of get account number

    public Betr_Clients_Page deleteClient(String clientName, String accountNum) throws InterruptedException {
        logger.log(LogStatus.INFO,"Deleting Client " + clientName + " with account number " + accountNum);
        Reusable_Methods_With_Loggers.userKeys(driver,searchSalesforce,clientName,"Search Field",logger);
        Thread.sleep(3000);
        Reusable_Methods_With_Loggers.clickByJs(driver, searchValueLink,"Client Name Link",logger);
        Thread.sleep(1500);
        String clientUrl = driver.getCurrentUrl();
        driver.navigate().to(clientUrl);
        Thread.sleep(1500);
        Reusable_Methods_With_Loggers.clickByJs(driver, accountNumber,"Client Account Number Link",logger);
        Thread.sleep(2000);
        driver.manage().timeouts().setScriptTimeout(2, TimeUnit.SECONDS);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,300)");
        Reusable_Methods_With_Loggers.clickByAsynJs(driver,editClient,"Edit Client",logger);
        Reusable_Methods_With_Loggers.clickByAsynJs(driver, closeIcon,"Close Icon",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,saveButton,"Save Button",logger);
        String accUrl = driver.getCurrentUrl();
        //Reusable_Methods_With_Loggers.userKeys(driver,searchSalesforce,clientName,"Search Field",logger);
        driver.navigate().to(clientUrl);
        Thread.sleep(2500);
        //Reusable_Methods_With_Loggers.clickByJs(driver, searchValueLink,"Client Name Link",logger);
        logger.log(LogStatus.INFO,"Deleting Betr Client " + clientName);
        Reusable_Methods_With_Loggers.clickByJs(driver,clientDeleteButton,"Delete Button",logger);
        Thread.sleep(1000);
        Reusable_Methods_With_Loggers.clickByJs(driver,popUpDeleteButton,"Delete Button",logger);

        //Reusable_Methods_With_Loggers.userKeys(driver,searchSalesforce,accountNum,"Search Field",logger);
        //Thread.sleep(2500);
        //Reusable_Methods_With_Loggers.clickByJs(driver, searchValueLink,"Account Number Link",logger);
        //Thread.sleep(2000);
        logger.log(LogStatus.INFO,"Deleting Account " + accountNum);
        driver.navigate().to(accUrl);
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.click(driver,accountDeleteButton,"Delete Button",logger);
        Thread.sleep(1500);
        Reusable_Methods_With_Loggers.clickByJs(driver,popUpDeleteButton,"Delete Button",logger);


        return new Betr_Clients_Page(driver);

    }





}//end of Betr Clients page
