package com.example.Dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Dao.ManegeDao;
import com.example.Enitity.Manege;

@Repository
public class ManegeDaoImpl implements ManegeDao{
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public Manege login(String loginId, String password) {
		String sql = """
				select * from management 
				where login_id = :login_id
				and password = :password
				""";
		
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("login_id", loginId);
        param.addValue("password", password);
        var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Manege>(Manege.class));
        return list.isEmpty() ? null : list.get(0);
	}

}
