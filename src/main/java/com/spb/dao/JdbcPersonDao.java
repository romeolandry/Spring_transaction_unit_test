package com.spb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.spb.model.Person;

public class JdbcPersonDao implements PersonDao {
	private JdbcTemplate jdbcTemplate;
	
	public void setJdbcTemplate (JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate= jdbcTemplate;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Person findById(final Long id) { 
		// TODO Auto-generated method stub
		return jdbcTemplate.queryForObject("select first_name,last_name from t_person where id=?",
				new RowMapper() {
					public Person mapRow(ResultSet rs,int rowNum)throws SQLException{
						Person person = new Person();
						person.setId(id);
						person.setFirstName(rs.getString("first_name"));
						person.setLastName(rs.getString("last_name"));
						return person;
					}
					},id);
	}

	public void create(Person person) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into t_person(id,first_name,last_name) "
				+ "value (t_person_sequence.nextval,?,?)",person.getFirstName(),person.getLastName());
	
	}

	public void update(Person person) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update t_person set first_name = ?,last_name = ? "
				+ "where id = ?", person.getFirstName(),person.getLastName(),person.getId());

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from t_person where id = ?",id);

	}

}
