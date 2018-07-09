package seckill.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seckill.entity.Seckill;

/**
 * 配置spring 跟 junit 整合， junit启动加载springIOC
 * 告诉junit spring配置文件在哪 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml"})
public class SeckillDaoTest {
	
	//注入DAO 实现类
	@Resource
	private SeckillDao seckillDao;
	
	@Test
	public void testReduceNumber() {
		int ok= seckillDao.reduceNumber(1000L, new Date());
		System.out.println(ok);
	}

	@Test
	public void testQueryById() {
		long id=1000;
		Seckill obj =seckillDao.queryById(id);
		System.out.println(obj);
	}

	@Test
	public void testQueryAll() {
	   List<Seckill> list=seckillDao.queryAll(0, 10);
	   for(Seckill obj : list){
		   System.out.println(obj);
	   }
	}

}
