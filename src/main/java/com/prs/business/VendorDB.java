package com.prs.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.prs.db.DBUtil;

public class VendorDB {
	public static Vendor getVendorById(int vendorID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			Vendor v = em.find(Vendor.class, vendorID);
			return v;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<Vendor> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<Vendor> vendors = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT v FROM Vendor v");
			vendors = q.getResultList();
		} finally {
			em.close();
		}
		return vendors;
	}

	public static boolean insertVendor(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(v);
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
	
	public static boolean update(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(v);
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
	
	public static boolean deleteVendor(Vendor v) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(v));
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
