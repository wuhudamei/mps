
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


@Controller
@RequestMapping(value = "${adminPath}/app/manager")
public class BizBusinessSignController extends BaseController{

	@Autowired
	private BizBusinessSignService bizBusinessSignService;
	@RequestMapping(value = "saveOrUpdateSign")
	public @ResponseBody String saveOrUpdateSign(BizBusinessSign detail, Model model, HttpServletRequest request) {
		String flag = "error";
		try{

			Manager manager = SessionUtils.getManagerSession(request);
			Date date = new Date();

			BizBusinessSign sign = bizBusinessSignService.queryEmployeeIsCheck(manager.getId(), detail.getRelatedBusinessIdInt());




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

			if (signDistance - 1000 < 0.000001 && signDistance - 1000 > -0.000001) {
				sign.setIsValid("1");
			} else {
				sign.setIsValid("0");
			}

			String checkDateTime = bizBusinessSignService.getCheckDatetimeByOrderId(detail.getRelatedBusinessIdInt());

			if (null == checkDateTime) {
				sign.setSignStep("10");
			} else if (isToday(checkDateTime, "yyyy-MM-dd mm:HH:ss")) {
				sign.setSignStep("10");
			} else {
				sign.setSignStep("20");
			}
			String orderId = bizBusinessSignService.getOrderIdBypPackId(detail.getRelatedBusinessIdInt());
			sign.setOrderId(orderId);
			Map<String, String> map = new HashMap<String,String>();
			map.put("orderId", orderId);
			map.put("managerId", manager.getId().toString());
			SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			map.put("signDateTime", sf.format(date));

			String isValit = bizBusinessSignService.getIsValidByOrderIdAndManagerIdAndSignDate(map);
			if(null==isValit){
				bizBusinessSignService.insertDayOrderByBizBusinessSign(sign);
			}else if(isValit.equals("0")){
				if(sign.getIsValid().equals("1")){
					bizBusinessSignService.updateDayOrderByBizBusinessSign(sign);
				}
			}
			flag = "success";
		}catch(Exception e){
			flag = "error";
		}
		
		return flag;
	}

		public static boolean isToday(String str, String formatStr)
				throws Exception {
			SimpleDateFormat format = new SimpleDateFormat(formatStr);
			Date date = format.parse(str);

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