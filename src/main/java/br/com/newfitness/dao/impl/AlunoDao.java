package br.com.newfitness.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Aluno;

@Repository("alunoDao")
@SuppressWarnings("unchecked")
public class AlunoDao extends AbstractDao<Aluno> implements GenericDao<Aluno> {

	public AlunoDao() {
		super(Aluno.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}

	public Aluno findByMatricula(Integer matricula) {
		StringBuilder hql = new StringBuilder("SELECT al FROM Aluno al WHERE al.matricula = :matricula");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("matricula", matricula);
		
		return (Aluno) query.uniqueResult();
	}

	public Aluno find(Integer matricula, String senha) {
		return (Aluno) sessionFactory.getCurrentSession()
									 .getNamedQuery("Aluno.findByMatriculaSenha")
									 .setParameter("matricula", matricula)
									 .setParameter("senha", senha).uniqueResult();
}

	public List<Aluno> find(String name) {
		StringBuilder hql = new StringBuilder("SELECT al FROM Aluno al WHERE upper(al.nome) like upper(:name)");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("name", "%" + name + "%");
		
		return (List<Aluno>) query.list();
	}

}
