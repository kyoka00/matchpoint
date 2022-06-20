package com.example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.dao.ManageDao;
import com.example.entity.Manage;
import com.example.util.Utility;

@Repository
public class PgManageDao implements ManageDao{
	private String tableName = "manage";
	
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
	public List<Manage> selectAll(Manage manage) {
		String sql = SELECT + PgManageDao.selectSql(manage);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEmptyNull(manage.getLoginId())) {
//			param.addValue("login_id", manage.getLoginId());
//		}
//		if(Utility.notIsEmptyNull(manage.getPassword())) {
//			param.addValue("password", manage.getPassword());
//		}
//		List<Manage> resultList = jdbcTemplate.query(sql, param, new BeanPropertyRowMapper<Manage>(Manage.class));
//		return resultList.isEmpty() ? null : resultList;
		return null;
	}
	
	@Override
	public void insertManage(Manage manage) {
		String sql = INSERT + PgManageDao.insertSql(manage);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEmptyNull(manage.getLoginId())) {
//			param.addValue("login_id", manage.getLoginId());
//		}
//		if(Utility.notIsEmptyNull(manage.getPassword())) {
//			param.addValue("password", manage.getPassword());
//		}
//		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public void deleteManage(Manage manage) {
		String sql = DELETE;
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		param.addValue(ID , manage.getLoginId());
//		jdbcTemplate.update(sql, param);
	}
	
	@Override
	public void updateManage(Manage manage) {
		String sql = UPDATE + updateSql(manage);
		System.out.println(sql);
//		MapSqlParameterSource param = new MapSqlParameterSource();
//		if(Utility.notIsEmptyNull(manage.getLoginId())) {
//			param.addValue("login_id", manage.getLoginId());
//		}
//		if(Utility.notIsEmptyNull(manage.getPassword())) {
//			param.addValue("password", manage.getPassword());
//		}
//		jdbcTemplate.update(sql, param);
	}

	public static String selectSql(Manage manage) {
		String where = "";
		String columnName = "";
		if(Utility.notIsEmptyNull(manage.getLoginId())) {
			columnName = COLUMN_NAME_LOGIN_ID + " = :" + COLUMN_NAME_LOGIN_ID;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		if(Utility.notIsEmptyNull(manage.getPassword())) {
			columnName = COLUMN_NAME_PASSWORD + " = :" + COLUMN_NAME_PASSWORD;
			where = !where.isEmpty() ? where + " AND " + columnName : columnName;
		}
		return !where.isEmpty() ? " WHERE " + where : "";
	}
	
	public static String insertSql(Manage manage) {
		String column = "";
		String values = "";
		String columnName = "";
		if( Utility.notIsEmptyNull(manage.getLoginId()) ) {
			columnName = COLUMN_NAME_LOGIN_ID;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		if( Utility.notIsEmptyNull(manage.getPassword()) ) {
			columnName = COLUMN_NAME_PASSWORD;
			column = !column.isEmpty() ? column + ", " + columnName : " (" + columnName;
			values = !values.isEmpty() ? values + ", :" + columnName : " values(:" + columnName;
		}
		column = !column.isEmpty() ? column + ")" : column;
		values = !values.isEmpty() ? values + ")" : column;
		return column + values;
	}
	
	public static String updateSql(Manage manage) {
		String set = "";
		String columnName = "";
		if(Utility.notIsEmptyNull(manage.getLoginId())) {
			columnName = COLUMN_NAME_LOGIN_ID + " = :" + COLUMN_NAME_LOGIN_ID;
			set = !set.isEmpty() ? set + "," + columnName : columnName;
		}
		if(Utility.notIsEmptyNull(manage.getPassword())) {
			columnName = COLUMN_NAME_PASSWORD + " = :" + COLUMN_NAME_PASSWORD;
			set = !set.isEmpty() ? set + ", " + columnName : columnName;
		}
		return !set.isEmpty() ? set + " WHERE " + ID + " = :" + ID: "";
	}
}