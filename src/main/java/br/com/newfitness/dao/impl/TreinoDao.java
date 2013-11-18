package br.com.newfitness.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Training;

@Repository("treinoDao")
public class TreinoDao extends AbstractDao<Training> implements GenericDao<Training> {

	public TreinoDao() {
		super(Training.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public Training findById(Integer treinoId) {
		return (Training) sessionFactory.getCurrentSession()
									  .getNamedQuery("Treino.findById")
									  .setParameter("id", treinoId).uniqueResult();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Training> findByAlunoMatricula(Integer matricula) {
		return sessionFactory.getCurrentSession()
							 .getNamedQuery("Treino.findByAlunoMatricula")
							 .setParameter("matricula", matricula).list();
		
	}

}
