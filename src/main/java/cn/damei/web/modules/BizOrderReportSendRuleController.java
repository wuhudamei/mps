
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


@Controller
@RequestMapping(value = "${adminPath}/orderreportsendrule/OrderReportSendRule")
public class BizOrderReportSendRuleController extends BaseController {

	@Autowired
	private BizOrderReportSendRuleService bizOrderReportSendRuleService;
	

	@RequiresPermissions("orderreportsendrule:bizOrderReportSendRule:edit")
	@RequestMapping(value = "form")
	public String form(Model model) {
		
		BizOrderReportSendRule bizOrderReportSendRule = bizOrderReportSendRuleService.selectNoExecution();
		model.addAttribute("sendRuleStatus",bizOrderReportSendRule != null ? bizOrderReportSendRule.getStatus() : null) ;
		model.addAttribute("sendRuleId",bizOrderReportSendRule != null && bizOrderReportSendRule.getStatus().equals("0") ? bizOrderReportSendRule.getId() : null) ;
		model.addAttribute("startDateTime", bizOrderReportSendRule != null ?  DateUtils.formatDate( bizOrderReportSendRule.getStartDatetime(),"yyyy-MM-dd") : DateUtils.formatDate( DateUtils.addDate( new Date(),1 ),"yyyy-MM-dd" ) ) ;
		

		model.addAttribute("serviceMembers",bizOrderReportSendRuleService.selectServiceMembersForPendingRule());
		

		model.addAttribute("ruleExecuteRuleRecord",bizOrderReportSendRuleService.selectRuleExecuteRecord());
		return "modules/orderreportsendrule/bizOrderReportSendRule";
	}


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
	

	@RequestMapping(value = "getServiceMembers",method=RequestMethod.GET)
	@ResponseBody
	public Object getServiceMembers(@RequestParam(required = true) Integer sendRuleId){
		return StatusDto.buildDataSuccessStatusDto( this.bizOrderReportSendRuleService.selectServiceMembersBySendRuleId(sendRuleId) );
	}
}