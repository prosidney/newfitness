package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Exercise;

@Repository("exercicioDao")
public class ExercicioDao extends AbstractDao<Exercise> implements GenericDao<Exercise>{

	public ExercicioDao() {
		super(Exercise.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
}
