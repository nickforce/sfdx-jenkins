package Test_Classes;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class test_ACH_Data extends Abstract_Class {

    @Test
    public void testACH() throws InterruptedException {

        driver.navigate().to("https://betr--dev1.my.salesforce.com/");

        Thread.sleep(2000);

        Base_Class.login_page().login();

        Thread.sleep(2000);

        driver.navigate().to("https://betr--dev1.lightning.force.com/lightning/r/Transaction__c/a0c19000002i6HQAAY/view");

        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(true);
        Base_Class.transactions_page().clickOnAchFileLink();
        Base_Class.ach_out_page().viewAllLink();

        String transactionNumber = "TRX-0000000165";
        String amount = "$100.00";
        String transactionClass = "D";
        String transactionStatus = "Pending";
        logger.log(LogStatus.INFO,"Verifying ACH Out data");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,amount,"Amount");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,transactionClass,"Transaction Class");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,transactionStatus,"Transaction Status");





    }












}
