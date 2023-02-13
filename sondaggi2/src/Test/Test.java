package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import Modello.Azienda;
import Modello.Relazione;
import Modello.Sondaggio;
import Persistenza.DAO;
import junit.framework.Assert;

class Test {

	DAO dao = new DAO ();
	
	//@org.junit.jupiter.api.Test
	void testCreazioneSchema() {
		
		try {
			dao.creazioneSchema();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//@org.junit.jupiter.api.Test
	void testCreazioneTabella() {
		
		try {
			dao.creazioneTabellaAzienda();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//@org.junit.jupiter.api.Test
	void testCreazioneTabellaSondaggio() {
		
		try {
			dao.creazioneTabellaSondaggio();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@org.junit.jupiter.api.Test
	void testInsertAzienda (){
		Azienda azienda = new Azienda (123,"fsefs","fer","ffew","fsef");
		
		try {
			dao.insertAzienda(azienda);
		} catch (SQLException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	//@org.junit.jupiter.api.Test
	void testInsertSondaggio (){
		Sondaggio sondaggio = new Sondaggio (345546,"ffwefr","ffew",3556);
		
		try {
			dao.insertSondaggio(sondaggio);
		} catch (SQLException e) {
			fail();
			e.printStackTrace();
		}
	}
	
	//@org.junit.jupiter.api.Test
	void testCreazioneTabellaRelazioni (){
		
		try {
			dao.creazioneTabellaRelazione();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@org.junit.jupiter.api.Test
	void testFindSelectSondaggioForAziendawhereTematica () {
		
		ArrayList <Relazione> lista = null;
		try {
			lista = dao.findSelectSondaggioForAziendawhereTematica("ricerca");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Assert.assertEquals(3, lista.size());
		
	}
	
	@org.junit.jupiter.api.Test
	void testInsertRelazione () {
		
		Relazione r = new Relazione (1,1,2000);
		
		
		try {
			dao.insertRelazione(r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
