/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.damei.common.persistence.Page;
import cn.damei.common.web.BaseController;
import cn.damei.common.utils.StringUtils;
import cn.damei.entity.modules.BizPmAttendDayOrder;
import cn.damei.entity.modules.ManagerSign;
import cn.damei.service.modules.ManagerSignService;
import cn.damei.common.utils.UserUtils;

/**
 * 项目经理签到查询Controller
 * @author 梅浩
 * @version 2016-09-26
 */
@Controller
@RequestMapping(value = "${adminPath}/managersign/managerSign")
public class ManagerSignController extends BaseController {

	@Autowired
	private ManagerSignService managerSignService;
	
	@ModelAttribute
	public ManagerSign get(@RequestParam(required=false) Integer id) {
		ManagerSign entity = null;
		if (StringUtils.isNotBlank(String.valueOf(id))){
			entity = managerSignService.get(id);
		}
		if (entity == null){
			entity = new ManagerSign();
		}
		return entity;
	}
	
	@RequiresPermissions("managersign:managerSign:view")
	@RequestMapping(value = {"list", ""})
	public String list(ManagerSign managerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
	
		return "modules/managersign/managerSignList";
	}
	@RequiresPermissions("managersign:managerSign:view")
	@RequestMapping(value = {"list1"})
	public String list1(ManagerSign managerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(null!=managerSign.getSignDate1()){
			
			
			model.addAttribute("signDate1", managerSign.getSignDate1());
		}
		if(null!=managerSign.getSignDate2()){
			
			
			model.addAttribute("signDate2", managerSign.getSignDate2());
		}
		int x = 0;
		//不是管理员就不能查全部门店
		if(!UserUtils.getUser().getOffice().getId().equals("1")){
			//安心查自己门店吧
			managerSign.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		Page<ManagerSign> page = managerSignService.findPage(new Page<ManagerSign>(request, response), managerSign); 
		if(x>0){
			managerSign.setStoreId(null);
			
		}
		model.addAttribute("page", page);
		return "modules/managersign/managerSignList";
	}
	
	@RequiresPermissions("managersign:managerSign:view")
	@RequestMapping(value = {"list2"})
	public String list2(ManagerSign managerSign, HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(null!=managerSign.getSignDate1()){
			
			
			model.addAttribute("signDate1", managerSign.getSignDate1());
		}
		if(null!=managerSign.getSignDate2()){
			
			
			model.addAttribute("signDate2", managerSign.getSignDate2());
		}
		int x = 0;
		//不是管理员就不能查全部门店
		if(!UserUtils.getUser().getOffice().getId().equals("1")){
			//安心查自己门店吧
			managerSign.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		Page<ManagerSign> page = managerSignService.findPage(new Page<ManagerSign>(request, response), managerSign); 
		if(x>0){
			managerSign.setStoreId(null);
			
		}
		model.addAttribute("page", page);
		return "modules/managersign/managerSignList";
	}

	@RequiresPermissions("managersign:managerSign:view")
	@RequestMapping(value = {"getDayOrderlist"})
	public String getDayOrderlist(ManagerSign managerSign,HttpServletRequest request, HttpServletResponse response, Model model){
		BizPmAttendDayOrder bizPmAttendDayOrder = new BizPmAttendDayOrder();
		if(null!=managerSign.getSignDate1()){
			model.addAttribute("signDate1", managerSign.getSignDate1());
		}
		if(null!=managerSign.getSignDate2()){
			model.addAttribute("signDate2", managerSign.getSignDate2());
		}
		int x = 0;
		//不是管理员就不能查全部门店
		if(!UserUtils.getUser().getOffice().getId().equals("1")){
			//安心查自己门店吧
			managerSign.setStoreId(Integer.parseInt(UserUtils.getUser().getStoreId()));
			x++;
		}
		bizPmAttendDayOrder.setSignDate1(managerSign.getSignDate1());
		bizPmAttendDayOrder.setSignDate2(managerSign.getSignDate2());
		bizPmAttendDayOrder.setStoreId(managerSign.getStoreId());
		bizPmAttendDayOrder.setOrderProjectMode(managerSign.getOrderProjectMode());
		bizPmAttendDayOrder.setEngineDepartId(managerSign.getEngineDepartId());
		bizPmAttendDayOrder.setOrderNumber(managerSign.getOrderNumber());
		bizPmAttendDayOrder.setCustomerName(managerSign.getCustomerName());
		bizPmAttendDayOrder.setCustomerAddress(managerSign.getCustomerInfo());
		bizPmAttendDayOrder.setIsValid(managerSign.getIsValid());
		bizPmAttendDayOrder.setManagerName(managerSign.getManagerName());
		bizPmAttendDayOrder.setConditionDistance1(managerSign.getConditionDistance1());
		bizPmAttendDayOrder.setConditionDistance2(managerSign.getConditionDistance2());
		Page<BizPmAttendDayOrder> page = managerSignService.findPage1(new Page<BizPmAttendDayOrder>(request, response), bizPmAttendDayOrder); 
		if(x>0){
			managerSign.setStoreId(null);
		}
		
		
		model.addAttribute("managerSign",managerSign);
		
		model.addAttribute("page", page);
		return "modules/managersign/bizPmAttendDayOrderList";
	}
	/**
	* @Description: 校验是否可以修改考勤数据
	* @Author zhangkangjian
	* @param
	* @return
	* @Date 2017/11/23 10:55
	*/
	@RequestMapping(value = "updateIsEnabledById")
	public @ResponseBody String updateIsValidById(String id,String isValid,String signId, RedirectAttributes redirectAttributes) {
		//判断改员工是否生成月度考勤单 排除作废的考勤单
		Boolean boo = managerSignService.isApplyAttendMonth(id);
		if(boo){
			return "fail";
		}else{
			managerSignService.updateIsValiddById(id,isValid,signId);
			return "success";
		}

	}
}