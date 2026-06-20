package com.jdc.demo.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.entity.AccessHistory.Status;
import com.jdc.demo.model.input.AccessSearch;
import com.jdc.demo.model.service.AccessHistoryService;

@Controller
@RequestMapping("member/access")
public class AccessHistoryController {
	
	@Autowired
	private AccessHistoryService service;

	@GetMapping
	String search(AccessSearch form, ModelMap model) {
		model.put("form", form);
		model.put("statusList", Status.values());
		model.put("typeList", AccessType.values());
		
		model.put("result", service.search(form));
		return "pages/member/access";
	}
}
