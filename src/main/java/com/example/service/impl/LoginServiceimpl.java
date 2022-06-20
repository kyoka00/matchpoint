package com.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Dao.ManegeDao;
import com.example.Enitity.Manege;
import com.example.service.LoginService;

@Service
public class LoginServiceimpl implements LoginService{
	
	 @Autowired
	 private ManegeDao manegeDao;
	 
	 @Override
	 public Manege login(String loginId, String password) {
		 return manegeDao.login(loginId,password);
	 
		}

}
