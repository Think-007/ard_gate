package com.thinker.gate;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinker.gate.dao.UserRegistMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ArdGateApplicationTests {
	@Autowired
	private UserRegistMapper userRegistMapper;

	@Test
	public void contextLoads() {

		int a = userRegistMapper.test();
		Assert.assertEquals(1, a);

	}

}
