package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	private CorsoDAO corsoDao;
	private StudenteDAO studenteDao;

	public Model() {
		this.corsoDao = new CorsoDAO();
		this.studenteDao = new StudenteDAO();
	}
	
	public List<Corso> getTuttiICorsi(){
		return this.corsoDao.getTuttiICorsi();
	}
	
	public List<Studente> getStudentiIscrittiACorso(Corso c){
		return this.corsoDao.getStudentiIscrittiAlCorso(c);
	}
	
	public Studente getStudenteDataMatricola(Integer matricola) {
		return studenteDao.getInfoStudente(matricola);
	}
	
	public boolean studentePresenteNelDatabase(Integer matricola) {
		return studenteDao.studentePresenteNelDatabase(matricola);
	}
	
	public List<Corso> corsiPerStudente(Integer matricola){
		return studenteDao.corsiPerStudente(matricola);
	}
	
	public boolean studenteSegueCorso(Integer matricola, Corso corso) {
		boolean ritorno = false;
		for(Corso c: this.corsiPerStudente(matricola)) {
			if(c.getCodins().equals(corso.getCodins())) {
				ritorno = true;
			}
		}
		return ritorno;
	}
}
