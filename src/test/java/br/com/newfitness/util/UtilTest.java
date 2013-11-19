package br.com.newfitness.util;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.junit.Before;
import org.junit.Test;

import br.com.newfitness.dao.impl.AlunoDao;
import br.com.newfitness.dao.impl.GymDao;
import br.com.newfitness.dao.impl.PaymentDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Aluno.Status;
import br.com.newfitness.model.Gym;
import br.com.newfitness.model.Payment;

public class UtilTest {
	
	Util util;
	
	PaymentDao paymentDao;
	
	AlunoDao alunoDao;
	
	GymDao gymDao;
	
	@Before
	public void setup(){
		util = new Util();

		paymentDao = mock(PaymentDao.class);
		alunoDao = mock(AlunoDao.class);
		gymDao = mock(GymDao.class);
		
		util.setPaymentDao(paymentDao);
		
		
		when(paymentDao.findPendentPayments(1)).thenReturn(new ArrayList<Payment>());
		
		ArrayList<Payment> payments = new ArrayList<Payment>();
		payments.add(new Payment());
		
		when(paymentDao.findPendentPayments(2)).thenReturn(payments);
	}

	@Test
	public void testRetrieveStatusMemberOK() {
		Status retrieveStatusMember = util.retrieveStatusMember(1);
		
		assertTrue(retrieveStatusMember.equals(Status.OK));
	}
	
	@Test
	public void testRetrieveStatusMemberNOK() {
		Status retrieveStatusMember = util.retrieveStatusMember(2);
		
		assertTrue(retrieveStatusMember.equals(Status.NOK));
	}

	@Test
	public void testGeneratePayments() throws Exception {
		util.setGymDao(gymDao);
		
		Gym gym = new Gym();
		gym.setAmount(new BigDecimal(50));
		
		ArrayList<Gym> arrayList = new ArrayList<Gym>();
		arrayList.add(gym);
		
		when(gymDao.findAll()).thenReturn(arrayList);
		
		Aluno member = new Aluno();
		member.setNome("Sidney");
		member.setMatricula(1);
		member.setDiaVencimentoParcela(20);
		
		List<Payment> payments = util.generatePayments(member, new Date());
		
		GregorianCalendar gc = new GregorianCalendar();
		
		DateTime start = new DateTime(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH), gc.get(Calendar.DAY_OF_MONTH), 0, 0, 0, 0);
		DateTime end = new DateTime(gc.get(Calendar.YEAR), 12, 30, 0, 0, 0, 0);
		Months months = Months.monthsBetween(start, end);
		
/*		System.out.println(months.getMonths());
		for (Payment payment : payments) {
			System.out.println(payment);
		}
		*/
		
		assertTrue(payments.size() == months.getMonths());
	}	
}
