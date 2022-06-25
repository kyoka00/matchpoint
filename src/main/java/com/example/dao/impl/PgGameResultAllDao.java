package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import com.example.dao.GameResultAllDao;
import com.example.entity.GameResultAll;


@Repository
public class PgGameResultAllDao implements GameResultAllDao{
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<GameResultAll> selectAll(GameResultAll gameResultAll, String keyword){
		if(keyword != null) {
			String sql = "select g.record_date, m.game_no, g.coat_no, g.judge_name, t.tournament_no from match m join game_info g on m.match_id = g.match_id join team t on t.team_id = m.team_id_a where g.judge_name like :keyword ";
			System.out.print(sql);
			MapSqlParameterSource param = new MapSqlParameterSource();
	        param.addValue("keyword", '%'+ keyword + '%');
	        System.out.println(keyword);
			List<GameResultAll> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<GameResultAll>(GameResultAll.class));
			return resultList.isEmpty() ? null : resultList;
		}else {
			String sql = "select g.record_date, m.game_no, g.coat_no, g.judge_name, t.tournament_no from match m join game_info g on m.match_id = g.match_id join team t on t.team_id = m.team_id_a";
			MapSqlParameterSource param = new MapSqlParameterSource();
			param.addValue("gameResultAll", gameResultAll);
			List<GameResultAll> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<GameResultAll>(GameResultAll.class));
			return resultList.isEmpty() ? null : resultList;
		}
	}
	
	@Override
	public List<GameResultAll> find(Integer keyword){
		String sql = "select g.record_date, m.game_no, g.coat_no, g.judge_name, t.tournament_no from match m join game_info g on m.match_id = g.match_id join team t on t.team_id = m.team_id_a where m.game_no || g.coat_no like '%' || :keyword || '%' ";
		System.out.print(sql);
		MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("keyword", '%'+ keyword + '%');
        List<GameResultAll> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<GameResultAll>(GameResultAll.class));
		return resultList.isEmpty() ? null : resultList;
	}
}