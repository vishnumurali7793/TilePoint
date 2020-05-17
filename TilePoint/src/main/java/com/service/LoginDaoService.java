package com.service;

import com.entities.LoginBean;
import com.hibernatedao.LoginDao;

public class LoginDaoService implements LoginService {

	LoginDao loginDao;

	public boolean authenticateUser(LoginBean loginBean) {
		return loginDao.authenticateUser(loginBean);
	}

}
