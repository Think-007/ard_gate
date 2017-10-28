package com.thinker.gate.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinker.gate.dao.ArdRoleMapper;
import com.thinker.gate.dao.ArdUserMapper;
import com.thinker.gate.domain.ArdRole;
import com.thinker.gate.domain.ArdUser;
import com.thinker.gate.domain.UserRegistParam;
import com.thinker.gate.service.UserRegistService;

@Service
public class UserRegistServiceImpl implements UserRegistService {

	@Resource
	private ArdUserMapper ardUserMapper;

	@Resource
	private ArdRoleMapper ardRoleMapper;

	@Override
	@Transactional
	public Map regitsAppUser(UserRegistParam userRegistParam, String salt,
			ArdRole ardRole) {
		// TODO Auto-generated method stub
		ArdUser ardUser = new ArdUser();

		ardUserMapper.insertArdUser(ardUser);
		ardRoleMapper.insertArdRole(ardRole);

		return null;
	}

}
