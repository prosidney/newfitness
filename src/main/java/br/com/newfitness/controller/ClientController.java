package br.com.newfitness.controller;

import java.util.Date;
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
import br.com.newfitness.dao.impl.PaymentDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.util.Util;


@Controller
public class ClientController {
	
	@Autowired
	AlunoDao clientDao;
	
	@Autowired
	PaymentDao paymentDao;
	
	@Autowired
	Util util;

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
	@RequestMapping(value="/saveClient.do", method=RequestMethod.POST)
	public String clientSave(@ModelAttribute("client") @Valid Aluno aluno, 
							 BindingResult result, 
							 HttpServletRequest request) throws Exception{
		if(result.hasErrors()){
			return "clientAdd";
		}
		
		try{
			if(aluno.getMatricula() == null){
				aluno.setRegisterDate(new Date());
				aluno.setPayments(util.generatePayments(aluno, new Date()));
			}
			clientDao.save(aluno);
			
			List<Aluno> all = clientDao.findAll();
			request.setAttribute("alunos", all);
			request.setAttribute("qtde", all.size());
			
		} catch (Exception e){
			request.setAttribute("errorMessage", e.getLocalizedMessage());
			
			return "clientAdd";
		}
		
		return "admin";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/findClient.do", method=RequestMethod.POST)
	public String findClients(HttpServletRequest request){
		List<Aluno> members = clientDao.find(request.getParameter("name"));
		members = util.fillStatusMembers(members);
		
		request.setAttribute("alunos", members);
		request.setAttribute("qtde", members.size());
		
		return "admin";
	}	
}
