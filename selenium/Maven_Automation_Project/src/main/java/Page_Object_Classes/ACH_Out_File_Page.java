package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.ReadFiles;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ACH_Out_File_Page extends Abstract_Class {
    ExtentTest logger;
    public ACH_Out_File_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    //delete all files in download directory
    public ACH_Out_File_Page deleteFiles(){
        logger.log(LogStatus.INFO,"Deleting files from download directory");
        File directory = new File("C:\\Users\\sumon.kashem\\Downloads");
        File[] files = directory.listFiles();
        for (File file : files)
        {
            file.delete();
        }
        return new ACH_Out_File_Page(driver);
    }//end of delete files from download folder

    public ACH_Out_File_Page verifyAchOutWithSF(String fileName,String transactionId, String amount) throws FileNotFoundException {
        File achFile = new File("C:\\Users\\sumon.kashem\\Downloads\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(achFile));
        List<String> list = new ArrayList<>();
        list = br.lines().collect(Collectors.toList());
        int batchCount = 0;
        int fivei = 0;
        String lineSixCreditAmount = null;
        String lineSixDebitAmount = null;
        String transactionIdNumber = null;
        logger.log(LogStatus.INFO,"Verifying If transaction ID " + transactionId + " exist on ACH Out File");
        for (int i = 0; i < list.size(); i++) {
            //starting with line from 5 to get first transaction to continue
            if (list.get(i).startsWith("52")) {
                batchCount++; //to get each batch count on the file
                fivei = i;//counter needed for second sub loop
                //running the sub loop to get all transaction for specific batch
                for (int j = fivei + 1; j < list.size(); j++) {
                    if (list.get(j).startsWith("62")) {
                        if (list.get(j).startsWith("622")) {
                            transactionIdNumber = list.get(j).substring(40, 54);
                            //verify if transactionId exist in ACH file
                            if (transactionIdNumber.equals(transactionId)) {
                                lineSixCreditAmount = list.get(j).substring(30,39);
                                logger.log(LogStatus.PASS,"Transaction Id " + transactionId + " exist on Credit transaction for batch " + batchCount);
                                if(lineSixCreditAmount.contains(amount)){
                                    logger.log(LogStatus.PASS,"Credit amount matches with Amount from SF for same transaction " + amount);
                                } else {
                                    logger.log(LogStatus.FAIL, "Credit amount doesn't match with amount from SF for same transaction. Expected:" + amount + " Actual:" + lineSixCreditAmount);
                                }
                            }//end of if transaction displays
                        } else if (list.get(j).startsWith("627")) {
                            transactionIdNumber = list.get(j).substring(40, 54);
                            if (transactionIdNumber.equals(transactionId)) {
                                lineSixDebitAmount = list.get(j).substring(30,39);
                                logger.log(LogStatus.PASS,"Transaction Id " + transactionId + " exist on Credit transaction for batch " + batchCount);
                                if(lineSixDebitAmount.contains(amount)){
                                    logger.log(LogStatus.PASS,"Debit amount matches with Amount from SF for same transaction " + amount);
                                } else {
                                    logger.log(LogStatus.FAIL, "Debit amount doesn't match with amount from SF for same transaction. Expected:" + amount + " Actual:" + lineSixCreditAmount);
                                }
                            }//end of if transaction displays
                        }//end of credit & debit
                    }//end of if 6
                    if (list.get(j).startsWith("82")) {
                        break;
                    }//end of if 8 then break
                }//end of sub for loop;
            }//end of fivei
        }//end of main loop
        return new ACH_Out_File_Page(driver);
    }//end of verify transaction id method

    public ACH_Out_File_Page verifyTransactionCount(String fileName, String transactionType) throws IOException {
        File achFile = new File("C:\\Users\\sumon.kashem\\Downloads\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(achFile));
        List<String> list = new ArrayList<>();
        list = br.lines().collect(Collectors.toList());

        int totalTransac = 0;
        int totalCreditTransac = 0;
        int totalDebitTransac = 0;
        int batchCount = 0;
        int fivei = 0;
        String debit = null;
        String credit = null;
        for (int i = 0; i < list.size(); i++) {
            //starting with line from 5 to get first transaction to continue
            if (list.get(i).startsWith("52")) {
                batchCount++; //to get each batch count on the file
                fivei = i;//counter needed for second sub loop
                //running the sub loop to get all transaction for specific batch
                for (int j = fivei + 1; j < list.size(); j++) {
                    if (list.get(j).startsWith("62")) {
                        totalTransac++; //to get total transaction per batch
                        if (list.get(j).startsWith("622")) {
                            totalCreditTransac++;
                        } else if (list.get(j).startsWith("627")) {
                            totalDebitTransac++;
                        }//end of credit & debit
                    }//end of if 6
                    if (list.get(j).startsWith("82")) {
                        break;
                    }//end of if 8 then break
                }//end of sub for loop;
            }//end of fivei
        }//end of main for loop
        if(transactionType.equals("622")) {
            if (totalTransac == (totalCreditTransac)) {
                logger.log(LogStatus.PASS, "Total transaction count matches for all credits " + totalTransac);
            } else {
                logger.log(LogStatus.FAIL,"Total transaction count doesn't match with all credit count. Total Transaction count : " + totalTransac + "  Total credit transaction count: " + totalCreditTransac);
            }
        } else if(transactionType.equals("627")) {
            if (totalTransac == (totalDebitTransac)) {
                logger.log(LogStatus.PASS, "Total transaction count matches for all debits " + totalTransac);
            } else {
                logger.log(LogStatus.FAIL,"Total transaction count doesn't match with all debit count. Total Transaction count : " + totalTransac + "  Total debit transaction count: " + totalDebitTransac);
            }
        }//end of total transaction verification

        return new ACH_Out_File_Page(driver);
    }//end of verify total transaction count method


    public ACH_Out_File_Page verifyTotalAmountOnControlRecords(String fileName, String transactionType) throws IOException {
        File achFile = new File("C:\\Users\\sumon.kashem\\Downloads\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(achFile));
        List<String> list = new ArrayList<>();
        list = br.lines().collect(Collectors.toList());
        int fivei = 0;
        int eighti = 0;
        int sixi = 0;
        int batchCount = 0;
        String lineSixCreditAmount = null;
        String lineSixDebitAmount = null;
        String line8CreditAmount = null;
        String line8DebitAmount = null;
        String line9CreditAmount = null;
        String line9DebitAmount = null;
        int totalTransactionAmount = 0;
        int creditAmountPlus = 0;
        int debitAmountPlus = 0;
        String transactionIdNumber = null;
        for (int i = 0; i < list.size(); i++) {
            //starting with line from 5 to get first transaction to continue
            if (list.get(i).startsWith("52")) {
                batchCount++; //to get each batch count on the file
                fivei = i;//counter needed for second sub loop
                //running the sub loop to get all transaction for specific batch
                debitAmountPlus = 0;
                creditAmountPlus = 0;
                for (int j = fivei + 1; j < list.size(); j++) {
                    if (list.get(j).startsWith("62")) {
                        if (list.get(j).startsWith("622")) {
                            lineSixCreditAmount = list.get(j).substring(30,39);
                            creditAmountPlus = (creditAmountPlus + Integer.parseInt(lineSixCreditAmount));
                            totalTransactionAmount = (totalTransactionAmount + Integer.parseInt(lineSixCreditAmount));
                        } else if(list.get(j).startsWith("627")) {
                            lineSixDebitAmount = list.get(j).substring(30,39);
                            debitAmountPlus = (debitAmountPlus + Integer.parseInt(lineSixDebitAmount));
                            totalTransactionAmount = (totalTransactionAmount + Integer.parseInt(lineSixDebitAmount));
                        }//end of credit & debit
                    }//end of if 6
                    if (list.get(j).startsWith("82")) {
                        line8CreditAmount = list.get(j).substring(33,44);
                        line8DebitAmount = list.get(j).substring(21,32);
                        if(transactionType.equals("622")) {
                            if (creditAmountPlus == Integer.parseInt(line8CreditAmount)) {
                                logger.log(LogStatus.PASS, "For batch " + batchCount + " total credit amount is same as batch control credit amount - " + creditAmountPlus);
                            } else {
                                logger.log(LogStatus.FAIL, "For batch " + batchCount + " total credit amount is not same as batch control credit amount - Expected: " + creditAmountPlus + " and Actual: " + line8CreditAmount);
                            }//end of batch control credit amount
                        } else if(transactionType.equals("627")){                             if (debitAmountPlus == Integer.parseInt(line8DebitAmount)) {
                                    logger.log(LogStatus.PASS, "For batch " + batchCount + " total debit amount is same as batch control debit amount - " + debitAmountPlus);
                                } else {
                                    logger.log(LogStatus.FAIL, "For batch " + batchCount + " total debit amount is not same as batch control debit amount - Expected: " + debitAmountPlus + " and Actual: " + line8DebitAmount);
                                }//end of batch control debit amount
                        }//end of transaction type condition
                        break;
                    }//end of if 8 then break
                }//end of sub for loop;
            }//end of fivei
            if(list.get(i).startsWith("90")){
                line9DebitAmount = list.get(i).substring(32,43);
                line9CreditAmount = list.get(i).substring(44,55);
                if(transactionType.equals("622")) {
                    if (totalTransactionAmount == Integer.parseInt(line9CreditAmount)) {
                        logger.log(LogStatus.PASS, "Total Credit amount on all batches is equal to credit amount in file control - " + totalTransactionAmount);
                    } else {
                        logger.log(LogStatus.FAIL, "Total Credit amount on all batches is NOT equal to credit amount in file control. Expected: " + totalTransactionAmount + " Actual: " + line9CreditAmount);

                    }
                } else if(transactionType.equals("627")){
                    if (totalTransactionAmount == Integer.parseInt(line9DebitAmount)) {
                        logger.log(LogStatus.PASS, "Total Debit amount on all batches is equal to debit amount in record 90 file control - " + totalTransactionAmount);
                    } else {
                        logger.log(LogStatus.FAIL, "Total Debit amount on all batches is not equal to debit amount in record 90 file control. Expected: " + totalTransactionAmount + " Actual: " + line9DebitAmount);

                    }

                }//end of of line 90 condition
                break;
            }//end of if 90 then break
        }//end of main for loop

        return new ACH_Out_File_Page(driver);
    }//end of verify total amount on batch control method


}//end of java class
