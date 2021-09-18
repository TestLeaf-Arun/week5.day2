package week5.day2;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.IOException;
import org.testng.annotations.DataProvider;

public class CreateLead1 extends BaseClassLeads1 {
	
	@Test(dataProvider="senddata")
	public void runCreateLead1(String company,String fname,String lname,String phno) {
		driver.findElement(By.linkText("Create Lead")).click();
		driver.findElement(By.id("createLeadForm_companyName")).sendKeys(company); 
		driver.findElement(By.id("createLeadForm_firstName")).sendKeys(fname);
		driver.findElement(By.id("createLeadForm_lastName")).sendKeys(lname);
		driver.findElement(By.id("createLeadForm_primaryPhoneNumber")).sendKeys(phno);
		driver.findElement(By.name("submitButton")).click();
	}
	
	
	@DataProvider
	public String[][] senddata() throws IOException {
		return readExcelLeads.readdata("Sheet1");  //readExcel is a class; readdata is a method from the same class
	}	
}