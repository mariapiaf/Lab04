package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	
	public Studente getInfoStudente(Integer matricola) {
		String sql = "SELECT * "
				+ "FROM studente s "
				+ "WHERE s.matricola = ?";
		Studente s = null;
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()) {
				s = new Studente(rs.getInt("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				rs.close();
				st.close();
				conn.close();
				return s;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return s;
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public boolean studentePresenteNelDatabase(Integer matricola) {
		String sql = "SELECT * "
				+ "FROM studente s "
				+ "WHERE s.matricola = ?";
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Corso> corsiPerStudente(Integer matricola){
		
		String sql = "SELECT c.codins, c.crediti, c.nome, c.pd "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE s.matricola = ? AND i.matricola = s.matricola AND i.codins = c.codins";
		
		List<Corso> corsi = new LinkedList<Corso>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
	
			while(rs.next()) {
				String codins = rs.getString("codins");
				int crediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int pd = rs.getInt("pd");
				Corso c = new Corso(codins, crediti, nome, pd);
				corsi.add(c);
			}
			
			rs.close();
			st.close();
			conn.close();
			

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
		return corsi;
		
	}
	
	/*public boolean studenteSegueCorso(Integer matricola, Corso corso) {
		String sql = "SELECT s.matricola, c.codins "
				+ "FROM studente s, iscrizione i, corso c "
				+ "WHERE i.matricola = s.matricola AND i.codins = c.codins AND s.matricola = ? AND c.codins = ?";
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();
	
			if(rs.next()) {
				rs.close();
				st.close();
				conn.close();
				return true;
			}
			else {
				rs.close();
				st.close();
				conn.close();
				return false;
			}

		} catch(SQLException e) {
			throw new RuntimeException(e);
		}
		
	}*/
}
