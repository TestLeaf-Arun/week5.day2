package week5.day2;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseClassServiceNow1 {
	
	public ChromeDriver driver;
	@Parameters({"url","username","password"})  //name should match with the name in the parameter in the xml file

	@BeforeMethod
	public void precondition(String url, String username, String password) {   //order should be same but argument name can be anything		
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(url);

		WebElement frame1 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));																		
		driver.switchTo().frame(frame1);
		driver.findElement(By.id("user_name")).sendKeys(username);
		driver.findElement(By.id("user_password")).sendKeys(password);		
		driver.findElement(By.id("sysverb_login")).click();
	}
	
	@AfterMethod
	public void postCondition() {
		driver.close();
	}
}