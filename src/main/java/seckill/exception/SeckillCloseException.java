package seckill.exception;
/**
 * 秒杀关闭异常
 *
 */
public class SeckillCloseException extends RuntimeException {

	public SeckillCloseException(String message, Throwable cause) {
		super(message, cause);
	}

	public SeckillCloseException(String message) {
		super(message);
	}	
}
