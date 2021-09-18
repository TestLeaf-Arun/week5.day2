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

public class ServiceNowCreate1 extends BaseClassServiceNow1 {
	
	@Test(dataProvider="senddata")
	public void createIncident1(String description) throws InterruptedException {
		driver.findElement(By.xpath("//input[@id='filter']")).sendKeys("incident", Keys.ENTER);

        driver.findElement(By.xpath("//div[@class='sn-widget-list-title' and text()='Create New']")).click();
		WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
		driver.switchTo().frame(frame2);
        driver.findElement(By.xpath("(//span[@class='icon icon-search'])[1]")).click();
		
		Set<String> windowHandlesSet = driver.getWindowHandles();
		List<String> windowHandlesList = new ArrayList<String>(windowHandlesSet);
		driver.switchTo().window(windowHandlesList.get(1));
		
		driver.findElement(By.xpath("(//a[@class='glide_ref_item_link'])[1]")).click();
		
		driver.switchTo().window(windowHandlesList.get(0));
		Thread.sleep(2000);		
		driver.switchTo().frame(frame2);
		driver.findElement(By.id("incident.short_description")).sendKeys(description);

		String incidentnumber = driver.findElement(By.xpath("//input[@id='incident.number']")).getAttribute("value");
		System.out.println("Incident number: " + incidentnumber);

        driver.findElement(By.xpath("(//button[text()='Submit'])[2]")).click();

		WebElement searchtext = driver.findElement(By.xpath("(//input[@class='form-control'])[1]"));
		searchtext.sendKeys(incidentnumber);
		searchtext.sendKeys(Keys.ENTER);

		String resultincident = driver.findElement(By.xpath("//a[@class='linked formlink']")).getText();
			if (incidentnumber.equalsIgnoreCase(resultincident)) {
				System.out.println("Incident is created successfully");
			} else {
				System.out.println("Incident is not created");
			}		
	}
	
	@DataProvider
	public String[][] senddata() throws IOException {
		return readExcelServiceNow.readdata("Sheet1");		
	}
}