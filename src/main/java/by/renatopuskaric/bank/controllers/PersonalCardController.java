package by.renatopuskaric.bank.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import by.renatopuskaric.bank.models.PersonalCard;
import by.renatopuskaric.bank.services.PersonalCardService;
import by.renatopuskaric.bank.utilities.CardUtilities;
import by.renatopuskaric.bank.utilities.StringUtilities;

@RestController
public class PersonalCardController {
	
	@Autowired
	PersonalCardService personalCardService;
	
	@GetMapping("/findAll")
	public List<PersonalCard> findAll(){
		
		return personalCardService.findAllPersonalCards();
	}
	
	@GetMapping("/findByOIB/{PersonalCardOIB}")
	private PersonalCard getPersonalCardByOIB(@PathVariable("PersonalCardOIB") String personalCardOIB){
		return personalCardService.findPersonalCardByOIB(personalCardOIB);
	}
	
	@GetMapping("/findById/{PersonalCardId}")
	private List<PersonalCard> getPersonalCardById(@PathVariable("PersonalCardId") int personalCardId){
		return personalCardService.findPersonalCardById(personalCardId);
	}
	
	@PostMapping("/insert")
	private String insertPersonalCard(@RequestBody PersonalCard personalCard) {
		return personalCardService.insertPersonalCard(personalCard);
		
	}
	
	@PutMapping("/update")
	private String updatePersonalCard(@RequestBody PersonalCard personalCard) {
		return personalCardService.updatePersonalCard(personalCard).toString();
	}
	
	@PutMapping("/make")
	private String makePersonalCard(@RequestBody PersonalCard personalCard) {
		PersonalCard databasePersonalCard =  getPersonalCardByOIB(personalCard.getOib());
		
		if(personalCard.getStatus().equals("ACTIVE")) {
			if ((databasePersonalCard.getStatus() != "ACTIVE")) {
				
				
				// Generiranje naziva datoteke
				String personalCardTimestamp = databasePersonalCard.getDatetime().toString();
				String cardName = personalCard.getOib() + StringUtilities.formatForFileName(personalCardTimestamp);
				String cardContent = databasePersonalCard.toString();
				
				if (CardUtilities.isCardExisting(cardName)) {
					return "Datoteka već postoji";
				}else {
				// Izrada datoteke
				CardUtilities.makeCard(cardName, cardContent);
				// Update statusa u bazi
				personalCardService.updatePersonalCard(personalCard);
				return "Maded " + personalCard.toString();
				}
			}else {
				return "Status za oib: " + databasePersonalCard.getOib() + "je već ACTIVE.";
			}
		}else {
			return "Promjenite status karice u ACTIVE da bi se izradila.";
		}
		
	}
	
	@DeleteMapping("/deleteByOIB/{PersonalCardOIB}")
	private String deleteBookByID(@PathVariable("PersonalCardOIB") String personalCardOIB) {
		CardUtilities.makeInactiveIfDeleted(personalCardOIB);
		return personalCardService.deleteByOIB(personalCardOIB);
	}

	
	
	
	
	
	

}
