package com.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{ConfigApp.class, SecurityConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/myapp/*"};
    }
}
