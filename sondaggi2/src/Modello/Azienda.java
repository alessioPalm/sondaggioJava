package Modello;

public class Azienda {

	private int id;
	private int partitaIva;
	private String ragioneSociale;
	private String via;
	private String provincia;
	private String citta;
	
	
	public Azienda(int partitaIva, String ragioneSociale, String via, String provincia, String citta) {
		this.partitaIva = partitaIva;
		this.ragioneSociale = ragioneSociale;
		this.via = via;
		this.provincia = provincia;
		this.citta = citta;
	}
	
	public Azienda() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPartitaIva() {
		return partitaIva;
	}

	public void setPartitaIva(int partitaIva) {
		this.partitaIva = partitaIva;
	}

	public String getRagioneSociale() {
		return ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getVia() {
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
	
	
}
