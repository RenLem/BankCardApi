package by.renatopuskaric.bank.services;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.renatopuskaric.bank.models.PersonalCard;
import by.renatopuskaric.bank.repositories.PersonalCardRepository;

@Service
@Transactional
public class PersonalCardService {
	
	@Autowired
	PersonalCardRepository personalCardRepository;

	
	public List<PersonalCard> findAllPersonalCards() {
		List<PersonalCard> personalCardsList = new ArrayList<>();
		personalCardRepository.findAll().forEach(personalCard -> personalCardsList.add(personalCard));
		return personalCardsList;
	}


	public PersonalCard findPersonalCardByOIB(String personalCardOIB) {
		return personalCardRepository.findByOIB(personalCardOIB);
		
	}


	public List<PersonalCard> findPersonalCardById(int personalCardId) {
		return personalCardRepository.findById(personalCardId);
	}


	public String insertPersonalCard(PersonalCard personalCard) {
		return personalCardRepository.insert(personalCard);
		
		
	}


	public String updatePersonalCard(PersonalCard personalCard) {
		return personalCardRepository.update(personalCard);
		
	}


	public String deleteByOIB(String personalCardOIB) {
		return personalCardRepository.delete(personalCardOIB);
		
	}

}
