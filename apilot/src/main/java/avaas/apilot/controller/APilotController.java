package avaas.apilot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import avaas.apilot.model.APilot;
import avaas.apilot.service.APilotService;

@Controller
@RequestMapping("apilot/ui")
public class APilotController {
	
	private APilotService aPilotService;

	public APilotController(APilotService aPilotService) {
		super();
		this.aPilotService = aPilotService;
	}


	@GetMapping("")
	public String showForm(Model model) {
		APilot apilot = aPilotService.consumeAPilotFromTopic();
		System.out.println(apilot);
		model.addAttribute("apilot", apilot);
		return "apilot";
	}

}
