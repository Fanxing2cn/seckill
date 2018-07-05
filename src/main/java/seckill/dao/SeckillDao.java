package seckill.dao;

import java.util.Date;
import java.util.List;

import seckill.entity.Seckill;

public interface SeckillDao {
	/**
	 * 减去库存	 
	 */
	int reduceNumber(long seckillId,Date killTime);
	
	/**
	 * 根据ID 查秒杀产品
	 */
	Seckill queryById(long seckillId);
	
	/**
	 * 根据偏移量查询商品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(int offset,int limit);

}
