package com.example;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    /* ・・・　関係ない設定は省略 ・・・ 

    *//**
     * ValidationメッセージをUTF-8で設定できるようにする
     *//*
    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource(messageSource());
        return validator;
    }

    private MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        //プロパティファイルの名前やディレクトリも変更可能
        messageSource.setBasename("classpath:/ValidationMessages");
        //UTF-8に設定
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }*/
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Bean
    public FilterRegistrationBean
    getFilterRegistrationBean()
    {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter( new CharacterEncodingFilter() );
        return filterRegistrationBean;
    }

    
    private static class CharacterEncodingFilter
    implements Filter
    {
        protected String encoding;

        public void init( FilterConfig filterConfig )
        throws ServletException
        {
            encoding = StandardCharsets.UTF_8.name();
        }
        
        public void doFilter( ServletRequest servletRequest
                            , ServletResponse servletResponse
                            , FilterChain filterChain )
        throws IOException, ServletException
        {
            HttpServletRequest request = ( HttpServletRequest )servletRequest;
            request.setCharacterEncoding( encoding );
            filterChain.doFilter( servletRequest, servletResponse );
        }
                
        
        public void destroy()
        {
            encoding = null;
        }
    }
    
    @Override
    public Validator getValidator()
    {
        return localValidatorFactoryBean();
    }

    @Bean
    public LocalValidatorFactoryBean localValidatorFactoryBean()
    {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource( messageSource() );
        return bean;
    }
 
    @Bean
    public MessageSource messageSource()
    {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        //bean.setBasename("classpath:/i18n/ValidationMessages");
        bean.setBasename("classpath:/ValidationMessages");
       bean.setDefaultEncoding("UTF-8");
        return bean;
    }
}

