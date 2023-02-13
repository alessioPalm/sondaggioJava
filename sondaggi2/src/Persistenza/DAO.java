package Persistenza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.jboss.logging.Logger;

import com.mysql.cj.jdbc.MysqlDataSource;

import Modello.Azienda;
import Modello.Relazione;
import Modello.Sondaggio;

public class DAO {

	private Connection con;
	private Logger logger = Logger.getLogger(getClass());
	
	//molti molti si inserisce una cartella intermedia
	
	public Connection getConnectionCreazione () throws SQLException {
		
		MysqlDataSource dataSource = new MysqlDataSource();
		
		dataSource.setPortNumber(3306);
		dataSource.setServerName("127.0.0.1");
		dataSource.setUser("root");
		dataSource.setPassword("Alessio2018*");
		
		return dataSource.getConnection();
		
	}
	
	public Connection getConnection () throws SQLException {
		
		if(con != null) {
			return con;
		}
		MysqlDataSource dataSource = new MysqlDataSource();
		
		dataSource.setDatabaseName("sondaggi");
		dataSource.setPortNumber(3306);
		dataSource.setServerName("127.0.0.1");
		dataSource.setUser("root");
		dataSource.setPassword("Alessio2018*");
		
		this.con = dataSource.getConnection();
		
		return con;
		
	}
	
	public void creazioneSchema () throws SQLException {
		
		String sql = "CREATE SCHEMA `sondaggi`";
		
		PreparedStatement ps = getConnectionCreazione().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.execute();
	}
	
	public void creazioneTabellaSondaggio() throws SQLException {
		
		String sql =" CREATE TABLE `sondaggi`.`new_table` (\n"
				+ "  `id` INT NOT NULL AUTO_INCREMENT,\n"
				+ "  `codice` INT NOT NULL,\n"
				+ "  `descrizione` VARCHAR(45) NOT NULL,\n"
				+ "  `tematica` VARCHAR(45) NOT NULL,\n"
				+ "  `annoScadenza` INT NOT NULL,\n"
				+ "  PRIMARY KEY (`id`),\n"
				+ "  UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.execute();
	}
	
	public void creazioneTabellaAzienda() throws SQLException {
		String sql = "CREATE TABLE `sondaggi`.`azienda`( "
				+ "`id` INT NOT NULL AUTO_INCREMENT, "
				+ "`partitaIva` INT NOT NULL, "
				+ "`ragioneSociale` VARCHAR(50) NOT NULL, "
				+ "`via` CHAR(20) NOT NULL, "
				+ "`provincia` CHAR(20) NOT NULL, "
				+ "`citta` CHAR(20) NOT NULL, "
				+ "PRIMARY KEY (`id`));";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.execute();
	}
	
	
	public void creazioneTabellaRelazione () throws SQLException {
		
		String sql = "CREATE TABLE `sondaggi`.`relazione` ("
				+ " `id` INT NOT NULL AUTO_INCREMENT, "
				+ " `dataSottomissione` INT NOT NULL,  "
				+ "  `idAzienda` INT NOT NULL,"
				+ "  `idSondaggio` INT NOT NULL,"
				+ "  PRIMARY KEY (`id`), "
				+ "  CONSTRAINT `relazioneAzienda`"
				+ "    FOREIGN KEY (`idAzienda`)"
				+ "    REFERENCES `sondaggi`.`azienda` (`id`)"
				+ "    ON DELETE NO ACTION"
				+ "    ON UPDATE NO ACTION,"
				+ "  CONSTRAINT `relazioneSondaggio`"
				+ "    FOREIGN KEY (`idSondaggio`)"
				+ "    REFERENCES `sondaggi`.`sondaggio` (`id`));";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.execute();
	}
	
	
	
	
	public void insertAzienda (Azienda azienda) throws SQLException {
		
		String sql = "INSERT INTO sondaggi.azienda(partitaIva, ragioneSociale, via, provincia, citta) "
				+ "VALUES  (?, ?, ?, ?, ?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		ps.setInt(1, azienda.getPartitaIva());
		ps.setString(2, azienda.getRagioneSociale());
		ps.setString(3, azienda.getVia());
		ps.setString(4, azienda.getProvincia());
		ps.setString(5, azienda.getCitta());
		
		ps.execute();
		
	}
	
	public void insertSondaggio(Sondaggio sondaggio) throws SQLException {
		
		String sql ="INSERT INTO sondaggi.sondaggio(codice, descrizione, tematica, annoScadenza) "
				+ "VALUES  (?, ?, ?, ?)";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, sondaggio.getCodice());
		ps.setString(2, sondaggio.getDescrizione());
		ps.setString(3, sondaggio.getTematica());
		ps.setInt(4, sondaggio.getAnnoScaednza());
		
		ps.execute();
		
	}
	
	public ArrayList <Relazione> findSelectSondaggioForAziendawhereTematica(String tematica) throws SQLException {
		
		String sql = "SELECT a.id, a.ragioneSociale, s.id, s.codice, s.descrizione, s.tematica, r.dataSottomissione "
				+ "FROM sondaggi.sondaggio as s join sondaggi.relazione as r ON (s.id = r.idSondaggio) join sondaggi.azienda as a ON (a.id = r.idAzienda)"
				+ "WHERE s.tematica = ?;";
		
		PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, tematica);
		
		ResultSet rs = ps.executeQuery();
		
		String somma = "";
		ArrayList <Relazione> lista = new ArrayList <Relazione> ();
		while(rs.next()) {
			
			Relazione r = new Relazione ();
			
			r.setIdSondaggio(rs.getInt(3));
			r.setIdAzienda(rs.getInt(1));
			r.setDataSottomissione(rs.getInt(7));
			
			lista.add(r);
		}
		return lista;
		
	}
	
	public void insertRelazione (Relazione relazione) throws SQLException {
		
		try {
			this.con = getConnection();
			con.setAutoCommit(false);
			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			String sql = "INSERT INTO sondaggi.relazione (idAzienda, idSondaggio, dataSottomissione) "
				+ " VALUES ( ? , ? , ? );";
			
			PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
			ps.setInt(1, relazione.getIdAzienda());
			ps.setInt(2, relazione.getIdSondaggio());
			ps.setInt(3, relazione.getDataSottomissione());
			
			ps.execute();
			con.commit();
		
	} catch(SQLException se){
		logger.error(se);
		try {con.rollback();} catch (Exception s) {logger.error(s);} } finally {
			if (con != null) { try {
				this.con.setAutoCommit(true);
		} catch (SQLException e) {	}
		}
		}
	}
		
}
