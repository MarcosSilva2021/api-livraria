package com.devm.api_livraria.conf;

import com.devm.api_livraria.filter.FiltroDoToken;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {

    @Bean
    public FilterRegistrationBean<FiltroDoToken> filtroDasRotas(){
        FilterRegistrationBean<FiltroDoToken> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new FiltroDoToken());
        filterRegistrationBean.addUrlPatterns("/auth/usuarios/*");
        return filterRegistrationBean;
    }
}
