package Test_Classes;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class Payments_ACH_Debit_Credit_Origination extends Abstract_Class {

    /** BETR-CLIENT1 TEST DATA **/
    String birthDate = "6/12/1991";
    String lastName = "QA_Tester_Auto_001";
    String ssn = "999334444";
    String phoneNum = "=6463334444";
    String emailAdd = "qa.testerau01@ndr.com.invalid";
    String streetAdd = "100 Bushwick Street";
    String city = "Brooklyn";
    String state = "NY";
    String zipcode = "11219";
    String policyGroup = "NDR ";
    String bankRouting = "011000138";
    String bankAccount = "908221029";
    String coClientFirstName = "Co-Client";
    String coCLientlastName = "Au01";
    String coClientBirthInfo = "6/5/1985";
    String coClientSsn = "232335555";
    String accountNumber = null;
    String transactionNumber = null;
    String currMinusTwo = Reusable_Methods_With_Loggers.getDateInFormat(-2);
    String tVal = "A";
    String amount = "100";
    String transactionUrl = null;

    /** BETR-CLIENT2 TEST DATA **/
    String birthDate2 = "6/20/1980";
    String lastName2 = "QA_Tester_Auto_002";
    String ssn2 = "121334444";
    String phoneNum2 = "2126664444";
    String emailAdd2 = "qa.testerau02@ndr.com.invalid";
    String streetAdd2 = "900 Greenwich Street";
    String city2 = "New York";
    String state2 = "NY";
    String zipcode2 = "10001";
    String policyGroup2 = "NDR ";
    String bankRouting2 = "021000021";
    String bankAccount2 = "428331029";
    String coClientFirstName2 = "Co-Client";
    String coCLientlastName2 = "Au02";
    String coClientBirthInfo2 = "7/5/1985";
    String coClientSsn2 = "191335555";
    String accountNumber2 = null;
    String transactionNumber2 = null;
    String currPlus1 = Reusable_Methods_With_Loggers.getDateInFormat(1);
    String tVal2 = "A";
    String amount2 = "150";
    String transactionUrl2 = null;

    /** ACH OUT VERIFICATION DATA  **/
    //String achTransactionNumber = "TRX-0000000165";
    String achAmount = "$100.00";
    String achAmount2 = "$150.00";
    String transactionClass = "D";
    String transactionClass2 = "W";
    String transactionStatus = "Pending";
    String transactionStatus2 = "Cleared";

    @Test
    public void CreateBetrClient() throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to Salesforce login page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/");
        //calling login method to login into salesforce dev sandbox
        Base_Class.login_page().login();
        //calling newBetrClient method to create a Betr Client
        Base_Class.betr_clients_page().newBetrClient(birthDate,lastName,ssn,phoneNum,emailAdd,streetAdd,
              city,state,zipcode,policyGroup,bankRouting,bankAccount,coClientFirstName,coCLientlastName,
              coClientBirthInfo,coClientSsn);
        //storing account number for reference
        accountNumber = Base_Class.betr_clients_page().getAccountNumber();
        logger.log(LogStatus.INFO,"Account number for Betr-Client 1 is " + accountNumber);

        /** for Betr-Client 2 **/
        //calling newBetrClient method to create a Betr Client
        Base_Class.betr_clients_page().newBetrClient(birthDate2,lastName2,ssn2,phoneNum2,emailAdd2,streetAdd2,
                city2,state2,zipcode2,policyGroup2,bankRouting2,bankAccount2,coClientFirstName2,coCLientlastName2,
                coClientBirthInfo2,coClientSsn2);
        //storing account number for reference
        accountNumber2 = Base_Class.betr_clients_page().getAccountNumber();
        logger.log(LogStatus.INFO,"Account number for Betr-Client 2 is " + accountNumber2);
    }//end of test scenario 1

    @Test(dependsOnMethods = "CreateBetrClient")
    public void CreateDebitAndCreditTransactions() throws InterruptedException {
        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal,amount,accountNumber,currMinusTwo);
        //storing transaction number for reference
        transactionNumber = Base_Class.transactions_page().getTransactionNumber();
        logger.log(LogStatus.INFO,"Transaction number for Betr-Client 1 is " + transactionNumber);
        //verify NACHA Submission checkbox is not checked
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(false);
        //capturing transaction 1 url
        transactionUrl = driver.getCurrentUrl();

        /** For Betr-Client 2 **/
        //calling createCreditTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal2,amount2,accountNumber2,currPlus1);
        //storing transaction number for reference
        transactionNumber2 = Base_Class.transactions_page().getTransactionNumber();
        logger.log(LogStatus.INFO,"Transaction number for Betr-Client 2 is " + transactionNumber);
        //verify NACHA Submission checkbox is not checked
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(false);
        //capturing transaction 2 url
        transactionUrl2 = driver.getCurrentUrl();
    }//end of test scenario 2

    @Test(dependsOnMethods = "CreateDebitAndCreditTransactions")
    public void callACHOutBatch() throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to Developer Console page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
        //calling ACHOutBatch
        Base_Class.developer_console_page().executeACHOutDebitBatch();
    }//end of test scenario 3

    @Test(dependsOnMethods = "callACHOutBatch")
    public void verifyACHOutData() throws InterruptedException {
        //waiting 15 seconds for ACH batch to be completed
        Thread.sleep(15000);
        logger.log(LogStatus.INFO,"Navigating to Transaction page");
        driver.navigate().to(transactionUrl);
        //verifying NACHA checkbox is selected on transaction 1
        logger.log(LogStatus.INFO,"Verifying NACHA Submission checkbox for " + transactionNumber);
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(true);

        //verifying NACHA checkbox is selected on transaction 2
        driver.navigate().to(transactionUrl2);
        logger.log(LogStatus.INFO,"Verifying NACHA Submission checkbox for " + transactionNumber2);
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(true);

        //clicking on ACH file Link
        Base_Class.transactions_page().clickOnAchFileLink();
        //click on view all link
        Base_Class.ach_out_page().viewAllLink();

        //for transaction 1
        Base_Class.ach_out_page().verifyAchData(transactionNumber,achAmount,"Amount");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,transactionClass,"Transaction Class");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,transactionStatus,"Transaction Status");
        Base_Class.ach_out_page().verifyAchData(transactionNumber,currMinusTwo,"Effective Date");

        //for transaction 2
        Base_Class.ach_out_page().verifyAchData(transactionNumber2,achAmount2,"Amount");
        Base_Class.ach_out_page().verifyAchData(transactionNumber2,transactionClass2,"Transaction Class");
        Base_Class.ach_out_page().verifyAchData(transactionNumber2,transactionStatus2,"Transaction Status");
        Base_Class.ach_out_page().verifyAchData(transactionNumber2,currPlus1,"Effective Date");
    }//end of test scenario 4

    @Test(dependsOnMethods = "verifyACHOutData")
    public void deleteAllData() throws InterruptedException {
        //calling deleteTransaction method
        Base_Class.transactions_page().deleteTransaction(transactionNumber);
        //calling deleteClient method
        Base_Class.betr_clients_page().deleteClient(lastName,accountNumber);

        /**for client 2 **/
        //calling deleteTransaction method
        Base_Class.transactions_page().deleteTransaction(transactionNumber2);
        //calling deleteClient method
        Base_Class.betr_clients_page().deleteClient(lastName2,accountNumber2);
    }//end of test scenario 5


}//end of java class
