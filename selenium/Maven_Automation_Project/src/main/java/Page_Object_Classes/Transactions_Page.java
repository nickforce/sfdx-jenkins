package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Transactions_Page extends Abstract_Class {
    ExtentTest logger;
    public Transactions_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    @FindBy(xpath = "//*[@title='New']")
    WebElement newButton;
    @FindBy(xpath = "//span[@class='slds-radio--faux']")
    List<WebElement>  radioButton;
    @FindBy(xpath = "//*[text()='Transaction Class']/following::a[@class='select']")
    WebElement transactionClass;
    @FindBy(xpath = "//*[text()='Transaction Type']/following::a[@class='select']")
    WebElement transactionType;
    @FindBy(xpath = "//*[text()='Transaction Status']/following::a[@class='select']")
    WebElement transactionStatus;
    @FindBy(xpath = "//a[@title='Cleared']")
    WebElement transactionStatusVal;
    @FindBy(xpath = "//span[text()='Next']")
    WebElement nextButton;
    @FindBy(xpath = "//*[text()='Amount']/following::input[@class='input uiInputSmartNumber']")
    WebElement amount;
    @FindBy(xpath = "//*[text()='Betr Client Account']/following::input[@title='Search Betr Accounts']")
    WebElement betrClientAccount;
    @FindBy(xpath = "//*[text()='Effective Date']/following::div[@class='form-element']/input")
    WebElement effectiveDate;
    @FindBy(xpath = "//button[@type='button' and @title='Save']")
    WebElement saveButton;
    @FindBy(xpath = "//img[@title='Betr Account']")
    WebElement accountResult;
    @FindBy(xpath = "//*[@slot='primaryField']/lightning-formatted-text")
    WebElement transactNumber;
    @FindBy(xpath = "//*[contains(@placeholder,'Search')]")
    WebElement searchSalesforce;
    @FindBy(xpath = "//*[@class='data-match']")
    WebElement searchValueLink;
    @FindBy(xpath = "//button[@type='button' and @name='Delete']")
    WebElement transactDeleteButton;
    @FindBy(xpath = "//button[@type='button' and @title='Delete']")
    WebElement popUpDeleteButton;
    @FindBy(xpath = "//*[text()='Submitted To NACHA']/following::input[@name='Submitted_To_NACHA__c']")
    WebElement nachaCheckbox;
    @FindBy(xpath = "//*[text()='ACH Out']/following::a[contains(text(),'INVBET')]")
    WebElement achFileLink;

    public Transactions_Page createDebitTransaction(String transactionTypeVal, String transacAmount, String account, String effecDate) throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to transaction page");
        driver.navigate().to("https://betr--dev1.lightning.force.com/lightning/o/Transaction__c/list?");
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.clickByJs(driver,newButton,"New Button",logger);
        Thread.sleep(1100);
        Reusable_Methods_With_Loggers.clickByJs(driver,radioButton.get(0),"Deposit Radiobutton",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,nextButton,"Next Button",logger);
        Thread.sleep(1100);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactionType,"Transaction Type Dropdown",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,driver.findElement(By.xpath("//a[@title='"+transactionTypeVal+"']")),"Transaction Type Value",logger);
        Reusable_Methods_With_Loggers.userKeys(driver,amount,transacAmount,"Amount",logger);
        Reusable_Methods_With_Loggers.click(driver,betrClientAccount,"Betr Client Account",logger);
        Thread.sleep(3000);
        Reusable_Methods_With_Loggers.userKeys(driver,betrClientAccount,account,"Betr Client Account",logger);
        Thread.sleep(2700);
        Reusable_Methods_With_Loggers.clickByJs(driver,driver.findElement(By.xpath("//*[@title='"+account+"']")),"Account Result Link",logger);
        Reusable_Methods_With_Loggers.userKeys(driver,effectiveDate,effecDate,"Effective Date",logger);
        Reusable_Methods_With_Loggers.click(driver,saveButton,"Save Button",logger);
        return new Transactions_Page(driver);
    }//end of create debit transaction method

    public Transactions_Page createCreditTransaction(String transactionClassVal, String transactionTypeVal, String transacAmount, String account, String effecDate) throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to transaction page");
        driver.navigate().to("https://betr--dev1.lightning.force.com/lightning/o/Transaction__c/list?");
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.clickByJs(driver,newButton,"New Button",logger);
        Thread.sleep(1100);
        Reusable_Methods_With_Loggers.clickByJs(driver,radioButton.get(2),"Deposit Radiobutton",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,nextButton,"Next Button",logger);
        Thread.sleep(1100);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactionClass,"Transaction Class Dropdown",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,driver.findElement(By.xpath("//a[@title='"+transactionClassVal+"']")),"Transaction Class Value",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactionType,"Transaction Type Dropdown",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,driver.findElement(By.xpath("//a[@title='"+transactionTypeVal+"']")),"Transaction Type Value",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactionStatus,"Transaction Status Dropdown",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactionStatusVal,"Transaction Status Value",logger);
        Reusable_Methods_With_Loggers.userKeys(driver,amount,transacAmount,"Amount",logger);
        Reusable_Methods_With_Loggers.click(driver,betrClientAccount,"Betr Client Account",logger);
        Thread.sleep(1500);
        Reusable_Methods_With_Loggers.userKeys(driver,betrClientAccount,account,"Betr Client Account",logger);
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.clickByJs(driver,driver.findElement(By.xpath("//*[@title='"+account+"']")),"Account Result Link",logger);
        Reusable_Methods_With_Loggers.userKeys(driver,effectiveDate,effecDate,"Effective Date",logger);
        Reusable_Methods_With_Loggers.click(driver,saveButton,"Save Button",logger);
        return new Transactions_Page(driver);
    }//end of create debit transaction method

    public String getTransactionNumber() throws InterruptedException {
        String result = Reusable_Methods_With_Loggers.captureText(driver,transactNumber,"Transaction Number",logger);
        return result;
    }//end of get Transaction number

    public String getTransactionId() throws InterruptedException {
        String transactionId = driver.getCurrentUrl();
        String[] arrayRes = transactionId.split("c/a");
        String[] arrayRes2 = arrayRes[1].split("/view");
        return "a"+arrayRes2[0];
    }//end of get Transaction Id

    public Transactions_Page verifyNachaSubmittionCheckbox(boolean state){
        WebDriverWait wait = new WebDriverWait(driver,9);
        try {
            if (state == true) {
                if (wait.until(ExpectedConditions.visibilityOf(nachaCheckbox)).isSelected()) {
                    logger.log(LogStatus.PASS, "Submitted to NACHA checkbox is selected on Transaction Page");
                } else {
                    logger.log(LogStatus.FAIL, "Submitted to NACHA checkbox is not selected on Transaction Page");
                    Reusable_Methods_With_Loggers.getScreenShot(driver, logger, "NACHA Checkbox");
                }
            } else if (state == false) {
                if (wait.until(ExpectedConditions.visibilityOf(nachaCheckbox)).isSelected()) {
                    logger.log(LogStatus.FAIL, "Submitted to NACHA checkbox is selected on Transaction Page");
                    Reusable_Methods_With_Loggers.getScreenShot(driver, logger, "NACHA Checkbox on Transaction Page");
                } else {
                    logger.log(LogStatus.PASS, "Submitted to NACHA checkbox is not selected");
                }
            }//end of if else logic

        } catch (Exception e) {
                logger.log(LogStatus.FAIL,"Unable to locate Submitted To NACHA Checkbox " + e);
                Reusable_Methods_With_Loggers.getScreenShot(driver, logger, "NACHA Checkbox");
        }//end of try catch exception

        return new Transactions_Page(driver);
    }//end of verify NACHA checkbox

    public Transactions_Page clickOnAchFileLink() throws InterruptedException {
        Thread.sleep(2600);
        Reusable_Methods_With_Loggers.clickByJs(driver,achFileLink,"ACH File Link",logger);
        return new Transactions_Page(driver);
    }//end of clicking on ACH File Link

    public Transactions_Page deleteTransaction(String transactionNum) throws InterruptedException {

        Reusable_Methods_With_Loggers.userKeys(driver,searchSalesforce,transactionNum,"Search Field",logger);
        Thread.sleep(1500);
        Reusable_Methods_With_Loggers.clickByJs(driver,searchValueLink,"Search Result Link",logger);
        Thread.sleep(1000);
        String trxUrl = driver.getCurrentUrl();
        driver.navigate().to(trxUrl);
        Thread.sleep(2000);
        logger.log(LogStatus.INFO,"Deleting Transaction " + transactionNum);
        Reusable_Methods_With_Loggers.clickByJs(driver,transactDeleteButton,"Delete Button",logger);
        Thread.sleep(1000);
        Reusable_Methods_With_Loggers.clickByJs(driver,popUpDeleteButton,"Delete Button",logger);

        return new Transactions_Page(driver);
    }//end of delete transaction




}//end of transaction class
