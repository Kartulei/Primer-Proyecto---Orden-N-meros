package Model;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Archivo {
	
	public boolean writeFile(String pathFile, ArrayList<Integer> list) throws Exception {
		try {
			String file = createFile(pathFile);
			
			FileWriter writer = new FileWriter(file);
			
			for (Integer item : list) {
				writer.write(String.valueOf(item) + "\n");
			}
								
			writer.close();
			
			return true;
			
		} catch (Exception e) {
			throw new Exception("Error al escribir el archivo " + pathFile);
		}
	}
	
	String createFile(String pathFile) throws Exception {
		try {
			File file = new File(pathFile);
			file.createNewFile();
			return file.getPath();
		} catch (Exception e) {
			throw new Exception("Error al crear el archivo " + pathFile);
		}
	}

}
