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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.think.creator.domain.ProcessResult;
import com.thinker.gate.util.ArdLog;

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
@RestController
@RequestMapping("/gate")
public class GateController {

	private static final Logger logger = LoggerFactory
			.getLogger(GateController.class);

	// 随机盐值
	// @Value("${shiro.salt}")
	private String saltStr = "333";
	// 加盐次数
	@Value("${salt.hashIterations}")
	private int hashIterations = 3;

	@RequestMapping("/registration")
	@ResponseBody
	public ProcessResult registUser(String userName, String password,
			String telNumber) {
		ArdLog.info(logger, "enter registUser ", null, "userName" + userName
				+ "password: " + password + "telnumber: " + telNumber);

		// 1.校验参数

		// 2.密码加盐

		// 3.创建用户,并绑定信息

		ProcessResult processResult = new ProcessResult();
		System.out.println(userName);
		System.out.println(password);
		System.out.println(saltStr);
		System.out.println(hashIterations);

		Md5Hash mh = new Md5Hash(password, saltStr, hashIterations);
		// 打印最终结果
		System.out.println(mh.toString());
		processResult.setRetCode(ProcessResult.SUCCESS);
		processResult.setRetMsg("ok");
		return processResult;
	}

	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping(value = "/authentication")
	public void doLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {
		try {
			String msg = "";
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			System.out.println(userName);
			System.out.println(password);
			UsernamePasswordToken token = new UsernamePasswordToken(userName,
					password);
			token.setRememberMe(true);
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if (subject.isAuthenticated()) {
					request.getRequestDispatcher("/admin/home.html").forward(
							request, response);
					return;
				}
			} catch (IncorrectCredentialsException e) {
				msg = "登录密码错误. Password for account " + token.getPrincipal()
						+ " was incorrect.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (ExcessiveAttemptsException e) {
				msg = "登录失败次数过多";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (LockedAccountException e) {
				msg = "帐号已被锁定. The account for username "
						+ token.getPrincipal() + " was locked.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (DisabledAccountException e) {
				msg = "帐号已被禁用. The account for username "
						+ token.getPrincipal() + " was disabled.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (ExpiredCredentialsException e) {
				msg = "帐号已过期. the account for username " + token.getPrincipal()
						+ "  was expired.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (UnknownAccountException e) {
				msg = "帐号不存在. There is no user with username of "
						+ token.getPrincipal();
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (UnauthorizedException e) {
				msg = "您没有得到相应的授权！" + e.getMessage();
				model.addAttribute("message", msg);
				System.out.println(msg);
			}
			response.sendRedirect("/index.html");

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	public String checkOut() {

		return "redirect:index.html";
	}

}
