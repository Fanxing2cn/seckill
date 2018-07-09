package seckill.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seckill.entity.SuccessKilled;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SuccessKilledDaoTest {
	
	@Resource
	private SuccessKilledDao successKilledDao;

	@Test
	public void testInsertSuccessKilled() {
		 long id=1001L;
		 String phone="13588779900";
		 int ok = successKilledDao.insertSuccessKilled(id, phone);
		 System.out.println(ok);
		 
	}

	@Test
	public void testQueryByIdWithSeckill() {
		long id=1000L;
		String phone="13588779900";
		SuccessKilled sk= successKilledDao.queryByIdWithSeckill(id, phone);
		System.out.println(sk);
		System.out.println(sk.getSeckill());
	}

}
