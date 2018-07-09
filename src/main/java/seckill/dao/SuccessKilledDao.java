package seckill.dao;

import org.apache.ibatis.annotations.Param;

import seckill.entity.SuccessKilled;

public interface SuccessKilledDao {

	/**
	 * 插入购买明细，可过滤重复，同个商品不能秒多次
	 */
	int insertSuccessKilled(@Param("seckillId") long seckillId,@Param("userPhone") String userPhone);
	
	SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") String userPhone);
}
