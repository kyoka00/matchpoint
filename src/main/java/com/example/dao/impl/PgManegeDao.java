package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ManegeDao;
import com.example.entity.Manege;
import com.example.util.Utility;

@Repository
public class PgManegeDao implements ManegeDao{
	private String tableName = "manege";
	
	private final static String ID = "login_id";
	
	private static final String COLUMN_NAME_LOGIN_ID = "login_id";
	private static final String COLUMN_NAME_PASSWORD = "password";
	
	private final String SELECT = "SELECT * FROM " + tableName;
	private final String INSERT = "INSERT INTO " + tableName;
	private final String DELETE = "DELETE FROM " + tableName + " WHERE " + ID  +  " = :" + ID;
	private final String UPDATE = "UPDATE " + tableName + " set ";
	
	@Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
	
	@Override
	public List<Manege> selectAll(Manege manege) {
		String sql = SELECT + PgManegeDao.selectSql(manege);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEnptyNull(manege.getLoginId())) {
//			param.addValue("login_id", manege.getLoginId());
//		}
//		if(Utility.notIsEnptyNull(manege.getPassword())) {
//			param.addValue("password", manege.getPassword());
//		}
//		List<Manege> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Manege>(Manege.class));
//		return resultList.isEmpty() ? null : resultList;
		return null;
	}
	
	@Override
	public void insertManege(Manege manege) {
		String sql = INSERT + PgManegeDao.insertSql(manege);
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
	
	@Override
	public void deleteManege(Manege manege) {
//		String sql = DELETE + PgManegeDao.deleteSql(manege);
		String sql = DELETE;
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
	
	@Override
	public void updateManege(Manege manege) {
//		String sql = DELETE + PgManegeDao.deleteSql(manege);
		String sql = UPDATE + updateSql(manege);
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

	public static String selectSql(Manege manege) {
		String where = "";
		String columnName = "";
		if(Utility.notIsEnptyNull(manege.getLoginId())) {
			columnName = COLUMN_NAME_LOGIN_ID + " = :" + COLUMN_NAME_LOGIN_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(manege.getPassword())) {
			columnName = COLUMN_NAME_PASSWORD + " = :" + COLUMN_NAME_PASSWORD;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		return !where.isEmpty() ? " WHERE " + where : "";
	}
	
	public static String insertSql(Manege manege) {
		String column = "";
		String values = "";
		String columnName = "";
		if( Utility.notIsEnptyNull(manege.getLoginId()) ) {
			columnName = COLUMN_NAME_LOGIN_ID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if( Utility.notIsEnptyNull(manege.getPassword()) ) {
			columnName = COLUMN_NAME_PASSWORD;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		column = !column.isEmpty() ? column + ")" : column;
		values = !values.isEmpty() ? values + ")" : column;
		return column + values;
	}
	
	public static String updateSql(Manege manege) {
		String set = "";
		String columnName = "";
		if(Utility.notIsEnptyNull(manege.getLoginId())) {
			columnName = COLUMN_NAME_LOGIN_ID + " = :" + COLUMN_NAME_LOGIN_ID;
			set = !set.isEmpty() ? set + "," + columnName : columnName;
		}
		if(Utility.notIsEnptyNull(manege.getPassword())) {
			columnName = COLUMN_NAME_PASSWORD + " = :" + COLUMN_NAME_PASSWORD;
			set = !set.isEmpty() ? set + ", " + columnName : columnName;
		}
		return !set.isEmpty() ? set + " WHERE " + ID + " = :" + ID: "";
	}
}