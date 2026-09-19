package com.jdc.spring.demo.api.management;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.management.input.PartnerSearch;
import com.jdc.spring.demo.api.management.output.PartnerListItem;
import com.jdc.spring.demo.api.management.service.PartnerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/partners")
public class PartnerManagementApi {
	
	private final PartnerService service;
	
	@GetMapping
	List<PartnerListItem> search(PartnerSearch form) {
		return service.search(form);
	}
}
