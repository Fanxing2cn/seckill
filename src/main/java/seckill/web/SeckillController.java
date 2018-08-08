package seckill.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import seckill.dto.Exposer;
import seckill.dto.SeckillExecution;
import seckill.dto.SeckillResult;
import seckill.entity.Seckill;
import seckill.exception.RepeatKillException;
import seckill.exception.SeckillCloseException;
import seckill.exception.SeckillException;
import seckill.service.SeckillService;

@Controller
public class SeckillController {
	
	@Autowired
	private SeckillService seckillService;

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model){
		//获取列表
		List<Seckill> list=seckillService.getSeckillList();
		
		model.addAttribute("list",list);
		// list.jsp + model =ModelAndView
		return "list";    //   =  /jsp/list.jsp
	}
	
	@RequestMapping(value="/detail/{seckillId}",method=RequestMethod.GET)
	public String detail(@PathVariable("seckillId") Long seckillId,Model model){
		if(seckillId == null){
			return "redirect:/seckill/list";
		}
		
		Seckill seckill=  seckillService.getById(seckillId);
		if (seckill == null) {
			return "forward:/seckill/list";
		}
		model.addAttribute("seckill",seckill);
		return "detail";  //   =  /WEB-INF/jsp/detail.jsp
	}
	
	//ajax json 得到秒杀地址
	@RequestMapping(value="/exposer/{seckillId}",
					method=RequestMethod.POST,
					produces={"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
		SeckillResult<Exposer> result=null;
		
		try {
			Exposer exposer=  seckillService.exportSeckillUrl(seckillId);
			result = new SeckillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			e.printStackTrace();
			result = new SeckillResult<Exposer>(false, e.getMessage());
		} 		
		return result;
	}
	
	@RequestMapping(value="/execution/{seckillId}/{md5}",
			method=RequestMethod.POST,
			produces={"application/json;charset=utf-8"})
	@ResponseBody
	public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
													@PathVariable("md5") String md5,
													@CookieValue(value="killPhone",required=false) String phone){
		if (phone == null) {
			return new SeckillResult<SeckillExecution>(false,"未注册");
		}
		
		SeckillResult<SeckillExecution> result=null;
		try {
			SeckillExecution execution = seckillService.executeSeckill(seckillId, phone, md5);		
			result =new  SeckillResult<SeckillExecution>(true,execution);
		} catch (RepeatKillException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, -1,"重复秒杀");
			result =new SeckillResult<SeckillExecution>(false,execution);
		}catch (SeckillCloseException e) {
			SeckillExecution execution = new SeckillExecution(seckillId, 0,"秒杀已经关闭");
			result =new SeckillResult<SeckillExecution>(false,execution);
		}catch (Exception e) {
			SeckillExecution execution = new SeckillExecution(seckillId, -2,"内部错误");
			result =new SeckillResult<SeckillExecution>(false,execution);
		}
		return result;
	}
	
	
	//查系统时间
	@RequestMapping(value="/time/now",method=RequestMethod.GET)
	@ResponseBody
	public SeckillResult<Long> time(){
		Date now =new Date();
		return new SeckillResult(true,now.getTime());
	}
}
