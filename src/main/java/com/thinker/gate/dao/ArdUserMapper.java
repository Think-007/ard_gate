package com.thinker.gate.dao;

import com.thinker.gate.domain.ArdUser;

public interface ArdUserMapper {

	/**
	 * 添加用户
	 * 
	 * @param ardUser
	 * @return
	 */
	public int insertArdUser(ArdUser ardUser);

	public int deleteArdUser(ArdUser ardUser);

	public int updateArdUser(ArdUser ardUser);

	public int queryArdUser(ArdUser ardUser);

}
