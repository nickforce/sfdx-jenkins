package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.AccessibleObject;

public class ACH_Out_Page extends Abstract_Class {

    ExtentTest logger;
    public ACH_Out_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    @FindBy(xpath = "//span[@class='view-all-label']")
    WebElement viewAllLink;
    @FindBy(xpath = "//span[contains(@title,'INVBETR')]")
    WebElement achOutFileLink;
    @FindBy(xpath = "//button[@title='Download']")
    WebElement downloadButton;

    public ACH_Out_Page viewAllLink() throws InterruptedException {
        Thread.sleep(2800);
        Reusable_Methods_With_Loggers.clickByJs(driver,viewAllLink,"View All Link",logger);
        return new ACH_Out_Page(driver);
    }//end of view all lilnk


    public ACH_Out_Page verifyAchData(String transactionNumber, String specificDataVerification, String verificationName) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,9);
        String data = null;
        logger.log(LogStatus.INFO,"Verifying ACH Out data for transaction " + transactionNumber);
        try {
            data = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@class,'slds-truncate') and @title='" + transactionNumber + "']/following::span[contains(@class,'slds-truncate') and text()='" + specificDataVerification + "']"))).getText();
            if (data.equals(specificDataVerification)) {
                logger.log(LogStatus.PASS, "For Transaction Number " + transactionNumber + " the " + verificationName + " value matches as expected in ACH File: " + specificDataVerification);
            } else {
                logger.log(LogStatus.FAIL, "Unable to match data or element not found ");
                Reusable_Methods_With_Loggers.getScreenShot(driver, logger, verificationName);
            }//end if else condition
        } catch (Exception e) {
             logger.log(LogStatus.FAIL,"Unable to locate/find element " + e);
             Thread.sleep(900);
             Reusable_Methods_With_Loggers.getScreenShot(driver,logger,"ACH Out Data Verification");
        }//end of exception
        return new ACH_Out_Page((driver));
    }//end of verify ACH Data method

    //return transaction ID
    public String getTransactionID(String transactionUrl) throws InterruptedException {
            String value = transactionUrl;
            String[] arrayVal = transactionUrl.split("/a");
            String[] arrayVal2 = arrayVal[1].split("/view");
            String result = "a"+arrayVal2[0];
            String finalResult = result.substring(0,15);
        return finalResult;
    }//end of get transaction id method

    //click on download to download ACH File
    public String downloadACHOutFileAndReturnFileName(String transactionUrl) throws InterruptedException {
        driver.navigate().to(transactionUrl);
        Thread.sleep(1500);
        Base_Class.transactions_page().clickOnAchFileLink();
        Thread.sleep(1500);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("window.scrollBy(0,1500)");
        Thread.sleep(3000);
        //gettext
        String fileName = Reusable_Methods_With_Loggers.captureText(driver,achOutFileLink,"ACH Out File Link",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,achOutFileLink,"ACH Out File Link",logger);
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.clickByJs(driver,downloadButton,"Download BUtton",logger);
        return fileName;
    }//end of download ACH Out File

}//end of ACH Out class
