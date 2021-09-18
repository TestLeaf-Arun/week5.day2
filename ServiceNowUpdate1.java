package week5.day2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServiceNowUpdate1 extends BaseClassServiceNow1 {
	
	@Test(dataProvider="senddata")
	public void updateIncident1(String incidentnumber) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='All'])[2]")).click();

		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//label[text()='Search']//following-sibling::input")).sendKeys(incidentnumber, Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[text()='INC0010011']")).click();
		
		driver.findElement(By.xpath("(//select[@id='incident.urgency']//option)[1]")).click();
		driver.findElement(By.xpath("(//select[@id='incident.state']//option)[2]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//button[text()='Update'])[1]")).click();

		String priority = driver.findElement(By.xpath("(//table/tbody/tr[1]/td[text()])[2]")).getText();
			if (priority.contains("High")) {
				System.out.println("Priority is set as High");
			} else {
				System.out.println("Priority is not High");
			}
			
		String str = driver.findElement(By.xpath("(//table/tbody/tr[1]/td[text()])[3]")).getText();
			if (str.contains("Progress")) {
				System.out.println("State is in progress");
			} else {
				System.out.println("Not started");
			}		
	}	
	
	@DataProvider
	public String[][] senddata() throws IOException {
		return readExcelServiceNow.readdata("Sheet2");		
	}
}