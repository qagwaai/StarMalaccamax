package com.qagwaai.starmalaccamax.server.listener;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.qagwaai.starmalaccamax.server.config.Configuration;

public class ApplicationStartup implements ServletContextListener {

	private static Logger log = Logger.getLogger(ApplicationStartup.class.getName());

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		log.severe("ServletContextListener destroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		log.severe("ServletContextListener started");
		Properties properties = new Properties();
		int factory = 0;
		try {
			properties.load(getClass().getResourceAsStream("/Application.properties"));
		} catch (IOException e) {
			log.severe("Application Config failed: " + e.getMessage());
		}
		try {
			String factoryProperty = (String) properties.get("DAOFactory");
			factory = Integer.valueOf(factoryProperty);
		} catch (NumberFormatException e) {
			log.severe("Application Config failed: " + e.getMessage());
		}
		if ((factory == 1) || (factory == 2) || (factory == 3)) {
			Configuration.getInstance().setDAOFactory(factory);
		} else {
			log.severe("Application Config Failed : incorrect dao factory [" + factory + "]");
		}
	}
}
