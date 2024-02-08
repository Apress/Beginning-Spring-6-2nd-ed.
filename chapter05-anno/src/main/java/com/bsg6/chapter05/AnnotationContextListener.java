package com.bsg6.chapter05;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.
        support.AnnotationConfigWebApplicationContext;


@WebListener
public class AnnotationContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ApplicationContext context = buildAnnotationContext();
        event.getServletContext().setAttribute("context", context);
    }

    private ApplicationContext buildAnnotationContext() {
        AnnotationConfigWebApplicationContext context =
                new AnnotationConfigWebApplicationContext();
        context.scan("com.bsg6.chapter03.mem03");
        context.refresh();
        return context;
    }
}
