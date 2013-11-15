package br.com.newfitness.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.newfitness.dao.impl.AlunoDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Payment;


@Controller
public class ClientController {
	
	@Autowired
	AlunoDao clientDao;

	@Transactional(readOnly=true)
	@RequestMapping(value="/addClient.do", method={RequestMethod.GET})
	public String showAddClientPage(HttpServletRequest request, HttpServletResponse response, Model model){
		Aluno client = new Aluno();
		
		String matId = request.getParameter("mat");
		if(matId != null && StringUtils.isNotEmpty(matId) && StringUtils.isAlphanumeric(matId)){
			client = clientDao.findByMatricula(Integer.parseInt(matId));
		}
		
		model.addAttribute("client", client);
		
		return "clientAdd";
	}
	
	@Transactional
	@RequestMapping(value="/saveClient.do", method={RequestMethod.GET,RequestMethod.POST})
	public String clientSave(@ModelAttribute("client") @Valid Aluno aluno, 
							 BindingResult result, 
							 HttpServletRequest request) throws Exception{
		if(result.hasErrors()){
			return "clientAdd";
		}
		
		/*aluno.setPayments(generatePayments(aluno));*/
		try{
			clientDao.save(aluno);
			
			request.setAttribute("alunos", clientDao.findAll());
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return "admin";
	}
	
	private List<Payment> generatePayments(Aluno aluno) {
		List<Payment> payments = new ArrayList<Payment>();
		
		GregorianCalendar from = new GregorianCalendar();
		from.setTime(new Date());
		
		boolean dayAfter = false;
		
		dayAfter = aluno.getDiaVencimentoParcela() < from.get(Calendar.DAY_OF_MONTH);
		
		from.set(Calendar.DAY_OF_MONTH, aluno.getDiaVencimentoParcela());
		
		GregorianCalendar at = new GregorianCalendar();
		at.set(Calendar.DAY_OF_MONTH, 30);
		at.set(Calendar.MONTH, 11);
		at.set(Calendar.YEAR, from.get(Calendar.YEAR));
		while (from.before(at) ) {
			Payment payment = new Payment();
			
			//TODO pegar a configuração do banco
			payment.setAluno(aluno);
			payment.setAmount(new BigDecimal(50));
			payment.setExpirationDate(from.getTime());
			
			//inclui o pagamento quando a data do cadastro for depois da data de vencimento
			if(dayAfter){
				payment.setDtPayment(new Date());
			}
			
			payments.add(payment);
			from.add(Calendar.MONTH, 1);
		}
		
		return payments;
	}

	@Transactional(readOnly=true)
	@RequestMapping(value="/findClient.do", method=RequestMethod.POST)
	public String findClients(HttpServletRequest request, HttpServletResponse response, Model model){
		request.setAttribute("alunos", clientDao.find(request.getParameter("name")));
		
		return "admin";
	}	
}
