package com.jdc.hello;

import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ListenerBean {

	@EventListener
	public void handleOnRefreshed(ContextRefreshedEvent e) {
		System.out.println("Context Refreshed");
	}
	
	@EventListener
	public void handleWillClose(ContextClosedEvent e) {
		System.out.println("Context Will Close soon");
	}
	
}
