package com.jdc.demo.router;

import java.net.URI;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import com.jdc.demo.dto.UserForm;

@Configuration
public class RouterConfig {

	@Bean
	RouterFunction<ServerResponse> routes() {
		return RouterFunctions.route()
				.GET("/", _ -> {
					// Prepare Model
					var model = new HashMap<String, Object>();
					model.put("title", "Hello Router Function");
					
					return ServerResponse.ok().render("welcome", model);
				})
				.POST("/", req -> {
					var form = req.bind(UserForm.class);
					System.out.println(form);
					return ServerResponse
							.seeOther(URI.create("/")).build();
				})
				.build();
	}
}
