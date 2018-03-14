/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package cn.damei.web.modules;

import java.util.Date;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.damei.common.utils.DateUtils;
import cn.damei.common.web.BaseController;
import cn.damei.entity.modules.StatusDto;
import cn.damei.entity.modules.BizOrderReportSendRule;
import cn.damei.service.modules.BizOrderReportSendRuleService;

/**
 * 返单客服轮训规则业务处理Controller
 * @author Liwancai
 * @version 2017-05-06
 */
@Controller
@RequestMapping(value = "${adminPath}/orderreportsendrule/OrderReportSendRule")
public class BizOrderReportSendRuleController extends BaseController {

	@Autowired
	private BizOrderReportSendRuleService bizOrderReportSendRuleService;
	
	/**
	 * 
	 * @param bizOrderReportSendRule
	 * @param model
	 * @return
	 */
	@RequiresPermissions("orderreportsendrule:bizOrderReportSendRule:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		
		BizOrderReportSendRule bizOrderReportSendRule = bizOrderReportSendRuleService.selectNoExecution();
		model.addAttribute("sendRuleStatus",bizOrderReportSendRule != null ? bizOrderReportSendRule.getStatus() : null) ;
		model.addAttribute("sendRuleId",bizOrderReportSendRule != null && bizOrderReportSendRule.getStatus().equals("0") ? bizOrderReportSendRule.getId() : null) ;
		model.addAttribute("startDateTime", bizOrderReportSendRule != null ?  DateUtils.formatDate( bizOrderReportSendRule.getStartDatetime(),"yyyy-MM-dd") : DateUtils.formatDate( DateUtils.addDate( new Date(),1 ),"yyyy-MM-dd" ) ) ;
		
		//待执行规则客服人员信息
		model.addAttribute("serviceMembers",bizOrderReportSendRuleService.selectServiceMembersForPendingRule());
		
		//规则执行记录
		model.addAttribute("ruleExecuteRuleRecord",bizOrderReportSendRuleService.selectRuleExecuteRecord());
		return "modules/orderreportsendrule/bizOrderReportSendRule";
	}

	/**
	 * 轮循规则保存
	 * @param startDateTime，开始执行时间
	 * @param serviceMembers，客服人员id，以逗号拼接，eg:1,2,3
	 * @return
	 */
	@RequestMapping(value = "save",method=RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(
			@RequestParam(required = false) Integer sendRuleId,
			@RequestParam(required = true) String startDateTime,
			@RequestParam(required = false) String[] rightEmployeeId
			) {
		try{
			int result = this.bizOrderReportSendRuleService.save(sendRuleId, startDateTime, rightEmployeeId);
			if( result > 0 ){
				return StatusDto.buildDataSuccessStatusDto(result); 
			}else{
				return StatusDto.buildDataFailureStatusDto("操作失败!");
			}
		}catch(Exception e){
			e.printStackTrace();
			return StatusDto.buildDataFailureStatusDto("操作失败!");
		}
		
	}
	
	/**
	 * 根据规则id查询对应的客服人员
	 * @param sendRuleId
	 * @return
	 */
	@RequestMapping(value = "getServiceMembers",method=RequestMethod.GET)
	@ResponseBody
	public Object getServiceMembers(@RequestParam(required = true) Integer sendRuleId){
		return StatusDto.buildDataSuccessStatusDto( this.bizOrderReportSendRuleService.selectServiceMembersBySendRuleId(sendRuleId) );
	}
}