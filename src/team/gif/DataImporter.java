package team.gif;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataImporter {
	
	// TODO: Make this not bad
	
	/**
	 * @param filePath The path to the input file
	 * @return A matrix of the X and Y coordinates
	 * @throws IOException
	 */
	public static float[][] getCoordinates(String filePath) throws IOException {
		
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		ArrayList<String> lines = new ArrayList<String>();
		String line = "";
		
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}
		
		float[][] coordinates = new float[lines.size()][2];
		
		int i = 0;
		for (String s : lines) {
			coordinates[i] = new float[] { Float.parseFloat(s.split(",")[0]), Float.parseFloat(s.split(",")[1]) };
			i++;
		}
		
		br.close();
		
		return coordinates;
		
	}
	
	
}
