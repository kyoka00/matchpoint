package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.CompDao;
import com.example.entity.Comp;
import com.example.util.Utility;

@Repository
public class PgCompDao implements CompDao{
	private String tableName = "comp";
	
	private final static String ID = "comp_id";
	
	private static final String COLUMN_NAME_COMP_ID = "comp_id";
	private static final String COLUMN_NAME_COMP_IOGIN_ID = "comp_login_id";
	private static final String COLUMN_NAME_COMP_NAME = "comp_name";
	private static final String COLUMN_NAME_COMP_DATE = "comp_date";
	private static final String COLUMN_NAME_COMP_PLACE = "comp_place";
	private static final String COLUMN_NAME_GAME_TYPE = "game_type";
	private static final String COLUMN_NAME_GAME_TYPE_STR = "gameTypeStr";
	private static final String COLUMN_NAME_TORNAMET_COUNT = "tornament_count";
	private static final String COLUMN_NAME_MEMO = "memo";
	
	private final String SELECT = "SELECT * FROM " + tableName;
	private final String INSERT = "INSERT INTO " + tableName;
	private final String DELETE = "DELETE FROM " + tableName + " WHERE " + ID  +  " = :" + ID;
	private final String UPDATE = "UPDATE " + tableName + " set ";
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Comp> selectAll(Comp comp) {
		String sql = SELECT + PgCompDao.selectSql(comp);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		
//		 if(Utility.notIsEnptyNull(comp.getCompId())) {
//			 param.addValue("compId",comp.getCompId()); 
//		 } 
//		 if(Utility.notIsEnptyNull(comp.getCompLoginId())) {
//			 param.addValue("compLoginId", comp.getCompLoginId());
//		 }
//		 if(Utility.notIsEnptyNull(comp.getCompName())) {
//			 param.addValue("compName",comp.getCompName());
//		 }
//		 if(Utility.notIsEnptyNull(comp.getCompDate())) {
//			 param.addValue("compDate", comp.getCompDate()); 
//		 }
//		 if(Utility.notIsEnptyNull(comp.getCompPlace())) {
//			 param.addValue("compPlace", comp.getCompPlace()); 
//		 }
//		 if(Utility.notIsEnptyNull(comp.getGameType())) {
//			 param.addValue("GameType", comp.getGameType());
//		 }
//		 if(Utility.notIsEnptyNull(comp.getTournamentNum())) {
//			 param.addValue("tournamentNum", comp.getTournamentNum()); 
//		 }
//		 if(Utility.notIsEnptyNull(comp.getMemo())) {
//			 param.addValue("memo", comp.getMemo()); 
//		 }
//		 List<Comp>resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Comp>(Comp.class)); 
//		 return resultList.isEmpty() ? null : resultList;
		 return null;
	}
	
	@Override
	public void insertComp(Comp comp) {
		String sql = INSERT + PgCompDao.insertSql(comp);
		System.out.println(sql);
		
		/*
		 * MapSqlParameterSource param = new MapSqlParameterSource();
		 * if(Utility.notIsEnptyNull(comp.getCompId())) { param.addValue("compId",
		 * comp.getCompId()); } if(Utility.notIsEnptyNull(comp.getCompLoginId())) {
		 * param.addValue("compLoginId", comp.getCompLoginId());
		 * jdbcTemplate.update(sql, param); }
		 * if(Utility.notIsEnptyNull(comp.getCompName())) { param.addValue("compName",
		 * comp.getCompName()); jdbcTemplate.update(sql, param); }
		 * if(Utility.notIsEnptyNull(comp.getCompDate())) { param.addValue("compDate",
		 * comp.getCompDate()); jdbcTemplate.update(sql, param); }
		 * if(Utility.notIsEnptyNull(comp.getCompPlace())) { param.addValue("compPlace",
		 * comp.getCompPlace()); jdbcTemplate.update(sql, param); }
		 * if(Utility.notIsEnptyNull(comp.getGameType())) {
		 * param.addValue("compGameType", comp.getGameType()); jdbcTemplate.update(sql,
		 * param); } if(Utility.notIsEnptyNull(comp.getTournamentNum())) {
		 * param.addValue("compTournamentNum", comp.getTournamentNum());
		 * jdbcTemplate.update(sql, param); } if(Utility.notIsEnptyNull(comp.getMemo()))
		 * { param.addValue("memo", comp.getMemo()); jdbcTemplate.update(sql, param); }
		 */
	}
	
	@Override
	public void deleteComp(Comp comp) {
		String sql = DELETE;
		System.out.println(sql);
		/*
		 * MapSqlParameterSource param = new MapSqlParameterSource(); param.addValue(ID
		 * , manege.getLoginId()); jdbcTemplate.update(sql, param);
		 */
	}
	
	@Override
	public void updateComp(Comp comp) {
//		String sql = DELETE + PgManegeDao.deleteSql(manege);
		String sql = UPDATE + updateSql(comp);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEnptyNull(manege.getLoginId())) {
//			param.addValue("loginId", manege.getLoginId());
//		}
//		if(Utility.notIsEnptyNull(manege.getPassword())) {
//			param.addValue("password", manege.getPassword());
//		}
//		jdbcTemplate.update(sql, param);
	}

	public static String selectSql(Comp comp) {
		String where = "";
		String columnName = "";
		if(comp.getCompId() == null) {
			columnName = COLUMN_NAME_COMP_ID + " = :" + COLUMN_NAME_COMP_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompLoginId())) {
			columnName = COLUMN_NAME_COMP_IOGIN_ID + " = :" + COLUMN_NAME_COMP_IOGIN_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompName())) {
			columnName = COLUMN_NAME_COMP_NAME + " = :" + COLUMN_NAME_COMP_NAME;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompPlace())) {
			columnName = COLUMN_NAME_COMP_PLACE + " = :" + COLUMN_NAME_COMP_PLACE;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompDate())) {
			columnName = COLUMN_NAME_COMP_DATE + " = :" + COLUMN_NAME_COMP_DATE;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(comp.getGameType() == null) {
			columnName = COLUMN_NAME_GAME_TYPE + " = :" + COLUMN_NAME_GAME_TYPE;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(comp.getTournamentNum() == null) {
			columnName = COLUMN_NAME_TORNAMET_COUNT + " = :" + COLUMN_NAME_TORNAMET_COUNT;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getMemo())) {
			columnName = COLUMN_NAME_MEMO + " = :" + COLUMN_NAME_MEMO;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		return !where.isEmpty() ? " WHERE " + where : "";
	}
	
	public static String insertSql(Comp comp) {
		String column = "";
		String values = "";
		String columnName = "";
		if(comp.getCompId() == null) {
			columnName = COLUMN_NAME_COMP_ID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompLoginId())) {
			columnName = COLUMN_NAME_COMP_IOGIN_ID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompName())) {
			columnName = COLUMN_NAME_COMP_NAME;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompDate())) {
			columnName = COLUMN_NAME_COMP_DATE;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompPlace())) {
			columnName = COLUMN_NAME_COMP_PLACE;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(comp.getGameType() == null) {
			columnName = COLUMN_NAME_GAME_TYPE;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(comp.getTournamentNum() == null) {
			columnName = COLUMN_NAME_TORNAMET_COUNT;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if(Utility.notIsEnptyNull(comp.getMemo())) {
			columnName = COLUMN_NAME_MEMO;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		column = !column.isEmpty() ? column + ")" : column;
		values = !values.isEmpty() ? values + ")" : column;
		return column + values;
	}
	
	public static String updateSql(Comp comp) {
		String set = "";
		String columnName = "";
		if(comp.getCompId() == null) {
			columnName = COLUMN_NAME_COMP_ID + " = :" + COLUMN_NAME_COMP_ID;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompLoginId())) {
			columnName = COLUMN_NAME_COMP_IOGIN_ID + " = :" + COLUMN_NAME_COMP_IOGIN_ID;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompName())) {
			columnName = COLUMN_NAME_COMP_NAME + " = :" + COLUMN_NAME_COMP_NAME;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompPlace())) {
			columnName = COLUMN_NAME_COMP_PLACE + " = :" + COLUMN_NAME_COMP_PLACE;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getCompDate())) {
			columnName = COLUMN_NAME_COMP_DATE + " = :" + COLUMN_NAME_COMP_DATE;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(comp.getGameType() == null) {
			columnName = COLUMN_NAME_GAME_TYPE + " = :" + COLUMN_NAME_GAME_TYPE;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getGameTypeStr())) {
			columnName = COLUMN_NAME_GAME_TYPE_STR + " = " + COLUMN_NAME_GAME_TYPE_STR;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(comp.getTournamentNum() == null) {
			columnName = COLUMN_NAME_TORNAMET_COUNT + " = :" + COLUMN_NAME_TORNAMET_COUNT;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(comp.getMemo())) {
			columnName = COLUMN_NAME_MEMO + " = :" + COLUMN_NAME_MEMO;
			set = !set.isEmpty() ? set + " AND " + columnName : columnName;
		}
		return !set.isEmpty() ? set + " WHERE " + ID + " = :" + ID: "";
	}
}