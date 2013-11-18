package br.com.newfitness.dao.impl;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.newfitness.dao.impl.AcademiaDao;
import br.com.newfitness.model.Gym;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springconfiguration-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class AparelhoDaoTest {
	
	@Autowired
	AcademiaDao aparelhoDao;

	@Test
	public void testFindById() {
		List<Gym> all = aparelhoDao.findAll();
		
		for (Gym academia : all) {
			System.out.println(academia);
		}
	}
}
