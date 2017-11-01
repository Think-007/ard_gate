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

import com.think.creator.domain.ProcessResult;
import com.thinker.gate.domain.ArdUserRole;
import com.thinker.gate.domain.UserRegistParam;
import com.thinker.gate.service.UserRegistService;
import com.thinker.gate.util.ArdError;
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
@Controller
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

	@Resource
	private UserRegistService userRegistService;

	@RequestMapping("/homepage")
	public String toHome() {

		return "/home";

	}

	@RequestMapping("/registration")
	@ResponseBody
	public ProcessResult registUser(UserRegistParam userRegistParam) {
		ArdLog.info(logger, "enter registUser ", null, "userRegistParam: "
				+ userRegistParam);
		ProcessResult processResult = new ProcessResult();

		// 1.校验参数

		// 2.密码加盐

		ArdLog.debug(logger, "registUser", null, "salt: " + saltStr
				+ "hashIterations: " + hashIterations);
		System.out.println(saltStr);
		System.out.println(hashIterations);

		Md5Hash mh = new Md5Hash(userRegistParam.getPassword(), saltStr,
				hashIterations);
		System.out.println(mh.toString());

		// 3.创建用户,并绑定信息

		ArdUserRole ardUserRole = new ArdUserRole();

		userRegistService.regitsUser(userRegistParam, saltStr, ardUserRole);

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
	@RequestMapping(value = "/app_authentication")
	@ResponseBody
	public ProcessResult doLogin(HttpServletRequest request,
			HttpServletResponse response, Model model) {

		ProcessResult result = new ProcessResult();
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

					result.setRetCode(ProcessResult.SUCCESS);

					return result;
				}
			} catch (IncorrectCredentialsException e) {
				msg = "登录密码错误. Password for account " + token.getPrincipal()
						+ " was incorrect.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (DisabledAccountException e) {
				msg = "帐号已被注销. The account for username "
						+ token.getPrincipal() + " was disabled.";
				model.addAttribute("message", msg);
				System.out.println(msg);
			} catch (UnknownAccountException e) {
				msg = "帐号不存在. There is no user with username of "
						+ token.getPrincipal();
				model.addAttribute("message", msg);
				System.out.println(msg);
			}
			result.setRetCode(ProcessResult.FAILED);

		} catch (Throwable t) {
			result.setRetCode(ProcessResult.FAILED);
			result.setErrorCode(ArdError.EXCEPTION);
			result.setErrorDesc(ArdError.EXCEPTION_MSG);
			t.printStackTrace();
		}

		return result;
	}

	/**
	 * web端登录地址
	 * 
	 * @return
	 */
	@RequestMapping("/web_authentication")
	public ModelAndView webLogin() {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("/admin/mainpage");
		return mv;
	}

	@RequestMapping("/signout_req")
	public String checkOut() {

		SecurityUtils.getSubject().logout();
		
		return "/gate/homepage";
	}

	@RequestMapping("/password_reset")
	public ProcessResult resetPassword() {

		ProcessResult result = new ProcessResult();

		return result;

	}

}
