package com.spb;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.spb.model.Person;
import com.spb.service.PersonService;


@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("classpath:/spring-beans.xml")
public class PersonServiceIntegrationTests {
	@Autowired
	private PersonService personService;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	 
	@Test
	public void shouldCreateNewPerson() {
		Person person = new Person();
		person.setFirstName("Romeo");
		person.setLastName("Kamgo");
		long countBeforInsert = jdbcTemplate.queryForObject("select count(*) "
				+ "from t_person", Long.class);
		Assert.assertEquals(2, countBeforInsert);
		personService.create(person);
		long countAfterInsert = jdbcTemplate.queryForObject("select count(*) "
				+ "from t_person", Long.class);
		Assert.assertEquals(3, countAfterInsert);
	}
	
	@Test
	public void shouldDeletNewPerson() {
		long countBeforeDelete = jdbcTemplate.queryForObject("select count(*) from"
				+ "t_person", Long.class);
		Assert.assertEquals(2, countBeforeDelete);
		personService.delete(1L);
		long countAfterDelete = jdbcTemplate.queryForObject("select count(*) from"
				+ "t_person", Long.class);
		Assert.assertEquals(1, countAfterDelete);
	}

	@Test
	public void shouldFindPersonsById() {
		Person person = personService.findById(1L);
		Assert.assertNotNull(person);
		Assert.assertEquals("John", person.getFirstName());
		Assert.assertEquals("Doe", person.getLastName());
	}
}
