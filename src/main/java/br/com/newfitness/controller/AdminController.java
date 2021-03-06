package br.com.newfitness.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.newfitness.dao.impl.AlunoDao;
import br.com.newfitness.model.Aluno;
import br.com.newfitness.util.Util;

@Controller
public class AdminController {

	@Autowired
	AlunoDao alunoDao;
	
	@Autowired
	Util util;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/admin", method=RequestMethod.GET)
	public String showAdminPage(HttpServletRequest request,HttpServletResponse response){
		List<Aluno> members = alunoDao.findAll();
		
		members = util.fillStatusMembers(members);
		
		request.setAttribute("alunos", members);
		request.setAttribute("qtde", members.size());
		
		return "admin";
	}
}
