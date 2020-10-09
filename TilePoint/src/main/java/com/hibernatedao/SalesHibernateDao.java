package com.hibernatedao;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entities.CustomerBean;
import com.entities.ProductBean;
import com.entities.SalesAmountBean;
import com.entities.SalesBaseBean;
import com.entities.SalesDetailsBean;

public class SalesHibernateDao {
	// get Purchase list
	@SuppressWarnings("unchecked")
	public List<SalesBaseBean> getPurchaseList() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			return session.createQuery("from SalesBaseBean as bk order by bk.salesId desc").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}

	}

	// invoiceno for purchase
	public Integer invoiceSales() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			String qr = "SELECT COUNT(*)  FROM SalesBaseBean";
			Long count = (Long) session.createQuery(qr).uniqueResult();
			return count.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public Integer getCusDetails(String ss) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			String qr = "SELECT customerId FROM CustomerBean where customerName=:vencode";
			return (Integer) session.createQuery(qr).setParameter("vencode", ss).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		} finally {
			session.close();
			sessionFactory.close();
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
			sessionFactory.close();
		}

	}

	@SuppressWarnings("unchecked")
	public Collection<Object> getCusListByCusCode(String cusCode) throws Exception {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			return session.createQuery(
					"SELECT customerCode, customerName, contact FROM CustomerBean WHERE customerCode LIKE '%" + cusCode
							+ "%'")
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
			return session.createQuery("from SalesDetailsBean where salesId.salesId=:purdetarg and deleteStatus='N'")
					.setParameter("purdetarg", purchasedetid).list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
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
			sessionFactory.close();
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
			sessionFactory.close();
		}

	}

	// get productamttot
	public SalesAmountBean getsalestotamt(Integer salesid) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			return (SalesAmountBean) session.createQuery("from SalesAmountBean where salesId.salesId=:salesid")
					.setParameter("salesid", salesid).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}

	}

	public void removeProductFromSales(SalesDetailsBean salesDetailBean) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		try {
			String query = "UPDATE SalesDetailsBean AS a SET a.deleteStatus = 'Y' WHERE a.salesDetailsId=:salesDetailsId";
			session.createQuery(query).setParameter("salesDetailsId", salesDetailBean.getSalesDetailsId())
					.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public SalesBaseBean getsalesbase(Integer salesid) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			return (SalesBaseBean) session.createQuery("from SalesBaseBean where salesId=:salesid")
					.setParameter("salesid", salesid).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}

	}

	@SuppressWarnings("unchecked")
	public Collection<Object> getSalesCountByDate(String startDate, String endDate) {

		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();

//		String queryString = "SELECT count(*), SUBSTRING(invoiceDate, 1, 10) FROM SalesBaseBean WHERE invoiceDate BETWEEN " + "'"
//				+ startDate + "'" + " AND " + "'" + endDate + "'" + " GROUP BY invoiceDate";
		String queryString = "SELECT count(*), SUBSTRING(invoiceDate, 1, 10) FROM SalesBaseBean GROUP BY invoiceDate";

		try {
			return session.createQuery(queryString).list();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<Object> getStateSetupAutoComplete(String stateCodeSTR) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String queryString = "SELECT stateName, stateCodeAlpha FROM StateSetup WHERE "
						   + "stateCodeAlpha LIKE '%" + stateCodeSTR + "%' OR stateCodeNumeric LIKE '%" + stateCodeSTR + "%' "
						   + "OR stateName LIKE '%" + stateCodeSTR + "%'";
		try {
			return session.createQuery(queryString).list();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	public Collection<ProductBean> getItemAutoCompleteForSales(String itemNameSTR) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		String queryString = "SELECT CONCAT(productCode, '-', productName, '-', category.categoryName), productId FROM ProductBean WHERE "
						   + "productCode LIKE '%" + itemNameSTR + "%' OR productName LIKE '%" + itemNameSTR + "%' ";
		try {
			return session.createQuery(queryString).list();
		} catch (Exception e) {
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public SalesBaseBean saveInvoiceBasicDetails(SalesBaseBean itemsBase) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		try {
			session.saveOrUpdate(itemsBase);
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
		return itemsBase;
		
	}

	public CustomerBean getCustomerDetailsByCode(String customerCode) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			String query = "FROM CustomerBean where customerName=:vencode";
			return (CustomerBean) session.createQuery(query).setParameter("vencode", customerCode).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}

	public ProductBean getProductDetailsById(Integer productId) {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
		Session session = sessionFactory.openSession();

		try {
			String query = "FROM ProductBean where productId=:productId";
			return (ProductBean) session.createQuery(query).setParameter("productId", productId).uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			session.close();
			sessionFactory.close();
		}
	}
}
