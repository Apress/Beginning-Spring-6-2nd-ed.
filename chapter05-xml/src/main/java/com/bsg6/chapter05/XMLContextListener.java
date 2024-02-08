package com.bsg6.chapter05;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;


@WebListener
public class XMLContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context =
                buildXmlContext(event.getServletContext());
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildXmlContext(ServletContext sc) {
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setServletContext(sc);
        context.refresh();
        return context;
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
