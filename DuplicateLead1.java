package week5.day2;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DuplicateLead1 extends BaseClassLeads1 {
	
	@Test(dataProvider="senddata")
	public void runDuplicateLead1(String phoneNumber) throws InterruptedException {
		driver.findElement(By.linkText("Find Leads")).click();
		driver.findElement(By.xpath("//span[text()='Phone']")).click();
		driver.findElement(By.xpath("//input[@name='phoneNumber']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//button[text()='Find Leads']")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//div[@class='x-grid3-cell-inner x-grid3-col-partyId']/a")).click();
		driver.findElement(By.linkText("Duplicate Lead")).click();
		driver.findElement(By.name("submitButton")).click();
	}
	
	@DataProvider
	public String[][] senddata() throws IOException {	
		return readExcelLeads.readdata("Sheet3");
	}
}