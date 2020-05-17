package com.hibernatedao;

import com.entities.LoginBean;

public interface LoginDao {

	boolean authenticateUser(LoginBean loginBean);

}
