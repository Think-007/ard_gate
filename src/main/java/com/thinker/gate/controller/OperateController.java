package com.thinker.gate.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.think.creator.domain.ProcessResult;

@Controller
@RequestMapping("/operation")
public class OperateController {

	@RequestMapping("/article_publish")
	@ResponseBody
	public ProcessResult publisArticle() {

		ProcessResult result = new ProcessResult();

		return result;
	}
}
