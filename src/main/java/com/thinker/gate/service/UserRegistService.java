package com.thinker.gate.service;

import java.util.Map;

import com.thinker.gate.domain.ArdRole;
import com.thinker.gate.domain.ArdUserRole;
import com.thinker.gate.domain.UserRegistParam;

public interface UserRegistService {

	/**
	 * 用户注册业务
	 * 
	 * @param userRegistParam
	 * @return
	 */
	public Map regitsUser(UserRegistParam userRegistParam, String salt,
			ArdUserRole ardUserRole);

}
