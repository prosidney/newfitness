package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Academia;

@Repository("academiaDao")
public class AcademiaDao extends AbstractDao<Academia> implements GenericDao<Academia> {

	public AcademiaDao() {
		super(Academia.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public Academia findById(Integer id) {
		return (Academia) sessionFactory.getCurrentSession()
										.getNamedQuery("Aparelho.findById")
										.setParameter("id", id).uniqueResult();
	}
}
