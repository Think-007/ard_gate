/*      						
 * Copyright 2012 LPF  All rights reserved.
 * 
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What  
 * 2017年10月26日	| LPF 	| 	create the file                       
 */

package com.thinker.gate.controller;

import java.io.IOException;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import com.think.creator.domain.ProcessResult;
import com.thinker.gate.domain.ArdUserRole;
import com.thinker.gate.domain.UserRegistParam;
import com.thinker.gate.service.UserRegistService;
import com.thinker.gate.util.ArdError;
import com.thinker.gate.util.ArdLog;
import com.thinker.gate.util.TokenUtil;

import ch.qos.logback.classic.spi.ThrowableProxyVO;

/**
 * 
 * 登录接口
 * 
 * <p>
 * 类详细描述
 * </p>
 * 
 * @author LPF
 * 
 */
@Controller
@RequestMapping("/gate")
public class GateController {

	private static final Logger logger = LoggerFactory.getLogger(GateController.class);

	// 随机盐值
	@Value("${shiro.salt}")
	private String saltStr;
	// 加盐次数
	@Value("${salt.hashIterations}")
	private int hashIterations;

	@Resource
	private UserRegistService userRegistService;

	@RequestMapping("/homepage")
	public String toHome() {

		return "/home";

	}

	/**
	 * web端登录地址
	 * 
	 * @return
	 */
	@RequestMapping("/web_authentication")
	public ModelAndView webLogin(HttpServletRequest request, HttpServletResponse response, Model model) {

		ModelAndView mv = new ModelAndView();
		// 1、校验验证码

		// 2、校验用户信息

		try {
			String msg = "";
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			System.out.println(userName);
			System.out.println(password);
			UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {

					mv.addObject("retcode", "0");
					mv.setViewName("/admin/mainpage");
					return mv;
				}
				mv.addObject("retcode", "-1");
				mv.setViewName("/home");

			} catch (IncorrectCredentialsException e) {
				msg = "登录密码错误. Password for account " + token.getPrincipal() + " was incorrect.";
				mv.addObject("errorcode", ArdError.PASSWORD_ERROR + "");
				mv.addObject("errordesc", msg);
				System.out.println(msg);
			} catch (DisabledAccountException e) {
				msg = "帐号已被注销. The account for username " + token.getPrincipal() + " was disabled.";
				mv.addObject("errorcode", ArdError.SMS_CODE_ERROR + "");
				mv.addObject("errordesc", msg);
				System.out.println(msg);
			} catch (UnknownAccountException e) {
				msg = "帐号不存在. There is no user with username of " + token.getPrincipal();
				mv.addObject("errorcode", ArdError.USER_NOT_EXIST + "");
				mv.addObject("errordesc", msg);
				System.out.println(msg);
			}

		} catch (Throwable t) {
			mv.addObject(ProcessResult.FAILED);
			mv.addObject("errorcode", ArdError.EXCEPTION + "");
			mv.addObject("errordesc", ArdError.EXCEPTION_MSG);
			t.printStackTrace();
		}
		return mv;
	}

	@RequestMapping("/signout_req")
	public void checkOut() {

		SecurityUtils.getSubject().logout();
		// app还要删除token

	}

	@RequestMapping("/password_reset")
	public ProcessResult resetPassword() {

		ProcessResult result = new ProcessResult();

		return result;

	}

}
