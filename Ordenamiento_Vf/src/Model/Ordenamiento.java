package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Ordenamiento {
	
	public ArrayList<Integer> lista = new ArrayList<Integer>();

	
	public void leerTexto (String nombreArchivo) 
	{
		lista = new ArrayList<Integer>();
		File archivo;
		FileReader fr;
		BufferedReader br;
		
		try 
		{
			
			archivo = new File (nombreArchivo);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			
			while((linea = br.readLine()) != null) 
			{
				
				int auxlinea = Integer.parseInt(linea);
				lista.add(auxlinea);
				
			}
			
			br.close();
			fr.close();
			
		} catch (Exception e) 
		{
			
			System.out.println("Hubo un error al leer el archivo");
			
		}	
	}
	
	public void ordenarAsc() 
	{
		
		int i,j;
		int aux;
		
		for(i=1; i<lista.size(); i++) 
		{
			
			aux = lista.get(i);
			
			j= i-1;
			
			while ((j>=0) && lista.get(j) > aux) 
			{
				
				lista.set((j+1), lista.get(j--));
				lista.set(j+1, aux);
				
			}
			
		}
		
		System.out.println(lista);
		
	}
	
	public void ordenarDsc() 
	{
		
		int i,j;
		int aux;
		
		for(i=1; i<lista.size(); i++) 
		{
			
			aux = lista.get(i);
			
			j= i-1;
			
			while ((j>=0) && lista.get(j) < aux) 
			{
				
				lista.set((j+1), lista.get(j--));
				lista.set((j+1), aux);
				
			}
			
		}
		
		System.out.println(lista);
		
	}
	
}
