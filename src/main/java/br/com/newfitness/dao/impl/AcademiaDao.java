package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Gym;

@Repository("academiaDao")
public class AcademiaDao extends AbstractDao<Gym> implements GenericDao<Gym> {

	public AcademiaDao() {
		super(Gym.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public Gym findById(Integer id) {
		return (Gym) sessionFactory.getCurrentSession()
										.getNamedQuery("Aparelho.findById")
										.setParameter("id", id).uniqueResult();
	}
}
