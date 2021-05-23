/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label labelCorsi;

    @FXML
    private ComboBox<Corso> boxCorsi;

    @FXML
    private Button buttonCercaIscrittiCorso;

    @FXML
    private Label labelStudente;

    @FXML
    private TextField txtMatricola;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtCognome;

    @FXML
    private Button buttonCercaCorsi;

    @FXML
    private Button buttonIscrivi;

    @FXML
    private TextArea txtRisultato;

    @FXML
    private Button buttonReset;
    
    @FXML
    private Button btnCheck;

    @FXML
    void doCercaCorsi(ActionEvent event) {
    	txtRisultato.clear();
    	String matricolaStringa = txtMatricola.getText();
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(matricolaStringa);
    	} catch(NumberFormatException ne) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	} catch(NullPointerException e) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	}
    	
    	if(!this.model.studentePresenteNelDatabase(matricola)) {
    		txtRisultato.appendText("Lo studente non è presente nel database");
    		return;
    	}
    	
    	txtCognome.setText(this.model.getStudenteDataMatricola(matricola).getCognome());
    	txtNome.setText(this.model.getStudenteDataMatricola(matricola).getNome());
    	
    	List<Corso> corsi = this.model.corsiPerStudente(matricola);
    	
    	if(corsi.size()==0) {
    		txtRisultato.setText("Lo studente non è iscritto a nessun corso");
    		return;
    	}
    	
    	for(Corso c: corsi) {
    		txtRisultato.appendText(c.toString()+"\n");
    	}
    	
    	
    }

    @FXML
    void doCheck(ActionEvent event) {
    	String matricolaStringa = txtMatricola.getText();
    	Integer matricola = 0;
    	try {
    		matricola = Integer.parseInt(matricolaStringa);
    	} catch(NumberFormatException ne) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	} catch(NullPointerException e) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	}
    	if(!this.model.studentePresenteNelDatabase(matricola)) {
    		txtRisultato.appendText("Lo studente non è presente nel database");
    		return;
    	}
    	txtCognome.setText(this.model.getStudenteDataMatricola(matricola).getCognome());
    	txtNome.setText(this.model.getStudenteDataMatricola(matricola).getNome());
    	
    }
    
    @FXML
    void doCercaIscritti(ActionEvent event) {
    	txtRisultato.clear();
    	Corso corso;
    	try {
    		corso = boxCorsi.getValue();
    	} catch(NullPointerException e) {
    		txtRisultato.setText("Devi scegliere un corso");
    		return;
    	}
    	if(corso.getCodins().equals("")) {
    		txtRisultato.setText("Devi scegliere un corso");
    		return;
    	}
    	List<Studente> studenti = this.model.getStudentiIscrittiACorso(corso);
    	for(Studente s: studenti) {
    		txtRisultato.appendText(s.toString()+"\n");
    	}
    	
    }

    @FXML
    void doIscrivi(ActionEvent event) { // cerca se lo studente inserito è iscritto al corso selezionato
    	txtRisultato.clear();

    	String matricolaStringa = txtMatricola.getText();
    	Integer matricola = 0;
    	
    	try {
    		matricola = Integer.parseInt(matricolaStringa);
    	} catch(NumberFormatException ne) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	} catch(NullPointerException e) {
    		txtRisultato.setText("Devi inserire un numero di matricola");
    		return;
    	}
    	
    	if(!this.model.studentePresenteNelDatabase(matricola)) {
    		txtRisultato.appendText("Lo studente non è presente nel database");
    		return;
    	}
    	if(this.model.studenteSegueCorso(matricola, boxCorsi.getValue())) {
    		txtRisultato.setText("Lo studente segue il corso!");
    	}
    	else {
    		txtRisultato.setText("Lo studente NON è iscritto al corso!");
    	}
    	
    }

    @FXML
    void doReset(ActionEvent event) {
    	txtCognome.clear();
    	txtMatricola.clear();
    	txtNome.clear();
    	txtRisultato.clear();
    }

    @FXML
    void soSelectCorso(ActionEvent event) {
    	this.model.getTuttiICorsi();
    }

    @FXML
    void initialize() {
        assert labelCorsi != null : "fx:id=\"labelCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxCorsi != null : "fx:id=\"boxCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonCercaIscrittiCorso != null : "fx:id=\"buttonCercaIscrittiCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert labelStudente != null : "fx:id=\"labelStudente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtMatricola != null : "fx:id=\"txtMatricola\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtNome != null : "fx:id=\"txtNome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCognome != null : "fx:id=\"txtCognome\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonCercaCorsi != null : "fx:id=\"buttonCercaCorsi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonIscrivi != null : "fx:id=\"buttonIscrivi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert buttonReset != null : "fx:id=\"buttonReset\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'Scene.fxml'.";
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	//boxCorsi.getItems().add("");
    	boxCorsi.getItems().addAll(this.model.getTuttiICorsi());
    }
}
