package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class Base_Class extends Abstract_Class {
    public Base_Class(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
    }//end of constructor class


    //object for betr-client page
    public static Betr_Clients_Page betr_clients_page(){
        Betr_Clients_Page betr_clients_page = new Betr_Clients_Page(driver);
        return betr_clients_page;
    }//end of betr client page object

    //object for login page
    public static Login_Page login_page(){
        Login_Page login_page = new Login_Page(driver);
        return login_page;
    }//end of login page object

    //object for Transaction page
    public static Transactions_Page transactions_page(){
        Transactions_Page transactions_page = new Transactions_Page(driver);
        return transactions_page;
    }//end of Transaction page object

    //object for ACH Out page
    public static ACH_Out_Page ach_out_page(){
        ACH_Out_Page ach_out_page = new ACH_Out_Page(driver);
        return ach_out_page;
    }//end of ACH out page object

    //object for Developer Console Page
    public static Developer_Console_Page developer_console_page(){
        Developer_Console_Page developer_console_page = new Developer_Console_Page(driver);
        return developer_console_page;
    }//end of Developer Console page object

    //object for ACH Out File Page
    public static ACH_Out_File_Page ach_out_file_page(){
        ACH_Out_File_Page ach_out_file_page = new ACH_Out_File_Page(driver);
        return ach_out_file_page;
    }//end of ACH Out File page object



}//end of java class
