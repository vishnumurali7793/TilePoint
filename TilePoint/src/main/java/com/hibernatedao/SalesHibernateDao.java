package com.hibernatedao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.entities.SalesAmountBean;
import com.entities.SalesBaseBean;
import com.entities.SalesDetailsBean;

public class SalesHibernateDao {
	//get Purchase list
	@SuppressWarnings("unchecked")
	public List<SalesBaseBean> getPurchaseList() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			return session.createQuery("from SalesBaseBean").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}

	}
	//invoiceno for purchase
	public Integer invoiceSales() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String qr = "SELECT COUNT(*)  FROM SalesBaseBean";
			Long count=(Long) session.createQuery(qr).uniqueResult();
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}finally {
			session.close();
		}
	}
	
	public Integer getCusDetails(String ss) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			String qr = "SELECT customerId FROM CustomerBean where customerName=:vencode";
			Query q = session.createQuery(qr).setParameter("vencode", ss);
			return (Integer) q.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
		}

	}
	
	// save purchasevendor data
		public void addSalestoBase(SalesBaseBean salBean) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			try {
				session.saveOrUpdate(salBean);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		}
		
		@SuppressWarnings("unchecked")
		public Collection<Object> getCusListByCusCode(String cusCode) throws Exception {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();

			try {
				return session
						.createQuery("SELECT customerCode, customerName, contact FROM CustomerBean WHERE customerCode LIKE '%"
								+ cusCode + "%'")
						.list();
			} catch (Exception e) {
				return null;
			}
		}
		
		// get salesdetails list
		@SuppressWarnings("unchecked")
		public List<SalesDetailsBean> getsalesDetailsList(Integer purchasedetid) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			session.beginTransaction();

			try {
				return session.createQuery("from SalesDetailsBean as a where a.salesId.salesId=:purdetarg and a.deleteStatus='N'")
						.setParameter("purdetarg", purchasedetid).list();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			} finally {
				session.close();
			}

		}
		
		// save purchasedetails data
		public void savesalesdetails(SalesDetailsBean saldetailBean) {
			SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			try {
				session.saveOrUpdate(saldetailBean);
				transaction.commit();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				session.close();
			}

		}
		
		//delete salesdetails
				public void deleteSalesById(SalesDetailsBean saldetBean) {
					SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
					Session session = sessionFactory.openSession();
					Transaction transaction = session.beginTransaction();

					try {
						session.createQuery("update SalesDetailsBean as p set p.deleteStatus='Y' where p.salesDetailsId=:salesDetailsId")
								.setParameter("salesDetailsId", saldetBean.getSalesDetailsId()).executeUpdate();
						transaction.commit();
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						session.close();
					}
				}
				
				// save purchasetotnetamt data
				public void savesalesnetamt(SalesAmountBean salnetamtBean) {
					SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
					Session session = sessionFactory.openSession();
					Transaction transaction = session.beginTransaction();

					try {
						session.saveOrUpdate(salnetamtBean);
						transaction.commit();
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						session.close();
					}

				}
				
				// get productamttot
				@SuppressWarnings("unchecked")
				public SalesAmountBean getsalestotamt(Integer salesid) {
					SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
					Session session = sessionFactory.openSession();
					session.beginTransaction();

					try {
						return (SalesAmountBean) session
								.createQuery("from SalesAmountBean where salesId.salesId=:salesid")
								.setParameter("salesid", salesid).uniqueResult();
					} catch (Exception e) {
						e.printStackTrace();
						return null;
					} finally {
						session.close();
					}

				}
}
