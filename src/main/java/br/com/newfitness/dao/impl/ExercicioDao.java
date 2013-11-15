package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Exercicio;

@Repository("exercicioDao")
public class ExercicioDao extends AbstractDao<Exercicio> implements GenericDao<Exercicio>{

	public ExercicioDao() {
		super(Exercicio.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
}
