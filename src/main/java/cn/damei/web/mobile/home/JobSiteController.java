package cn.damei.web.mobile.home;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.PicRootName;
import cn.damei.entity.mobile.home.CustomerBroadCastEntity;
import cn.damei.entity.mobile.home.CustomerOrderVo;
import cn.damei.service.mobile.home.JobSiteService;
import cn.damei.dao.mobile.home.HomeReportDao;
import cn.damei.entity.mobile.home.ViewLog;

@Controller
@RequestMapping(value="${adminPath}/app/home/jobsite")
public class JobSiteController {

	
	
	@Autowired
	private JobSiteService service;
	@Autowired
	private HomeReportDao logDao;
	
	private Logger logger = LoggerFactory.getLogger(JobSiteController.class);
	
	@RequestMapping(value="index.php")
	public String index(HttpServletRequest request,Model model,String orderId) throws IOException{
		CustomerOrderVo orderVo = new CustomerOrderVo();
			orderVo.setCustomerPhone((String)request.getSession().getAttribute("customerPhone"));
			
			
		List<CustomerOrderVo> list = service.findBroadCastWithOrderLimitByCustomerPhone(orderVo);
		
		if(null!=list&&list.size()>0){
			//如果有播报单
			CustomerOrderVo  Vo = null;
			Integer x =0;
			for (CustomerOrderVo customerOrderVo : list) {
				//遍历
				if(null!=customerOrderVo.getBroadcastList()&&customerOrderVo.getBroadcastList().size()>0){
					for (CustomerBroadCastEntity broadcastEntity : customerOrderVo.getBroadcastList()) {
					
						//根据播报单id 查询是否已读
						ViewLog  log = 	new ViewLog();
						log.setBusinessIdInt(broadcastEntity.getBroadcastId());
						log.setBusinessType("4");
						log.setBusinessViewerOnlyMark(orderVo.getCustomerPhone());
						Integer integer = logDao.findView(log);
						if(null==integer||integer<1){
							//未读
							x++;
							broadcastEntity.setReadStatus("1");
							customerOrderVo.setCount(x);
							Vo = customerOrderVo;
						}else{
							broadcastEntity.setReadStatus("0");
						}
					}
					
				}
			
				x=0;
				if(orderId==null||"".equals(orderId)||!JobSiteController.isNum(orderId)){
					
					model.addAttribute("order",Vo==null?customerOrderVo:Vo);
					if((Vo==null?customerOrderVo.getBroadcastList():Vo.getBroadcastList()).size()>0){
						model.addAttribute("broadcast",Vo==null?customerOrderVo.getBroadcastList():Vo.getBroadcastList());
						// 
					}else{
						model.addAttribute("none", "1");
					}
				}else{
					//如果是根据订单查询, 那就以订单为主
					if(String.valueOf(customerOrderVo.getOrderId()).equals(orderId)){
						model.addAttribute("order",customerOrderVo);
						
						if(customerOrderVo.getBroadcastList().size()>0){
					
							model.addAttribute("broadcast",customerOrderVo.getBroadcastList());	
						}else{
							model.addAttribute("none", "1");
							
						}
					}
					
				}
				
			}
			

			
			model.addAttribute("list", list);
			model.addAttribute("picPrefix", PicRootName.picPrefixName());
			return "mobile/modules/home/cusindex/jobsite/constructionSite";
			
		}else{
		//没有订单
			return "mobile/modules/home/cusindex/jobsite/none";
			
		}
			
	}
	public static boolean isNum(String str){
		return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
	}
	
	@RequestMapping(value="save_log.php")
	public  @ResponseBody String saveLog(HttpServletRequest request,Model model,String broadcastId){
		
		//进入施工现场, 插入log日志 表明已看过
				//查询log日志, 是否看过
			ViewLog  log = 	new ViewLog();
			log.setBusinessType("4");
			Date date = new Date();
			log.setBusinessViewDatetime(date);
			log.setBusinessViewDatetime(date);
			log.setBusinessViewerOnlyMark((String)request.getSession().getAttribute("customerPhone"));
			log.setCreateDate(date);
			log.setUpdateDate(date);
			log.setDelFlag("0");
			log.setBusinessIdInt(Integer.parseInt(JobSiteController.isNum(broadcastId)?broadcastId:"0"));
			if(log.getBusinessIdInt() == 0){
				//参数不合法
				logger.warn("客户查看播报图片时: 插入log 参数不合法   int类型是必须的 :broadcastId:  "+broadcastId);
				return "0";
			}
			logDao.insertView(log);
			
			
			return "1";
		
	}

				
	
}
