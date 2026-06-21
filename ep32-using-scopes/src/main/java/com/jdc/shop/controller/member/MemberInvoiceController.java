package com.jdc.shop.controller.member;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.management.input.InvoiceSearch;
import com.jdc.shop.model.entity.Invoice.Status;
import com.jdc.shop.model.service.InvoiceService;

@Controller
@RequestMapping("member/invoice")
public class MemberInvoiceController {
	
	@Autowired
	private InvoiceService service;

	@GetMapping
	String search(InvoiceSearch form,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		model.put("statusList", Status.values());
		model.put("result", service.search(form, page, size));
		return "pages/member/invoice-list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable UUID id, ModelMap model) {
		model.put("data", service.findBuId(id));
		return "pages/member/invoice-details";
	}
	
}
