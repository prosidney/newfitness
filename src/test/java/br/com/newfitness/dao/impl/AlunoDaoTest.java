package br.com.newfitness.dao.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.newfitness.exception.BusinessException;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Payment;
import br.com.newfitness.util.Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springconfiguration-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
@Transactional
public class AlunoDaoTest {

	@Autowired
	AlunoDao alunoDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	Util util;
	
	@Test
	@Ignore
	public void test() throws BusinessException {
		Aluno aluno = alunoDao.findByMatricula(1);
		
		for (Payment pay : aluno.getPayments()) {
			pay.setAluno(null);
			
			paymentDao.delete(pay);
		}
		
		System.out.println(aluno.getPayments().size());
	}
	
	@Test
	@Ignore
	public void test1() throws BusinessException {
		Aluno aluno = alunoDao.findByMatricula(1);
		
		GregorianCalendar gc = new GregorianCalendar();
		gc.set(Calendar.DAY_OF_MONTH, 1);
		gc.set(Calendar.MONTH, 0);
		gc.set(Calendar.YEAR, 2013);
		
		aluno.setPayments(util.generatePayments(aluno, gc.getTime()));
		
		paymentDao.saveAll(aluno.getPayments());
		for (Payment pay : aluno.getPayments()) {
			System.out.println(pay);
		}
		
		alunoDao.save(aluno);
	}
	
	@Test
	@Ignore
	public void test2() throws BusinessException {
		Aluno aluno = alunoDao.findByMatricula(1);
		
		System.out.println(aluno.getPayments().size());
		for (Payment pay : aluno.getPayments()) {
			System.out.println(pay);
		}
	}

}
