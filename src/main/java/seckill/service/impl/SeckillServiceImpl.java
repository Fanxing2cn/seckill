package seckill.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.spi.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import seckill.dao.SeckillDao;
import seckill.dao.SuccessKilledDao;
import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.entity.Seckill;
import seckill.entity.SuccessKilled;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.exception.SeckillException;
import seckill.service.SeckillService;

@Service
public class SeckillServiceImpl implements SeckillService {

	@Resource
	private SeckillDao seckillDao;
	@Resource
	private SuccessKilledDao successKilledDao;
	// 混淆器
	private final String mix = "asdjfaklsj12312^%&^%&123+_)(";

	@Override
	public List<Seckill> getSeckillList() {
		return seckillDao.queryAll(0, 10);
	}

	@Override
	public Seckill getById(long seckillId) {
		return seckillDao.queryById(seckillId);
	}

	@Override
	public Exposer exportSeckillUrl(long seckillId) {
		Seckill seckill = this.getById(seckillId);
		if (seckill == null) {
			// 没有此秒杀商品
			return new Exposer(false, seckillId);
		}
		long startTime = seckill.getStartTime().getTime();
		long endTime = seckill.getEndTime().getTime();
		long now = new Date().getTime(); // 系统时间
		if (now < startTime || now > endTime) {
			return new Exposer(false, seckillId, now, startTime, endTime);
		}

		String md5 = getMD5(seckillId);
		return new Exposer(true, md5, seckillId);
	}

	// 算MD5
	private String getMD5(long seckillId) {
		String base = seckillId + "/" + mix;
		String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
		return md5;
	}

	@Override
	@Transactional
	/**
	 * 注解来控制事务方法
	 */
	public SeckillExecution executeSeckill(long seckillId, String userPhone, String md5)
			throws SeckillException, RepeatKillException, SeckillCloseException {
		if (md5 == null || !md5.equals(getMD5(seckillId))) {
			throw new SeckillException("秒杀数据被串改...error");
		}

		// 秒杀逻辑 减库存 + 记录购买记录
		int n = seckillDao.reduceNumber(seckillId, new Date());
		if (n <= 0) {
			// 没有更新
			throw new SeckillCloseException("秒杀结束了");
		} else {
			// 记录购买记录
			int insertn = successKilledDao.insertSuccessKilled(seckillId, userPhone);
			if (insertn <= 0) {
				throw new RepeatKillException("重复秒杀");
			} else {
				// 秒杀成功
				SuccessKilled sk = successKilledDao.queryByIdPhone(seckillId, userPhone);
				return new SeckillExecution(seckillId, 1, "秒杀成功", sk);
			}

		}

	}

}
