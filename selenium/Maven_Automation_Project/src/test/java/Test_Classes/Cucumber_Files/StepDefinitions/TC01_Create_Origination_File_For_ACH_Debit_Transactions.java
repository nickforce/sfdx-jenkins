package Test_Classes.Cucumber_Files.StepDefinitions;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.LogStatus;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class TC01_Create_Origination_File_For_ACH_Debit_Transactions extends Abstract_Class {

    /** BETR-CLIENT1 TEST DATA **/
    String birthDate = "6/12/1991";
    String lastName = "QA_Tester_Auto_001";
    String ssn = "999334444";
    String phoneNum = "6463334444";
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
    String achOutPage = null;

    /** ACH OUT VERIFICATION DATA  **/
    //String achTransactionNumber = "TRX-0000000165";
    String achAmount = "$100.00";
    String achAmount2 = "$150.00";
    String transactionClass = "D";
    String transactionClass2 = "D";
    String transactionStatus = "Pending";
    String transactionStatus2 = "Pending";
    String fileName = null;

    @Test(priority = 0)
    @Given("^I navigate to Salesforce page on Dev1 sandbox$")
    public void loginToSalesforce() throws InterruptedException {
        logger.log(LogStatus.INFO, "Navigating to Salesforce login page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/");
        //calling login method to login into salesforce dev sandbox
        Base_Class.login_page().login();
    }//end of given method

    @Test(dependsOnMethods = "loginToSalesforce")
    @When("^I go to Betr-Client tab to create test client 1$")
    public void createBetrClient1() throws InterruptedException {
        //calling newBetrClient method to create a Betr Client
        Base_Class.betr_clients_page().newBetrClient(birthDate, lastName, ssn, phoneNum, emailAdd, streetAdd,
                city, state, zipcode, policyGroup, bankRouting, bankAccount, coClientFirstName, coCLientlastName,
                coClientBirthInfo, coClientSsn);
        //storing account number for reference
        accountNumber = Base_Class.betr_clients_page().getAccountNumber();
        logger.log(LogStatus.INFO, "Account number for Betr-Client 1 is " + accountNumber);
    }//end of betr client 1 method

    @Test(dependsOnMethods = "createBetrClient1")
    @When("^I go to Betr-Client tab to create test client 2$")
    public void createBetrClient2() throws InterruptedException {
        //calling newBetrClient method to create a Betr Client
        Base_Class.betr_clients_page().newBetrClient(birthDate2,lastName2,ssn2,phoneNum2,emailAdd2,streetAdd2,
                city2,state2,zipcode2,policyGroup2,bankRouting2,bankAccount2,coClientFirstName2,coCLientlastName2,
                coClientBirthInfo2,coClientSsn2);
        //storing account number for reference
        accountNumber2 = Base_Class.betr_clients_page().getAccountNumber();
        logger.log(LogStatus.INFO,"Account number for Betr-Client 2 is " + accountNumber2);
    }//end of betr client 2 method

    @Test(dependsOnMethods = "createBetrClient2")
    @When("^I go to Transaction tab to create 2 debit transaction for test clients$")
    public void createDebitTransactionsForClients() throws InterruptedException {

        /** For Betr-Client 1 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal, amount, accountNumber, currMinusTwo);
        //storing transaction number for reference
        transactionNumber = Base_Class.transactions_page().getTransactionNumber();
        logger.log(LogStatus.INFO, "Transaction number for Betr-Client 1 is " + transactionNumber);
        //verify NACHA Submission checkbox is not checked
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(false);
        //capturing transaction 1 url
        transactionUrl = driver.getCurrentUrl();

        /** For Betr-Client 2 **/
        //calling createCreditTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal2,amount2,accountNumber2,currPlus1);
        //storing transaction number for reference
        transactionNumber2 = Base_Class.transactions_page().getTransactionNumber();
        logger.log(LogStatus.INFO,"Transaction number for Betr-Client 2 is " + transactionNumber);
        //verify NACHA Submission checkbox is not checked
        Base_Class.transactions_page().verifyNachaSubmittionCheckbox(false);
        //capturing transaction 2 url
        transactionUrl2 = driver.getCurrentUrl();
    }//end of create 2 debit transaction method for client 1 & client 2

    @Test(dependsOnMethods = "createDebitTransactionsForClients")
    @Then("^I call ACH batch job to Originate ACH Out file$")
    public void callACHOutBatch() throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to Developer Console page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
        //calling ACHOutBatch
        Base_Class.developer_console_page().executeACHOutDebitBatch();
    }//end of ACH out batch method

    @Test(dependsOnMethods = "callACHOutBatch")
    @Then("^I verify specific data from each transaction matches with data from in ACH Page$")
    public void verifyACHOutDataForAllTransactions() throws InterruptedException {
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
        Thread.sleep(1500);
        //capture ach out page url
        achOutPage = driver.getCurrentUrl();

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
    }//end of ACH Out Data verification method in salesforce

    @Test(dependsOnMethods = "verifyACHOutDataForAllTransactions")
    @Then("^I verify transaction ID and amount matches with SF in ACH Out File$")
    public void verifyACHOutFileTransactionIdAndAmountWithSf() throws InterruptedException, FileNotFoundException {
        //get transaction id
        String transactId1 = Base_Class.ach_out_page().getTransactionID(transactionUrl);
        String transactId2 = Base_Class.ach_out_page().getTransactionID(transactionUrl2);
        //deleting files from download directory to get fresh ACH Out File
        Base_Class.ach_out_file_page().deleteFiles();
        //clicking on download ACH Out File
        fileName = Base_Class.ach_out_page().downloadACHOutFileAndReturnFileName(transactionUrl);
        Thread.sleep(4800);
        //verifying transaction ID and amount matches with SF for transaction 1
        Base_Class.ach_out_file_page().verifyAchOutWithSF(fileName,transactId1,amount);
        //verifying transaction ID and amount matches with SF for transaction 2
        Base_Class.ach_out_file_page().verifyAchOutWithSF(fileName,transactId2,amount2);
    }//end of verify ach out file transaction id method

    @Test(dependsOnMethods = "verifyACHOutFileTransactionIdAndAmountWithSf")
    @Then("^I verify all transaction count equal to all Debit count in ACH Out File$")
    public void verifyACHOutFileTotalTransactionCount() throws InterruptedException, IOException {
        Base_Class.ach_out_file_page().verifyTransactionCount(fileName,"627");
    }//end of verify ach out file total transaction count method

    @Test(dependsOnMethods = "verifyACHOutFileTotalTransactionCount")
    @Then("^I verify all debit amount from a batch is equal to debit amount in batch control line$")
    public void verifyACHOutFileTotalDebitBatchControlAmount() throws InterruptedException, IOException {
        //fileName = "INVBETR04_O_DEPOSITS.7-6-2020.pgp";
        Base_Class.ach_out_file_page().verifyTotalAmountOnControlRecords(fileName,"627");
    }//end of verify ach out file total debit amount on batch control method

    @Test(dependsOnMethods = "verifyACHOutFileTotalDebitBatchControlAmount")
    @Then("^I delete all QA transactions, clients and accounts from salesforce$")
    public void deleteAllData() throws InterruptedException {
        /**for client 1 **/
        //calling deleteTransaction method
        Base_Class.transactions_page().deleteTransaction(transactionNumber);
        //calling deleteClient method
        Base_Class.betr_clients_page().deleteClient(lastName,accountNumber);

        /**for client 2 **/
        //calling deleteTransaction method
        Base_Class.transactions_page().deleteTransaction(transactionNumber2);
        //calling deleteClient method
        Base_Class.betr_clients_page().deleteClient(lastName2,accountNumber2);
    }//end of delete all data method


}//end of java class
