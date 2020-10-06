package com.hibernatedao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.entities.LoginBean;

public class LoginHibernateDao {

	public boolean authenticateUser(LoginBean loginBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			LoginBean resultLoginBean = (LoginBean) session
					.createQuery("from LoginBean bean where bean.userName=:userName and bean.password=:password")
					.setParameter("userName", loginBean.getUserName()).setParameter("password", loginBean.getPassword())
					.uniqueResult();
			if (resultLoginBean != null) {
				return true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}
}
