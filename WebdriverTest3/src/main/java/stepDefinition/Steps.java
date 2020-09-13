package stepDefinition;

import static org.junit.Assert.assertTrue;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import cucumber.api.java.en.Given;		
import cucumber.api.java.en.Then;		
import cucumber.api.java.en.When;		

public class Steps {				

	WebDriver driver;
	
	String url = "https://www.saucedemo.com/";
    String driverPath = "C:\\Users\\rocio\\chromedriver.exe";
    String user = "standard_user";
	String password = "secret_sauce";
	String first_name = "Rocío";
	String last_name = "Prueba";
	String postal_code = "41009";
	
	By by_username = By.id("user-name");
	By by_password = By.id("password");
	By by_login = By.id("login-button");
	By by_inventory_container = By.id("inventory_container");
	By by_add_to_cart = By.xpath("//*[@class='inventory_list']/div[1]/div[3]/button");
	By by_span_cart = By.xpath("//*[@id='shopping_cart_container']/a/span");
	By by_shopping_cart = By.id("shopping_cart_container");
	By by_cart_contents = By.id("cart_contents_container");
	By by_checkout_button = By.xpath("//*[contains(@class,'checkout_button')]");
	By by_checkout_container = By.id("checkout_info_container");
	By by_first_name = By.id("first-name");
	By by_last_name = By.id("last-name");
	By by_postal_code = By.id("postal-code");
	By by_cart_button = By.xpath("//*[contains(@class,'cart_button')]");
	By by_checkout_summary = By.id("checkout_summary_container");
	By by_checkout_complete = By.id("checkout_complete_container");
	By by_menu_sidebar_button = By.xpath("//button[.='Open Menu']");
	By by_menu_sidebar = By.className("bm-menu");
	By by_logout_button = By.id("logout_sidebar_link");
	By by_login_wraper = By.className("login_wrapper");
	
    @Given("^Open Chrome and launch the website$")				
    public void open_Chrome_launch_website() throws Throwable							
    {	
    	System.setProperty("webdriver.chrome.driver",driverPath);
    	driver = new ChromeDriver();
    	driver.get(url);			
    }	
    
    @When("^I login into the website$")					
    public void login_website() throws Throwable 							
    {	
    	//login into the website
    	driver.findElement(by_username).sendKeys(user);
    	driver.findElement(by_password).sendKeys(password);
    	driver.findElement(by_login).click();
    	
    	//check if login is finished correctly
  	    assertTrue("The page is not loaded correctly",driver.findElement(by_inventory_container).isDisplayed());
    }		
    
    @Then("^I buy a product$")					
    public void i_buy_product() throws Throwable 							
    {	
    	WebDriverWait wait = new WebDriverWait(driver,10);
    	
    	//click on "Add to cart" button of the first item
        driver.findElement(by_add_to_cart).click();	
        
        //check item is added correctly
        assertTrue("The item is not added correctly",driver.findElement(by_span_cart).isDisplayed());
  	   
        //click on the shopping cart
  	    driver.findElement(by_shopping_cart).click();
  	    
  	    //wait for my cart page is loaded correctly
  	    wait.until(ExpectedConditions.visibilityOfElementLocated(by_cart_contents));
  	    
  	    //click on checkout button
  	    driver.findElement(by_checkout_button).click();
  	    
  	    //wait for checkout page is loaded correctly
  	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("checkout_info_container")));
  	    
  	    //fill the fields and click on continue
  	    driver.findElement(by_first_name).sendKeys(first_name);
  	    driver.findElement(by_last_name).sendKeys(last_name);
  	    driver.findElement(by_postal_code).sendKeys(postal_code);
  	    driver.findElement(by_cart_button).click();
  	    
  	    //check if checkout overview is loaded correctly
  	    assertTrue("The checkout overview is not loaded correctly",driver.findElement(by_checkout_summary).isDisplayed());
  	    
  	    //click on finish button
 	    driver.findElement(by_cart_button).click();
 	    
 	    //check if purchase is finished successfully
 	    assertTrue("The purchase is not finished correctly",driver.findElement(by_checkout_complete).isDisplayed()); 
    }	
    
    @Then("^I logout$")					
    public void i_logout() throws Throwable 							
    {	
    	WebDriverWait wait = new WebDriverWait(driver,10);
    	
    	//Click on menu sidebar
    	driver.findElement(by_menu_sidebar_button).click();
    	
    	//wait menu sidebar is opened
    	wait.until(ExpectedConditions.visibilityOfElementLocated(by_menu_sidebar));
    	
    	//Click on logout button
    	driver.findElement(by_logout_button).click();
    	
    	//check if logout is finished correctly
    	assertTrue("The logout is not performed correctly",driver.findElement(by_login_wraper).isDisplayed());
   	    
    	//close driver
    	driver.close();
    }

}
