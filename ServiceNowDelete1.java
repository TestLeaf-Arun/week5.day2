package week5.day2;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.io.IOException;
import org.testng.annotations.DataProvider;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ServiceNowDelete1 extends BaseClassServiceNow1 {
	
	@Test(dataProvider="senddata")
	public void deleteIncident1(String incidentnumber) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='Open'])[1]")).click();
		Thread.sleep(2000);
		
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
			
		driver.findElement(By.xpath("//label[text()='Search']//following-sibling::input")).sendKeys(incidentnumber, Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		driver.findElement(By.xpath("(//button[text()='Delete'])[1]")).click();
		
		String update = driver.findElement(By.xpath("//tbody[@class='list2_body']/tr/td[text()='No records to display']")).getText();
			if (update.contains("No records")) {
				System.out.println("Incident is Deleted");
			} else {
				System.out.println("Incident is not Deleted");
			}
	}
	
	
	@DataProvider
	public String[][] senddata() throws IOException {
		return readExcelServiceNow.readdata("Sheet4");			
	}
}