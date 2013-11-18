package br.com.newfitness.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.newfitness.dao.impl.AlunoDao;
import br.com.newfitness.dao.impl.GymDao;
import br.com.newfitness.dao.impl.PaymentDao;
import br.com.newfitness.exception.BusinessException;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Aluno.Status;
import br.com.newfitness.model.Gym;
import br.com.newfitness.model.Payment;
import br.com.newfitness.model.PaymentType;

@Service("util")
public class Util {
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	AlunoDao alunoDao;
	
	@Autowired
	GymDao gymDao;
	
	public Status retrieveStatusMember(int mat) {
		if(paymentDao.findPendentPayments(mat).size() == 0){
			return Status.OK;
		} 
			
		return Status.NOK;
	}

	public List<Payment> generatePayments(Aluno member) throws BusinessException{
		GregorianCalendar today = createGregorian(new Date());
		
		List<Payment> payments = new ArrayList<Payment>();
		
		Integer dueDate = member.getDiaVencimentoParcela();
		
		GregorianCalendar gregorianCount = createGregorian(new Date());
		gregorianCount.set(Calendar.DAY_OF_MONTH, dueDate);
		gregorianCount.set(Calendar.HOUR_OF_DAY, 0);
		gregorianCount.set(Calendar.MINUTE, 0);
		gregorianCount.set(Calendar.SECOND, 0);
		gregorianCount.set(Calendar.MILLISECOND, 0);
		
		if(today.get(Calendar.DAY_OF_MONTH) > dueDate.intValue()){
			gregorianCount.add(Calendar.MONTH, 1);
		} 
		
		Payment init = new Payment();
		
		List<Gym> gym = gymDao.findAll();
		
		if(gym.size() == 0){
			throw new BusinessException("Necessário entrar na tela de administração da academia para realizar os cadastros básicos");
		}
		
		BigDecimal amount = gym.get(0).getAmount();
		
		
		init.setAmount(amount);
		init.setAluno(member);
		init.setDtPayment(null);
		init.setPaymentType(PaymentType.DI);
		init.setExpirationDate(gregorianCount.getTime());
		
		payments.add(init);
		
		
		gregorianCount.add(Calendar.MONTH, 1);
		while (gregorianCount.get(Calendar.YEAR) == today.get(Calendar.YEAR)) {
			Payment payment = new Payment();
			
			payment.setAmount(amount);
			payment.setAluno(member);
			payment.setDtPayment(null);
			payment.setExpirationDate(gregorianCount.getTime());
			payment.setPaymentType(PaymentType.DI);
			payments.add(payment);
			
			gregorianCount.add(Calendar.MONTH, 1);
		}
		
		return payments;
	}
	
	private GregorianCalendar createGregorian(Date date){
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		return gc;
	}

	public void setPaymentDao(PaymentDao paymentDao) {
		this.paymentDao = paymentDao;
	}

	public void setAlunoDao(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public void setGymDao(GymDao gymDao) {
		this.gymDao = gymDao;
	}

}
