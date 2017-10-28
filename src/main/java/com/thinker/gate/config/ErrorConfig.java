/*      						
 * Copyright 2010 Beijing Xinwei, Inc. All rights reserved.
 * 
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What  
 * 2017年9月1日	| lipengfeia 	| 	create the file                       
 */

package com.thinker.gate.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 
 * 404 500页面提示的配置
 * 
 * <p>
 * 类详细描述
 * </p>
 * 
 * @author lipengfeia
 * 
 */
//@Configuration
//public class ErrorConfig {
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return new EmbeddedServletContainerCustomizer() {
//			@Override
//			public void customize(ConfigurableEmbeddedServletContainer container) {
//				ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND,
//						"/error/404.html");
//				ErrorPage error500Page = new ErrorPage(
//						HttpStatus.INTERNAL_SERVER_ERROR, "/error/500.html");
//				container.addErrorPages(error404Page, error500Page);
//			}
//		};
//	}
//}
