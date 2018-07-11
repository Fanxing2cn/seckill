package seckill.dto;

//暴露秒杀接口
public class Exposer {
	
	private boolean exposed;
	
	private String md5;
	
	private long seckillId;
	
	//系统时间
	private long now;
	
	private long start;
	
	private long end;
	
	public Exposer(boolean exposed,String md5,long seckillId){
		this.exposed =exposed;
		this.md5=md5;
		this.seckillId =seckillId;
	}

	public Exposer(boolean exposed, long now, long start, long end) {
		this.exposed = exposed;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	public Exposer(boolean exposed,long seckillId){
		this.exposed =exposed;
		this.seckillId =seckillId;
	}
	
	
}