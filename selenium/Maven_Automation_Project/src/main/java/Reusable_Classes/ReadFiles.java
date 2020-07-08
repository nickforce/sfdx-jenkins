package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ReadFiles {
    public static void verifyAchTransactionId(String fileName, String transactionId, ExtentTest logger) throws FileNotFoundException {
        File achFile = new File("C:\\Users\\sumon.kashem\\Downloads\\" + fileName);
        BufferedReader br = new BufferedReader(new FileReader(achFile));
        List<String> list = new ArrayList<>();
        list = br.lines().collect(Collectors.toList());
        int batchCount = 0;
        int fivei = 0;
        String transactionIdNumber = null;
        logger.log(LogStatus.INFO,"Verifying If transaction ID " + transactionId + " exist");
        for (int i = 0; i < list.size(); i++) {
            //starting with line from 5 to get first transaction to continue
            if (list.get(i).startsWith("52")) {
                batchCount++; //to get each batch count on the file
                fivei = i;//counter needed for second sub loop
                //running the sub loop to get all transaction for specific batch
                for (int j = fivei + 1; j < list.size(); j++) {
                    if (list.get(j).startsWith("62")) {
                        if (list.get(j).startsWith("622")) {
                            transactionIdNumber = list.get(j).substring(39, 54);
                            //verify if transactionId exist in ACH file
                            if (transactionIdNumber.equals(transactionId)) {
                                System.out.println("Transaction Id " + transactionId + " exist on Credit transaction for batch " + batchCount);
                                logger.log(LogStatus.PASS,"Transaction Id " + transactionId + " exist on Credit transaction for batch " + batchCount);
                            }//end of if
                        } else if (list.get(j).startsWith("627")) {
                            transactionIdNumber = list.get(j).substring(39, 54);
                            //verify if transactionId exist in ACH file
                            if (transactionIdNumber.equals(transactionId)){
                                System.out.println("Transaction Id " + transactionId + " exist on Debit transaction for batch " + batchCount);
                                logger.log(LogStatus.PASS,"Transaction Id " + transactionId + " exist on Debit transaction for batch " + batchCount);
                            }//end of if
                        }//end of credit & debit
                    }//end of if 6
                    if (list.get(j).startsWith("82")) {
                        break;
                    }//end of if 8 then break
                }//end of sub for loop;
            }//end of fivei
        }//end of main loop
    }//end of verify transaction id

    public static void readAchFile(String fileName) throws IOException {
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
                            String creditIdNum = list.get(j).substring(39,54);
                            System.out.println("For batch " + batchCount + " Credit Identification Number is " + creditIdNum);
                           // System.out.println("For batch " + batchCount + " Credit transaction is " + list.get(j));
                        } else if (list.get(j).startsWith("627")) {
                            totalDebitTransac++;
                            String debitIdNum = list.get(j).substring(39,54);
                            System.out.println("For batch " + batchCount + " Debit Identification Number is " + debitIdNum);
                            //System.out.println("For batch " + batchCount + " Debit transaction is " + list.get(j));
                        }//end of credit & debit
                    }//end of if 6
                    if (list.get(j).startsWith("82")) {
                        //eighti = j;
                         debit = list.get(j).substring(20,31);
                         credit = list.get(j).substring(32,43);
                        break;
                    }//end of if 8 then break
                }//end of sub for loop;
                System.out.println("Line 8 for batch " + batchCount + " Debit is " + debit + " credit is " + credit);
            }//end of fivei
        }//end of main for loop
    }//end of method


}//end of java class
