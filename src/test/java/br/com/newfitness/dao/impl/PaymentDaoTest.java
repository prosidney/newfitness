package br.com.newfitness.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.newfitness.model.Payment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springconfiguration-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class PaymentDaoTest {

	@Autowired
	PaymentDao paymentDao;
	
	@Test
	public void test() {
		List<Payment> findPendentPayments = paymentDao.findPendentPayments(1);
		
		for (Payment payment : findPendentPayments) {
			System.out.println(payment);
		}
	}
}
