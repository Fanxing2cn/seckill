package seckill.service;

import java.util.List;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.entity.Seckill;

public interface SeckillService {
	
	//查询所有秒杀记录
	List<Seckill> getSeckillList();
	
	//查询单个秒杀记录
	Seckill getById(long seckillId);
	
	//秒杀开启时输出接口地址，没开启返回系统时间和秒杀开启时间
	//目的不让你猜地址	
	Exposer exportSeckillUrl(long seckillId);
	
	//执行秒杀操作
	SeckillExecution executeSeckill(long seckillId,String userPhone,String md5);

}
