package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CompDao;
import com.example.entity.Comp;

@Repository
public class PgCompDao implements CompDao{
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
    private CompDao compDao;
	
	public List<Comp> findBycompName(Comp compName) {
		String sql = "SELECT comp_name, comp_date, comp_place, game_type, tournament_count, memo FROM comp WHERE comp_name = :comp_name";
		
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		return jdbcTemplate.query(sql, param,  new BeanPropertyRowMapper<Comp>(Comp.class));
	}
}