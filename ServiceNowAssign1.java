package week5.day2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ServiceNowAssign1 extends BaseClassServiceNow1 {
	
	@Test(dataProvider="senddata")
	public void assignIncident1(String incidentnumber, String group, String worknotes) throws InterruptedException {
		
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);
		driver.findElement(By.xpath("(//div[@class='sn-widget-list-title' and text()='Open'])[1]")).click();
			
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
		Thread.sleep(2000);
		
		WebElement searchbox = driver.findElement(By.xpath("//span[@class='input-group-addon input-group-select']/following-sibling::input"));
		searchbox.sendKeys(incidentnumber);
		searchbox.sendKeys(Keys.ENTER);
		driver.findElement(By.xpath("//a[@class='linked formlink']")).click();
		
		driver.findElement(By.id("lookup.incident.assignment_group")).click();
			
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		Thread.sleep(2000);
			
		WebElement searchbar = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchbar.sendKeys(group);
		searchbar.sendKeys(Keys.ENTER);
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();

		driver.switchTo().window(windowHandlesList.get(0));
		WebElement frame3 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame3);
			
		driver.findElement(By.id("activity-stream-textarea")).sendKeys(worknotes);
		driver.findElement(By.xpath("(//button[text()='Update'])[1]")).click();

		String assigngroup = driver.findElement(By.xpath("(//a[@class='linked'])[4]")).getText();
		System.out.println("Assignment group: " + assigngroup);
	}
	
	@DataProvider
	public String[][] senddata() throws IOException {
		return readExcelServiceNow.readdata("Sheet3");
	}
}