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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB
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

		File Directorio = new File(RutaCarga);
		if (!Directorio.exists()) {
			Directorio.mkdir();
		}
		Directorio = new File(RutaOrdenado);
		if (!Directorio.exists()) {
			Directorio.mkdir();
		}
		String RutaCompleta = "";
		String TipoOrdenamiento = request.getParameter("tipoOrdenamiento"); 
		String NombreArchivo = ""; 
		for (Part part : request.getParts()) {
			RutaCompleta = extractFileName(part);
			
			if(!RutaCompleta.equals("")) {
				NombreArchivo = new File(RutaCompleta).getName();
				part.write(RutaCarga + File.separator + NombreArchivo);
			}
		}

		Ordenamiento objOrdenamiento = new Ordenamiento();
		objOrdenamiento.leerTexto(RutaCarga + File.separator + NombreArchivo);
		if(TipoOrdenamiento.equals("ASC")) {
			objOrdenamiento.ordenarAsc();
		}else {
			objOrdenamiento.ordenarDsc();
		}

		Archivo objArchivo = new Archivo();
		try {
			objArchivo.writeFile(RutaOrdenado + File.separator + NombreArchivo, objOrdenamiento.lista);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        FileInputStream archivo = new FileInputStream(RutaOrdenado + File.separator + NombreArchivo);
        int longitud = archivo.available();
        byte[] datos = new byte[longitud];
        archivo.read(datos);
        archivo.close();
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename="+NombreArchivo);
        ServletOutputStream ouputStream = response.getOutputStream();
        ouputStream.write(datos);
        ouputStream.flush();
        ouputStream.close();

		request.setAttribute("mensaje", "Se ha generado el archivo de manera Satisfactoria!");
		getServletContext().getRequestDispatcher("/mensaje.jsp").forward(request, response);
	}

	/**
	 * Extracts file name from HTTP header content-disposition
	 */
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