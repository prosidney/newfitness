package br.com.newfitness.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
import br.com.newfitness.dao.impl.GymDao;
import br.com.newfitness.dao.impl.ParameterDao;
import br.com.newfitness.dao.impl.PaymentDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.model.Gym;
import br.com.newfitness.model.Payment;
import br.com.newfitness.util.Util;


@Controller
public class PaymentController {
	
	private static final int FIVE = 5;

	@Autowired
	AlunoDao clientDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	ParameterDao parameterDao;
	
	@Autowired
	GymDao gymDao;
	
	@Autowired
	Util util;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/viewPaymentsByMat.do", method=RequestMethod.GET)
	public String showClientPayments(HttpServletRequest request, HttpServletResponse response){
		String matId = request.getParameter("mat");
		String currPage = request.getParameter("page");
		
		if(matId != null && StringUtils.isNotEmpty(matId) && StringUtils.isAlphanumeric(matId)){
			List<Payment> all = new ArrayList<Payment>();
			int size = 0;
			if(StringUtils.isEmpty(currPage)){
				all = paymentDao.findAllPaymentsByMat(Integer.parseInt(matId));
				size = all.size();
			} else {
				all = paymentDao.findAllPaymentsByMat(Integer.parseInt(matId), Integer.parseInt(currPage) , FIVE);
				size = paymentDao.findAllPaymentsByMatCount(Integer.parseInt(matId)).intValue();
			}
			
			request.setAttribute("payments", all);
			request.setAttribute("mat", matId);
			request.setAttribute("totalItens", size);
			request.setAttribute("itensPerPage", FIVE);
			request.setAttribute("currentPage", currPage);
		}
		
		return "paymentsList";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/addPayment.do", method={RequestMethod.GET})
	public String showAddPaymentPage(HttpServletRequest request, HttpServletResponse response, Model model){
		List<Gym> findAll = gymDao.findAll();
		
		if(findAll.size() == 0){
			request.setAttribute("errorMessage", "Necessário entrar na tela de administração da academia para realizar os cadastros básicos");
			return "admin";
		}
		
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
			pay.setAmount(findAll.get(0).getAmount());
		}
		
		model.addAttribute("payment", pay);
		
		request.setAttribute("paymentTypesList", generateTypes());
		
		return "paymentAdd";
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
			
		request.setAttribute("payments", aluno.getPayments());
		request.setAttribute("mat", aluno.getMatricula());
		request.setAttribute("totalItens", aluno.getPayments().size());
		
		return "paymentsList";
	}
	
	@Transactional
	@RequestMapping(value="/generatePaymentsYear.do", method=RequestMethod.POST)
	public String generatePaymentsYear(HttpServletRequest request) throws Exception{
		//TODO verificar se o aluno nao tem nenhum pagamento para o ano solicitado, caso possua nao deixar fazer.
		if(request.getParameter("mat") != null){
			String matId = (String) request.getParameter("mat");
			String year = (String) request.getParameter("year");
			
			Aluno aluno = clientDao.findByMatricula(Integer.parseInt(matId));
			
			GregorianCalendar gc = new GregorianCalendar();
			gc.set(Calendar.DAY_OF_MONTH, 1);
			gc.set(Calendar.MONTH, 0);
			gc.set(Calendar.YEAR, Integer.parseInt(year));
			
			List<Payment> newPayments = util.generatePayments(aluno, gc.getTime());
			paymentDao.saveAll(newPayments);
			aluno.getPayments().addAll(newPayments);
			
			List<Payment> allPaidPayments = paymentDao.findAllPaidPaymentsByMatId(Integer.valueOf(matId));
			List<Payment> allPendentPayments = paymentDao.findAllPendentPaymentsByMatId(Integer.valueOf(matId));
			List<Payment> all = unionPayments(allPaidPayments, allPendentPayments);
			
			request.setAttribute("payments", all);
			request.setAttribute("qtPendent", allPendentPayments.size());
			request.setAttribute("qtPaid", allPaidPayments.size());
			
			request.setAttribute("mat", matId);
		} 
		
		return "paymentsList";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/paymentsReport.do", method=RequestMethod.GET)
	public String showAllPayments(HttpServletRequest request, HttpServletResponse response){
		List<Payment> allPaidPayments = paymentDao.findAllPaidPayments();
		List<Payment> allPendentPayments = paymentDao.findAllPendentPayments();
		List<Payment> all = unionPayments(allPaidPayments, allPendentPayments);
		
		request.setAttribute("payments", all);
		request.setAttribute("qtPendent", allPendentPayments.size());
		request.setAttribute("qtPaid", allPaidPayments.size());
		
		return "paymentReports";
	}	
	
	@Transactional(readOnly=true)
	@RequestMapping(value={"/viewPaymentsByMemberName.do"}, method=RequestMethod.POST)
	public String findPaymentsByClientName(HttpServletRequest request){
		request.setAttribute("payments", paymentDao.findAllPaymentsByClientName(request.getParameter("name")));
		
		return "paymentsList";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/viewPaymentsReportByMemberName.do", method=RequestMethod.POST)
	public String findPaymentsByClientNameReport(HttpServletRequest request){
		String clientName = request.getParameter("name");
		
		List<Payment> allPaidPayments = paymentDao.findAllPaidPaymentsByClientName(clientName);
		List<Payment> allPendentPayments = paymentDao.findAllPendentPayments(request.getParameter("name"));
		List<Payment> all = unionPayments(allPaidPayments, allPendentPayments);
		
		request.setAttribute("payments", all);
		request.setAttribute("qtPendent", allPendentPayments.size());
		request.setAttribute("qtPaid", allPaidPayments.size());
		
		return "paymentReports";
	}
	
	private List<Payment> unionPayments(List<Payment> allPaidPayments, List<Payment> allPendentPayments) {
		List<Payment> all = new ArrayList<Payment>();
		all.addAll(allPendentPayments);
		all.addAll(allPaidPayments);
		
		if(all != null && all.size() > 1){
			Collections.sort(all);
		}
		
		return all;
	}	
	
	private Map<String, String> generateTypes() {
		Map<String,String> payTypes = new LinkedHashMap<String,String>();
		payTypes.put("DI", "Dinheiro");
		payTypes.put("CA", "Cart�o");
		payTypes.put("CH", "Cheque");
		
		return payTypes;
	}
	
}
