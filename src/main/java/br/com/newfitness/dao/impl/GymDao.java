package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Gym;

@Repository("gymDao")
public class GymDao extends AbstractDao<Gym> implements GenericDao<Gym> {

	public GymDao() {
		super(Gym.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
}
