package com.ebay.cbt.skunkworks.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	public Criteria getCriteria(Class<T> clazz) {
		return getCurrentSession().createCriteria(clazz);
	}
	
	public void flush() {
		getCurrentSession().flush();
	}
	
	public int getTotal(Class<T> clazz) {
		return getCriteria(clazz).list().size();
	}
}