package Page_Object_Classes;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Methods;
import Reusable_Classes.Reusable_Methods_With_Loggers;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.model.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_Page extends Abstract_Class {
    ExtentTest logger;
    public Login_Page(WebDriver driver){
        super();
        PageFactory.initElements(driver,this);
        this.logger = super.logger;
    }//end of constructor class

    @FindBy(xpath = "//*[@id='username']")
    WebElement username;
    @FindBy(xpath = "//*[@id='password']")
    WebElement pass;
    @FindBy(xpath = "//*[@value='Log In to Sandbox']")
    WebElement loginButton;

    public Login_Page login() throws InterruptedException {
        Thread.sleep(2000);
        Reusable_Methods_With_Loggers.userCredential(driver,username,"sumon@betrlink.dev1","Username",logger);
        Reusable_Methods_With_Loggers.userCredential(driver,pass,"llending#1","Password",logger);
        Reusable_Methods_With_Loggers.clickByJs(driver,loginButton,"Login Button",logger);
        return new Login_Page(driver);
    }//end of login method





}//end of java class
