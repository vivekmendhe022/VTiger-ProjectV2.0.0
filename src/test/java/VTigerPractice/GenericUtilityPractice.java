package VTigerPractice;

import java.io.IOException;

import com.generic.utilities.ExcelFileUtility;
import com.generic.utilities.JavaUtility;
import com.generic.utilities.PropertyFileUtility;

public class GenericUtilityPractice {
	public static void main(String[] args) throws IOException {
		PropertyFileUtility pUtil=new PropertyFileUtility();		
		String value = pUtil.readDatafromPropertyFile("username");
		System.out.println(value);
		
		ExcelFileUtility eutil=new ExcelFileUtility();
		String data = eutil.readDataFromExcel("Trail", 0, 0);
		System.out.println(data);
		
//		eutil.writeDataIntoExcel("Trail", 0, 0, "Data Added");
//		System.out.println("Data Added");
		
		JavaUtility jutil = new JavaUtility();
		System.out.println(jutil.getRandomNumber());
		System.out.println(jutil.getSystemDate());
		System.out.println(jutil.getSystemDateInFormate());
	}
}
