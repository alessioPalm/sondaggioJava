package Modello;

public class Sondaggio {

	private int id;
	private int codice;
	private String descrizione;
	private String tematica;
	private int annoScaednza;	
	
	public Sondaggio () {
		
	}
	
	public Sondaggio(int codice, String descrizione, String tematica, int annoScaednza) {
		this.codice = codice;
		this.descrizione = descrizione;
		this.tematica = tematica;
		this.annoScaednza = annoScaednza;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCodice() {
		return codice;
	}
	public void setCodice(int codice) {
		this.codice = codice;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getTematica() {
		return tematica;
	}
	public void setTematica(String tematica) {
		this.tematica = tematica;
	}
	public int getAnnoScaednza() {
		return annoScaednza;
	}
	public void setAnnoScaednza(int annoScaednza) {
		this.annoScaednza = annoScaednza;
	}

	@Override
	public String toString() {
		return "Sondaggio [id=" + id + ", codice=" + codice + ", descrizione="
				+ descrizione + ", tematica=" + tematica + ", annoScaednza=" + annoScaednza + "]";
	}
	
	
	
}
