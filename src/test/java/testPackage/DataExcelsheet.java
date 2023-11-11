	package testPackage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DataExcelsheet {
	
	
	  public static Workbook book;
	  public static Sheet sheet;
	  
	public static  Object[][] testdata(String sheetname)
	{
		
		FileInputStream file = null;
		try 
		{
			file=new FileInputStream("C:\\Users\\mohan\\eclipse-workspace\\AutomationP\\src\\test\\java\\testdata\\automateData.xlsx");
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
		try 
		{
		book=WorkbookFactory.create(file);
			
		}
		
		catch (IOException e)
		{
			
			e.printStackTrace();
		}
		
		//reading data from sheet
		sheet=book.getSheet(sheetname);
	
		Object[][] data=new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		
		for(int i=0; i<sheet.getLastRowNum(); i++)
		{
			for(int j=0; j<sheet.getRow(0).getLastCellNum(); j++)
			{
				data[i][j]=sheet.getRow(i+1).getCell(j).toString();
				System.out.println(data[i][j]);
			}
		}
				
		return data;
		
		
	}

}
