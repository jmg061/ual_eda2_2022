package practica01;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class DyV {
	
	public void load() {
		
		BufferedReader br;
		
		try {
			br=new BufferedReader(new FileReader("src"+File.separator+"practica01"+File.separator+"NbaStats.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
