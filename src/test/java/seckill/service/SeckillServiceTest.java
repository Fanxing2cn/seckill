package seckill.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.entity.Seckill;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-dao.xml","classpath:spring-service.xml"})
public class SeckillServiceTest {

	@Resource
	private SeckillService seckillService;
	
	@Test
	public void testGetSeckillList() {
		List list=seckillService.getSeckillList();
		System.out.println(list);
	}

	@Test
	public void testGetById() {
		Seckill seckill = seckillService.getById(1000L);
		System.out.println(seckill);
		
	}

	@Test
	public void testExportSeckillUrl() {
		long id=1001;
		Exposer exposer= seckillService.exportSeckillUrl(id);
		System.out.println(exposer);
		
		//Exposer [exposed=false, md5=null, seckillId=2001, now=0, start=0, end=0]
		//Exposer [exposed=true, md5=a4f81d6d759979da1f6e10b645bae091, seckillId=1000, now=0, start=0, end=0]
		//Exposer [exposed=false, md5=null, seckillId=1001, now=1531280368112, start=1528214400000, end=1528300800000]


	}

	@Test
	public void testExecuteSeckill() {
		long id=1001;
		String phone="15570768080";
		String md5="4da9706236f3c011c91ae13330b774a5";
		SeckillExecution se= seckillService.executeSeckill(id, phone, md5);
		System.out.println(se);
	}

}
