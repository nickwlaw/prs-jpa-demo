package com.prs.business;

import java.util.*;

import javax.persistence.*;

import com.prs.db.DBUtil;

public class PurchaseRequestLineItemDB {
	public static PurchaseRequestLineItem getPurchaseRequestLineItemById(int purchaseRequestLineItemID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			PurchaseRequestLineItem purchaseRequestLineItem = em.find(PurchaseRequestLineItem.class, purchaseRequestLineItemID);
			return purchaseRequestLineItem;
		} finally {
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	public static List<PurchaseRequestLineItem> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<PurchaseRequestLineItem> prlis = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT prli FROM PurchaseRequestLineItem prli");
			prlis = q.getResultList();
		} finally {
			em.close();
		}
		return prlis;
	}

	public static List<PurchaseRequestLineItem> getAllByPRId(int prid) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<PurchaseRequestLineItem> prlis = new ArrayList<>();
		try {
			String qString = "SELECT prli FROM PurchaseRequestLineItem prli "
						   + " WHERE prli.purchaseRequest.id = :inPrid";
			TypedQuery<PurchaseRequestLineItem> tq = em.createQuery(qString, PurchaseRequestLineItem.class);
			tq.setParameter("inPrid", prid);
			prlis = tq.getResultList();
		} finally {
			em.close();
		}
		return prlis;
	}
	
//	@SuppressWarnings("unchecked")
//	public static List<PurchaseRequestLineItem> getAllById(int prid) {
//		EntityManager em = DBUtil.getEmFactory().createEntityManager();
//		List<PurchaseRequestLineItem> prids = new ArrayList<>();
//		try {
//			Query q = em.createQuery("SELECT prli FROM PurchaseRequestLineItem prli");
//			prids = q.getResultList();
//			for (int i = 0; i < prids.size(); i++) {
//				PurchaseRequestLineItem prli = prids.get(i);
//				if (!(prli.getPurchaseRequest().getId() == prid)) {
//					prids.remove(prli);
//				}
//			}
//		} finally {
//			em.close();
//		}
//		return prids;
//	}
	
	public static boolean insertPurchaseRequestLineItem(PurchaseRequestLineItem prli) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(prli);
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
	
	public static boolean update(PurchaseRequestLineItem prli) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(prli);
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
	
	public static boolean deletePurchaseRequestLineItem(PurchaseRequestLineItem prli) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(prli));
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
