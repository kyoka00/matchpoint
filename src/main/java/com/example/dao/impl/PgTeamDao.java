package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.TeamDao;
import com.example.entity.Team;
import com.example.util.Utility;

@Repository
public class PgTeamDao implements TeamDao{
	private String tableName = "team";

	private final static String ID = "team_id";

	private static final String COLUMN_NAME_TEAM_ID = "team_id";
	private static final String COLUMN_NAME_COMP_ID = "comp_id";
	private static final String COLUMN_NAME_PLAYER_A_NAME = "player_a_name";
	private static final String COLUMN_NAME_PLAYER_B_NAME = "player_b_name";
	private static final String COLUMN_NAME_TOURNAMENT_NO = "tournament_no";

	private final String SELECT = "SELECT * FROM " + tableName;
	private final String INSERT = "INSERT INTO " + tableName;
	private final String DELETE = "DELETE FROM " + tableName + " WHERE " + ID + " = :" + ID;
	private final String UPDATE = "UPDATE " + tableName + " set ";
	
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Team> selectAll(Team team) {
		String sql = SELECT + PgTeamDao.selectSql(team);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEmptyNull(team.getTeamId())) {
//			param.addValue("score_id", team.getTeamId());
//		}
//		if(Utility.notIsEmptyNull(team.getCompId())) {
//			param.addValue("game_info_id", team.getCompId());
//		}
//		if(Utility.notIsEmptyNull(team.getPlayerAName())) {
//			param.addValue("set_no", team.getPlayerAName());
//		}
//		if(Utility.notIsEmptyNull(team.getPlayerBName())) {
//			param.addValue("team_a_score", team.getPlayerBName());
//		}
//		if(Utility.notIsEmptyNull(team.getTournamentNo())) {
//			param.addValue("team_b_score", team.getTournamentNo());
//		}
//		List<Team> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Team>(Team.class));
//		return resultList.isEmpty() ? null : resultList;
		return null;
	}

	@Override
	public void insertScore(Team team) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void deleteScore(Team team) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void updateScore(Team team) {
		// TODO 自動生成されたメソッド・スタブ
		
	}
	
	public static String selectSql(Team team) {
		String where = "";
		String columnName = "";
		if (Utility.notIsEmptyNull(team.getTeamId())) {
			columnName = COLUMN_NAME_TEAM_ID + " = :" + COLUMN_NAME_TEAM_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(team.getCompId())) {
			columnName = COLUMN_NAME_COMP_ID + " = :" + COLUMN_NAME_COMP_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(team.getPlayerAName())) {
			columnName = COLUMN_NAME_PLAYER_A_NAME + " = :" + COLUMN_NAME_PLAYER_A_NAME;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(team.getPlayerBName())) {
			columnName = COLUMN_NAME_PLAYER_B_NAME + " = :" + COLUMN_NAME_PLAYER_B_NAME;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(team.getTournamentNo())) {
			columnName = COLUMN_NAME_TOURNAMENT_NO + " = :" + COLUMN_NAME_TOURNAMENT_NO;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		return !where.isEmpty() ? " WHERE " + where : "";
	}
}