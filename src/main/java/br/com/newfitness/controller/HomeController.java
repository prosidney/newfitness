package br.com.newfitness.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.newfitness.dao.impl.AcademiaDao;
import br.com.newfitness.model.Gym;

@Controller
public class HomeController {

	@Autowired
	AcademiaDao academiaDao;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="ola.do", method=RequestMethod.GET)
	public String showIndex(HttpServletRequest request,HttpServletResponse response){	
		
		List<Gym> all = academiaDao.findAll();
		
		for (Gym academia : all) {
			System.out.println(academia);
		}
		
		return "ola";
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="olaJson.do", method=RequestMethod.GET)
	public @ResponseBody Gym showIndexJson(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		
		List<Gym> all = academiaDao.findAll();
		
		for (Gym academia : all) {
			System.out.println(academia);
			
			return academia;
		}
		
		throw new Exception();
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="olaJson1.do", method=RequestMethod.GET)
	public @ResponseBody List<Gym> showIndexJson1(HttpServletRequest request,HttpServletResponse response) throws Exception{	
		
		List<Gym> findAll = academiaDao.findAll();
		
		return findAll;
	}	
}
