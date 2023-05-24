package main.java.utils;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

public class GG_OpenCSV {
	
	public static Object[][] getCSVParameters(String path, int rows, int cols)
			throws InterruptedException, CsvValidationException, IOException {

		String curDir = CC_Parametros.gloDir;
		String filePath = curDir + "\\data\\" + path;
		
		FileReader file = new FileReader(filePath);
		
		try (CSVReader reader = new CSVReader(file)) {
			String[] cell;
			
			int x = rows;
			int y = cols;
			
			String[][] data = new String[x][y];
			
			int cont = -1;
			boolean skip = true;
			while ((cell = reader.readNext()) != null) {
				if(!skip) {
					cont = cont + 1;
					for (int i = 0; i < y; i++) {
						data[cont][i] = cell[i];
					}
				}
				skip = false;
			}
			return data;
		}
	}
}

