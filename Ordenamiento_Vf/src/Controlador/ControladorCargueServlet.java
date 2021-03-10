package Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Model.Archivo;
import Model.Ordenamiento;

@WebServlet("/ControladorCargueServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 200, // 2MB
		maxFileSize = 1024 * 1024 * 1000, // 10MB
		maxRequestSize = 1024 * 1024 * 5000) // 50MB
public class ControladorCargueServlet extends HttpServlet {

	/**
	 * Name of the directory where uploaded files will be saved, relative to the web
	 * application directory.
	 */
	private static final String DIR_CARGA = "ArchivosCargados";
	private static final String DIR_ORDEN = "ArchivosOrdenados";

	/**
	 * handles file upload
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Obtiene la ruta absoluta del la aplicacion web
		String RutaAplicacion = request.getServletContext().getRealPath("");
		String RutaCarga = RutaAplicacion + File.separator + DIR_CARGA;
		String RutaOrdenado = RutaAplicacion + File.separator + DIR_ORDEN;
		//Crear ruta de carga
		File Directorio = new File(RutaCarga);
		if (!Directorio.exists()) {
			Directorio.mkdir();
		}
		//Crear ruta de archivo ordenado
		Directorio = new File(RutaOrdenado);
		//Verifica que el directorio exista
		if (!Directorio.exists()) {
			//Crea el directorio en caso de que no exista
			Directorio.mkdir();
		}
		
		String RutaCompleta = "";
		//Obtiene el tipo de ordenamiento de los radio button
		String TipoOrdenamiento = request.getParameter("tipoOrdenamiento"); 
		String NombreArchivo = ""; 
		for (Part part : request.getParts()) {
			//Extrae el nombre del archivo  
			RutaCompleta = extractFileName(part);
			//Verifica que contenga el nombre del este lleno en el parametro
			if(!RutaCompleta.equals("")) {
				//obtiene el nombre del archivo 
				NombreArchivo = new File(RutaCompleta).getName();
				part.write(RutaCarga + File.separator + NombreArchivo);
			}
		}

		Archivo objArchivo = new Archivo();
		
		try {
			
			//Se envia la lista para crear el archivo en una ruta especifica
			objArchivo.sortFileContent(RutaCarga + File.separator + NombreArchivo,RutaOrdenado + File.separator + NombreArchivo,TipoOrdenamiento);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//lee el archivo generado
        FileInputStream archivo = new FileInputStream(RutaOrdenado + File.separator + NombreArchivo);
        int longitud = archivo.available();
        byte[] datos = new byte[longitud];
        archivo.read(datos);
        archivo.close();
        //Ejecuta una peticion de respuesta al cliente que permite enviar un stream
        response.setContentType("application/octet-stream");
        //Se asigna tipo de adjunto en la respuesta y nombre del archivo
        response.setHeader("Content-Disposition","attachment;filename="+TipoOrdenamiento+NombreArchivo);
        ServletOutputStream ouputStream = response.getOutputStream();
        //Escribe el arreglo de bytes que genero del archivo para enviar y descargar 
        ouputStream.write(datos);
        //Envia
        ouputStream.flush();
        //Cierra conexion de respuesta
        ouputStream.close();

        //Redirige a JSP que maneja mensaje de respuesta exitosa
		request.setAttribute("mensaje", "Se ha generado el archivo de manera Satisfactoria!");
		getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);
	}

	
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}