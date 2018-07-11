package seckill.exception;
/**
 * 秒杀关闭异常
 *
 */
public class SeckillCloaseException extends RuntimeException {

	public SeckillCloaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloaseException(String message) {
		super(message);
	}	
}
