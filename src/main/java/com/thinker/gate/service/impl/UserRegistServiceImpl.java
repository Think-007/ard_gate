package com.thinker.gate.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinker.gate.dao.ArdRoleMapper;
import com.thinker.gate.dao.ArdUserMapper;
import com.thinker.gate.dao.ArdUserRoleMapper;
import com.thinker.gate.domain.ArdRole;
import com.thinker.gate.domain.ArdUser;
import com.thinker.gate.domain.ArdUserRole;
import com.thinker.gate.domain.UserRegistParam;
import com.thinker.gate.service.UserRegistService;

@Service
public class UserRegistServiceImpl implements UserRegistService {

	@Resource
	private ArdUserMapper ardUserMapper;

	@Resource
	private ArdUserRoleMapper ardUserRoleMapper;

	@Override
	@Transactional
	public Map<String, Object> regitsUser(UserRegistParam userRegistParam, String salt, ArdUserRole ardUserRole)
			throws Exception {
		// TODO Auto-generated method stub
		ArdUser ardUser = new ArdUser();
		ardUser.setUserId(4);
		ardUser.setUserName(userRegistParam.getUserName());
		ardUser.setPassword(userRegistParam.getPassword());
		ardUser.setSalt(salt);

		ardUserRole.setUserId(ardUser.getUserId());

		ardUserMapper.insertArdUser(ardUser);
		ardUserRoleMapper.insertAruUserRole(ardUserRole);
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("userid", ardUser.getUserId());
		userInfo.put("username", ardUser.getUserName());
		userInfo.put("role", ardUserRole.getRoleId());
		return userInfo;
	}

}
