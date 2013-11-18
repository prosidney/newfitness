package br.com.newfitness.dao.impl;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.com.newfitness.dao.impl.TreinoDao;
import br.com.newfitness.model.Training;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:springconfiguration-test.xml"})
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=true)
@Transactional
public class TreinoDaoTest {

	@Autowired
	TreinoDao treinoDao;
	
	@Test
	@Ignore
	public void testFindById() {
		Training treino = treinoDao.findById(1);
		
		System.out.println(treino.getExercicios());
	}

	@Test
	@Ignore
	public void testFindByAlunoMatricula() {
		List<Training> treinos = treinoDao.findByAlunoMatricula(1);
		
		System.out.println(treinos);
	}
}
