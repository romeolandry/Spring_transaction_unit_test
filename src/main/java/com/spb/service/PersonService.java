package com.spb.service;

import com.spb.dao.PersonDao;
import com.spb.model.Person;

public class PersonService {
	private PersonDao personDao;
	
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
