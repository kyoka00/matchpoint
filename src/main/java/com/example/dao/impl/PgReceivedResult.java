package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ReceivedResultDao;
import com.example.entity.ReceivedResult;
import com.example.util.Utility;

@Repository
public class PgReceivedResult implements ReceivedResultDao {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private String tableName = "receivedResult";

	private final static String ID = "game_info_id";

	private static final String COLUMN_NAME_MATCHID = "match_id";
	private static final String COLUMN_NAME_COMPID = "comp_id";
	private static final String COLUMN_NAME_GAMENO = "game_no";
	private static final String COLUMN_NAME_COATNO = "coat_no";
	private static final String COLUMN_NAME_JUDGENAME = "judge_name";
	private static final String COLUMN_NAME_RECORDSTATUS = "record_status";
	private static final String COLUMN_NAME_RECORDDATE = "record_date";
	private static final String COLUMN_NAME_MAXPOINT = "max_point";
	private static final String COLUMN_NAME_GAMECOUNT = "game_count";
	private static final String COLUMN_NAME_TOURNAMENTNO = "tournament_no";
	private static final String COLUMN_NAME_TEAMIDA = "team_id_a";
	private static final String COLUMN_NAME_TEAMAPLAYER1 = "team_a_player_1";
	private static final String COLUMN_NAME_TEAMAPLAYER2 = "team_a_player_2";
	private static final String COLUMN_NAME_TEAMIDB = "team_id_b";
	private static final String COLUMN_NAME_TEAMBPLAYER1 = "team_b_player_1";
	private static final String COLUMN_NAME_TEAMBPLAYER2 = "team_b_player_2";

	private final String SELECT = "SELECT * FROM " + tableName;
	private final String INSERT = "INSERT INTO " + tableName;
	private final String UPDATE = "UPDATE " + tableName + " set ";

	@Override
	public List<ReceivedResult> search(ReceivedResult result, String keyword) {
		String sql = SELECT + selectSql(result, keyword);
		System.out.println(sql);
		MapSqlParameterSource param = new MapSqlParameterSource();
		if (Utility.notIsEmptyNull(result.getGameInfoId())) {
			param.addValue("match_id", result.getGameInfoId());
		}
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			param.addValue("record_status", result.getRecordStatus());
		}
		if (Utility.notIsEmptyNull(keyword)) {
			param.addValue("record_status", '%'+ keyword +'%');
		}
		

		List<ReceivedResult> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ReceivedResult>(ReceivedResult.class));
		return resultList.isEmpty() ? null : resultList;
	}

	@Override
	public int insert(ReceivedResult result) {
		String sql = INSERT + insertSql(result);
		MapSqlParameterSource param = new MapSqlParameterSource();
		if (Utility.notIsEmptyNull(result.getMatchId())) {
			param.addValue("match_id", result.getMatchId());
		}
		if (Utility.notIsEmptyNull(result.getCompId())) {
			param.addValue("comp_id", result.getCompId());
		}
		if (Utility.notIsEmptyNull(result.getGameInfoId())) {
			param.addValue("game_info_id", result.getGameInfoId());
		}
		if (Utility.notIsEmptyNull(result.getGameNo())) {
			param.addValue("game_no", result.getGameNo());
		}
		if (Utility.notIsEmptyNull(result.getCoatNo())) {
			param.addValue("coat_no", result.getCoatNo());
		}
		if (Utility.notIsEmptyNull(result.getJudgeName())) {
			param.addValue("judge_name", result.getJudgeName());
		}
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			param.addValue("record_status", result.getRecordStatus());
		}
		if (result.getRecordDate() == null) {
			param.addValue("record_date", result.getRecordDate());
		}
		if (Utility.notIsEmptyNull(result.getMaxPoint())) {
			param.addValue("max_point", result.getMaxPoint());
		}
		if (Utility.notIsEmptyNull(result.getGameCount())) {
			param.addValue("game_count", result.getGameCount());
		}
		if (Utility.notIsEmptyNull(result.getTournamentNo())) {
			param.addValue("tournament_no", result.getTournamentNo());
		}
		if (Utility.notIsEmptyNull(result.getTeamIdA())) {
			param.addValue("team_id_a", result.getTeamIdA());
		}
		if (Utility.notIsEmptyNull(result.getTeamAPlayer1())) {
			param.addValue("team_a_player_1", result.getTeamAPlayer1());
		}
		if (Utility.notIsEmptyNull(result.getTeamAPlayer2())) {
			param.addValue("team_a_player_2", result.getTeamAPlayer2());
		}
		if (Utility.notIsEmptyNull(result.getTeamIdB())) {
			param.addValue("team_id_a", result.getTeamIdB());
		}
		if (Utility.notIsEmptyNull(result.getTeamBPlayer1())) {
			param.addValue("team_b_player_1", result.getTeamBPlayer1());
		}
		if (Utility.notIsEmptyNull(result.getTeamBPlayer2())) {
			param.addValue("team_b_player_2", result.getTeamBPlayer2());
		}

		return jdbcTemplate.update(sql, param);
	}

	@Override
	public int update(ReceivedResult result) {
		String sql = UPDATE + updateSql(result);
		MapSqlParameterSource param = new MapSqlParameterSource();
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			param.addValue("record_status", result.getRecordStatus());
		}
		param.addValue("game_info_id", result.getGameInfoId());

		return jdbcTemplate.update(sql, param);
	}

	@Override
	public ReceivedResult findByGameInfoId(ReceivedResult result) {
		String sql = SELECT + selectSql(result, "");
		System.out.println(sql);
		MapSqlParameterSource param = new MapSqlParameterSource();
		if (Utility.notIsEmptyNull(result.getGameInfoId())) {
			param.addValue("match_id", result.getGameInfoId());
		}
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			param.addValue("record_status", result.getRecordStatus());
		}
		

		List<ReceivedResult> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<ReceivedResult>(ReceivedResult.class));
		return resultList.isEmpty() ? null : resultList.get(0);
	}

	public static String selectSql(ReceivedResult result, String keyword) {
		String where = "";
		String columnName = "";
		if (Utility.notIsEmptyNull(result.getGameInfoId())) {
			columnName = ID + " = :" + ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			columnName = COLUMN_NAME_RECORDSTATUS + " = :" + COLUMN_NAME_RECORDSTATUS;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if (Utility.notIsEmptyNull(keyword)) {
			columnName = COLUMN_NAME_JUDGENAME + "||" + COLUMN_NAME_MATCHID + "||" + COLUMN_NAME_COATNO + "||"
					+ COLUMN_NAME_TOURNAMENTNO + " = :" + "keyword";
			where = !where.isEmpty() ? where + " AND " + columnName : columnName + "ORDER BY record_date";
		}

		return !where.isEmpty() ? " WHERE " + where : "";
	}

	public static String insertSql(ReceivedResult result) {
		String column = "";
		String values = "";
		String columnName = "";
		if (Utility.notIsEmptyNull(result.getMatchId())) {
			columnName = COLUMN_NAME_MATCHID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getCompId())) {
			columnName = COLUMN_NAME_COMPID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getGameInfoId())) {
			columnName = ID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getGameNo())) {
			columnName = COLUMN_NAME_GAMENO;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getCoatNo())) {
			columnName = COLUMN_NAME_COATNO;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getJudgeName())) {
			columnName = COLUMN_NAME_JUDGENAME;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			columnName = COLUMN_NAME_RECORDSTATUS;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (result.getRecordDate() == null) {
			columnName = COLUMN_NAME_RECORDDATE;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getMaxPoint())) {
			columnName = COLUMN_NAME_MAXPOINT;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getGameCount())) {
			columnName = COLUMN_NAME_GAMECOUNT;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTournamentNo())) {
			columnName = COLUMN_NAME_TOURNAMENTNO;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamIdA())) {
			columnName = COLUMN_NAME_TEAMIDA;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamAPlayer1())) {
			columnName = COLUMN_NAME_TEAMAPLAYER1;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamAPlayer2())) {
			columnName = COLUMN_NAME_TEAMAPLAYER2;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamIdB())) {
			columnName = COLUMN_NAME_TEAMIDB;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamBPlayer1())) {
			columnName = COLUMN_NAME_TEAMBPLAYER1;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if (Utility.notIsEmptyNull(result.getTeamBPlayer2())) {
			columnName = COLUMN_NAME_TEAMBPLAYER2;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		column = !column.isEmpty() ? column + ")" : column;
		values = !values.isEmpty() ? values + ")" : column;
		return column + values;
	}

	public static String updateSql(ReceivedResult result) {
		String set = "";
		String columnName = "";
		if (Utility.notIsEmptyNull(result.getRecordStatus())) {
			columnName = COLUMN_NAME_RECORDSTATUS + " = :" + COLUMN_NAME_RECORDSTATUS;
			set = !set.isEmpty() ? set + "," + columnName : columnName;
		}

		return !set.isEmpty() ? set + " WHERE " + ID + " = :" + ID : "";
	}

}
