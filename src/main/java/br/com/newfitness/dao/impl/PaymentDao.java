package br.com.newfitness.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import br.com.newfitness.dao.AbstractDao;
import br.com.newfitness.dao.GenericDao;
import br.com.newfitness.model.Payment;

@Repository("paymentDao")
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
}
