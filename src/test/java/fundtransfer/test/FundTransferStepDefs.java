package fundtransfer.test;
 
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import cucumber.api.java.*;
import cucumber.api.java.en.*;
import static org.junit.Assert.assertEquals;
 
public class FundTransferStepDefs {
    protected WebDriver driver;
 
    @Before
    public void setUp() {
        // En este case utilizaremos Google Chrome como navegador
        // Para ello necesitaremos definir la propiedad webdriver.chrome.driver con la ruta donde tenemos
        // descargado el driver para Google Chrome. Además lo instanciaremos con ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\eriol\\chromedriver.exe");
        driver = new ChromeDriver();
    }
 
    @Given("the user is on Fund Transfer Page")
    public void The_user_is_on_fund_transfer_page() {
        driver.get("file:///C:/Users/eriol/Desktop/fundTransfer.html");
    }
 
    @When("he enters \"([^\"]*)\" as payee name")
    public void He_enters_payee_name(String payeeName) {
        WebElement payee = (new WebDriverWait(driver, 5))
                .until(new ExpectedCondition<WebElement>() {
                    public WebElement apply(WebDriver d) {
                        return d.findElement(By.id("payee"));
                    }
        });
        payee.sendKeys(payeeName); 
    }
 
    @And("he enters \"([^\"]*)\" as amount")
    public void He_enters_amount(String amount) {
        driver.findElement(By.id("amount")).sendKeys(amount);
    }
 
    @And("he Submits request for Fund Transfer")
    public void He_submits_request_for_fund_transfer() {
        driver.findElement(By.id("transfer")).click();
    }
     
    @Then("^ensure the fund transfer is complete with \"([^\"]*)\" message$")
    public void ensure_the_fund_transfer_is_complete_with_message(String arg1) {
        WebElement message = driver.findElement(By.id("message"));
        assertEquals(message.getText(), arg1);
    }
 
    @Then("^ensure a transaction failure message \"([^\"]*)\" is displayed$")
    public void Ensure_a_transaction_failure_message(String arg1) {
        WebElement message = driver.findElement(By.id("message"));
        assertEquals(message.getText(), arg1);
    }
 
    @After
    public void tearDown() {
        driver.close();
    }
}
