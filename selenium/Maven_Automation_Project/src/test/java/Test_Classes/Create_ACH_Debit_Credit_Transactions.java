package Test_Classes;

import Page_Object_Classes.Base_Class;
import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.annotations.Test;

public class Create_ACH_Debit_Credit_Transactions extends Abstract_Class {

    /** BETR-CLIENT1 TEST DATA **/
    String birthDate = "6/12/1991";
    String lastName = "QA_Tester_1001";
    String ssn = "222334444";
    String phoneNum = "2123334444";
    String emailAdd = "qa.tester@ndr.com.invalid";
    String streetAdd = "400 Bushwick Street";
    String city = "Brooklyn";
    String state = "NY";
    String zipcode = "11219";
    String policyGroup = "NDR ";
    String bankRouting = "011000138";
    String bankAccount = "608221029";
    String coClientFirstName = "Co-Client";
    String coCLientlastName = "01";
    String coClientBirthInfo = "6/5/1985";
    String coClientSsn = "222335555";
    String accountNumber = null;
    String transaction1aDebitNumber = null;
    String debit1aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-2);
    String transaction1aCreditNumber = null;
    String credit1aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-3);
    String transaction1bDebitNumber = null;
    String debit1bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-2);
    String transaction1bCreditNumber = null;
    String credit1bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-3);
    String tVal = "A";
    String amount = "100";
    String transaction1aDebitUrl = null;
    String transaction1bDebitUrl = null;
    String transaction1aCreditUrl = null;
    String transaction1bCreditUrl = null;


    /** BETR-CLIENT2 TEST DATA **/
    String birthDate2 = "6/20/1980";
    String lastName2 = "QA_Tester_1002";
    String ssn2 = "111334444";
    String phoneNum2 = "2125554444";
    String emailAdd2 = "qa.tester02@ndr.com.invalid";
    String streetAdd2 = "500 Greenwich Street";
    String city2 = "New York";
    String state2 = "NY";
    String zipcode2 = "10001";
    String policyGroup2 = "NDR ";
    String bankRouting2 = "021000021";
    String bankAccount2 = "608331029";
    String coClientFirstName2 = "Co-Client";
    String coCLientlastName2 = "02";
    String coClientBirthInfo2 = "7/5/1985";
    String coClientSsn2 = "111335555";
    String accountNumber2 = null;
    String transaction2aDebitNumber = null;
    String debit2aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-4);
    String transaction2aCreditNumber = null;
    String credit2aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-5);
    String transaction2bDebitNumber = null;
    String debit2bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-4);
    String transaction2bCreditNumber = null;
    String credit2bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-5);
    String tVal2 = "A";
    String amount2 = "150";
    String transaction2aDebitUrl = null;
    String transaction2bDebitUrl = null;
    String transaction2aCreditUrl = null;
    String transaction2bCreditUrl = null;

    /** BETR-CLIENT3 TEST DATA **/
    String birthDate3 = "6/2/1980";
    String lastName3 = "QA_Tester_1003";
    String ssn3 = "555334444";
    String phoneNum3 = "7185554444";
    String emailAdd3 = "qa.tester03@ndr.com.invalid";
    String streetAdd3 = "600 Greenwich Street";
    String city3 = "New York";
    String state3 = "NY";
    String zipcode3 = "10001";
    String policyGroup3 = "NDR ";
    String bankRouting3 = "021000021";
    String bankAccount3 = "709331029";
    String coClientFirstName3 = "Co-Client";
    String coCLientlastName3 = "03";
    String coClientBirthInfo3 = "7/5/1985";
    String coClientSsn3 = "444335555";
    String accountNumber3 = null;
    String transaction3aDebitNumber = null;
    String debit3aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-1);
    String transaction3aCreditNumber = null;
    String credit3aEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(0);
    String transaction3bDebitNumber = null;
    String debit3bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(-1);
    String transaction3bCreditNumber = null;
    String credit3bEffectiveDate = Reusable_Methods_With_Loggers.getDateInFormat(0);
    String tVal3 = "A";
    String amount3 = "150";
    String transaction3aDebitUrl = null;
    String transaction3bDebitUrl = null;
    String transaction3aCreditUrl = null;
    String transaction3bCreditUrl = null;

    @Test(priority = 1)
    public void CreateBetrClients() throws InterruptedException {

        logger.log(LogStatus.INFO,"Navigating to Salesforce login page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/");
        //calling login method to login into salesforce dev sandbox
        Base_Class.login_page().login();

        //creating client 1
        Base_Class.betr_clients_page().newBetrClient(birthDate,lastName,ssn,phoneNum,emailAdd,streetAdd,
                city,state,zipcode,policyGroup,bankRouting,bankAccount,coClientFirstName,coCLientlastName,
                coClientBirthInfo,coClientSsn);
        //storing account number for reference
        accountNumber = Base_Class.betr_clients_page().getAccountNumber();

        //creating client 2
        Base_Class.betr_clients_page().newBetrClient(birthDate2,lastName2,ssn2,phoneNum2,emailAdd2,streetAdd2,
                city2,state2,zipcode2,policyGroup2,bankRouting2,bankAccount2,coClientFirstName2,coCLientlastName2,
                coClientBirthInfo2,coClientSsn2);
        //storing account number for reference
        accountNumber2 = Base_Class.betr_clients_page().getAccountNumber();

        //creating client 3
        Base_Class.betr_clients_page().newBetrClient(birthDate3,lastName3,ssn3,phoneNum3,emailAdd3,streetAdd3,
                city3,state3,zipcode3,policyGroup3,bankRouting3,bankAccount3,coClientFirstName3,coCLientlastName3,
                coClientBirthInfo3,coClientSsn3);
        //storing account number for reference
        accountNumber3 = Base_Class.betr_clients_page().getAccountNumber();

    }//end of create clients


    @Test(priority = 2)
    public void CreateTransactionsForClient1() throws InterruptedException {
        /** Debit Transactions for client 1 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal,amount,accountNumber,debit1aEffectiveDate);
        //storing transaction number for reference
        transaction1aDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction1aDebitUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal,amount,accountNumber,debit1bEffectiveDate);
        //storing transaction number for reference
        transaction1bDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction1bDebitUrl= driver.getCurrentUrl();

        /** Credit Transactions for client 1 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal,amount,accountNumber,credit1aEffectiveDate);
        //storing transaction number for reference
        transaction1aCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction1aCreditUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal,amount,accountNumber,credit1bEffectiveDate);
        //storing transaction number for reference
        transaction1bCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction1bCreditUrl= driver.getCurrentUrl();
    }//end of create transactions for client 1

    @Test(priority = 3)
    public void CreateTransactionsForClient2() throws InterruptedException {
        /** Debit Transactions for client 2 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal2,amount2,accountNumber2,debit2aEffectiveDate);
        //storing transaction number for reference
        transaction2aDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction2aDebitUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal2,amount2,accountNumber2,debit2bEffectiveDate);
        //storing transaction number for reference
        transaction2bDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction2bDebitUrl= driver.getCurrentUrl();

        /** Credit Transactions for client 2 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal2,amount2,accountNumber2,credit2aEffectiveDate);
        //storing transaction number for reference
        transaction2aCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction2aCreditUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal2,amount,accountNumber2,credit2bEffectiveDate);
        //storing transaction number for reference
        transaction2bCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction2bCreditUrl= driver.getCurrentUrl();
    }//end of create transactions for client 2

    @Test(priority = 4)
    public void CreateTransactionsForClient3() throws InterruptedException {
        /** Debit Transactions for client 3 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal3,amount3,accountNumber3,debit3aEffectiveDate);
        //storing transaction number for reference
        transaction3aDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction3aDebitUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createDebitTransaction(tVal3,amount3,accountNumber3,debit3bEffectiveDate);
        //storing transaction number for reference
        transaction3bDebitNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction3bDebitUrl= driver.getCurrentUrl();

        /** Credit Transactions for client 3 **/
        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal3,amount3,accountNumber3,credit3aEffectiveDate);
        //storing transaction number for reference
        transaction3aCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction3aCreditUrl= driver.getCurrentUrl();

        //calling createDebitTransaction method
        Base_Class.transactions_page().createCreditTransaction("W",tVal3,amount3,accountNumber3,credit3bEffectiveDate);
        //storing transaction number for reference
        transaction3bCreditNumber = Base_Class.transactions_page().getTransactionNumber();
        //capturing transaction 1a debit url
        transaction3bCreditUrl= driver.getCurrentUrl();
    }//end of create transactions for client 3

    @Test(dependsOnMethods = "CreateTransactionsForClient3")
    public void callACHOutBatch() throws InterruptedException {
        logger.log(LogStatus.INFO,"Navigating to Developer Console page");
        driver.navigate().to("https://betr--dev1.my.salesforce.com/_ui/common/apex/debug/ApexCSIPage");
        //calling ACHOutBatch
        Base_Class.developer_console_page().executeACHOutDebitBatch();
    }//end of test scenario 3



}//end of loop
