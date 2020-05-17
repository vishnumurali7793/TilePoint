package com.hibernatedao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.CategoryBean;

public class CategoryHibernateDao {
	
	//save category data
		public void addCategorydata(CategoryBean catBean) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			try {
				session.saveOrUpdate(catBean);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		}
	//get category list
		@SuppressWarnings("unchecked")
		public List<CategoryBean> getCategryList() {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			try {
				return session.createQuery("from CategoryBean where deleteStatus='N'").list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.close();
			}

		}
		
		//delete category
		public void deleteCategoryById(CategoryBean catBean) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			try {
				session.createQuery("update CategoryBean as cat set cat.deleteStatus='Y' where cat.categoryId=:catId")
						.setParameter("catId", catBean.getCategoryId()).executeUpdate();
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				session.close();
			}
		}

		// Edit category
			public CategoryBean getCatEditById(CategoryBean catBean) {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				try {
					return session.get(CategoryBean.class, catBean.getCategoryId());

				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					session.close();
				}

			}
}
