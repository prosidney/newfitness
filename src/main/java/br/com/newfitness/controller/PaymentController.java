package br.com.newfitness.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.newfitness.dao.impl.AlunoDao;
import br.com.newfitness.dao.impl.ParameterDao;
import br.com.newfitness.dao.impl.PaymentDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Payment;


@Controller
@SuppressWarnings({ "rawtypes", "unchecked" })
public class PaymentController {
	
	@Autowired
	AlunoDao clientDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	ParameterDao parameterDao;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/viewPayments.do", method=RequestMethod.GET)
	public String showPayments(HttpServletRequest request, HttpServletResponse response){
		Aluno client = new Aluno();
		
		String matId = request.getParameter("mat");
		if(matId != null && StringUtils.isNotEmpty(matId) && StringUtils.isAlphanumeric(matId)){
			client = clientDao.findByMatricula(Integer.parseInt(matId));
		}
		
		client.getPayments().size();
		request.setAttribute("payments", client.getPayments());
		request.setAttribute("mat", client.getMatricula());
		
		return "paymentsList";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/addPayment.do", method={RequestMethod.GET})
	public String showAddPaymentPage(HttpServletRequest request, HttpServletResponse response, Model model){
		Payment pay = new Payment();
		
		String paymentId = request.getParameter("payId");
		if(paymentId != null && StringUtils.isNotEmpty(paymentId) && StringUtils.isAlphanumeric(paymentId)){
			pay = paymentDao.find(Integer.parseInt(paymentId));
		} else {
			Aluno aluno = clientDao.findByMatricula(Integer.parseInt(request.getParameter("mat")));
			Date today = new Date();
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.setTime(new Date());
			gc.set(Calendar.DAY_OF_MONTH, aluno.getDiaVencimentoParcela());
			
			pay.setAluno(aluno);
			pay.setExpirationDate(gc.getTime());
			pay.setDtPayment(today);
			pay.setAmount(parameterDao.get().getAmount());
		}
		
		model.addAttribute("payment", pay);
		
		request.setAttribute("paymentTypesList", generateTypes());
		
		return "paymentAdd";
	}

	private Map<String, String> generateTypes() {
		Map<String,String> payTypes = new LinkedHashMap<String,String>();
		payTypes.put("DI", "Dinheiro");
		payTypes.put("CA", "Cartão");
		payTypes.put("CH", "Cheque");
		return payTypes;
	}
	
	@Transactional
	@RequestMapping(value="/savePayment.do", method={RequestMethod.GET,RequestMethod.POST})
	public String paymentSave(@ModelAttribute("payment") @Valid Payment pay, 
							 BindingResult result, 
							 HttpServletRequest request) throws Exception{
		if(result.hasErrors()){
			return "paymentAdd";
		}
		
		Aluno aluno = clientDao.find(Integer.parseInt(request.getParameter("mat")));

		pay.setAluno(aluno);
		paymentDao.save(pay);
			
		aluno.getPayments().size();
		request.setAttribute("payments", aluno.getPayments());
		request.setAttribute("mat", aluno.getMatricula());
			
		return "paymentsList";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {
/*		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));*/
	 }
}
