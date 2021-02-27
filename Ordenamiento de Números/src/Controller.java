

public class Controller {
	
	private View view;
	private Model model;
	
	public Controller(Model model) {
		this.model = model;
	}
	
	public void setView(View view) {
		this.view = view;
	}
	
	public void attemptSortFileContent(String inputPathFile, String outputPathFile) {
		try {
			if (this.model.sortFileContent(inputPathFile, outputPathFile)) {
				this.view.showResult("El archivo resultante es " + outputPathFile);
			}
		} catch (Exception e) {
			this.view.showError(e.getMessage());
		}
	}

}

