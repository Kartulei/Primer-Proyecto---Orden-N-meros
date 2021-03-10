<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" href="css/bootstrap.min.css">   		
 <script src="js/bootstrap.min.js"></script>    
<title>Ordenamiento Numerico</title>
</head>
<body>
<center>
<div class="container p-3 my-3 border">
	<h4>Organiza tus datos numericos facil y rapido</h4>
	<form method="post" action="ControladorCargueServlet"
		enctype="multipart/form-data">
		
		<label class="form-label" for="customFile">Seleccione un archivo</label>		
		<input type="file" class="form-control" name="file" size="60" /><br />
		<label class="form-label" for="Recordatorio">Recuerda seleccionar un archivo .CSV</label>	
		<br />
		<label class="form-label" for="tipoOrden">¿Cómo deseas ordenar tus datos?</label>		<br />
        <div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="tipoOrdenamiento" id="inlineRadio1" value="ASC" checked>
		  <label class="form-check-label" for="inlineRadio1">Ascendente</label>
		</div>
		<div class="form-check form-check-inline">
		  <input class="form-check-input" type="radio" name="tipoOrdenamiento" id="inlineRadio2" value="DSC">
		  <label class="form-check-label" for="inlineRadio2">Descendente </label>
		</div>
		<br /><br /> <input type="submit" value="Ordenar Datos"  class="btn btn-primary btn-lg btn-block"/>
		<br /><br />
		<label class="form-label" for="Info">La descarga es automatica y quedara guardada en tu ordenador en formato CSV</label>		
		<br />
		
	</form>
</div>
</center>
</body>
</html>