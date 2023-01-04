package com.rent.car.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rent.car.models.JobTitle;
import com.rent.car.services.JobTitleService;

@Controller
public class JobTitleController {

	@Autowired private JobTitleService jobTitleService;

	@GetMapping("/jobTitles")
	public String getJobTitles(JobTitle jobTitle, Model model) {
		
		model.addAttribute("jobTitles", jobTitleService.getJobTitles());
		return "/types/jobTitle";
	}
	
	@PostMapping("/jobTitles/addNew")
	public String addNew(@Valid JobTitle jobTitle, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("jobTitles", jobTitleService.getJobTitles());
			return "/types/jobTitle";
		}
		
		jobTitleService.save(jobTitle);
		return "redirect:/jobTitles";
	}
	
	@RequestMapping("/jobTitles/findById")
	@ResponseBody
	public Optional<JobTitle> findById(int id) {
		return jobTitleService.findById(id);
	}
	
	@RequestMapping(value="/jobTitles/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(@Valid JobTitle jobTitle, BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("jobTitles", jobTitleService.getJobTitles());
			return "/types/jobTitle";
		}
		
		jobTitleService.save(jobTitle);
		return "redirect:/jobTitles";
	}
	
	@RequestMapping(value="/jobTitles/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(int id) {
		jobTitleService.delete(id);
		return "redirect:/jobTitles";
	}
}
