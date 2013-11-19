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
								.append("AND pm.expirationDate < sysdate ")
								.append("AND pm.dtPayment = null ");
		
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery(hql.toString());
		query.setParameter("mat", matricula);
		
		return (List<Payment>) query.list();
	}
	
	public List<Payment> findAllPendentPayments() {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
									.append("pm.expirationDate < sysdate ")
									.append("AND pm.dtPayment = null ");
		
		return sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
	}
	
	public List<Payment> findAllPaidPayments() {
		StringBuilder hql = new StringBuilder("SELECT pm FROM Payment pm WHERE ")
		.append("pm.expirationDate < sysdate ")
		.append("AND pm.dtPayment != null ");
		
		return sessionFactory.getCurrentSession().createQuery(hql.toString()).list();
	}
}
