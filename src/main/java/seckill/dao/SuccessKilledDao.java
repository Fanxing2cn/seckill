package seckill.dao;

import seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复，同个商品不能秒多次
	 */
	int insertSuccessKilled(long seckillId,String userPhone);
	
	SuccessKilled queryByIdWithSeckill(long seckillId);
}
