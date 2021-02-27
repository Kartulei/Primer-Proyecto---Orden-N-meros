

public class View {
	
	private Controller controller;
	
	public View(Controller controller) {
		this.controller = controller;
		this.controller.setView(this);
	}
	
	public void showError(String message) {
		System.err.println(message);
	}
	
	public void showResult(String message) {
		System.out.println(message);
	}
	
	public void start() {
		// Pedir el archivo de entrada: el que se va a ordenar
		// Pedir el archivo de salida: el que va estar ordenado
		// Cuando ya tenga los datos, que llame al metodo attemptSortFileContent
		
		this.controller.attemptSortFileContent("Ordenamiento de Números\\src\\unsorted.csv", "Ordenamiento de Números\\src\\unisorted.csv");
		
	}

}
