/*      						
 * Copyright 2012 LPF  All rights reserved.
 * 
 * History:
 * ------------------------------------------------------------------------------
 * Date    	|  Who  		|  What  
 * 2017年10月26日	| LPF 	| 	create the file                       
 */

package com.thinker.gate.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.think.creator.domain.ProcessResult;

/**
 * 
 * 类简要描述
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
public class LoginController {

	// 随机盐值
	@Value("${shiro.salt}")
	private String saltStr;
	// 加盐次数
	@Value("${salt.hashIterations}")
	private int hashIterations;

	public static void main(String[] args) {

		LoginController loginController = new LoginController();
		loginController.registUser("lpf", "123456");

	}

	@RequestMapping("/user_regist")
	public ProcessResult registUser(String userName, String password) {

		// 1.校验参数

		// 2.密码加盐

		// 3.创建用户,并绑定信息

		System.out.println(password);
		System.out.println(userName);

		Md5Hash mh = new Md5Hash(password, saltStr, hashIterations);
		// 打印最终结果
		System.out.println(mh.toString());
		return new ProcessResult();
	}

	/**
	 * 实际的登录代码 如果登录成功，跳转至首页；登录失败，则将失败信息反馈对用户
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/dologin")
	public String doLogin(HttpServletRequest request, Model model) {
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
				return "redirect:/";
			} else {
				return "login";
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
			msg = "帐号已被锁定. The account for username " + token.getPrincipal()
					+ " was locked.";
			model.addAttribute("message", msg);
			System.out.println(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. The account for username " + token.getPrincipal()
					+ " was disabled.";
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
		return "login";
	}

}
