package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.Dao.CompDao;
import com.example.Enitity.Comp;
import com.example.service.CompService;

public class CompServiceimpl implements CompService{
	

	
	@Autowired
	 private CompDao compDao;
	 
	 @Override
	 public List<Comp> find() {
		 return compDao.find();
		 
		}
	 
	 @Override
	 public Comp compLoginId(String CompLoginId, int id) {
		 return compDao.compLoginId(CompLoginId, id);
		 
		}
	 
	 @Override
	 public int insert(Comp c) {
		 return compDao.insert(c);
		 
		}

}
