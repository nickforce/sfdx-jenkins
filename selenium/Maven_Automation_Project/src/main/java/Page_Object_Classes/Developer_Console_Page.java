package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Developer_Console_Page extends Abstract_Class {

    ExtentTest logger;
    public Developer_Console_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    @FindBy(xpath = "//*[@id='debugMenuEntry-btnInnerEl']")
    WebElement debuggerDropdown;
    @FindBy(xpath = "//div[text()='Open Execute Anonymous Window']")
    WebElement openWindowButton;
    @FindBy(xpath = "//button//span[text()='Execute']")
    WebElement executeButton;

    public Developer_Console_Page executeACHOutDebitBatch() throws InterruptedException {
        Actions actions = new Actions(driver);
        Thread.sleep(2500);
        //clicking on debug menu Entry
        Reusable_Methods_With_Loggers.clickByJs(driver,debuggerDropdown,"Debugger Menu",logger);
        Thread.sleep(2000);
        //clicking on Open Execute Window Button
        Reusable_Methods_With_Loggers.clickByJs(driver,openWindowButton,"Open Execute Window BUtton",logger);
        Thread.sleep(2500);
        //Calling ACHOutBatch to be executed
        actions.sendKeys("Database.executeBatch(new ACHOutBatch('BetrLink Master Bank', true));\n").perform();
        //clicking on Execute button to execute the batch
        Reusable_Methods_With_Loggers.click(driver,executeButton,"Execute Button",logger);

        return new Developer_Console_Page(driver);
    }//end of execute ACH out batch

    public Developer_Console_Page executeACHOutCreditBatch() throws InterruptedException {
        Actions actions = new Actions(driver);
        Thread.sleep(2500);
        //clicking on debug menu Entry
        Reusable_Methods_With_Loggers.clickByJs(driver,debuggerDropdown,"Debugger Menu",logger);
        Thread.sleep(2000);
        //clicking on Open Execute Window Button
        Reusable_Methods_With_Loggers.clickByJs(driver,openWindowButton,"Open Execute Window BUtton",logger);
        Thread.sleep(2500);
        //Calling ACHOutBatch to be executed
        actions.sendKeys("Database.executeBatch(new ACHOutBatch('BetrLink Master Bank', false));\n").perform();
        //clicking on Execute button to execute the batch
        Reusable_Methods_With_Loggers.click(driver,executeButton,"Execute Button",logger);

        return new Developer_Console_Page(driver);
    }//end of execute ACH out batch

}//end of java class
