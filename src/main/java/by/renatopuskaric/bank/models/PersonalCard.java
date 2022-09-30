package by.renatopuskaric.bank.models;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class PersonalCard {

	@Id
	@Column
	private int id;
	@Column
	private String ime;
	@Column
	private String prezime;
	@Column
	private String oib;
	@Column
	private String status;
	@Column
	private Timestamp datetime;
	
	
	public PersonalCard() {
		super();
	}


	public PersonalCard(int id, String ime, String prezime, String oib, String status, Timestamp datetime) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.oib = oib;
		this.status = status;
		this.datetime = datetime;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getIme() {
		return ime;
	}


	public void setIme(String ime) {
		this.ime = ime;
	}


	public String getPrezime() {
		return prezime;
	}


	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}


	public String getOib() {
		return oib;
	}


	public void setOib(String oib) {
		this.oib = oib;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Timestamp getDatetime() {
		return datetime;
	}


	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}


	@Override
	public String toString() {
		return "PersonalCard [id=" + id + ", ime=" + ime + ", prezime=" + prezime + ", oib=" + oib + ", status="
				+ status + ", datetime=" + datetime + "]";
	}

	
	
	
	

	
	
	
	
	
	
}
