package VTigerPractice;

import org.testng.annotations.Test;

public class ReadDataFromCMDLine {

	@Test
	public void read(){
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		System.out.println(BROWSER);
		System.out.println(URL);
	}
}
