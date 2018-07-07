package seckill.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

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
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

}
