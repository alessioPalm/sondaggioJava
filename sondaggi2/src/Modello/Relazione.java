package Modello;

public class Relazione {

	private int id;
	private int idAzienda;
	private int idSondaggio;
	private int dataSottomissione;
	
	
	public Relazione(int idAzienda, int idSondaggio, int dataSottomissione) {
		this.idAzienda = idAzienda;
		this.idSondaggio = idSondaggio;
		this.dataSottomissione = dataSottomissione;
	}
	
	public Relazione() {
		
	}

	public int getIdAzienda() {
		return idAzienda;
	}

	public void setIdAzienda(int idAzienda) {
		this.idAzienda = idAzienda;
	}

	public int getIdSondaggio() {
		return idSondaggio;
	}

	public void setIdSondaggio(int idSondaggio) {
		this.idSondaggio = idSondaggio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDataSottomissione() {
		return dataSottomissione;
	}

	public void setDataSottomissione(int dataSottomissione) {
		this.dataSottomissione = dataSottomissione;
	}

	@Override
	public String toString() {
		return "Relazione [idAzienda=" + idAzienda + ", idSondaggio=" + idSondaggio + "]";
	}
	
	
}
