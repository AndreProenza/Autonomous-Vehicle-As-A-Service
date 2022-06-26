package avaas.apilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import avaas.apilot.model.AvResult;
import avaas.apilot.service.AvResultService;

@Controller
@RequestMapping("avresult/ui")
public class AvResultController {
	
	private AvResultService avResultService;

	public AvResultController(AvResultService avResultService) {
		super();
		this.avResultService = avResultService;
	}


	@GetMapping("")
	public String showForm(Model model) {
		AvResult avResult = avResultService.consumeAvResultFromTopic();
		System.out.println(avResult);
		model.addAttribute("avResult", avResult);
		return "avresult";
	}

}