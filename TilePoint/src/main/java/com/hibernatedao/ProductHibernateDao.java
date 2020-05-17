package com.hibernatedao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.ProductBean;

public class ProductHibernateDao {

	//save product data
			public void addProductdata(ProductBean prodBean) {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();

				try {
					session.saveOrUpdate(prodBean);
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					session.close();
				}

			}
			//get product list
			@SuppressWarnings("unchecked")
			public List<ProductBean> getProductList() {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				session.beginTransaction();

				try {
					return session.createQuery("from ProductBean where deleteStatus='N'").list();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					session.close();
				}

			}
			
			//delete product
			public void deleteProductById(ProductBean productBean) {
				SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
				Session session = sessionFactory.openSession();
				Transaction transaction = session.beginTransaction();

				try {
					session.createQuery("update ProductBean as por set por.deleteStatus='Y' where por.productId=:productId")
							.setParameter("productId", productBean.getProductId()).executeUpdate();
					transaction.commit();
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					session.close();
				}
			}
			
			// Edit product
					public ProductBean getProdEditById(ProductBean prodBean) {
						SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
						Session session = sessionFactory.openSession();
						session.beginTransaction();

						try {
							return session.get(ProductBean.class, prodBean.getProductId());

						} catch (Exception e) {
							e.printStackTrace();
							return null;
						} finally {
							session.close();
						}

					}
}
