package com.luvina;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.vendor.HibernateJpaSessionFactoryBean;


@Configuration
public class ApplicationConfig {
	/**
	 * config bean sessionFactory create new HibernateJpaSessionFactoryBean
	 * @return
	 */
	@Bean
	public HibernateJpaSessionFactoryBean sessionFactory() {
		return new HibernateJpaSessionFactoryBean();
	}
}
