package com.prs.business;

import java.util.*;

import javax.persistence.*;

import com.prs.db.DBUtil;

public class ProductDB {
	public static Product getProductById(int productID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Product product = em.find(Product.class, productID);
			return product;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Product> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Product> products = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT p FROM Product p");
			products = q.getResultList();
		} finally {
			em.close();
		}
		return products;
	}
	
	public static List<Product> getAllbyVendorID(int vid) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Product> products = new ArrayList<>();
		try {
			String qString = "SELECT p FROM Product p "
						   + " WHERE p.vendor.id = :inVid";
			TypedQuery<Product> tq = em.createQuery(qString, Product.class);
			tq.setParameter("inVid", vid);
			products = tq.getResultList();
		} finally {
			em.close();
		}
		return products;
	}

	public static boolean insertProduct(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(p);
			trans.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
		return false;
	}
	
	public static boolean update(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(p);
			trans.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
		return false;
	}
	
	public static boolean deleteProduct(Product p) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(p));
			trans.commit();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			trans.rollback();
		} finally {
			em.close();
		}
		return false;
	}
}
