/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.mobile.Manager;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.ConstantUtils;
import cn.damei.common.web.BaseController;
import cn.damei.common.SessionUtils;
import cn.damei.entity.mobile.Manager.BizBusinessSign;
import cn.damei.service.mobile.Manager.BizBusinessSignService;
import cn.damei.entity.mobile.Manager.Manager;

/**
 * 人员签到Controller
 * @author qww
 * @version 2016-11-03
 */
@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class BizBusinessSignController extends BaseController{

	@Autowired
	private BizBusinessSignService bizBusinessSignService;
	@RequestMapping(value = "saveOrUpdateSign")
	public @ResponseBody String saveOrUpdateSign(BizBusinessSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{
			// 获取项目经理id
			Manager manager = SessionUtils.getManagerSession(request);
			Date date = new Date();
			// 根据orderId、packId、signType、managerId查询项目经理结算任务包时是否已签到
			BizBusinessSign sign = bizBusinessSignService.queryEmployeeIsCheck(manager.getId(), detail.getRelatedBusinessIdInt());
			// 获取经纬度
			/*Double  x =LngAndLatUtils.getLngAndLat(detail.getSignAddress()).get("lng");
			Double  y =LngAndLatUtils.getLngAndLat(detail.getSignAddress()).get("lat");*/
			// 1.如果查询结果为空，表示该项目经理结算任务包时还没有签到，则新增签到数据
			// 2.如果查询结果不为空，表示该项目经理已经签过到了，可以继续签到，更新签到表
			if(sign == null){
				sign = new BizBusinessSign();
				sign.setSignType(ConstantUtils.SIGN_TYPE_MANAGER_CHECK);
				sign.setRelatedBusinessIdInt(detail.getRelatedBusinessIdInt());
				sign.setSignEmployeeId(manager.getId());
				sign.setSignDatetime(new Date());
				sign.setSignAddress(detail.getSignAddress());
				sign.setSignXy(detail.getLon()+","+detail.getLat());
				sign.setSignErrorDistance(detail.getSignErrorDistance());
				sign.setCreateDate(date);
				sign.setUpdateDate(date);
				bizBusinessSignService.insert(sign);
			}else{
				sign.setSignDatetime(date);
				sign.setSignAddress(detail.getSignAddress());
				sign.setSignXy(detail.getLon()+","+detail.getLat());
				sign.setSignErrorDistance(detail.getSignErrorDistance());
				sign.setUpdateDate(date);
				bizBusinessSignService.update(sign);
			}
			double signDistance =detail.getSignErrorDistance();
			// 判断签到距离是否超过1000米，没有超过为合格，超过了为不合格
			if (signDistance - 1000 < 0.000001 && signDistance - 1000 > -0.000001) {
				sign.setIsValid("1");
			} else {
				sign.setIsValid("0");
			}
			// 查出该订单的基装验收时间
			String checkDateTime = bizBusinessSignService.getCheckDatetimeByOrderId(detail.getRelatedBusinessIdInt());
			// 如果验收时间为空，则此时段为基装时段，签到为基装签到
			if (null == checkDateTime) {
				sign.setSignStep("10"); // 基装签到
			} else if (isToday(checkDateTime, "yyyy-MM-dd mm:HH:ss")) { // 如果签到时间和基装验收时间是同一天，则按基装签到
				sign.setSignStep("10"); // 基装签到
			} else {
				sign.setSignStep("20"); // 主材签到
			}
			String orderId = bizBusinessSignService.getOrderIdBypPackId(detail.getRelatedBusinessIdInt());
			sign.setOrderId(orderId);
			Map<String, String> map = new HashMap<String,String>();
			map.put("orderId", orderId);
			map.put("managerId", manager.getId().toString());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			map.put("signDateTime", sf.format(date));
			//查出这个date所在时间当天在biz_pm_attend_day_order表中是否已经有数据，如果没有不论合格不合格直接插入
			String isValit = bizBusinessSignService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
			if(null==isValit){
				bizBusinessSignService.insertDayOrderByBizBusinessSign(sign);
			}else if(isValit.equals("0")){  //如果不合格，则需要更新数据
				if(sign.getIsValid().equals("1")){ //签到数据为合格时，更新biz_pm_attend_day_order数据
					bizBusinessSignService.updateDayOrderByBizBusinessSign(sign);
				}
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}
	// 判断日期是否为今天
		public static boolean isToday(String str, String formatStr)
				throws Exception {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			Date date = format.parse(str);
			/*try {
				date = format.parse(str);
			} catch (ParseException e) {
				// logger.error("解析日期错误", e);
			}*/
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			int year1 = c1.get(Calendar.YEAR);
			int month1 = c1.get(Calendar.MONTH) + 1;
			int day1 = c1.get(Calendar.DAY_OF_MONTH);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(new Date());
			int year2 = c2.get(Calendar.YEAR);
			int month2 = c2.get(Calendar.MONTH) + 1;
			int day2 = c2.get(Calendar.DAY_OF_MONTH);
			if (year1 == year2 && month1 == month2 && day1 == day2) {
				return true;
			}
			return false;
		}

}