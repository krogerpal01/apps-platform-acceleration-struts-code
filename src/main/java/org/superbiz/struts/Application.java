package org.superbiz.struts;

import com.opensymphony.module.sitemesh.filter.PageFilter;
import org.apache.struts2.dispatcher.ActionContextCleanUp;
import org.apache.struts2.dispatcher.FilterDispatcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class Application {

    @Bean
    public FilterRegistrationBean struts2Filter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new FilterDispatcher());
        bean.setName("struts2");
        bean.addInitParameter("actionPackages", "com.lq");
        bean.setUrlPatterns(Collections.singleton("/*"));
        bean.setOrder(3);

        return bean;
    }

    @Bean
    public FilterRegistrationBean strutsCleanupFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new ActionContextCleanUp());
        bean.setName("struts-cleanup");
        bean.setUrlPatterns(Collections.singleton("/*"));
        bean.setOrder(1);

        return bean;
    }

    @Bean
    public FilterRegistrationBean sitemeshFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new PageFilter());
        bean.setName("sitemesh");
        bean.setUrlPatterns(Collections.singleton("/*"));
        bean.setOrder(2);

        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
