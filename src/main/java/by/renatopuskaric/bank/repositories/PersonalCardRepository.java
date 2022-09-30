package by.renatopuskaric.bank.repositories;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import by.renatopuskaric.bank.models.PersonalCard;

@Transactional
@Repository
public class PersonalCardRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	private RowMapper<PersonalCard> rowMappper = (ResultSet rs, int rowNum) -> {
		PersonalCard personalCard = new PersonalCard();
		personalCard.setId(rs.getInt(1));
		personalCard.setIme(rs.getString(2));
		personalCard.setPrezime(rs.getString(3));
		personalCard.setOib(rs.getString(4));
		personalCard.setStatus(rs.getString(5));
		personalCard.setDatetime(rs.getTimestamp(6));
		return personalCard;
	};
	
	public List<PersonalCard> findAll(){
		List<PersonalCard> list = null;
		try {
			list =  jdbcTemplate.query("SELECT * FROM PERSONAL_CARD", rowMappper);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return list;
		
	}
	
	//TODO Get by OIB
	public PersonalCard findByOIB(String oib) {
		
		List<PersonalCard> list = null;
		PersonalCard personalCard = new PersonalCard();
		
		try {
			list = jdbcTemplate.query("SELECT * FROM PERSONAL_CARD WHERE oib = ?", rowMappper, oib);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (list.size() > 1) {
			System.out.println("Postoji više od jednog zapisa za oib: " + oib);
			return null;
		}else {
			personalCard = list.get(0);
			return personalCard;
		}
	}
	
	
	//TODO Get by Id
	public List<PersonalCard> findById(int id) {
		List<PersonalCard> list = null;
		try {
			list = jdbcTemplate.query("SELECT * FROM PERSONAL_CARD WHERE id = ?", rowMappper, id);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//TODO insert
	public String insert(PersonalCard personalCard) {
			
			
			List<Object> params = new ArrayList<>();
			params.add(personalCard.getIme());
			params.add(personalCard.getPrezime());
			params.add(personalCard.getOib());
			params.add(personalCard.getStatus());
			
			try {
				int rowsAffected = jdbcTemplate.update("INSERT INTO PERSONAL_CARD(ime, prezime, oib, status) VALUES (?, ?, ?, ?)", params.toArray());
				return "INSERT - Dodano " + rowsAffected + " redaka. Dodali novu kerticu.";
			} catch (DataAccessException e) {
				e.printStackTrace();
				return "INSERT - Došlo je do greške prilikom spremanja, pokušajte ponovno.";
			}
			
		
		
		
	}
	
	//TODO update
	public String update(PersonalCard personalCard) {
		
		List<Object> params = new ArrayList<>();
		params.add(personalCard.getStatus());
		params.add(personalCard.getOib());
		
		try {
			int rowsAffected = jdbcTemplate.update("UPDATE PERSONAL_CARD SET status='" + personalCard.getStatus() + "' WHERE oib='" + personalCard.getOib() + "'");
			return "UPDATE - Promjena u " + rowsAffected + " redaka. Uspješno ste promjenili status za karticu:" + personalCard.toString();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "UPDATE - Došlo je do greške prilikom updatea, pokušajte ponovno.";
		}
	}
	
	//TODO delete
	public String delete(String oib) {
		try {
			int rowsAffected = jdbcTemplate.update("DELETE FROM PERSONAL_CARD WHERE oib=?", oib);
			return "DELETE - Broj obrisanih redaka " + rowsAffected + " - Brisan zapis oib-a: " + oib;
		} catch (DataAccessException e) {
			e.printStackTrace();
			return "Došlo je do pogreške prilikom brisanja.";
		}
	}
	
	
	
	

}
