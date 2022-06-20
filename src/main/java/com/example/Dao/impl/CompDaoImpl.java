package com.example.Dao.impl;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.Dao.CompDao;
import com.example.Enitity.Comp;
import com.example.Enitity.Manege;

@Repository
public class CompDaoImpl implements CompDao{
	
	private String tableName = "manege";
	
	private final String SELECT ="SELECT * FROM " + tableName;
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	public List<Comp> find() {
		
		String sql = SELECT + PgManegeDao.selectSql(manege);
		System.out.println(sql);
		MapSqlParameterSource param = new MapSqlParameterSource();
		if(Utility.notIsEnptyNull(manege.getLoginId())) {
			param.addValue("loginId", manege.getLoginId());
		}
		if(Utility.notIsEnptyNull(manege.getPassword())) {
			param.addValue("password", manege.getPassword());
		}
		List<Manege> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Manege>(Manege.class));
		return resultList.isEmpty() ? null : resultList;
		
//		String sql = """
//				select comp_id
//				, comp_name
//				, comp_date 
//				from comp order by comp_id
//				""";
//		
//		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<Comp>(Comp.class));
	}
	
	public Comp compLoginId(String CompLoginId, int id) {
		String sql = "select * from comp where comp_login_id = :comp_login_id";
		if (id > 0) {
			sql += "and id <> :id";
		}
		MapSqlParameterSource param = new MapSqlParameterSource();
		param.addValue("comp_login_id", CompLoginId);
		param.addValue("id", id);
		var list = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Comp>(Comp.class));
        return list.isEmpty() ? null : list.get(0);
	}
	
	public int insert(Comp c) {
		String sql = """
				
				insert into comp (comp_login_id,comp_name,comp_date,comp_place,game_type,tournament_count,memo)
				values (:comp_login_id,:comp_name,:comp_date,:comp_place,:game_type,:tournament_count,:memo);
				
				""";
		MapSqlParameterSource param = new MapSqlParameterSource();
		
		// 変換対象の日付文字列
		String dateStr = c.getCompDate();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		
		
        param.addValue("comp_login_id", c.getCompLoginId());
        param.addValue("comp_name", c.getCompName());
        param.addValue("comp_date", c.getCompDate());
        param.addValue("comp_place", c.getCompPlace());
        param.addValue("game_type", c.getGameType());
        param.addValue("tournament_count", c.getTournamentNum());
        param.addValue("memo", c.getMemo());
        
        return jdbcTemplate.update(sql, param);
	}

}
