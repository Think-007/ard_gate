package com.thinker.gate.service.impl;

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
	public Map regitsUser(UserRegistParam userRegistParam, String salt,
			ArdUserRole ardUserRole) {
		// TODO Auto-generated method stub
		ArdUser ardUser = new ArdUser();

		ardUserMapper.insertArdUser(ardUser);

		return null;
	}

}
