package seckill.dto;

// 封装JSON 数据
public class SeckillResult<T> {
	private boolean success;
	
	private T data;
	
	private String error;

	//正确
	public SeckillResult(boolean success, T data) {
		this.success = success;
		this.data = data;
	}

	//错误
	public SeckillResult(boolean success, String error) {
		this.success = success;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}
