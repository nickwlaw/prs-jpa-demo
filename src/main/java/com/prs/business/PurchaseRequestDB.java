package com.prs.business;

import java.util.*;

import javax.persistence.*;

import com.prs.db.DBUtil;

public class PurchaseRequestDB {
	public static PurchaseRequest getPurchaseRequestById(int purchaseRequestID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequest purchaseRequest = em.find(PurchaseRequest.class, purchaseRequestID);
			return purchaseRequest;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PurchaseRequest> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<PurchaseRequest> purchaseRequests = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT pr FROM PurchaseRequest pr");
			purchaseRequests = q.getResultList();
		} finally {
			em.close();
		}
		return purchaseRequests;
	}

	public static boolean insertPurchaseRequest(PurchaseRequest p) {
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
	
	public static boolean update(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(pr);
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
	
	public static boolean deletePurchaseRequest(PurchaseRequest pr) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(pr));
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
	
	public static void recalculateTotal(PurchaseRequest pr) {
		List<PurchaseRequestLineItem> prlis = PurchaseRequestLineItemDB.getAllByPRId(pr.getId());
		double subTotal = 0.0;
		for (PurchaseRequestLineItem prli : prlis) {
			subTotal += (prli.getQuantity() * prli.getProduct().getPrice());
		}
		pr.setTotal(subTotal);
		PurchaseRequestDB.update(pr);
	}
}
