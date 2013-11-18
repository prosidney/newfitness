package br.com.newfitness.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.com.newfitness.dao.impl.GymDao;
import br.com.newfitness.model.Gym;

@Controller
public class GymController {
	
	@Autowired
	GymDao gymDao;

	@Transactional(readOnly=true)
	@RequestMapping(value="/gym", method=RequestMethod.GET)
	public String showGymAdmPage(HttpServletRequest request,HttpServletResponse response, Model model){
		List<Gym> gyns = gymDao.findAll();
		Gym gym = new Gym();
		
		if(gyns.size() > 0){
			gym = gyns.get(0);
		} 
		
		model.addAttribute("gym", gym);
		
		return "gym";
	}

	@Transactional
	@RequestMapping(value="/saveGym.do", method=RequestMethod.POST)
	public String saveGymAdmPage(@ModelAttribute("gym") @Valid Gym gym, 
			 					 BindingResult result, 
			 					 HttpServletRequest request,
			 					 Model model){
		if(result.hasErrors()){
			return "gym";
		}
		
		gymDao.save(gym);
		
		return "gym";
	}
	
}
