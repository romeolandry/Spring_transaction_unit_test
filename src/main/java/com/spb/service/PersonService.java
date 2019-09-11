package com.spb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spb.dao.PersonDao;
import com.spb.model.Person;

@Service
@Transactional
public class PersonService {
	private PersonDao personDao;
	
	@Autowired
	public void setPersonDao (PersonDao personDao) {
		this.personDao = personDao;
	}
	
	public Person findById (Long id) {
		return personDao.findById(id);
	}
	public void create(Person person) {
		personDao.create(person);
	}
	public void update (Person person) {
		personDao.update(person);
	}
	public void delete (Long id) {
		personDao.delete(id);
	}
}
