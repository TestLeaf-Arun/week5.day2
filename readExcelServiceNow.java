package week5.day2;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class readExcelServiceNow {
	
	public static String[][] readdata(String filename) throws IOException {
	
	//  Locate the workbook or set up the path
		XSSFWorkbook wb = new XSSFWorkbook("./Data/ServiceNowExcel.xlsx");
			
	//  Get into the sheet1
		XSSFSheet ws = wb.getSheet(filename);
		
		int rowcount = ws.getLastRowNum();  //ws.getPhysicalNumberOfRows() ----> using this method, we can take including header
		int cellcount = ws.getRow(0).getLastCellNum();   //index starts from 0,1,2,3
		System.out.println(cellcount);
		
		String[][] data = new String[rowcount][cellcount];
			for (int i = 1; i <= rowcount; i++) {
				for (int j = 0; j < cellcount; j++) {
				String rowtext = ws.getRow(i).getCell(j).getStringCellValue();
				System.out.println(rowtext);
				data[i-1][j] = rowtext;
				}
			}
	
		wb.close();  // workbook should be closed ----> this step is important
		return data;		
	}
}