package br.com.newfitness.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Parameter;

@Repository("parameterDao")
public class ParameterDao extends AbstractDao<Parameter> implements GenericDao<Parameter> {

	public ParameterDao() {
		super(Parameter.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	public Parameter get() {
		StringBuilder hql = new StringBuilder("SELECT param FROM Parameter param");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		
		return (Parameter) query.uniqueResult();
	}
}
