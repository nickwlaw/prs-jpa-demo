package com.prs.business;

import java.util.*;

import javax.persistence.*;

import com.prs.db.DBUtil;

public class UserDB {
	public static User getUserById(int userID) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		try {
			User user = em.find(User.class, userID);
			return user;
		} finally {
			em.close();
		}
	}
	
	public static User getUserByUserName(String userName) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		User u = new User();
		try {
			Query q = em.createQuery("SELECT u FROM User u"
								   + " WHERE u.userName = '"+userName+"'");
			u = (User) q.getSingleResult();
		} catch (NoResultException nre) {
			System.out.println("No user with Username " + userName + " was found.");
		} finally {
			em.close();
		}
		return u;
	}

	@SuppressWarnings("unchecked")
	public static List<User> getAll() {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		List<User> users = new ArrayList<>();
		try {
			Query q = em.createQuery("SELECT u FROM User u");
			users = q.getResultList();
		} finally {
			em.close();
		}
		return users;
	}

	public static boolean insertUser(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.persist(u);
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

	public static boolean update(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.merge(u);
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

	public static boolean deleteUser(User u) {
		EntityManager em = DBUtil.getEmFactory().createEntityManager();
		EntityTransaction trans = em.getTransaction();
		trans.begin();
		try {
			em.remove(em.merge(u));
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
