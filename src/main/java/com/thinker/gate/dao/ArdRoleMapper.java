package com.thinker.gate.dao;

import com.thinker.gate.domain.ArdRole;

public interface ArdRoleMapper {

	/**
	 * 添加角色
	 * 
	 * @param ardRole
	 * @return
	 */
	public int insertArdRole(ArdRole ardRole);

	public int deleteArdRole(ArdRole ardRole);

	public int updateArdRole(ArdRole ardRole);

	public int queryArdRole(ArdRole ardRole);

}
