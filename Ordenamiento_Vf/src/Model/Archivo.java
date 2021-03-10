package Model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Archivo {
	
	public boolean sortFileContent(String inputPathFile, String outputPathFile, String OrderBy) throws Exception {
		List<Integer> content = readFile(inputPathFile, OrderBy );
		return writeFile(outputPathFile, content);
	}

	public List<Integer> readFile(String pathFile, String OrderBy) throws Exception {

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
					int auxlinea = Integer.parseInt(line);
					list.add(auxlinea);
			}

			buffer.close();
			reader.close();

		} catch (Exception e) {
			throw new Exception("Error al leer el archivo " + pathFile + "\n" + e.getMessage());
		}
		if (OrderBy.equals("ASC")) {
			return shellSortAsc(list);
		}else {
			return shellSortDsc(list);
		}
		
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
				writer.write(String.valueOf(item) + "\n");
			}
								
			writer.close();
			
			return true;
			
		} catch (Exception e) {
			throw new Exception("Error al escribir el archivo " + pathFile);
		}
	}

	public  List<Integer>  shellSortDsc(List<Integer> lista) {
		for(int increment = lista.size()/ 2; 
				 increment > 0; 
				 increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))) {
			
			for (int i = increment; i < lista.size(); i++) {
				
				for(int j = i; j>= increment && lista.get(j-increment) < lista.get(j); j -= increment) {
					
					int temp = lista.get(j);
					lista.set((j), lista.get(j-increment));
					lista.set((j-increment), temp);
					
				}
				
			}
			
		}
		
		return lista;
		
	}
	
	public List<Integer> shellSortAsc(List<Integer> lista){
		for(int increment = lista.size() /2;
				increment > 0;
				increment = (increment == 2 ? 1 : (int) Math.round(increment / 2.2))) {
			
			for (int i= increment; i< lista.size(); i++) {
				for(int j = i; j >= increment && lista.get(j-increment) > lista.get(j); j -= increment) {
					int temp = lista.get(j);
					lista.set((j), lista.get(j-increment));
					lista.set((j-increment), temp);
				}
			}
		}
		return lista;
	}
}