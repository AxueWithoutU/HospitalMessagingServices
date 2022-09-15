package com.capstone.emailsendr;

import java.nio.charset.StandardCharsets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

@Configuration
public class EmailConfig {
	
	
	@Bean
	public SpringTemplateEngine springTemplateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.addTemplateResolver(htmlTempResolvr());
		return templateEngine;
	}
	
	@Bean
	public SpringResourceTemplateResolver htmlTempResolvr() {
		SpringResourceTemplateResolver emailTempResolvr = new SpringResourceTemplateResolver();
		emailTempResolvr.setPrefix("classpath:/templates/");
		emailTempResolvr.setSuffix(".html");
		emailTempResolvr.setTemplateMode(TemplateMode.HTML);
		emailTempResolvr.setCharacterEncoding(StandardCharsets.UTF_8.name());
		return emailTempResolvr;
	}

}
