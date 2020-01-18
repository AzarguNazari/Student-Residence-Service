package de.srs.appliance.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextProvider implements ApplicationContextAware{

	private ApplicationContext context;
	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		this.context = appContext;
	}

	public ApplicationContext getContext(){
		return context;
	}
}
