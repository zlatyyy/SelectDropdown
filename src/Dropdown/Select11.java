package Dropdown;
import static org.junit.Assert.*;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.Select;
import org.apache.james.mime4j.message.Message;
import org.hamcrest.core.IsEqual;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.*;
import org.w3c.css.sac.SelectorList;
import com.google.gson.annotations.Until;
import com.thoughtworks.selenium.webdriven.commands.IsVisible;
public class Select11 {
	private WebDriver driver;
	String baseUrl;
	@Before
	public void openDriver()  {
		//System.setProperty("webdriver.chrome.driver", "C:/Users/Zlatka/workspace/chromedriver_win32/chromedriver.exe");	
		driver = new FirefoxDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
	}
	@After
	public void closeDriver(){
		driver.quit();
	}
	@Test
	public void test(){
		baseUrl="http://shop.pragmatic.bg/";
		driver.get(baseUrl+"/admin/");
		driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys("parola");
        WebElement submit=driver.findElement(By.className("button"));
        submit.click();
        WebElement nextPage = driver.findElement(By.cssSelector("div#header>div#menu>ul.right.sf-js-enabled>li#store+li>a.top"));
        if(nextPage.isDisplayed()){
        	Select dropdown=new Select(driver.findElement(By.id("range")));
     		//Verify Dropdown does not support multiple selection
     		assertFalse(dropdown.isMultiple());
     		//Verify Dropdown has four options for selection
        	assertEquals(4, dropdown.getOptions().size());    	
        	//We will verify Dropdown has expected values as listed in a array
        	List<String> exp_options = Arrays.asList(new String[]{"Today", "This Week", "This Month","This Year"});
        	List<String> act_options = new ArrayList<String>();        	
        	//Retrieve the option values from Dropdown using getOptions() method
        	for(WebElement option : dropdown.getOptions())
        		 act_options.add(option.getText());    	
        	//Verify expected options array and actual options array match
        	assertArrayEquals(exp_options.toArray(),act_options.toArray());
        }
        else{
 			fail("The password or the username is wrong!");
        }
	} 
	@Test
	public void checkBoxes(){
		String baseUrl="https://dl.dropboxusercontent.com/";
		driver.get(baseUrl+"/u"+"/55228056/"+"Config.html");
		WebElement air = driver.findElement(By.name("airbags"));
		WebElement sensor = driver.findElement(By.name("parksensor"));
		air.click();
		sensor.click();
		if(air.isSelected() && sensor.isSelected()){
		driver.findElement(By.name("Conf_Next"));
		}
		else{System.out.println("The checkboxes are not marked");}
	}
}
