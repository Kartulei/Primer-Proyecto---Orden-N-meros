import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Model {
	
	public boolean sortFileContent(String inputPathFile, String outputPathFile) throws Exception {
		List<Integer> content = readFile(inputPathFile);
		return writeFile(outputPathFile, content);
	}

	public List<Integer> readFile(String pathFile) throws Exception {

		File file;
		FileReader reader;
		BufferedReader buffer;
		List<Integer> list = new ArrayList<>();

		try {
			file = new File(pathFile);
			reader = new FileReader(file);
			buffer = new BufferedReader(reader);

			String line;

			while ((line = buffer.readLine()) != null) {
				String[] row = line.split(",");
				
				for (String number: row) {
					int auxlinea = Integer.parseInt(number);
					list.add(auxlinea);
				}
				
			}

			buffer.close();
			reader.close();

		} catch (Exception e) {
			throw new Exception("Error al leer el archivo " + pathFile + "\n" + e.getMessage());
		}
		return sort(list);
	}

	public String createFile(String pathFile) throws Exception {
		try {
			File file = new File(pathFile);
			file.createNewFile();
			return file.getPath();
		} catch (Exception e) {
			throw new Exception("Error al crear el archivo " + pathFile);
		}
	}

	public boolean writeFile(String pathFile, List<Integer> list) throws Exception {
		try {
			String file = createFile(pathFile);
			
			FileWriter writer = new FileWriter(file);
			
			for (Integer item : list) {
				writer.write(String.valueOf(item) + ",");
			}
								
			writer.close();
			
			return true;
			
		} catch (Exception e) {
			throw new Exception("Error al escribir el archivo " + pathFile);
		}
	}

	public List<Integer> sort(List<Integer> list) {

		int i, j;
		int aux;

		for (i = 1; i < list.size(); i++) {

			aux = list.get(i);
			j = i - 1;

			while ((j >= 0) && list.get(j) > aux) {
				list.set((j + 1), list.get(j--));
				list.set((j + 1), aux);
			}
		}

		return list;
	}
}
