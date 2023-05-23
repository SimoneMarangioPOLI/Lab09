
package it.polito.tdp.borders;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.borders.db.BordersDAO;
import it.polito.tdp.borders.db.Confini;
import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	int anno = Integer.parseInt(txtAnno.getText());
    	this.model.creaGrafo(anno);
    	if(this.model.isGraphCreated()) {
    		txtResult.clear();
    	txtResult.setText("Grafo fatto" + "\n" +"\n");
    	}
    	else {
    		txtResult.clear();
    		txtResult.setText("Grafo non fatto" + "\n");
    	}
    	BordersDAO dao = new BordersDAO();
    	List<Confini> lista = dao.getCountrySpecifici(anno);
    	for(Confini c : lista) {
    	txtResult.appendText(c.toString() + "\n");
    	}
    	txtResult.appendText("\n" + "Il numero di grafi connessi è: " + this.model.calcolaConnesse());
    	

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
