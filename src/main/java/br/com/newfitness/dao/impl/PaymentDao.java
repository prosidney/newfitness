package br.com.newfitness.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Payment;

@Repository("paymentDao")
@SuppressWarnings("unchecked")
public class PaymentDao extends AbstractDao<Payment> implements GenericDao<Payment> {

	public PaymentDao() {
		super(Payment.class);
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Payment save(Payment payment){
		if(payment.getId() == null){
			return super.save(payment);
		} else{
			return super.merge(payment);
		}
	}

	public List<Payment> findPendentPayments(Integer matricula) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
								.append("pm.aluno.matricula = :mat ")
								.append("AND pm.expirationDate < current_date() ")
								.append("AND pm.dtPayment = null ");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("mat", matricula);
		
		return (List<Payment>) query.list();
	}
	
	public List<Payment> findAllPendentPayments() {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
									.append("pm.expirationDate < current_date() ")
									.append("AND pm.dtPayment = null ");
		
		return sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
	}
	
	public List<Payment> findAllPaidPayments() {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
		.append("pm.expirationDate < current_date() ")
		.append("AND pm.dtPayment != null ");
		
		return sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
	}

	public List<Payment> findAllPaidPaymentsByClientName(String name) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
								.append("pm.expirationDate < current_date() ")
								.append("AND pm.dtPayment != null ")
								.append("AND upper(pm.aluno.nome) like upper(:name) ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("name", "%" + name + "%");
		
		return query.list();
	}
	
	public List<Payment> findAllPaymentsByClientName(String name) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
		.append(" upper(pm.aluno.nome) like upper(:name) ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("name", "%" + name + "%");
		
		return query.list();
	}

	public List<Payment> findAllPendentPayments(String name) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
								.append("pm.expirationDate < current_date() ")
								.append("AND pm.dtPayment = null ")
								.append("AND upper(pm.aluno.nome) like upper(:name) ");
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("name", "%" + name + "%");
		
		return query.list();
	}

	public List<Payment> findAllPaidPaymentsByMatId(Integer matId) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
											.append("pm.expirationDate < current_date() ")
											.append("AND pm.dtPayment != null ")
											.append("AND pm.aluno.matricula = :matId ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("matId", matId);
		
		return query.list();
	}

	public List<Payment> findAllPendentPaymentsByMatId(Integer matId) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
												.append("pm.expirationDate < current_date() ")
												.append("AND pm.dtPayment = null ")
												.append("AND pm.aluno.matricula = :matId ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("matId", matId);
		
		return query.list();
	}

	public List<Payment> findAllPaymentsByMat(int matId) {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
		.append(" pm.aluno.matricula = :matId ");

		Query query = sessionFactory.getCurrentSession().createQuery(hql.toString());
		query.setParameter("matId", matId);

		return query.list();
	}
}
