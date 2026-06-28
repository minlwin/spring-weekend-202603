package com.jdc.shop.controller.anonymous;

import java.util.UUID;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.jdc.shop.controller.anonymous.output.ShoppingCart;
import com.jdc.shop.model.service.InvoiceService;
import com.jdc.shop.model.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@SessionAttributes("shoppingCart")
@RequiredArgsConstructor
public class ShoppingCartController {
	
	private final ProductService productService;
	private final InvoiceService invoiceService;

	@GetMapping("anonymous/cart")
	String showCart(@ModelAttribute("shoppingCart") ShoppingCart cart) {
		return "pages/cart";
	}

	@ResponseBody
	@PostMapping("anonymous/cart/add/{product}")
	ShoppingCart addToCart(@PathVariable UUID product,
			@ModelAttribute("shoppingCart") ShoppingCart cart) {
		productService.findById(product).ifPresent(item -> {
			cart.add(item);
		});
		return cart;
	}

	@ResponseBody
	@PostMapping("anonymous/cart/remove/{product}")
	ShoppingCart removeFromCart(@PathVariable UUID product,
			@ModelAttribute("shoppingCart") ShoppingCart cart) {
		return cart.remove(product);
	}

	@PostMapping("anonymous/cart/clear")
	String clear(SessionStatus session) {
		session.setComplete();
		return "redirect:/";
	}

	@GetMapping("member/checkout")
	String checkOut(ModelMap model, Authentication authentication) {
		
		var lastAddresses = invoiceService.findAddresses(authentication.getName());
		model.put("addressList", lastAddresses);
		
		return "pages/cart-address";
	}

	@PostMapping("member/checkout")
	String checkOutAction(@Validated @ModelAttribute("shoppingCart") ShoppingCart cart, BindingResult result, SessionStatus session) {
		
		if(result.hasErrors()) {
			return "pages/cart-address";
		}
		
		var id = invoiceService.checkOut(cart);
		session.setComplete();
		return "redirect:/member/invoice/%s".formatted(id);
	}
	
	@ModelAttribute(name = "shoppingCart")
	ShoppingCart cart() {
		return new ShoppingCart();
	}
}
